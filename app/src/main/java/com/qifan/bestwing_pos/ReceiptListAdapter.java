package com.qifan.bestwing_pos;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qifan.bestwing_pos.Model.Order;
import com.qifan.bestwing_pos.ViewModel.SharedViewModel;
import com.qifan.bestwing_pos.databinding.PaymentItemBinding;
import com.qifan.bestwing_pos.databinding.ReceiptItemBinding;

import java.util.List;

public class ReceiptListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private LiveData<List<Order>> mOrderList;
    public static int CurrentPosition = 0;
    int mViewType = 0;

    public ReceiptListAdapter(Context context, LiveData<List<Order>> orders) {
        mContext = context;
        mOrderList = orders;
    }
    public ReceiptListAdapter(Context context, LiveData<List<Order>> orders, int viewType) {
        mContext = context;
        mOrderList = orders;
        mViewType = viewType;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (mViewType){

            case 1:
                PaymentItemBinding paymentItemBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.payment_item,viewGroup,false);
                return new paymentItemHolder(paymentItemBinding.getRoot());
            default:
                ReceiptItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                        R.layout.receipt_item, viewGroup, false);
                return new itemHolder(itemBinding.getRoot());

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (mViewType){
            case 1:
                itemHolder itemHolder = (ReceiptListAdapter.itemHolder) viewHolder;
                Order order = mOrderList.getValue().get(i);
                itemHolder.mBinding.setOrder(order);
                itemHolder.mBinding.setViewModel(ViewModelProviders.of((FragmentActivity) mContext).get(SharedViewModel.class));
                itemHolder.mBinding.executePendingBindings();
                makeListItemSelectable(itemHolder, i);
                DeleteItem(itemHolder, i);
                DuplicateItem(itemHolder, i);

        }

    }


    private void DuplicateItem(itemHolder itemHolder, final int i) {
        itemHolder.mBinding.duplicateItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Have to do this otherwise it will reference to the same object when selected
                Order order = mOrderList.getValue().get(i);
                Order duplicateWing = new Order();
                duplicateWing.setItemName(order.getItemName());
                duplicateWing.setItemDetail(order.getItemDetail());
                duplicateWing.setSideItem(order.getSideItem());
                duplicateWing.setSubtotal(order.getSubtotal(),"other");
                mOrderList.getValue().add(i,duplicateWing);
                notifyItemInserted(i);

            }
        });
    }

    private void DeleteItem(@NonNull final itemHolder itemHolder, final int i) {
        itemHolder.mBinding.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOrderList.getValue().remove(i);
                notifyDataSetChanged();
            }
        });
    }

    private void makeListItemSelectable(@NonNull final itemHolder itemHolder, final int i) {
        itemHolder.mBinding.itemBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Make Item in RecyclerView Selectable, Single Selection
                CurrentPosition = i;
                for (Order order : mOrderList.getValue()) {
                    order.setSelect(false);
                }
                mOrderList.getValue().get(CurrentPosition).setSelect(true);

                Log.d("position", "onClick " + CurrentPosition);
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        if (mOrderList.getValue() != null) {
            return mOrderList.getValue().size();
        } else {
            return 0;
        }
    }


    public class itemHolder extends RecyclerView.ViewHolder {
        ReceiptItemBinding mBinding;

        public itemHolder(@NonNull View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }

    public class paymentItemHolder extends RecyclerView.ViewHolder {
        PaymentItemBinding mBinding;

        public paymentItemHolder(@NonNull View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }

}
