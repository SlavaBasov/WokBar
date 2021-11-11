package com.basovProjects.wokBar.model;

import javax.persistence.*;

@Entity
@Table(name = "line_item")
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    private Product product;

    public LineItem() {
    }

    public LineItem(Integer quantity, Order order, Product product) {
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", order=" + order +
                ", product=" + product +
                '}';
    }
}
