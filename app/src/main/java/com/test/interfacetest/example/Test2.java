package com.test.interfacetest.example;

import android.content.Context;

import com.test.interfacetest.item.BaseThreeItems;

public class Test2 extends BaseThreeItems {
    public Test2(Context context) {
        super(context);
    }

    @Override
    public String getThirdName() {
        return "Test2_3";
    }

    @Override
    public String thirdItemClickResult() {
        return "Test2_3";
    }

    @Override
    public String getSecondName() {
        return "Test2_2";
    }

    @Override
    public String secondItemClickResult() {
        return "Test2_2";
    }

    @Override
    public String getName() {
        return "Test2";
    }

    @Override
    protected String itemClickResult() {
        return "Test2";
    }
}
