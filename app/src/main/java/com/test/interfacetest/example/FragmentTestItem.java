package com.test.interfacetest.example;

import android.content.Context;

import com.test.interfacetest.item.BaseItem;

public class FragmentTestItem extends BaseItem {
    public FragmentTestItem(Context context) {
        super(context);
    }

    @Override
    public String getName() {
        return "FragmentTest";
    }

    @Override
    protected String itemClickResult() {
        return "";
    }
}
