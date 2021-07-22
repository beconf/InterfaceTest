package com.test.interfacetest.example;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.test.interfacetest.fragment.BaseItemFragment;
import com.test.interfacetest.item.BaseItem;

import java.util.ArrayList;
import java.util.List;

public class FragmentTest extends BaseItemFragment {
    @Override
    public List<BaseItem> onSubCreate(@Nullable Bundle savedInstanceState) {
        List<BaseItem> itemList = new ArrayList<>();
        createItemList(itemList);
        return itemList;
    }

    @Override
    public boolean doClickListener() {
        return true;
    }

    private void createItemList(List<BaseItem> list) {
        //test items
        Test1 test1 = new Test1(getContext());
        addItemList(list, test1);
        Test2 test2 = new Test2(getContext());
        addItemList(list, test2);
        TestClickItem testClickItem = new TestClickItem(getContext());
        testClickItem.setClickListener(mViewItemClick);
        addItemList(list, testClickItem);
    }

    @Override
    public boolean doItemClick(BaseItem item) {
        if (item instanceof TestClickItem) {
            Toast.makeText(getContext(), "Click handle in Fragment",
                    Toast.LENGTH_SHORT).show();
            //example: startActivityForResult
            return true;
        }
        return false;
    }
}
