package com.qifan.bestwing_pos.Fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qifan.bestwing_pos.R;
import com.qifan.bestwing_pos.ViewModel.SharedViewModel;
import com.qifan.bestwing_pos.databinding.FragmentSandwichBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SandwichFragment extends Fragment {
private SharedViewModel mSharedViewModel;
    private FragmentSandwichBinding mBinding;
    public SandwichFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentSandwichBinding.inflate(inflater);
        mSharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        mBinding.setViewModel(mSharedViewModel);
        return mBinding.getRoot();
    }

}
