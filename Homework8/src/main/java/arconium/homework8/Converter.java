package arconium.homework8;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

class Converter {

    protected static ExchangeRates convert(EntityManager em, CurrencyType cFrom, CurrencyType cTo){
        ExchangeRates exchangeRates;
        try{
            TypedQuery<ExchangeRates> exchangeQuery = em.createQuery(
                    "select e from ExchangeRates e where e.cFrom = :cFrom and e.cTo = :cTo" , ExchangeRates.class);
            exchangeQuery.setParameter("cFrom", cFrom);
            exchangeQuery.setParameter("cTo", cTo);
            exchangeRates = exchangeQuery.getSingleResult();
        } catch (
                NoResultException ex) {
            System.out.println("Exchange rate not found!");
            return null;
        } catch (
                NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return null;
        }
        return exchangeRates;
    }
}
