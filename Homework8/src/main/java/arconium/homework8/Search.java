package arconium.homework8;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import java.util.Scanner;

class Search {

    protected static User userSearch(Scanner sc, EntityManager em){
        System.out.println("Enter user name");
        String name = sc.nextLine();
        User user;
        try{
            TypedQuery<User> userQuery = em.createQuery(
                    "select x from User x WHERE x.name = :name" , User.class);
            userQuery.setParameter("name", name);
            user = userQuery.getSingleResult();
        }catch (NoResultException ex) {
            System.out.println("User not found!");
            return null;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return null;
        }
        return user;
    }

    protected static Account accountSearch(Scanner sc, EntityManager em){
        System.out.println("Enter account number");
        String number = sc.nextLine();
        Account account;
        try{
            TypedQuery<Account> accountQuery = em.createQuery(
                    "select x from Account x WHERE x.accountNumber = :number" , Account.class);
            accountQuery.setParameter("number", number);
            account = accountQuery.getSingleResult();
        }catch (NoResultException ex) {
            System.out.println("User not found!");
            return null;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return null;
        }
        return account;
    }
}
