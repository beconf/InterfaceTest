package com.test.interfacetest;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.test.interfacetest.fragment.BaseFragment;
import com.test.interfacetest.fragment.FragmentUtil;
import com.test.interfacetest.example.MainFragment;

import java.util.List;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private SdkService mSdkService;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mResultView = findViewById(R.id.result);

        bindAidl();

        MainFragment mainFragment = new MainFragment();
        if (mSdkService != null) {
            mainFragment.setServiceConnect(mSdkService);
        }
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.add(R.id.main_container, mainFragment);
        ft.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
                }
                searchView.clearFocus();
                BaseFragment currentFragment = (BaseFragment) mFragmentManager
                        .findFragmentById(R.id.main_container);
                if (currentFragment != null && currentFragment.isVisible()) {
                    currentFragment.doCommandQuery(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSdkService != null) {
            mSdkService.disconnect();
        }
    }

    private void bindAidl() {
        // To bind server via aidl or other way
        new SdkService(this, sdkService -> {
            if (sdkService != null) {
                mSdkService = sdkService;
                List<BaseFragment> fragments = FragmentUtil.getInstance().getFragments();
                for (BaseFragment fragment : fragments) {
                    Log.d(TAG, "fragment:" + fragment);
                    fragment.setServiceConnect(mSdkService);
                }
            }
        });
    }
}