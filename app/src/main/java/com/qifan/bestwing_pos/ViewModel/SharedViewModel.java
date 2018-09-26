package com.qifan.bestwing_pos.ViewModel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.qifan.bestwing_pos.Model.Wing;



public class SharedViewModel extends ViewModel{
 private MutableLiveData<Wing> wing = new MutableLiveData<>();

 public void setFlavor(String flavor){
   setFlavorFormat(flavor);
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


 public void setFlavorFormat(String flavor){
     LiveData<Wing> wing = getWing();
     String oldFlavor= wing.getValue().getFlavor();

     if(oldFlavor == null){
         wing.getValue().setFlavor(flavor);
     }else {
         String endWithNewLine=oldFlavor.substring(oldFlavor.length()-1);
         if(flavor == "||"){
             wing.getValue().setFlavor(oldFlavor+flavor+"\n");
         }else if(endWithNewLine.equals("\n")) {
             wing.getValue().setFlavor(oldFlavor+"•    "+flavor);
         }else {
             wing.getValue().setFlavor(oldFlavor+"+"+flavor);
         }

     }

     setWing(wing.getValue());
 }
}
