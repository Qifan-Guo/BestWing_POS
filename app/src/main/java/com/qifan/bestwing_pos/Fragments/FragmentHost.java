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
        fragmentList.add(new Pair<String, Fragment>("Sandwiches",new SandwichFragment()));
        fragmentList.add(new Pair<String, Fragment>("Salad || Rice",new Salad_RiceFragment()));
        fragmentList.add(new Pair<String, Fragment>("Fish || Shrimp || Tender",new Fish_Tender_ShrimpFragment()));
        fragmentList.add(new Pair<String, Fragment>("EggRoll || Side Items",new Side_ItemsFragment()));

        mViewPagerAdapter = new ViewPagerAdapter(this.getFragmentManager(),fragmentList);
        mBinding.viewPager.setAdapter(mViewPagerAdapter);
        mBinding.viewPager.setOffscreenPageLimit(5);
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager);
        return mBinding.getRoot();
    }

}
