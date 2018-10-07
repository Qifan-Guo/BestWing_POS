package com.qifan.bestwing_pos.Model;

import android.util.Log;

public class Order {
    private String itemName;
    private String itemDetail;
    private double itemPrice;
    private String sideItem;
    private double subtotal = 0;
    private Boolean isSelect = false;


    public Order() {
    }

    public Order(String itemName, double itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public Order(String itemName, String itemDetail, double itemPrice) {
        this.itemName = itemName;
        this.itemDetail = itemDetail;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(String itemDetail) {
        this.itemDetail = itemDetail;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public double getSubtotal() {
        return subtotal;
    }


    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }


    public Boolean getSelect() {
        return isSelect;
    }

    public void setSelect(Boolean select) {
        isSelect = select;
    }

    public String getSideItem() {
        return sideItem;
    }

    public void setSideItem(String sideItem) {
        this.sideItem = sideItem;
    }

    double mainItemPrice = 0;
    double sidePrice = 0;
    double otherPrice = 0;
    public void setSubtotal(double subtotal,String category) {

        if(category == "mainItem"){
           mainItemPrice = subtotal;
        }else
            if(category == "FF" || category == "FR"){
            sidePrice = subtotal;
        }else {
            otherPrice += subtotal;
            }
        double total = mainItemPrice + sidePrice +otherPrice;
        otherPrice =0;
        this.subtotal = total;

        if(category =="clear"){
            this.subtotal = subtotal;

        }
    }


}
