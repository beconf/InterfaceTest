package com.test.interfacetest.example;

import android.content.Context;

import com.test.interfacetest.item.BaseItem;

public class TestClickItem extends BaseItem {
    public TestClickItem(Context context) {
        super(context);
    }

    @Override
    public String getName() {
        return "Test Click";
    }

    @Override
    protected String itemClickResult() {
        return "";
    }
}
