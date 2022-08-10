package arconium.homework8;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id_from")
    private Account from;

    @ManyToOne
    @JoinColumn(name = "account_id_to")
    private Account to;

    @Column(name = "amount", nullable = false)
    private Double amount;

    public Transaction() {
    }

    public Transaction(Account from, Account to, Double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public void Transfer(ExchangeRates exchangeRates){
        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount * exchangeRates.getRates());
    }


    public Account getAccount() {
        return from;
    }

    public void setAccount(Account account) {
        this.from= account;
    }

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", from=" + from.getAccountNumber() +
                ", to=" + to.getAccountNumber() +
                ", amount=" + amount +
                '}';
    }
}
