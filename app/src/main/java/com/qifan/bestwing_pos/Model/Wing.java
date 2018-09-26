package com.qifan.bestwing_pos.Model;

public class Wing {
    private int Quantity;
    private String Flavor;

    public Wing(int quantity, String flavor) {
        Quantity = quantity;
        Flavor = flavor;
    }
    public Wing(){}
    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getFlavor() {
        return Flavor;
    }

    public void setFlavor(String flavor) {
        Flavor = flavor;
    }
}
