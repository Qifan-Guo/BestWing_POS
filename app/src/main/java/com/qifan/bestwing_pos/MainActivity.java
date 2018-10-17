package com.qifan.bestwing_pos;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.qifan.bestwing_pos.Fragments.FragmentHost;
import com.qifan.bestwing_pos.Fragments.PaymentFragment;
import com.qifan.bestwing_pos.Fragments.SpecialOptionFragment;
import com.qifan.bestwing_pos.Fragments.WingOrderFragment;
import com.qifan.bestwing_pos.Fragments.ReceiptPageFragment;
import com.qifan.bestwing_pos.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements iMainActivity {
    private ActivityMainBinding mBinding;
    private FragmentManager mFragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        initView();


    }
    public void initView(){
        mFragmentManager=getSupportFragmentManager();
        FragmentHost fragmentHost = new FragmentHost();
        ReceiptPageFragment receiptPageFragment=new ReceiptPageFragment();
        mFragmentManager.beginTransaction().replace(R.id.fragmentContainer1, fragmentHost).commit();
        mFragmentManager.beginTransaction().replace(R.id.fragmentContainer2,receiptPageFragment).commit();

    }

    @Override
    public void showPaymentFragment() {
        PaymentFragment paymentFragment = new PaymentFragment();
        paymentFragment.show(getSupportFragmentManager(),"paymentFragment");

    }

    @Override
    public void showSpecialOptionFragment() {
        SpecialOptionFragment specialOptionFragment = new SpecialOptionFragment();
        specialOptionFragment.show(getSupportFragmentManager(),"specialOptionFragment");
    }
}
