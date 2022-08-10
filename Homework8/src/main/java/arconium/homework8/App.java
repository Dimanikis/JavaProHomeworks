package arconium.homework8;


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
            emf = Persistence.createEntityManagerFactory("Homework8");
            em = emf.createEntityManager();
            Test.addTest(em);

            try {
                while (true){
                    System.out.println("1: create user");
                    System.out.println("2: create account");
                    System.out.println("3: refill");
                    System.out.println("4: transaction from one account to another");
                    System.out.println("5: Total funds in hryvnia");
                    System.out.println("6: view user list");
                    System.out.println("7: view account list");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            createUser(sc);
                            break;
                        case "2":
                            createAccount(sc);
                            break;
                        case "3":
                            refill(sc);
                            break;
                        case "4":
                            transaction(sc);
                            break;
                        case "5":
                            total(sc);
                            break;
                        case "6":
                            viewUserList();
                            break;
                        case "7":
                            viewAccountList(sc);
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


    private static void createUser(Scanner sc){
        System.out.println("Enter user name");
        String name = sc.nextLine();
        User user = new User(name);

        performTransaction(() -> {
            em.persist(user);
            return null;
        });
    }

    private static void createAccount(Scanner sc){
        User user = Search.userSearch(sc, em);
        System.out.println("Enter account number");
        String aNumber = sc.nextLine();
        System.out.println("Enter account type(USD,EUR,UAH)");
        CurrencyType ct = CurrencyType.valueOf(sc.nextLine());
        Account account = new Account(aNumber,ct);

        performTransaction(() -> {
            em.persist(account);
            user.addAccount(account);
            return null;
        });
    }

    private static void refill(Scanner sc){
        Account account = Search.accountSearch(sc, em);
        System.out.println("Enter your currency(USD,EUR,UAH)");
        CurrencyType ct = CurrencyType.valueOf(sc.nextLine());
        System.out.println("Enter value");
        double value = Double.parseDouble(sc.nextLine());
        ExchangeRates exchangeRates = Converter.convert(em,ct,account.getCurrencyType());


        performTransaction(() -> {
            account.setBalance(account.getBalance() + value * exchangeRates.getRates());
            em.persist(account);
            return null;
        });
    }

    private static void transaction(Scanner sc){
        System.out.println("choose your account");
        Account from = Search.accountSearch(sc, em);
        System.out.println("choose target account");
        Account to = Search.accountSearch(sc, em);
        System.out.println("Enter amount");
        double amount = Double.parseDouble(sc.nextLine());
        ExchangeRates exchangeRates = Converter.convert(em,from.getCurrencyType(),to.getCurrencyType());
        Transaction transaction = new Transaction(from,to,amount);

        performTransaction(() -> {
            transaction.Transfer(exchangeRates);
            em.persist(transaction);
            from.addTransactionFrom(transaction);
            to.addTransactionTo(transaction);
            return null;
        });
    }

    private static void total(Scanner sc){
        User user = Search.userSearch(sc, em);
        double amount = 0;

        for(Account account : user.getAccountList()){
            amount += account.getBalance() * Converter.convert(em,account.getCurrencyType(),CurrencyType.UAH).getRates();
        }
        System.out.println(amount + " total hryvnia in accounts");
    }

    private static void viewUserList(){
        TypedQuery<User> userQuery = em.createQuery(
                "select u from User u", User.class);
        List<User> userList = userQuery.getResultList();
        for(User u : userList){
            System.out.println(u);
        }
    }

    private static void viewAccountList(Scanner sc){
        User user = Search.userSearch(sc,em);

        List<Account> accountList = user.getAccountList();
        for(Account b : accountList){
            System.out.println(b);
        }
    }

    protected static <T> T performTransaction(Callable<T> action) {
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
