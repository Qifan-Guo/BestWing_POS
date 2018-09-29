package com.qifan.bestwing_pos.ViewModel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.qifan.bestwing_pos.Model.Order;
import com.qifan.bestwing_pos.ReceiptListAdapter;
import java.util.List;



public class SharedViewModel extends ViewModel {
    private MutableLiveData<List<Order>> OrderList = new MutableLiveData<>();
    private int mCurrentPosition = ReceiptListAdapter.CurrentPosition;
    private static Order mCurrentOrder;
    



    public void setOrderList(LiveData<List<Order>> list) {
       OrderList.setValue(list.getValue());

    }

//specific method to set flavor for Wing
    public void setFlavor(String flavor) {
        setFlavorFormat(flavor);
    }

    public void setItemName(String itemName){
        mCurrentPosition = ReceiptListAdapter.CurrentPosition;
        mCurrentOrder = OrderList.getValue().get(mCurrentPosition);
        mCurrentOrder.setItemName(itemName);
        setOrderList(OrderList);
    }

    //set name to the order such as "12 wings" or "CheeseBurger"
    public void setItemDetail(String detail) {
        mCurrentPosition = ReceiptListAdapter.CurrentPosition;
        Log.d("viewModel", "setQuantity: "+mCurrentPosition);
        mCurrentOrder = OrderList.getValue().get(mCurrentPosition);
        mCurrentOrder.setItemDetail(detail);
        setOrderList(OrderList);

    }
    

    public LiveData<List<Order>> getOrderList() {
        return OrderList;
    }

    public void addNewOrder(){
        OrderList.getValue().add(new Order());
        setOrderList(OrderList);
    }

    public void setFlavorFormat(String flavor) {
        mCurrentPosition = ReceiptListAdapter.CurrentPosition;
        mCurrentOrder = OrderList.getValue().get(mCurrentPosition);

        String oldFlavor = mCurrentOrder.getItemDetail();

        if (oldFlavor == null || oldFlavor.equals("")) {
            mCurrentOrder.setItemDetail("•    " +flavor);
        } else {
            String endWithNewLine = oldFlavor.substring(oldFlavor.length() - 1);
            if (flavor .equals("||") ) {
               mCurrentOrder.setItemDetail(oldFlavor + flavor + "\n");
            } else if (endWithNewLine.equals("\n")) {
                mCurrentOrder.setItemDetail(oldFlavor + "•    " + flavor);
            } else {
                mCurrentOrder.setItemDetail(oldFlavor + "+" + flavor);
            }

        }

        setOrderList(OrderList);
    }
}
