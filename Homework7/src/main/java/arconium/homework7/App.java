package arconium.homework7;


import javax.persistence.*;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class App {

    static EntityManagerFactory emf;

    static EntityManager em;

    public static void main( String[] args ) {
        Scanner sc = new Scanner(System.in);
        try {
            emf = Persistence.createEntityManagerFactory("Homework7");
            em = emf.createEntityManager();
            try {
                while (true){
                    System.out.println("1: create client");
                    System.out.println("2: create goods");
                    System.out.println("3: create order");
                    System.out.println("4: delete client");
                    System.out.println("5: delete goods");
                    System.out.println("6: view client order");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            createClient(sc);
                            break;
                        case "2":
                            createGoods(sc);
                            break;
                        case "3":
                            createOrder(sc);
                            break;
                        case "4":
                            deleteClient(sc);
                            break;
                        case "5":
                            deleteGoods(sc);
                            break;
                        case "6":
                            viewClientOrder(sc);
                            break;
                        default:
                            return;
                    }
                }

            } finally {
                sc.close();
                em.close();
                emf.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    private static void createClient(Scanner sc){
        System.out.println("Enter client name");
        String name = sc.nextLine();
        Client c = new Client(name);

        performTransaction(() -> {
            em.persist(c);
            return null;
        });
    }

    private static void createGoods(Scanner sc){
        System.out.println("Enter goods name");
        String name = sc.nextLine();
        Goods g = new Goods(name);

        performTransaction(() -> {
           em.persist(g);
           return null;
        });
    }

    private static void createOrder(Scanner sc){
        System.out.println("Enter client name");
        String name = sc.nextLine();
        Client c;
        try{
            TypedQuery<Client> cQuery = em.createQuery(
                    "select x from Client x WHERE x.name = :name" , Client.class);
            cQuery.setParameter("name", name);
            c = cQuery.getSingleResult();
        }catch (NoResultException ex) {
            System.out.println("Client not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        Order o = new Order(c);

        System.out.println("");

        TypedQuery<Goods> gQuery = em.createQuery(
                "select x from Goods x", Goods.class);
        List<Goods> goodsList = gQuery.getResultList();

        while (true){
            for(Goods g : goodsList){
                System.out.println(g);
            }
            System.out.println("Choose goods or enter 0 to stop");
            name = sc.nextLine();
            if (name.equals("0")){
                break;
            }
            for(Goods g : goodsList){
                if(g.getName().equals(name)){
                    o.addGoods(g);
                    break;
                }
            }
        }


        Client finalC = c;
        performTransaction(() -> {
            em.persist(o);
            finalC.addOrder(o);
            return null;
        });

    }

    private static void deleteClient(Scanner sc){
        System.out.println("Enter client name");
        String name = sc.nextLine();
        Client c;
        try{
            TypedQuery<Client> cQuery = em.createQuery(
                    "select x from Client x WHERE x.name = :name" , Client.class);
            cQuery.setParameter("name", name);
            c = cQuery.getSingleResult();
        }catch (NoResultException ex) {
            System.out.println("Client not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        performTransaction(() -> {
            Client client = em.getReference(Client.class, c.getId());
            em.remove(client);
            return null;
        });
    }

    private static void deleteGoods(Scanner sc){
        System.out.println("Enter goods name");
        String name = sc.nextLine();
        Goods g;
        try{
            TypedQuery<Goods> gQuery = em.createQuery(
                    "select x from Goods x WHERE x.name = :name" , Goods.class);
            gQuery.setParameter("name", name);
            g = gQuery.getSingleResult();
        }catch (NoResultException ex) {
            System.out.println("Client not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        performTransaction(() -> {
            Goods goods = em.getReference(Goods.class, g.getId());
            em.remove(goods);
            return null;
        });
    }

    private static void viewClientOrder(Scanner sc){
        System.out.print("Enter clients name: ");
        String name = sc.nextLine();
        TypedQuery<Client> typedQuery = em.createQuery("select c from Client c where c.name = :name", Client.class);
        typedQuery.setParameter("name", name);
        Client c = typedQuery.getSingleResult();
        List<Order> list = c.getOrders();
        for (Order o:list) {
            System.out.println(o.getGoodses());
        }
    }

    private static <T> T performTransaction(Callable<T> action) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            T result = action.call();
            transaction.commit();

            return result;
        } catch (Exception ex) {
            if (transaction.isActive())
                transaction.rollback();

            throw new RuntimeException(ex);
        }
    }


}
