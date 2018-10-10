package com.qifan.bestwing_pos.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qifan.bestwing_pos.R;
import com.qifan.bestwing_pos.databinding.FragmentSideItemsBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class Side_ItemsFragment extends Fragment {

 private FragmentSideItemsBinding mBinding;
    public Side_ItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = FragmentSideItemsBinding.inflate(inflater);

        return mBinding.getRoot();
    }

}
