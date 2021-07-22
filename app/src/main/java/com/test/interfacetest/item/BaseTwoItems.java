package com.test.interfacetest.item;

import android.content.Context;

public  abstract class BaseTwoItems extends BaseItem {
    private BaseItem mSecondItem;
    public BaseTwoItems(Context context) {
        super(context);
        mSecondItem = new BaseItem(context) {
            @Override
            public String getName() {
                return getSecondName();
            }

            @Override
            protected String itemClickResult() {
                return secondItemClickResult();
            }
        };
    }

    public BaseItem getSecondItem() {
        return mSecondItem;
    }

    //第二个测试项的名称
    public abstract String getSecondName();
    //第二个测试项的执行操作
    public abstract String secondItemClickResult();
}
