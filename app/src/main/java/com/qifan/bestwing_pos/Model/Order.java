package com.qifan.bestwing_pos.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

public class Order implements Parcelable {
    private String mainItem;
    private String itemDetail;
    private double itemPrice;

    public static final double SPLIT_CHARGE =0.5;
    public int SPLIT_COUNT = 0;

    public String getDrinkToString() {
        return drinkToString;
    }

    public String getMainItemToString() {
        return mainItemToString;
    }

    public String getSideItemToString() {
        return sideItemToString;
    }
    public String getAdditionalItemToString() {return additionalItemToString;}

    private String drinkToString = "";
    private String mainItemToString = "";
    private String sideItemToString = "";
    private String flavorToString = "";
    private String additionalItemToString = "";

    HashMap<String,Double> mItemPriceMap = ItemPrices.itemPriceMap;
    private ArrayList<String> sideItems = new ArrayList<>();
    private ArrayList<String> drinks = new ArrayList<>();
    private ArrayList<String> additionalItems = new ArrayList<>();

    private double subtotal = 0;
    private Boolean isSelect = false;

    //Constructors --------------------------------------------------------------------------------------
    public Order() {
    }

    public Order(String mainItem, double itemPrice) {
        this.mainItem = mainItem;
        this.itemPrice = itemPrice;
    }

    public Order(String mainItem, String itemDetail, double itemPrice) {
        this.mainItem = mainItem;
        this.itemDetail = itemDetail;
        this.itemPrice = itemPrice;
    }

//Constructors ^ -------------------------------------------------------------------------------------^

//Functions -------------------------------------------------------------------------------------

    public void updateSubtotal() {
        double subtotal = 0;
        for (String drink : drinks) {
            subtotal += mItemPriceMap.get(drink) == null ? 0 : mItemPriceMap.get(drink);
        }
        for (String sideItem : sideItems) {
            subtotal += mItemPriceMap.get(sideItem) == null ? 0 : mItemPriceMap.get(sideItem);
        }
        for(String additionalItem : additionalItems){
            subtotal += mItemPriceMap.get(additionalItem) == null ? 0 : mItemPriceMap.get(additionalItem);
        }
        if(mainItem != null){
            subtotal += mItemPriceMap.get(mainItem);
        }
        if(SPLIT_COUNT != 0){
            subtotal += (SPLIT_CHARGE*SPLIT_COUNT);
        }
        this.subtotal = subtotal;
    }


    public void setSideItemToString() {
        String lastItem = sideItems.get(sideItems.size() - 1);
        if(lastItem.equals("FF") || lastItem.equals("FR")){
            sideItemToString = "";
        }
        sideItemToString += lastItem + "------$" + mItemPriceMap.get(lastItem) + "\n";
    }

    public void setDrinkItemToString() {
        String lastDrink = drinks.get(drinks.size() - 1);
        drinkToString += lastDrink + "------$" + mItemPriceMap.get(lastDrink) + "\n";
    }

    public void setAdditionalItemToString(){
        String lastItem = additionalItems.get(additionalItems.size() - 1);
        additionalItemToString += lastItem + "------$" + mItemPriceMap.get(lastItem) + "\n";
    }

    public void clearText(String option){
        if(option.equals("side")){
            sideItemToString = "";
        }
        if(option.equals("drink")){
            drinkToString = "";
        }
        if(option.equals("flavor")){
            SPLIT_COUNT = 0;
            flavorToString = "";
        }
        if(option.equals("additionalItem")){
            additionalItemToString = "";
        }
    }


//Functions^ -------------------------------------------------------------------------------------^


//Getters and Setters  ------------------------------------------------------------------------------------


    public String getFlavorToString() {
        return flavorToString;
    }

    public void setFlavorToString(String flavor) {
        if (flavorToString .equals("") || flavorToString.substring(flavorToString.length() - 1).equals("\n")) {
            flavorToString += "•    " + flavor;
        } else if (flavor.equals("||=")) {
            flavorToString += flavor + "-----$" + mItemPriceMap.get(flavor) + "\n";
            SPLIT_COUNT +=1;
        } else {
            flavorToString += "+" + flavor;
        }


    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setSideItems(String sideItem) {
        sideItems.add(sideItem);
        setSideItemToString();
    }

    public ArrayList<String> getSideItems() {
        return sideItems;
    }

    public ArrayList<String> getDrinks() {
        return drinks;
    }

    public void setDrink(String drink) {
        drinks.add(drink);
        setDrinkItemToString();
    }

    public ArrayList<String> getAdditionalItems() {
        return additionalItems;
    }

    public void setAdditionalItems(String additionalItem) {
        additionalItems.add(additionalItem);
        setAdditionalItemToString();
    }
    public String getMainItem() {
        return mainItem;
    }

    public void setMainItem(String mainItem) {
        this.mainItem = mainItem;
        mainItemToString = mainItem + "--------$" + mItemPriceMap.get(mainItem);

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


    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }


    public Boolean getSelect() {
        return isSelect;
    }

    public void setSelect(Boolean select) {
        isSelect = select;
    }


//Getters and Setters  ^---------------------------------------------------------------------------------^


    protected Order(Parcel in) {
        mainItem = in.readString();
        itemDetail = in.readString();
        itemPrice = in.readDouble();
        SPLIT_COUNT = in.readInt();
        drinkToString = in.readString();
        mainItemToString = in.readString();
        sideItemToString = in.readString();
        flavorToString = in.readString();
        additionalItemToString = in.readString();
        sideItems = in.createStringArrayList();
        drinks = in.createStringArrayList();
        additionalItems = in.createStringArrayList();
        subtotal = in.readDouble();
        byte tmpIsSelect = in.readByte();
        isSelect = tmpIsSelect == 0 ? null : tmpIsSelect == 1;
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mainItem);
        parcel.writeString(itemDetail);
        parcel.writeDouble(itemPrice);
        parcel.writeInt(SPLIT_COUNT);
        parcel.writeString(drinkToString);
        parcel.writeString(mainItemToString);
        parcel.writeString(sideItemToString);
        parcel.writeString(flavorToString);
        parcel.writeString(additionalItemToString);
        parcel.writeStringList(sideItems);
        parcel.writeStringList(drinks);
        parcel.writeStringList(additionalItems);
        parcel.writeDouble(subtotal);
        parcel.writeByte((byte) (isSelect == null ? 0 : isSelect ? 1 : 2));
    }


}
