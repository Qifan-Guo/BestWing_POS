package com.qifan.bestwing_pos.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qifan.bestwing_pos.adapter.ViewPagerAdapter;
import com.qifan.bestwing_pos.databinding.FragmentFragmentHostBinding;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHost extends Fragment {

    private FragmentFragmentHostBinding mBinding;
    private ArrayList<Pair<String,Fragment>> fragmentList = new ArrayList<>();
    private ViewPagerAdapter mViewPagerAdapter;

    public FragmentHost() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentFragmentHostBinding.inflate(inflater);
        fragmentList.add(new Pair<String, Fragment>("Wing",new WingOrderFragment()));
        fragmentList.add(new Pair<String, Fragment>("Burger",new BurgerOrderFragment()));
        mViewPagerAdapter = new ViewPagerAdapter(this.getFragmentManager(),fragmentList);
        mBinding.viewPager.setAdapter(mViewPagerAdapter);
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager);
        return mBinding.getRoot();
    }

}
