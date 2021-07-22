package com.test.interfacetest.item;

import android.content.Context;

public abstract class BaseThreeItems extends BaseTwoItems {
    private BaseItem mThirdItem;
    public BaseThreeItems(Context context) {
        super(context);
        mThirdItem = new BaseItem(context) {
            @Override
            public String getName() {
                return getThirdName();
            }

            @Override
            protected String itemClickResult() {
                return thirdItemClickResult();
            }
        };
    }

    public BaseItem getThirdItem() {
        return mThirdItem;
    }

    //第三个测试项的名称
    public abstract String getThirdName();
    //第三个测试项的执行操作
    public abstract String thirdItemClickResult();
}
