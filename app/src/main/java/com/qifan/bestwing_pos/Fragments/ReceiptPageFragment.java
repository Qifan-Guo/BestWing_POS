package com.qifan.bestwing_pos.Fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qifan.bestwing_pos.Model.Wing;
import com.qifan.bestwing_pos.ViewModel.SharedViewModel;
import com.qifan.bestwing_pos.databinding.FragmentReceiptPageBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiptPageFragment extends Fragment {
    private FragmentReceiptPageBinding mBinding;
    private SharedViewModel mSharedViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedViewModel= ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        mSharedViewModel.getWing().observe(this, new Observer<Wing>() {
            @Override
            public void onChanged(@Nullable Wing wing) {
                String flavor = wing.getFlavor();
                int Quantity = wing.getQuantity();
                if(flavor!=null){
                    mBinding.textView.append(flavor);
                }
                if(Quantity != 0){
                    mBinding.textView.append(Integer.toString(Quantity));
                }

            }
        });

    }

    public ReceiptPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     mBinding=FragmentReceiptPageBinding.inflate(inflater);
        return mBinding.getRoot();
    }

}
