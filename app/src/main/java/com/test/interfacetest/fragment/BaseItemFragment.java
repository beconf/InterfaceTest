package com.test.interfacetest.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.interfacetest.BaseActivity;
import com.test.interfacetest.R;
import com.test.interfacetest.RecycleViewDivider;
import com.test.interfacetest.RecyclerAdapter;
import com.test.interfacetest.SdkService;
import com.test.interfacetest.item.BaseItem;
import com.test.interfacetest.item.BaseThreeItems;
import com.test.interfacetest.item.BaseTwoItems;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseItemFragment extends BaseFragment {
    private static final String TAG = "BaseItemFragment";
    public List<BaseItem> mData;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;

    private List<Integer> getItemPosition(List<BaseItem> list, String query) {
        List<Integer> posList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().contains(query)) {
                posList.add(i);
            }
        }
        return posList;
    }

    public void addItemList(List<BaseItem> list, BaseItem bi) {
        list.add(bi);
        if (bi instanceof BaseTwoItems) {
            list.add(((BaseTwoItems) bi).getSecondItem());
        }
        if (bi instanceof BaseThreeItems) {
            list.add(((BaseThreeItems) bi).getThirdItem());
        }
    }

    @Override
    public void doCommandQuery(String query) {
        List<Integer> posList = getItemPosition(mData, query);
        if (posList.size() > 0) {
            Log.d(TAG, "Scroll to position:" + posList.get(0));
            mRecyclerView.scrollToPosition(posList.get(0));
            for(int pos : posList) {
                BaseItem bi = mData.get(pos);
                bi.setRedColor();
                mData.set(pos, bi);
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = onSubCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new RecyclerAdapter(getContext(), mData, result -> {
            BaseActivity activity = ((BaseActivity) getActivity());
            if (activity != null) {
                activity.mResultView.setText(result);
            }
        });
        if (mService != null) {
            mAdapter.setServiceConnect(mService);
        }
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getContext()));
    }

    @Override
    public void setServiceConnect(SdkService service) {
        super.setServiceConnect(service);
        if (service != null && mAdapter != null) {
            mAdapter.setServiceConnect(service);
        }
    }

    public abstract List<BaseItem> onSubCreate(@Nullable Bundle savedInstanceState);
}
