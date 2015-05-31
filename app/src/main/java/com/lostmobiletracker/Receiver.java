package com.lostmobiletracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class Receiver extends BroadcastReceiver{

	private final String tag=" RECEIVER CLASS DEBUG ";

	SharedPreferences preferences;
	
	 
	
	@Override
	public void onReceive(Context context, Intent intent)
	{
		preferences =PreferenceManager.getDefaultSharedPreferences(context);
		MainActivity.finderCode = preferences.getString(Constants.codeEnableKey,Constants.defaultCodeEnable);
		MainActivity.disablerCode = preferences.getString(Constants.codeDisableKey,Constants.defaultCodeDisable); 
		Log.i(Constants.APP_TAG, MainActivity.finderCode );
		Log.i(Constants.APP_TAG, MainActivity.disablerCode);
		Intent serviceIntent=new Intent(context, Tracker.class);
		Bundle bundleObject=intent.getExtras();
		if(bundleObject!=null)
		{
			Log.d(tag, " BUNDLE IS NOT NULL ...");

			Object []ary= (Object[]) bundleObject.get("pdus");
			String msgStr="";
			String mobileNumber="";
			for(short i=0;i<ary.length;i++)
			{
				SmsMessage smsMessageObject=SmsMessage.createFromPdu((byte[]) ary[i]);
				msgStr+=smsMessageObject.getMessageBody();
				mobileNumber=smsMessageObject.getOriginatingAddress();
			}
			
			if(msgStr!=null)
			{
				if(msgStr.equalsIgnoreCase(MainActivity.finderCode))
				{
					
					context.startService(serviceIntent);
					
				}
				else if(msgStr.equalsIgnoreCase(MainActivity.disablerCode)) {
					Log.i(Constants.APP_TAG, "disabling");
					context.stopService(serviceIntent);
					
				}
			}

		}
		else
		{
			Log.d(tag, " BUNDLE IS NULL ...");
		}

	}



}
