package com.test.interfacetest.example;

import android.content.Context;

import com.test.interfacetest.item.BaseTwoItems;

public class Test1 extends BaseTwoItems {
    public Test1(Context context) {
        super(context);
    }

    @Override
    public String getSecondName() {
        return "Test1_2";
    }

    @Override
    public String secondItemClickResult() {
        return "Test1_2";
    }

    @Override
    public String getName() {
        return "Test1";
    }

    @Override
    protected String itemClickResult() {
        return "Test1";
    }
}
