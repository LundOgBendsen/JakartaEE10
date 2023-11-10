package dk.lundogbendsen.rest.model;


import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.json.bind.annotation.JsonbTransient;

@JsonbPropertyOrder(value = {"id", "name", "price"})
public class Product {

    @JsonbProperty("product-id")
    private Integer id;

    @JsonbProperty("product-name")
    private String name;

    @JsonbProperty("product-price")
    private double price;

    @JsonbTransient
    private Double profit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }
}
