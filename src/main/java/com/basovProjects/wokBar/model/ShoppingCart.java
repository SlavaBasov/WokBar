package com.basovProjects.wokBar.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {

    private final Map<Long, ShoppingCartLineItem> lineItems;

    private double subTotalCost;

    public ShoppingCart() {
        lineItems = new HashMap<>();
    }

    public void addLineItem(ShoppingCartLineItem lineItem) {
//        if(!lineItems.contains(lineItem)){
//            lineItems.add(lineItem);
//        }
        Long productId = lineItem.getProduct().getId();
        ShoppingCartLineItem item = lineItems.get(productId);

        if (lineItems.isEmpty() || item == null) {
            lineItems.put(productId, lineItem);
        } else {
            lineItems.get(productId).setQuantity(item.getQuantity() + 1);
        }
        calculateSubTotalCost();

    }

    public boolean deleteLineItem(Long key){
        if(key<1L){
            return false;
        }
        if(lineItems.isEmpty()){
            return false;
        }
        if(!lineItems.containsKey(key)){
            return false;
        }
        lineItems.remove(key);
        calculateSubTotalCost();
        return true;

    }

    public boolean deleteAllLineItems(){
        if(lineItems.isEmpty()){
            return false;
        }
        lineItems.clear();
        subTotalCost=0;
        return true;
    }


    public boolean subtractQuantity(ShoppingCartLineItem lineItem) {
        Long productId = lineItem.getProduct().getId();
        ShoppingCartLineItem item = lineItems.get(productId);

        if (lineItems.isEmpty() || item == null || lineItems.get(productId).getQuantity()==0) {
            return false;
        }else {
            lineItems.get(productId).setQuantity(item.getQuantity() - 1);
            calculateSubTotalCost();
            return true;
        }
    }

    public Map<Long, ShoppingCartLineItem> getLineItems() {
        return lineItems;
    }

    public double getSubTotalCost() {
        return subTotalCost;
    }

    private void calculateSubTotalCost(){
        subTotalCost=lineItems.values().stream().mapToDouble(i->
                i.getPrice()*i.getQuantity()).sum();
    }

    public void setSubTotalCost(double subTotalCost) {
        this.subTotalCost = subTotalCost;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "lineItems=" + lineItems +
                ", subTotalCost=" + subTotalCost +
                '}';
    }
}
