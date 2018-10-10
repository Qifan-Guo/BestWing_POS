package com.qifan.bestwing_pos.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qifan.bestwing_pos.R;
import com.qifan.bestwing_pos.databinding.FragmentFishTenderShrimpBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fish_Tender_ShrimpFragment extends Fragment {
private FragmentFishTenderShrimpBinding mBinding;

    public Fish_Tender_ShrimpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentFishTenderShrimpBinding.inflate(inflater);

        return mBinding.getRoot();
    }

}
