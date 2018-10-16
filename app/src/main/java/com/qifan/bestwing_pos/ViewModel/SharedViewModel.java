package com.qifan.bestwing_pos.ViewModel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.qifan.bestwing_pos.Model.ItemPrices;
import com.qifan.bestwing_pos.Model.Order;
import com.qifan.bestwing_pos.ReceiptListAdapter;

import java.util.ArrayList;
import java.util.List;


public class SharedViewModel extends ViewModel {
    private MutableLiveData<List<Order>> OrderList = new MutableLiveData<>();

    private int mCurrentPosition = ReceiptListAdapter.CurrentPosition;
    private static Order mCurrentOrder;

    //PriceChecker
    private MutableLiveData<String> orderFinalPrice = new MutableLiveData<>();
    private MutableLiveData<String> payListFinalPrice = new MutableLiveData<>();

    ItemPrices mItemPrices = new ItemPrices();
    private final static double ATLANTA_CITY_TAX = 0.089;


    public void setOrderList(LiveData<List<Order>> list) {
        OrderList.setValue(list.getValue());
    }

    public LiveData<List<Order>> getOrderList() {
        return OrderList;
    }

    public void addNewOrder() {
        if(OrderList.getValue() == null){
            List<Order> orders = new ArrayList<>();
            orders.add(new Order());
            OrderList.setValue(orders);
            setOrderList(OrderList);
        }else {
            OrderList.getValue().add(new Order());
            setOrderList(OrderList);
        }

    }
    public void initOrderList(){
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        OrderList.setValue(orders);
        setOrderList(OrderList);
    }


    //Functions ---------------------------------------------------------------------
    public void getCurrentOrder() {
        mCurrentPosition = ReceiptListAdapter.CurrentPosition;
        mCurrentOrder = OrderList.getValue().get(mCurrentPosition);
    }

    public void notifyPropertiesChange() {
        mCurrentOrder.updateSubtotal();
        getOrderFinalPrice();
        setOrderList(OrderList);
    }

//Functions ^---------------------------------------------------------------------^

    public void setFlavor(String flavor) {
        getCurrentOrder();
        mCurrentOrder.setFlavorToString(flavor);

        notifyPropertiesChange();
    }

    public void setItemName(String itemName) {
        getCurrentOrder();
        mCurrentOrder.setMainItem(itemName);
        notifyPropertiesChange();
    }


    public void setItemDetail(String detail) {
        getCurrentOrder();
        mCurrentOrder.setItemDetail(detail);
        notifyPropertiesChange();

    }

    public void setSideItem(String side) {
        getCurrentOrder();
        mCurrentOrder.setSideItems(side);
        notifyPropertiesChange();
    }

    public void setAdditionalItem(String additionalItem){
        getCurrentOrder();
        mCurrentOrder.setAdditionalItems(additionalItem);
        notifyPropertiesChange();
    }

    public void setDrink(String drink) {
        getCurrentOrder();
        mCurrentOrder.setDrink(drink);
        notifyPropertiesChange();
    }


    //Clear out the ItemDetail and SideItem Text
    public void clearText(String text) {
        getCurrentOrder();

        if (text.equals("side")) {
            mCurrentOrder.getSideItems().removeAll(mCurrentOrder.getSideItems());
            mCurrentOrder.clearText(text);
            notifyPropertiesChange();
        } else if (text.equals("drink")) {
            mCurrentOrder.getDrinks().removeAll(mCurrentOrder.getDrinks());
            mCurrentOrder.clearText(text);
            notifyPropertiesChange();
        }else if(text.equals("additionalItem")){
            mCurrentOrder.getAdditionalItems().removeAll(mCurrentOrder.getAdditionalItems());
            mCurrentOrder.clearText(text);
            notifyPropertiesChange();
        } else {
            mCurrentOrder.clearText(text);
            notifyPropertiesChange();
        }

    }


