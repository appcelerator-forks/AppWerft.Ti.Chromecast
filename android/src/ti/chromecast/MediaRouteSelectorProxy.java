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
import org.appcelerator.kroll.KrollProxy;

import android.content.Context;
import android.os.Message;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastMediaControlIntent;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.TiApplication;
import android.support.v7.media.MediaRouteSelector;
import com.google.android.gms.cast.CastMediaControlIntent;
import ti.chromecast.TichromecastModule;

@Kroll.proxy(creatableInModule = MediaRouteSelectorProxy.class)
public class MediaRouteSelectorProxy extends KrollProxy {
	// Standard Debugging variables
	private static final String LCAT = "TichromecastModule";

	private MediaRouter mMediaRouter;
	private MediaRouteSelector mMediaRouteSelector;
	// private MediaRouter.Callback mMediaRouterCallback;

	private static final int MSG_FIRST_ID = KrollModule.MSG_LAST_ID + 1;
	private static final int MSG_MEDIAROUTER_START = MSG_FIRST_ID + 100;
	protected static final int MSG_LAST_ID = MSG_FIRST_ID + 999;

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

	public MediaRouteSelectorProxy(String AppID) {
		super();

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

		} catch (Exception e) {
			Log.e(LCAT, "exception: " + e.getMessage());

		}
	}
}