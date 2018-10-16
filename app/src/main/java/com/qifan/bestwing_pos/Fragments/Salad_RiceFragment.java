package com.qifan.bestwing_pos.Fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qifan.bestwing_pos.R;
import com.qifan.bestwing_pos.ViewModel.SharedViewModel;
import com.qifan.bestwing_pos.databinding.FragmentSaladRiceBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class Salad_RiceFragment extends Fragment {
    private FragmentSaladRiceBinding mBinding;
    private SharedViewModel mSharedViewModel;


    public Salad_RiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentSaladRiceBinding.inflate(inflater);
        mSharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        mBinding.setViewModel(mSharedViewModel);
        return mBinding.getRoot();
    }

}
