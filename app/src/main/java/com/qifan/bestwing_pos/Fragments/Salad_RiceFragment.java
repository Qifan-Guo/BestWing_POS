package com.qifan.bestwing_pos.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qifan.bestwing_pos.R;
import com.qifan.bestwing_pos.databinding.FragmentSaladRiceBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class Salad_RiceFragment extends Fragment {
    private FragmentSaladRiceBinding mBinding;


    public Salad_RiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentSaladRiceBinding.inflate(inflater);

        return mBinding.getRoot();
    }

}
