package com.qifan.bestwing_pos.Fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qifan.bestwing_pos.R;
import com.qifan.bestwing_pos.ViewModel.SharedViewModel;
import com.qifan.bestwing_pos.databinding.FragmentFishTenderShrimpBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fish_Tender_ShrimpFragment extends Fragment {
private FragmentFishTenderShrimpBinding mBinding;
    private SharedViewModel mSharedViewModel;


    public Fish_Tender_ShrimpFragment() {
        // Required empty public constructor
    }

//TODO TENDER ADDONE FEATURE NOT WORKING
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentFishTenderShrimpBinding.inflate(inflater);
        mSharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        mBinding.setViewModel(mSharedViewModel);

        return mBinding.getRoot();
    }

}
