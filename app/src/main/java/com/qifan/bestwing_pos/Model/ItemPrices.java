package com.qifan.bestwing_pos.Model;

import java.util.HashMap;

public class ItemPrices {
    public static HashMap<String,Double> itemPriceMap;
    public Order[] mOrders = new Order[]{addVeg,canSoda,bottleSoda,medLemonade,largeLemonade,SixWing,TenWing,FifteenWing,TwentyWing,ThirtyWing,FortyWing,
    FiftyWing,HundredWing,sideFriedRice,sideFries,addBeef,addChicken,addShrimp,addHH,addTY,split,addHouse,gardenSalad,turkeyClub,turkeySalad,hamClub,hamSalad,chefSalad,grilledChickenSalad,
    fishSalad,regularFriedRice,beefFriedRice,shrimpFriedRice,chickenFriedRice,houseFriedRice,vegFriedRice,phillyChicken,phillySteak,hamburger,cheeseburger,doubleCheeseburger,fishSandwich,
    gyro,vegRoll,shrimpRoll,steakRoll,twoFish,threeFish,fourFish,fiveFish,sixShrimp,twelveShrimp,fourTender,addTender,smallFries,largeFries,cornDog,crabRagoon,extraEgg,extraMeat};




    public ItemPrices(){
        itemPriceMap = new HashMap<>();
        for(Order order : mOrders){
            itemPriceMap.put(order.getMainItem(),order.getItemPrice());
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
    public static final Order addVeg = new Order("+Veggies",1.5);
    public static final Order addHH= new Order("+HH",0.5);
    public static final Order addTY = new Order("+TY",0.5);
    public static final Order split = new Order("||=",0.5);
    public static final Order canSoda = new Order("canSoda",1.00);
    public static final Order bottleSoda = new Order("bottleSoda",1.76);
    public static final Order medLemonade = new Order("medLemonade",1.00);
    public static final Order largeLemonade = new Order("largeLemonade",2.00);

    public static final Order gardenSalad = new Order("gardenSalad",5.59);
    public static final Order turkeySalad = new Order("turkeySalad",5.59);
    public static final Order hamSalad = new Order("hamSalad",5.59);
    public static final Order chefSalad = new Order("chefSalad",5.59);
    public static final Order grilledChickenSalad = new Order("grilledChickenSalad",5.99);
    public static final Order fishSalad = new Order("fishSalad",6.99);
    public static final Order regularFriedRice = new Order("regularFriedRice",3.99);
    public static final Order chickenFriedRice = new Order("chickenFriedRice",5.59);
    public static final Order beefFriedRice = new Order("beefFriedRice",5.59);
    public static final Order shrimpFriedRice = new Order("shrimpFriedRice",6.59);
    public static final Order houseFriedRice = new Order("houseFriedRice",7.99);
    public static final Order vegFriedRice = new Order("vegFriedRice",5.19);
    public static final Order phillySteak = new Order("phillySteak",4.99);
    public static final Order phillyChicken = new Order("phillyChicken",4.99);
    public static final Order hamburger = new Order("hamburger",3.69);
    public static final Order cheeseburger = new Order("cheeseburger",3.99);
    public static final Order doubleCheeseburger = new Order("doubleCheeseburger",5.19);
    public static final Order fishSandwich = new Order("fishSandwich",3.99);
    public static final Order gyro = new Order("gyro",4.99);
    public static final Order hamClub = new Order("hamClub",3.99);
    public static final Order turkeyClub = new Order("turkeyClub",3.99);
    public static final Order vegRoll = new Order("vegRoll",1.29);
    public static final Order shrimpRoll = new Order("shrimpRoll",1.39);
    public static final Order  steakRoll= new Order("steakRoll",1.99);
    public static final Order  twoFish= new Order("twoFish w/ff/hp",6.49);
    public static final Order  threeFish= new Order("threeFish w/ff/hp",8.49);
    public static final Order  fourFish= new Order("fourFish w/ff/hp",10.49);
    public static final Order  fiveFish= new Order("fiveFish w/ff/hp",12.49);
    public static final Order sixShrimp = new Order("sixShrimp w/ff",7.99);
    public static final Order  twelveShrimp= new Order("twelveShrimp w/ff",11.99);
    public static final Order  fourTender= new Order("fourTender w/ff",5.69);
    public static final Order  addTender= new Order("addTender",1.29);
    public static final Order smallFries = new Order("smallFries",1.59);
    public static final Order  largeFries= new Order("largeFries",2.99);
    public static final Order  cornDog = new Order("cornDog",1.00);
    public static final Order  crabRagoon = new Order("crabRagoon",1.39);
    public static final Order  extraEgg = new Order("extraEgg",0.5);
    public static final Order  extraMeat = new Order("extraMeat",2.00);









}
