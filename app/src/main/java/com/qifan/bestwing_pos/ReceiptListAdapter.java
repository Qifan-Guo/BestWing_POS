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


    private LiveData<List<Order>> mPayList;
    private Context mContext;
    private LiveData<List<Order>> mOrderList;
    public static int CurrentPosition = 0;
    public static int SelectionListCurrentPosition = 0;
    public static int PayListCurrentPosition = 0;
    int mViewType = 0;

    public ReceiptListAdapter(Context context, LiveData<List<Order>> orders) {
        mContext = context;
        mOrderList = orders;
    }
    public ReceiptListAdapter(Context context, LiveData<List<Order>> orders, int viewType) {
        if(viewType == 1){
            mContext = context;
            mOrderList = orders;
            mViewType = viewType;
        }else if(viewType == 2){
            mContext = context;
            mPayList = orders;
            mViewType = viewType;
        }

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (mViewType){

            case 1 :
            case 2 :
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
                Order order = mOrderList.getValue().get(i);
                paymentItemHolder paymentItemHolder=(paymentItemHolder) viewHolder;
                paymentItemHolder.mBinding.setOrder(order);
                paymentItemHolder.mBinding.executePendingBindings();
                makeListItemSelectable(paymentItemHolder,i,"payment");
                break;
            case 2:
                Order payOrder = mPayList.getValue().get(i);
                paymentItemHolder paidItemHolder=(paymentItemHolder) viewHolder;
                paidItemHolder.mBinding.setOrder(payOrder);
                paidItemHolder.mBinding.executePendingBindings();
                makeListItemSelectable(paidItemHolder,i,"pay");
                break;
                default:
                    Order mOrder = mOrderList.getValue().get(i);
                    itemHolder itemHolder = (itemHolder) viewHolder;
                    itemHolder.mBinding.setOrder(mOrder);
                    itemHolder.mBinding.setViewModel(ViewModelProviders.of((FragmentActivity) mContext).get(SharedViewModel.class));
                    itemHolder.mBinding.executePendingBindings();
                    makeListItemSelectable(itemHolder, i,"default");
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



    @Override
    public int getItemCount() {
        if(mViewType == 2){
            if(mPayList != null){
                return mPayList.getValue().size();
            }else{
                return 0;
            }
        }else if (mOrderList.getValue() != null) {
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
    private void makeListItemSelectable(@NonNull final RecyclerView.ViewHolder viewHolder, final int i,String check) {
        if(check == "default"){
            itemHolder itemHolder =(itemHolder) viewHolder;
            itemHolder.mBinding.itemBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Make Item in RecyclerView Selectable, Single Selection
                    CurrentPosition = i;
                    for (Order order : mOrderList.getValue()) {
                        order.setSelect(false);
                    }
                    mOrderList.getValue().get(CurrentPosition).setSelect(true);

                    notifyDataSetChanged();

                }
            });
        }else if(check =="pay"){
            paymentItemHolder paymentItemHolder = (paymentItemHolder) viewHolder;
            paymentItemHolder.mBinding.itemBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Make Item in RecyclerView Selectable, Single Selection
                    PayListCurrentPosition = i;
                    for (Order order : mPayList.getValue()) {
                        order.setSelect(false);
                    }
                   mPayList.getValue().get(PayListCurrentPosition).setSelect(true);

                    notifyDataSetChanged();

                }
            });


        } else
            {
            paymentItemHolder paymentItemHolder = (paymentItemHolder) viewHolder;
            paymentItemHolder.mBinding.itemBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Make Item in RecyclerView Selectable, Single Selection
                    SelectionListCurrentPosition = i;
                    for (Order order : mOrderList.getValue()) {
                        order.setSelect(false);
                    }


                    mOrderList.getValue().get(SelectionListCurrentPosition).setSelect(true);

                    notifyDataSetChanged();

                }
            });
        }

    }

}
