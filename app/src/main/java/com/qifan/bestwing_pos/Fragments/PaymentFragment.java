package com.qifan.bestwing_pos.Fragments;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qifan.bestwing_pos.Model.Order;
import com.qifan.bestwing_pos.R;
import com.qifan.bestwing_pos.ReceiptListAdapter;
import com.qifan.bestwing_pos.ViewModel.SharedViewModel;
import com.qifan.bestwing_pos.databinding.FragmentPaymentBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends DialogFragment {
    final int PAYMENT_VIEW_TYPE = 1;
    final int PAID_VIEW_TYPE = 2;
    private MutableLiveData<List<Order>> paymentList = new MutableLiveData<>();

    private FragmentPaymentBinding mBinding;

    public PaymentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentPaymentBinding.inflate(inflater);
        if(getActivity()!= null){
        SharedViewModel sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);


        mBinding.setViewModel(sharedViewModel);
        mBinding.selectionList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        mBinding.selectionList.setAdapter(new ReceiptListAdapter(this.getContext(), sharedViewModel.getOrderList(), PAYMENT_VIEW_TYPE));


        ArrayList<Order> initializeList = new ArrayList<>();
        initializeList.add(new Order("New Order", 0.0));
        paymentList.setValue(initializeList);
        sharedViewModel.setPayOrderList(paymentList);

        mBinding.paymentList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mBinding.paymentList.setAdapter(new ReceiptListAdapter(this.getContext(),sharedViewModel.getPayOrderList(), PAID_VIEW_TYPE));

        mBinding.getViewModel().getOrderList().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(@Nullable List<Order> orders) {
                if(mBinding.selectionList.getAdapter() != null){
                mBinding.selectionList.getAdapter().notifyDataSetChanged();}
            }
        });

        mBinding.getViewModel().getPayOrderList().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(@Nullable List<Order> orders) {
                if(mBinding.paymentList.getAdapter() != null){
                    mBinding.paymentList.getAdapter().notifyDataSetChanged();
                }

            }
        });}

        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        // safety check
        if (getDialog().getWindow() == null){
            Log.d("null dialog", "onStart: Dialog is null");
        }else {
            int dialogWidth = 2400;
            int dialogHeight = 1200;

            getDialog().getWindow().setLayout(dialogWidth, dialogHeight);

        }



        // ... other stuff you want to do in your onStart() method
    }

}
