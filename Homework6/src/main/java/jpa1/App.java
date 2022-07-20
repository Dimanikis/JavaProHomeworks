package jpa1;

import javax.persistence.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // create connection
            emf = Persistence.createEntityManagerFactory("JPATest");
            em = emf.createEntityManager();
            try {
                while (true) {
                    System.out.println("1: add apart");
                    System.out.println("2: add random apart");
                    System.out.println("3: delete apart");
                    System.out.println("4: change apart");
                    System.out.println("5: view apart");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addApart(sc);
                            break;
                        case "2":
                            insertRandomApart(sc);
                            break;
                        case "3":
                            deleteApart(sc);
                            break;
                        case "4":
                            changeApart(sc);
                            break;
                        case "5":
                            viewApart();
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

    private static void addApart(Scanner sc) {
        System.out.print("Enter apart region: ");
        String region = sc.nextLine();
        System.out.print("Enter apart address: ");
        String address = sc.nextLine();
        System.out.print("Enter apart area: ");
        String sArea = sc.nextLine();
        int area = Integer.parseInt(sArea);
        System.out.print("Enter apart number of rooms: ");
        String sNor = sc.nextLine();
        int nor = Integer.parseInt(sNor);
        System.out.print("Enter apart price: ");
        String sPrice = sc.nextLine();
        int price = Integer.parseInt(sPrice);

        em.getTransaction().begin();
        try {
            Apartments a = new Apartments(region,address,area,nor,price);
            em.persist(a);
            em.getTransaction().commit();

            System.out.println(a.getId());
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void deleteApart(Scanner sc) {
        System.out.print("Enter apart id: ");
        String sId = sc.nextLine();
        long id = Long.parseLong(sId);

        Apartments a = em.getReference(Apartments.class, id);
        if (a == null) {
            System.out.println("Apart not found!");
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(a);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void changeApart(Scanner sc) {
        System.out.print("Enter apart id: ");
        String sId = sc.nextLine();
        long id = Long.parseLong(sId);

        System.out.print("Enter new apart region: ");
        String region = sc.nextLine();
        System.out.print("Enter new apart address: ");
        String address = sc.nextLine();
        System.out.print("Enter new apart area: ");
        String sArea = sc.nextLine();
        int area = Integer.parseInt(sArea);
        System.out.print("Enter new apart number of rooms: ");
        String sNor = sc.nextLine();
        int nor = Integer.parseInt(sNor);
        System.out.print("Enter new apart price: ");
        String sPrice = sc.nextLine();
        int price = Integer.parseInt(sPrice);

        Apartments a = null;
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Apartments x WHERE x.id = :id", Apartments.class);
            query.setParameter("id", id);
            a = (Apartments) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Apart not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        ///........

        em.getTransaction().begin();
        try {
            a.setRegion(region);
            a.setAddress(address);
            a.setArea(area);
            a.setNor(nor);
            a.setPrice(price);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void insertRandomApart(Scanner sc) {
        System.out.print("Enter apart count: ");
        String aCount = sc.nextLine();
        int count = Integer.parseInt(aCount);

        em.getTransaction().begin();
        try {
            for (int i = 0; i < count; i++) {
                Apartments a = new Apartments(randomRegion(), randomAddress(),RND.nextInt(100) + 1,RND.nextInt(5) + 1,RND.nextInt(1000) + 1);
                em.persist(a);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void viewApart() {
        Query query = em.createQuery(
                "SELECT a FROM Apartments a", Apartments.class);
        List<Apartments> list = (List<Apartments>) query.getResultList();

        for (Apartments a : list)
            System.out.println(a);
    }

    static final String[] Region = {"Голосiївський", "Дарницький", "Деснянський", "Днiпровський", "Оболонський","Печерський","Подiльський","Святошинський","Солом'янський","Шевченківський"};

    static final Random RND = new Random();

    static String randomRegion() {
        return Region[RND.nextInt(Region.length)];
    }
    static String randomAddress(){
        return ((RND.nextInt(100) + 1) + "/" + (RND.nextInt(100) + 1));
    }
}


