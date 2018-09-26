package com.qifan.bestwing_pos.Fragments;



import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qifan.bestwing_pos.Model.Wing;
import com.qifan.bestwing_pos.ViewModel.SharedViewModel;
import com.qifan.bestwing_pos.databinding.FragmentOrderingPageBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderingPageFragment extends Fragment {
    private SharedViewModel mSharedViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        mSharedViewModel.setWing(new Wing());

    }


    FragmentOrderingPageBinding mBinding;
    public OrderingPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentOrderingPageBinding.inflate(inflater);
        mBinding.setViewModel(mSharedViewModel);
        return mBinding.getRoot();
    }

}
