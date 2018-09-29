package com.qifan.bestwing_pos.databinding;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SelectedItem {
    @BindingAdapter("SelectItem")
    public static void selectItem(RelativeLayout view, final String string){
         view.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 view.setBackgroundColor(Color.parseColor("#eeff00"));

             }
         });
    }
}
