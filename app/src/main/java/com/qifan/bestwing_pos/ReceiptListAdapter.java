package com.qifan.bestwing_pos;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qifan.bestwing_pos.Model.Order;
import com.qifan.bestwing_pos.databinding.ReceiptItemBinding;

import java.util.List;

public class ReceiptListAdapter extends RecyclerView.Adapter<ReceiptListAdapter.itemHolder> {


    private Context mContext;
    private LiveData<List<Order>> mOrderList;
    public static int CurrentPosition = 0;

    public ReceiptListAdapter(Context context, LiveData<List<Order>> orders) {
        mContext = context;
        mOrderList = orders;
    }

    @NonNull
    @Override
    public itemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ReceiptItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.receipt_item, viewGroup, false);
        return new itemHolder(itemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull final itemHolder itemHolder, final int i) {
        Order order = mOrderList.getValue().get(i);
        itemHolder.mBinding.setOrder(order);
        itemHolder.mBinding.executePendingBindings();

        makeListItemSelectable(itemHolder, i);
        DeleteItem(itemHolder, i);
        DuplicateItem(itemHolder, i);

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

}
