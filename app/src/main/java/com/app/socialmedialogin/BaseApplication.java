package com.app.socialmedialogin;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.auth0.core.Token;
import com.auth0.core.UserProfile;
import com.auth0.lock.Lock;
import com.auth0.lock.LockContext;

public class BaseApplication extends Application {

    private static final String TAG = BaseApplication.class.getCanonicalName();

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            UserProfile userProfile = intent.getParcelableExtra(Lock.AUTHENTICATION_ACTION_PROFILE_PARAMETER);
            Token accessToken = intent.getParcelableExtra(Lock.AUTHENTICATION_ACTION_TOKEN_PARAMETER);
            Log.i(TAG, "User " + userProfile.getName() + " logged in");

            Intent loggedInIntent = new Intent(getApplicationContext(), LoggedinActivity.class);
            loggedInIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            loggedInIntent.putExtra("name", userProfile.getName());
            loggedInIntent.putExtra("email", userProfile.getEmail());
            loggedInIntent.putExtra("id", userProfile.getId());
            loggedInIntent.putExtra("nickname", userProfile.getNickname());
            loggedInIntent.putExtra("picture", userProfile.getPictureURL());
            loggedInIntent.putExtra("date", userProfile.getCreatedAt());
            loggedInIntent.putExtra("token", accessToken.getAccessToken());
            startActivity(loggedInIntent);
        }
    };

    public void onCreate() {
        super.onCreate();

        LockContext.configureLock(new Lock.Builder().loadFromApplication(this).closable(true));

        final LocalBroadcastManager broadcastManager = LocalBroadcastManager
                .getInstance(getApplicationContext());
        broadcastManager.registerReceiver(receiver, new IntentFilter(Lock.AUTHENTICATION_ACTION));
    }
}
