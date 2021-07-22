package com.test.interfacetest.example;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.test.interfacetest.fragment.BaseItemFragment;
import com.test.interfacetest.item.BaseItem;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends BaseItemFragment {

    @Override
    public List<BaseItem> onSubCreate(@Nullable Bundle savedInstanceState) {
        List<BaseItem> mainItemList = new ArrayList<>();
        createMainItemList(mainItemList);
        return mainItemList;
    }

    @Override
    public boolean doClickListener() {
        return true;
    }

    private void createMainItemList(List<BaseItem> list) {
        //Main test items
        VersionItem version = new VersionItem(getContext());
        addItemList(list, version);
        Test1 test1 = new Test1(getContext());
        addItemList(list, test1);
        Test2 test2 = new Test2(getContext());
        addItemList(list, test2);
        FragmentTestItem fti = new FragmentTestItem(getContext());
        FragmentTest ft = new FragmentTest();
        fti.setFragment(ft, item -> mViewItemClick.onItemClick(item));
        addItemList(list, fti);
    }
}
