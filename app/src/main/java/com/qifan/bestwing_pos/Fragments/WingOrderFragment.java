package com.qifan.bestwing_pos.Fragments;



import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qifan.bestwing_pos.Model.Order;
import com.qifan.bestwing_pos.ViewModel.SharedViewModel;
import com.qifan.bestwing_pos.databinding.FragmentWingOrderBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WingOrderFragment extends Fragment {
    private SharedViewModel mSharedViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        MutableLiveData<List<Order>> orderList = new MutableLiveData<>();
        //Initialize the list with one item
        List<Order> mOrder = new ArrayList<>();
        Order order =new Order("","",0.0);
        mOrder.add(order);
        orderList.setValue(mOrder);
        //need change later
        mSharedViewModel.setOrderList(orderList);

    }


    FragmentWingOrderBinding mBinding;
    public WingOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentWingOrderBinding.inflate(inflater);

        mBinding.setViewModel(mSharedViewModel);
        return mBinding.getRoot();
    }

}
