package arconium.homework8;

import javax.persistence.*;

@Entity
@Table(name = "exchange_rates")
class ExchangeRates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "currency_from", nullable = false)
    private CurrencyType cFrom;

    @Column(name = "currency_to", nullable = false)
    private CurrencyType cTo;

    @Column(name = "rates", nullable = false)
    private Double rates;

    public ExchangeRates() {
    }

    public ExchangeRates(CurrencyType cFrom, CurrencyType cTo, Double rates) {
        this.cFrom = cFrom;
        this.cTo = cTo;
        this.rates = rates;
    }

    public CurrencyType getcFrom() {
        return cFrom;
    }

    public void setcFrom(CurrencyType cFrom) {
        this.cFrom = cFrom;
    }

    public CurrencyType getcTo() {
        return cTo;
    }

    public void setcTo(CurrencyType cTo) {
        this.cTo = cTo;
    }

    public Double getRates() {
        return rates;
    }

    public void setRates(Double rates) {
        this.rates = rates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ExchangeRates{" +
                "id=" + id +
                ", cFrom=" + cFrom +
                ", cTo=" + cTo +
                ", rates=" + rates +
                '}';
    }
}
