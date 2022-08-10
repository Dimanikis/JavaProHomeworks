package arconium.homework8;

import javax.persistence.EntityManager;

class Test {

    protected static void addTest(EntityManager em){
        User test = new User("test");
        Account a1 = new Account("11", CurrencyType.USD);
        Account a2 = new Account("12", CurrencyType.EUR);
        Account a3 = new Account("13", CurrencyType.UAH);

        User test2 = new User("test2");
        Account b1 = new Account("21", CurrencyType.USD);
        Account b2 = new Account("22", CurrencyType.EUR);
        Account b3 = new Account("23", CurrencyType.UAH);

        App.performTransaction(() -> {
            em.persist(test);
            em.persist(a1);
            test.addAccount(a1);
            em.persist(a2);
            test.addAccount(a2);
            em.persist(a3);
            test.addAccount(a3);

            em.persist(test2);
            em.persist(b1);
            test2.addAccount(b1);
            em.persist(b2);
            test2.addAccount(b2);
            em.persist(b3);
            test2.addAccount(b3);

            em.persist(new User("test3"));

            em.persist(new ExchangeRates(CurrencyType.USD,CurrencyType.UAH,37.12));
            em.persist(new ExchangeRates(CurrencyType.USD,CurrencyType.EUR,0.98));
            em.persist(new ExchangeRates(CurrencyType.USD,CurrencyType.USD,1.0));
            em.persist(new ExchangeRates(CurrencyType.EUR,CurrencyType.UAH,37.82));
            em.persist(new ExchangeRates(CurrencyType.EUR,CurrencyType.USD,1.02));
            em.persist(new ExchangeRates(CurrencyType.EUR,CurrencyType.EUR,1.0));
            em.persist(new ExchangeRates(CurrencyType.UAH,CurrencyType.USD,0.027));
            em.persist(new ExchangeRates(CurrencyType.UAH,CurrencyType.EUR,0.026));
            em.persist(new ExchangeRates(CurrencyType.UAH,CurrencyType.UAH,1.0));
            return null;
        });
    }
}
