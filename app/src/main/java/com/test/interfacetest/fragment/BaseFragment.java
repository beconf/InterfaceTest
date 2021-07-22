package com.test.interfacetest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.test.interfacetest.R;
import com.test.interfacetest.SdkService;
import com.test.interfacetest.item.BaseItem;
import com.test.interfacetest.item.OnViewItemClick;

public abstract class BaseFragment extends Fragment {

    private static final String TAG = "BaseFragment";
    public SdkService mService;
    public OnViewItemClick mViewItemClick;

    public void setServiceConnect(SdkService service) {
        mService = service;
    }

    public boolean doItemClick(BaseItem item) {
        return false;
    }

    //Fragment receive the click action
    public boolean doClickListener() {
        return false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (doClickListener()) {
            mViewItemClick = item -> {
                if (!doItemClick(item)) {
                    //Default is switch fragment
                    BaseFragment fragment = item.getFragment();
                    Log.d(TAG, "fragment:" + fragment);
                    if (fragment != null) {
                        switchFragment(fragment);
                    }
                }
            };
        }
    }

    public void switchFragment(BaseFragment fragment) {
        if (fragment != null) {
            fragment.setServiceConnect(mService);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.main_container, fragment);
            ft.addToBackStack(null).commit();
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        FragmentUtil.getInstance().addFragment(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        FragmentUtil.getInstance().removeFragment(this);
    }

    public abstract void doCommandQuery(String query);
}