    public LiveData<String> getOrderFinalPrice() {
        if (OrderList.getValue() == null) {
            orderFinalPrice.setValue("Total Price Before Tax:\n$0.00\nTotal Price After Tax:\n$0.00");
        } else {
            double totalPrice = 0;
            String totalBeforeTax;
            String totalAfterTax;
            String tax;
            double temp = 0;
            for (Order order : OrderList.getValue()) {
                totalPrice += order.getSubtotal();
            }
            temp = totalPrice * ATLANTA_CITY_TAX;
            //TODO Format the price
            tax = String.format("%.2f", temp);//Tax in innerCity atlanta 8.9%
            totalAfterTax = String.format("%.2f", temp + totalPrice);
            totalBeforeTax = String.format("%.2f",totalPrice);
            orderFinalPrice.setValue("Total Price Before Tax:\n$" + totalBeforeTax + "\n8.9% Tax:\n$" + tax + "\nTotal Price After Tax:\n$" + totalAfterTax);
        }
        return orderFinalPrice;
    }

    public void setPayOrderList(LiveData<List<Order>> payOrderList) {
        mPayOrderList.setValue(payOrderList.getValue());

    }

    public MutableLiveData<List<Order>> getPayOrderList() {
        return mPayOrderList;
    }

    // Below are codes for PaymentFragment------------------------------------------

    private int SelectionListPosition;
    private int PayListPosition;
    private MutableLiveData<List<Order>> mPayOrderList = new MutableLiveData<>();
    private Order mCurrentPayOrder;

    public void addToRightList() {
        getPosition();
        OrderList.getValue().remove(mCurrentOrder);
        if (mCurrentPayOrder.getMainItem() == "New Order") {
            mPayOrderList.getValue().add(mCurrentOrder);
            mPayOrderList.getValue().remove(mCurrentPayOrder);
        } else {
            mPayOrderList.getValue().add(mCurrentOrder);
        }
        OrderList.setValue(OrderList.getValue());
        mPayOrderList.setValue(mPayOrderList.getValue());
        getPayListFinalPrice();


    }

    public void addToLeftList() {
        PayListPosition = ReceiptListAdapter.PayListCurrentPosition;
        mCurrentPayOrder = mPayOrderList.getValue().get(PayListPosition);
        if (mPayOrderList.getValue().size() == 1) {
            mPayOrderList.getValue().add(new Order("New Order", 0.0));
            mPayOrderList.getValue().remove(mCurrentPayOrder);
        } else {
            mPayOrderList.getValue().remove(mCurrentPayOrder);
        }

        OrderList.getValue().add(mCurrentPayOrder);
        OrderList.setValue(OrderList.getValue());
        mPayOrderList.setValue(mPayOrderList.getValue());
        getPayListFinalPrice();



    }

    public void addAll() {
        List<Order> toRemove = new ArrayList<>();
        if (mPayOrderList.getValue().get(0).getMainItem() == "New Order") {
            mPayOrderList.getValue().remove(0);
        }
        for (Order order : OrderList.getValue()) {
            mPayOrderList.getValue().add(order);
            toRemove.add(order);
        }
        OrderList.getValue().removeAll(toRemove);
        OrderList.setValue(OrderList.getValue());
        mPayOrderList.setValue(mPayOrderList.getValue());
        getPayListFinalPrice();
    }

    public void getPosition() {
        SelectionListPosition = ReceiptListAdapter.SelectionListCurrentPosition;
        PayListPosition = ReceiptListAdapter.PayListCurrentPosition;
        mCurrentOrder = OrderList.getValue().get(SelectionListPosition);
        mCurrentPayOrder = mPayOrderList.getValue().get(PayListPosition);
    }

    public LiveData<String> getPayListFinalPrice() {
        if (mPayOrderList.getValue() == null) {
            payListFinalPrice.setValue("Total Price Before Tax:\n$0.00\nTotal Price After Tax:\n$0.00");
        } else {
            double totalPrice = 0;
            double totalAfterTax;
            double tax;
            for (Order order : mPayOrderList.getValue()) {
                totalPrice += order.getSubtotal();
            }
            tax = totalPrice * ATLANTA_CITY_TAX;
            totalAfterTax = totalPrice + tax;
            String finalOutput = String.format("Total Price Before Tax:\n$ %.2f \n8.9 Tax:\n$ %.2f \nTotal Price After Tax:\n$ %.2f",totalPrice,tax,totalAfterTax);
            payListFinalPrice.setValue(finalOutput);

        }
        return payListFinalPrice;
    }

}
