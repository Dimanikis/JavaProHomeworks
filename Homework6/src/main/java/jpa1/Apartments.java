package jpa1;

import org.hibernate.annotations.Generated;

import javax.persistence.*;

@Entity
public class Apartments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private int area;

    @Column(nullable = false)
    private int nor;

    @Column(nullable = false)
    private int price;

    public Apartments() {}

    public Apartments(String region, String address, int area, int nor, int price) {
        this.region = region;
        this.address = address;
        this.area = area;
        this.nor = nor;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getNor() {
        return nor;
    }

    public void setNor(int nor) {
        this.nor = nor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Apartments{" +
                "id=" + id +
                ", region='" + region + '\'' +
                ", address='" + address + '\'' +
                ", area=" + area +
                ", nor=" + nor +
                ", price=" + price +
                '}';
    }
}
