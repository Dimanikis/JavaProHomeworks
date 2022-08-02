package arconium.homework7;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "order_goods",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "goods_id"))
    private Set<Goods> goodses = new HashSet<>();

    public Order() {
    }

    public Order(Client client) {
        this.client = client;
    }

    public void addGoods(Goods goods){
        goodses.add(goods);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Goods> getGoodses() {
        return goodses;
    }

    public void setGoodses(Set<Goods> goods) {
        this.goodses = goods;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", goods=" + goodses +
                '}';
    }
}
