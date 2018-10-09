package com.qifan.bestwing_pos.Model;

import java.util.HashMap;

public class ItemPrices {
    public static HashMap<String,Double> itemPriceMap;
    public Order[] mOrders = new Order[]{canSoda,bottleSoda,medLemonade,largeLemonade,SixWing,TenWing,FifteenWing,TwentyWing,ThirtyWing,FortyWing,
    FiftyWing,HundredWing,sideFriedRice,sideFries,addBeef,addChicken,addShrimp,addHH,addTY,split,addHouse};

    public ItemPrices(){
        itemPriceMap = new HashMap<>();
        for(Order order : mOrders){
            itemPriceMap.put(order.getItemName(),order.getItemPrice());
        }

    }

    public static final Order SixWing = new Order("6 wings",4.49);
    public static final Order TenWing = new Order("10 wings",5.49);
    public static final Order FifteenWing = new Order("15 wings",8.49);
    public static final Order TwentyWing = new Order("20 wings",10.49);
    public static final Order ThirtyWing = new Order("30 wings",16.49);
    public static final Order FortyWing = new Order("40 wings",22.99);
    public static final Order FiftyWing = new Order("50 wings",28.99);
    public static final Order HundredWing = new Order("100 wings",49.99);
    public static final Order sideFries = new Order("FF",1.0);
    public static final Order sideFriedRice = new Order("FR",2.0);
    public static final Order addChicken = new Order("+Chicken",2.0);
    public static final Order addBeef = new Order("+Beef",2.0);
    public static final Order addShrimp = new Order("+Shrimp",2.0);
    public static final Order addHouse = new Order("+House",3.0);
    public static final Order addHH= new Order("+HH",0.5);
    public static final Order addTY = new Order("+TY",0.5);
    public static final Order split = new Order("||=",0.5);
    public static final Order canSoda = new Order("canSoda",1.00);
    public static final Order bottleSoda = new Order("bottleSoda",1.76);
    public static final Order medLemonade = new Order("medLemonade",1.00);
    public static final Order largeLemonade = new Order("largeLemonade",2.00);



}
