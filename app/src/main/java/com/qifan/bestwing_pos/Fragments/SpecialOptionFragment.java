package com.qifan.bestwing_pos.Fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qifan.bestwing_pos.R;
import com.qifan.bestwing_pos.ViewModel.SharedViewModel;
import com.qifan.bestwing_pos.databinding.SpecialOptionsBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialOptionFragment extends DialogFragment {


    public SpecialOptionFragment() {
        // Required empty public constructor
    }

    private SpecialOptionsBinding mBinding;
    private SharedViewModel sharedViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        mBinding = SpecialOptionsBinding.inflate(inflater);
        mBinding.setViewModel(sharedViewModel);
        mBinding.closeOptionWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return mBinding.getRoot();
    }

}
