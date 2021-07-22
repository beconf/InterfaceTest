package com.test.interfacetest.fragment;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FragmentUtil {
    private static FragmentUtil mFragmentUtil;
    private List<BaseFragment> mFragments = new ArrayList<>();
    private FragmentUtil() {
    }

    public static FragmentUtil getInstance() {
        if (mFragmentUtil == null) {
            mFragmentUtil = new FragmentUtil();
        }
        return mFragmentUtil;
    }

    //用于获取SdkService连接通知的Fragment
    public void addFragment(BaseFragment bf) {
        Log.d("FragmentUtil", "addFragment:" + bf);
        mFragments.add(bf);
    }

    public void removeFragment(BaseFragment bf) {
        Log.d("FragmentUtil", "removeFragment:" + bf);
        if (mFragments.contains(bf)) {
            mFragments.remove(bf);
        }
    }

    public List<BaseFragment> getFragments() {
        return mFragments;
    }

}
