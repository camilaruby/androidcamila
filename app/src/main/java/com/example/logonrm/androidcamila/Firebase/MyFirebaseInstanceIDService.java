package com.example.logonrm.androidcamila.Firebase;

import com.google.firebase.iid.FirebaseInstanceIdService;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {

        //update token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "New token: " + refreshedToken);
        sendRegistrationToServer(refreshedToken);

    }

    public void sendRegistrationToServer(String token){

        Log.d("TOKEN", token );
    }


}
