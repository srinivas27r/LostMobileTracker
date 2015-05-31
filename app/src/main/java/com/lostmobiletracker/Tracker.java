package com.lostmobiletracker;



import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;


import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class Tracker extends Service {

	MediaPlayer player;
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		return Service.START_NOT_STICKY;
	}
	
	public void onCreate()
	{
		super.onCreate();
		Log.i(Constants.APP_TAG, "service started");
		AudioManager am;
		am= (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
		
		am.setStreamVolume(AudioManager.STREAM_MUSIC,Constants.volumeValue, 0);
		//For Normal mode
		am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		
		 player = MediaPlayer.create(this,Settings.System.DEFAULT_RINGTONE_URI);
		
			//To play an alarm
		player.start();
			//To play repeatedly
		player.setLooping(true);
		
		
	}
	public void onDestroy()
	{
		super.onDestroy();
		Log.i(Constants.APP_TAG, "service stopped");
		player.stop();
		Tracker.this.stopSelf();
		
	}
	
	
	
	
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
