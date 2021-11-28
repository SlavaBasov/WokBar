package com.basovProjects.wokBar.model;

import java.util.Objects;

public class ShoppingCartLineItem {
    private Product product;
    private int quantity;
    private Double price;
    private Double totalPrice;

    public ShoppingCartLineItem(Product product, int quantity, Double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = price * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = quantity*price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
        this.totalPrice = quantity*price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "ShoppingCartLineItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartLineItem that = (ShoppingCartLineItem) o;
        return quantity == that.quantity && Objects.equals(product, that.product) && Objects.equals(price, that.price) && Objects.equals(totalPrice, that.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity, price, totalPrice);
    }
}
