/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package ti.chromecast;

import org.appcelerator.kroll.KrollModule;
import java.util.HashMap;
import java.util.ArrayList;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
//import org.appcelerator.kroll.common.TiConfig;
import org.appcelerator.kroll.KrollFunction;
import android.content.ContentValues;

import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.util.TiRHelper;

//import android.media.MediaRouter;
import android.support.v7.media.MediaControlIntent;
import android.support.v7.app.MediaRouteActionProvider;
import android.support.v7.app.MediaRouteButton;
//import android.support.v7.app.MediaRouteDialogFactory;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter.RouteInfo;
import android.support.v7.media.MediaRouter.Callback;

import android.content.Context;
import android.os.Message;

import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.Cast.ApplicationConnectionResult;
import com.google.android.gms.cast.Cast.MessageReceivedCallback;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;

import android.support.v7.app.MediaRouteDialogFactory;

@Kroll.module(name = "Tichromecast", id = "ti.chromecast")
public class TichromecastModule extends KrollModule {

	// Standard Debugging variables
	private static final String LCAT = "TichromecastModule";
	private MediaRouter mMediaRouter;
	private MediaRouteSelector mMediaRouteSelector;
	// private MediaRouter.Callback mMediaRouterCallback;
	private CastDevice mSelectedDevice;
	private KrollFunction krollRouteCallback;
	private KrollFunction krollAppCallback;


	private static final int MSG_FIRST_ID = KrollModule.MSG_LAST_ID + 1;
	private static final int MSG_MEDIAROUTER_START = MSG_FIRST_ID + 100;
	protected static final int MSG_LAST_ID = MSG_FIRST_ID + 999;

	public TichromecastModule() {
		super();
	}

	// http://stackoverflow.com/questions/17842845/how-do-i-discover-a-chromecast-device-using-android
	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app) {
	}

	/**
	 * message handler
	 * 
	 * @param message
	 */
	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case MSG_MEDIAROUTER_START: {
			Context context = TiApplication.getInstance()
					.getApplicationContext();
			mMediaRouter = MediaRouter.getInstance(context);
			return true;
		}
		default: {
			return super.handleMessage(msg);
		}
		}
	}

	@Kroll.method()
	public MediaRouteSelector createDeviceManager(String AppID) {
		Log.d(LCAT,
				"========================\nstartMediaRouter called with AppID="
						+ AppID);
		// in force run main thread:
		getMainHandler().obtainMessage(MSG_MEDIAROUTER_START).sendToTarget();
		// getting appid from cromecast receiver:
		String mAppID = (AppID.equals("DEFAULT_MEDIA_RECEIVER")) ? mAppID = CastMediaControlIntent.DEFAULT_MEDIA_RECEIVER_APPLICATION_ID
				: AppID;
		Log.d(LCAT, "AppId=" + mAppID);
		// Configure Cast device discovery
		try {
			MediaRouteSelector mMediaRouteSelector = new MediaRouteSelector.Builder()
					.addControlCategory(
							CastMediaControlIntent.categoryForCast(mAppID))
					.build();
			Log.d(LCAT, "MediaRouteSelector created");
		} catch (Exception e) {
			Log.e(LCAT, "exception: " + e.getMessage());
			return null;
		}
		// later will come:
		// java.lang.NoSuchFieldError: No static field
		// mr_user_route_category_name of type I in class
		return MediaRouteSelector;
	}

	

}

// Vorbild:
// https://github.com/googlecast/CastHelloText-android/blob/master/src/com/example/casthelloworld/MainActivity.java