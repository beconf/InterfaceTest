package com.test.interfacetest.example;

import android.content.Context;

import com.test.interfacetest.item.BaseItem;

public class VersionItem extends BaseItem {
    public VersionItem(Context context) {
        super(context);
    }

    @Override
    public String getName() {
        return "Version";
    }

    @Override
    protected String itemClickResult() {
        return "1.0";
    }
}
