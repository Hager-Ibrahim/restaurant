package com.example.restaurant.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class BroadcastReceiverImp extends BroadcastReceiver {

    private BroadcastInterface broadcastInterface;
    private boolean isConnected;
    public BroadcastReceiverImp(BroadcastInterface broadcastInterface) {
        this.broadcastInterface = broadcastInterface;
    }
    public BroadcastReceiverImp(){}

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            boolean noConnectivity = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
            );
            if (noConnectivity) {
                if(broadcastInterface != null)
                broadcastInterface.internetDisconnected();
                isConnected =false;
            } else {
                isConnected = true;
                if(broadcastInterface != null)
                    broadcastInterface.internetConnected();
            }
        }
    }

    public boolean isConnected(){
        return isConnected;
    }
}
