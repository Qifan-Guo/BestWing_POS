package com.qifan.bestwing_pos.Fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qifan.bestwing_pos.R;
import com.qifan.bestwing_pos.ReceiptListAdapter;
import com.qifan.bestwing_pos.ViewModel.SharedViewModel;
import com.qifan.bestwing_pos.databinding.FragmentPaymentBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends DialogFragment {
    final int PAYMENT_VIEW_TYPE = 1;

private FragmentPaymentBinding mBinding;
    public PaymentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedViewModel sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        mBinding =FragmentPaymentBinding.inflate(inflater);
        mBinding.selectionList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        mBinding.selectionList.setAdapter(new ReceiptListAdapter(this.getContext(),sharedViewModel.getOrderList(),PAYMENT_VIEW_TYPE));

        return mBinding.getRoot();
    }
    @Override
    public void onStart()
    {
        super.onStart();

        // safety check
        if (getDialog() == null)
            return;

        int dialogWidth = 2400;
        int dialogHeight = 1200;

        getDialog().getWindow().setLayout(dialogWidth, dialogHeight);

        // ... other stuff you want to do in your onStart() method
    }

}
