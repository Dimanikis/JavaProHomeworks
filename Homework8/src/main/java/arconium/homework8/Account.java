package arconium.homework8;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

enum CurrencyType {USD,EUR,UAH}

@Entity
@Table(name = "account")
class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "account_type", nullable = false)
    private CurrencyType currencyType;

    @Column(name = "balance")
    private Double balance = 0.0;

    @OneToMany(mappedBy = "from", cascade = CascadeType.ALL)
    private List<Transaction> listFrom = new ArrayList<>();

    @OneToMany(mappedBy = "to", cascade = CascadeType.ALL)
    private List<Transaction> listTo = new ArrayList<>();

    public Account() {
    }

    public Account(String accountNumber, CurrencyType ct) {
        this.accountNumber = accountNumber;
        this.currencyType = ct;
    }

    protected void addTransactionFrom(Transaction transaction) {
        if ( ! listFrom.contains(transaction)) {
            listFrom.add(transaction);
        }
    }

    protected void addTransactionTo(Transaction transaction) {
        if ( ! listTo.contains(transaction)) {
            listTo.add(transaction);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Transaction> getListFrom() {
        return listFrom;
    }

    public void setListFrom(List<Transaction> listFrom) {
        this.listFrom = listFrom;
    }

    public List<Transaction> getListTo() {
        return listTo;
    }

    public void setListTo(List<Transaction> listTo) {
        this.listTo = listTo;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", user=" + user.getName() +
                ", accountNumber='" + accountNumber + '\'' +
                ", currencyType=" + currencyType +
                ", balance=" + balance +
                ", listFrom=" + listFrom +
                ", listTo=" + listTo +
                '}';
    }
}
