package com.qifan.bestwing_pos.ViewModel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.qifan.bestwing_pos.Model.ItemPrices;
import com.qifan.bestwing_pos.Model.Order;
import com.qifan.bestwing_pos.ReceiptListAdapter;

import java.util.HashMap;
import java.util.List;


public class SharedViewModel extends ViewModel {
    private MutableLiveData<List<Order>> OrderList = new MutableLiveData<>();
    private int mCurrentPosition = ReceiptListAdapter.CurrentPosition;
    private static Order mCurrentOrder;

    //PriceChecker
    private ItemPrices mItemPrices = new ItemPrices();
    private HashMap<String, Double> mPriceMap = mItemPrices.itemPriceMap;
    private MutableLiveData<String> subtotal = new MutableLiveData<>();


    public void setOrderList(LiveData<List<Order>> list) {
        OrderList.setValue(list.getValue());

    }

    //specific method to set flavor for Wing
    public void setFlavor(String flavor) {
        setFlavorFormat(flavor);
    }

    //set name to the order such as "12 wings" or "CheeseBurger"
    public void setItemName(String itemName) {
        mCurrentPosition = ReceiptListAdapter.CurrentPosition;
        mCurrentOrder = OrderList.getValue().get(mCurrentPosition);
        double itemPrice = (mPriceMap.get(itemName) == null) ? 0 : mPriceMap.get(itemName);
        mCurrentOrder.setSubtotal(itemPrice, "mainItem");
        getSubtotal();
        mCurrentOrder.setItemName(formatOutput(itemName, itemPrice));
        setOrderList(OrderList);
    }


    public void setItemDetail(String detail) {
        mCurrentPosition = ReceiptListAdapter.CurrentPosition;
        mCurrentOrder = OrderList.getValue().get(mCurrentPosition);
        mCurrentOrder.setItemDetail(detail);
        setOrderList(OrderList);

    }

    public void setSideItem(String side) {
        mCurrentPosition = ReceiptListAdapter.CurrentPosition;
        mCurrentOrder = OrderList.getValue().get(mCurrentPosition);
        String oldSide = mCurrentOrder.getSideItem();

        double sidePrice = (mPriceMap.get(side) == null) ? 0 : mPriceMap.get(side);
        mCurrentOrder.setSubtotal(sidePrice, side);
        getSubtotal();
        String finalOutput = formatOutput(side, sidePrice);
        if (side.equals("FF")) {
            mCurrentOrder.setSideItem(finalOutput);
        } else if (side.equals("FR")) {
            mCurrentOrder.setSideItem(finalOutput);
        } else {
            mCurrentOrder.setSideItem(oldSide + "  " + finalOutput);
        }
        setOrderList(OrderList);
    }

    public LiveData<List<Order>> getOrderList() {
        return OrderList;
    }

    public void addNewOrder() {
        OrderList.getValue().add(new Order());
        setOrderList(OrderList);
    }

    //Clear out the ItemDetail and SideItem Text
    public void clearText(String text) {
        mCurrentPosition = ReceiptListAdapter.CurrentPosition;
        mCurrentOrder = OrderList.getValue().get(mCurrentPosition);
        double minusPrice; //Price to be deduct from the subtotal;
        double subtotal = mCurrentOrder.getSubtotal();

        if (text.equals("side")) {
            String item = mCurrentOrder.getSideItem().substring(0,mCurrentOrder.getSideItem().indexOf(" "));
            minusPrice = findPrice(item);
            mCurrentOrder.setSubtotal(subtotal - minusPrice, "clear");
            mCurrentOrder.setSideItem("");
            getSubtotal();
        } else {
            minusPrice = findPrice("||=");
            mCurrentOrder.setSubtotal(subtotal - minusPrice, "clear");
            mCurrentOrder.setItemDetail("");
            getSubtotal();
        }


        setOrderList(OrderList);
    }

    public void setFlavorFormat(String flavor) {
        mCurrentPosition = ReceiptListAdapter.CurrentPosition;
        mCurrentOrder = OrderList.getValue().get(mCurrentPosition);

        String oldFlavor = mCurrentOrder.getItemDetail();

        if (oldFlavor == null || oldFlavor.equals("")) {
            mCurrentOrder.setItemDetail("•    " + flavor);
        } else {
            String endWithNewLine = oldFlavor.substring(oldFlavor.length() - 1);
            if (flavor.equals("||=")) {
                //get the price for Split
                double splitPrice = findPrice(flavor);
                String itemFlavor = oldFlavor + flavor;
                mCurrentOrder.setSubtotal(splitPrice, "other");
                String finalOutput = formatOutput(itemFlavor, splitPrice);
                mCurrentOrder.setItemDetail(finalOutput);
                getSubtotal();
            } else if (endWithNewLine.equals("\n")) {
                mCurrentOrder.setItemDetail(oldFlavor + "•    " + flavor);
            } else {
                mCurrentOrder.setItemDetail(oldFlavor + "+" + flavor);
            }

        }

        setOrderList(OrderList);
    }

    private String formatOutput(String item, double price) {
        String mprice = "----  $" + price;
        String finalOutput = String.format(item + "%15s %n", mprice);
        return finalOutput;
    }

    private double findPrice(String itemName) {

        if (mPriceMap.get(itemName) == null) {
            return 0.0;
        } else {
            Double price = mPriceMap.get(itemName);
            return price;
        }

    }

    public LiveData<String> getSubtotal() {
        if (OrderList.getValue() == null) {
            subtotal.setValue("Total Price Before Tax:\n$0.00\nTotal Price After Tax:\n$0.00");
        } else {
            double totalPrice = 0;
            String totalAfterTax;
            String tax;
            double temp = 0;
            for (Order order : OrderList.getValue()) {
                totalPrice += order.getSubtotal();
            }
            temp = totalPrice * 0.089;
            tax = String.format("%.2f", temp);//Tax in innerCity atlanta 8.9%
            totalAfterTax = String.format("%.2f", temp + totalPrice);
            subtotal.setValue("Total Price Before Tax:\n$" + totalPrice + "\n8.9% Tax:\n$" + tax + "\nTotal Price After Tax:\n$" + totalAfterTax);
        }
        return subtotal;
    }
}
