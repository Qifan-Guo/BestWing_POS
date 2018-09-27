package com.qifan.bestwing_pos.ViewModel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.qifan.bestwing_pos.Model.Wing;
import com.qifan.bestwing_pos.ReceiptListAdapter;

import java.util.List;


public class SharedViewModel extends ViewModel {
    private MutableLiveData<List<Wing>> wingList = new MutableLiveData<>();
    private int mCurrentPosition = ReceiptListAdapter.CurrentPosition;
    private Wing mCurrentWing;


    public void setWingList(LiveData<List<Wing>> list) {
        wingList.setValue(list.getValue());

    }

    public LiveData<List<Wing>> getWingList() {
        return wingList;
    }

    public void setFlavor(String flavor) {
        setFlavorFormat(flavor);
    }

    public void setQuantity(String Quantity) {
        mCurrentWing = wingList.getValue().get(mCurrentPosition);
        mCurrentWing.setQuantity(Integer.parseInt(Quantity));
        setWingList(wingList);

    }

    public String getFlavor() {
//        return wing.getValue().getFlavor();
        return null;
    }

    public LiveData<List<Wing>> getFullListWings() {
        return wingList;
    }

    public void addNewWing(){
        wingList.getValue().add(new Wing());
        setWingList(wingList);
    }


    public void setFlavorFormat(String flavor) {
        mCurrentWing = wingList.getValue().get(mCurrentPosition);

        String oldFlavor = mCurrentWing.getFlavor();

        if (oldFlavor.equals(null) || oldFlavor.equals("")) {
            mCurrentWing.setFlavor("•    " +flavor);
        } else {
            String endWithNewLine = oldFlavor.substring(oldFlavor.length() - 1);
            if (flavor .equals("||") ) {
               mCurrentWing.setFlavor(oldFlavor + flavor + "\n");
            } else if (endWithNewLine.equals("\n")) {
                mCurrentWing.setFlavor(oldFlavor + "•    " + flavor);
            } else {
                mCurrentWing.setFlavor(oldFlavor + "+" + flavor);
            }

        }

        setWingList(wingList);
    }
}
