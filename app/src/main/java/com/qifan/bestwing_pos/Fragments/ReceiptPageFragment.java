package com.qifan.bestwing_pos.Fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qifan.bestwing_pos.Model.Wing;
import com.qifan.bestwing_pos.ReceiptListAdapter;
import com.qifan.bestwing_pos.ViewModel.SharedViewModel;
import com.qifan.bestwing_pos.databinding.FragmentReceiptPageBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiptPageFragment extends Fragment {
    private FragmentReceiptPageBinding mBinding;
    private SharedViewModel mSharedViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        mSharedViewModel.getFullListWings().observe(this, new Observer<List<Wing>>() {
            @Override
            public void onChanged(@Nullable List<Wing> wings) {
                mBinding.orderList.getAdapter().notifyDataSetChanged();
            }
        });


    }

    @Nullable
    private void setUpRecycleView() {

        mBinding.orderList.setLayoutManager(new LinearLayoutManager(getContext()));
        ReceiptListAdapter receiptListAdapter = new ReceiptListAdapter(getContext(), mSharedViewModel.getWingList());
        mBinding.orderList.setAdapter(receiptListAdapter);

    }

    public ReceiptPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentReceiptPageBinding.inflate(inflater);
        mBinding.setViewModel(mSharedViewModel);
        setUpRecycleView();
        return mBinding.getRoot();
    }


}
