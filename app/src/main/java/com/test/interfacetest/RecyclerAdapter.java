package com.test.interfacetest;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.interfacetest.item.BaseItem;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<BaseItem> mData;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(String result);
    }

    public RecyclerAdapter(Context context, List<BaseItem> data, OnItemClickListener listener) {
        mContext = context;
        mData = data;
        mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent,
                false);
        return new NormalHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NormalHolder normalHolder = (NormalHolder) holder;
        normalHolder.getTextView().setText(mData.get(position).getName());
        if (mData.get(position).isRedColor()) {
            normalHolder.getTextView().setTextColor(Color.RED);
        } else {
            normalHolder.getTextView().setTextColor(Color.GRAY);
        }
        normalHolder.getTextView().setOnClickListener(v -> {
            String result = mData.get(position).onItemClick();
            if (mListener != null) {
                mListener.onItemClick(result);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setServiceConnect(SdkService service) {
        for (BaseItem item : mData) {
            item.serviceConnect(service);
        }
    }

    public static class NormalHolder extends RecyclerView.ViewHolder {
        private final TextView mTV;
        public NormalHolder(@NonNull View itemView) {
            super(itemView);
            mTV = itemView.findViewById(R.id.item_tv);
        }

        public TextView getTextView() {
            return mTV;
        }
    }
}
