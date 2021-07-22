package com.test.interfacetest;

import android.content.Context;

/**
 * This is a simulate class for AIDL
 * TODO Replace it with a work class
 */
public class SdkService {

    public interface OnServiceConnection {
        void onServiceConnected(SdkService sdkService);
    }

    public SdkService(Context context, OnServiceConnection connection) {
        connection.onServiceConnected(this);
    }

    public void disconnect() {

    }
}
