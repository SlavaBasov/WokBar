package com.basovProjects.wokBar.enums;

public enum OrderStatus {
    NOT_PAID("During"),
    PAID("Paid"),
    COMPLETED("Completed"),
    CANCELED("Canceled");

    private final String str;

    OrderStatus(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
