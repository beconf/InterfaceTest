package com.test.interfacetest.item;

import android.content.Context;
import android.widget.Toast;

import com.test.interfacetest.R;
import com.test.interfacetest.SdkService;
import com.test.interfacetest.fragment.BaseFragment;

public abstract class BaseItem {
    private static final String TAG = "BaseItem";
    protected Context mContext;
    public SdkService mSdkService = null;
    private boolean redColor = false;
    private BaseFragment mFragment = null;
    private OnViewItemClick mClick;

    public BaseItem(Context context) {
        mContext = context;
    }

    public boolean isRedColor() {
        return redColor;
    }

    public void setRedColor() {
        redColor = true;
    }

    public void setFragment(BaseFragment fragment, OnViewItemClick click) {
        mFragment = fragment;
        mClick = click;
    }

    public void setClickListener(OnViewItemClick click) {
        mClick = click;
    }

    public BaseFragment getFragment() {
        return mFragment;
    }

    public void serviceConnect(SdkService service) {
        mSdkService = service;
    }

    public String onItemClick() {
        if (mSdkService == null) {
            return mContext.getString(R.string.service_null);
        } else {
            if (mClick != null) {
                mClick.onItemClick(this);
                return "";
            }
            return itemClickResult();
        }
    }

    public String showResult(int resId, String value) {
        return mContext.getString(resId) + " : " + value;
    }

    public String showResult(int resId, boolean value) {
        return mContext.getString(resId) + " : " +
                (value ? mContext.getString(R.string.result_success)
                        : mContext.getString(R.string.result_fail));
    }

    public void showResultToast(int resId, String value) {
        String result = mContext.getString(resId) + " : " + value;
        Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
    }

    public String showOpenCloseResult(int resId, boolean value) {
        return mContext.getString(resId)  + " : " +
                (value ? mContext.getString(R.string.result_open)
                        : mContext.getString(R.string.result_close));
    }

    public String showYesNoResult(int resId, boolean value) {
        return mContext.getString(resId) + " : " +
                (value ? mContext.getString(R.string.result_yes)
                        : mContext.getString(R.string.result_no));
    }

    //测试项的名称
    public abstract String getName();
    //测试项的执行操作
    protected abstract String itemClickResult();
}
