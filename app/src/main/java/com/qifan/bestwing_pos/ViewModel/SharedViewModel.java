package com.qifan.bestwing_pos.ViewModel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.qifan.bestwing_pos.Model.Wing;



public class SharedViewModel extends ViewModel{
 private MutableLiveData<Wing> wing = new MutableLiveData<>();

 public void setFlavor(String flavor){
    LiveData<Wing> wing = getWing();
    wing.getValue().setFlavor(flavor);
    setWing(wing.getValue());
 }
 public void setQuantity(String Quantity){
     LiveData<Wing> wing = getWing();
     wing.getValue().setQuantity(Integer.parseInt(Quantity));
     setWing(wing.getValue());
 }

 public void setWing(Wing selectedWing){
     wing.setValue(selectedWing);

 }
 public String getFlavor(){
     return wing.getValue().getFlavor();
 }

 public LiveData<Wing> getWing(){
     return wing;
 }

}
