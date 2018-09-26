package com.qifan.bestwing_pos;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qifan.bestwing_pos.Fragments.OrderingPageFragment;
import com.qifan.bestwing_pos.Fragments.ReceiptPageFragment;
import com.qifan.bestwing_pos.ViewModel.SharedViewModel;
import com.qifan.bestwing_pos.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        initView();


    }
    public void initView(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        OrderingPageFragment orderingPageFragment =new OrderingPageFragment();
        ReceiptPageFragment receiptPageFragment=new ReceiptPageFragment();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer1, orderingPageFragment).commit();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer2,receiptPageFragment).commit();

    }
}
