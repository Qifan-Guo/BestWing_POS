package com.qifan.bestwing_pos;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qifan.bestwing_pos.Model.Wing;
import com.qifan.bestwing_pos.databinding.ReceiptItemBinding;

import java.util.List;

public class ReceiptListAdapter extends RecyclerView.Adapter<ReceiptListAdapter.itemHolder> {


    private Context mContext;
    private LiveData<List<Wing>> mWings;
    public static int CurrentPosition;




    public ReceiptListAdapter(Context context, LiveData<List<Wing>> wings) {
        mContext = context;
        mWings = wings;
    }

    @NonNull
    @Override
    public itemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ReceiptItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
               R.layout.receipt_item,viewGroup,false );
        return new itemHolder(itemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull itemHolder itemHolder, int i) {
        CurrentPosition=i;
        Wing wing = mWings.getValue().get(i);
        itemHolder.mBinding.setWing(wing);
        itemHolder.mBinding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        if(mWings.getValue()!= null){
            return mWings.getValue().size();
        }else
        {
            return 0;
        }
    }


    public class itemHolder extends RecyclerView.ViewHolder {
        ReceiptItemBinding mBinding;
        public itemHolder(@NonNull View itemView) {
            super(itemView);
            mBinding= DataBindingUtil.bind(itemView);
        }
    }

}
