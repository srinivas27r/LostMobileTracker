package com.lostmobiletracker;




import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	public static String finderCode,disablerCode;
	Button setButtonEnable,setButtonDisable;
	EditText setCodeEnable,setCodeDisable;
	SharedPreferences preferences;
	TextView credits,help;
	Editor edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setButtonEnable=(Button)findViewById(R.id.setButtonEnable);
		setCodeEnable=(EditText)findViewById(R.id.setCodeEnable);
		setButtonDisable=(Button)findViewById(R.id.setButtonDisable);
		setCodeDisable=(EditText)findViewById(R.id.setCodeDisable);
		credits=(TextView)findViewById(R.id.credits);
		help=(TextView)findViewById(R.id.help);
		help.setOnClickListener(this);
		credits.setOnClickListener(this);
		setButtonEnable.setOnClickListener(this);
		setButtonDisable.setOnClickListener(this);
		preferences =PreferenceManager.getDefaultSharedPreferences(this);
		finderCode = preferences.getString(Constants.codeEnableKey,Constants.defaultCodeEnable);
		disablerCode = preferences.getString(Constants.codeDisableKey,Constants.defaultCodeDisable); 
		if(finderCode!=null)
		{
			Log.i(Constants.APP_TAG, finderCode );

		}else
		{
			finderCode=Constants.defaultCodeEnable;
		}
		if(disablerCode!=null)
		{
			Log.i(Constants.APP_TAG, disablerCode );

		}else
		{
			disablerCode=Constants.defaultCodeDisable;
		}
		
		setCodeEnable.setText(finderCode);
		setCodeDisable.setText(disablerCode);
		
		//to set cursor at the end of text
		setCodeEnable.requestFocus();
		setCodeEnable.setSelection(finderCode.length());
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent1;
		AlertDialog.Builder msgBox  = new AlertDialog.Builder(this);
		if(v.getId()==R.id.setButtonEnable){
			if(setCodeEnable.getText().toString().trim().equalsIgnoreCase(setCodeDisable.getText().toString().trim()))
			{
			msgBox.setMessage(Constants.disableMsgBody);
			msgBox.setTitle(Constants.disableMsgTitle);
			
			}else{
			preferences = PreferenceManager.getDefaultSharedPreferences(this);
			edit = preferences.edit();
			//to clear previous values
			//edit.clear();it will clear previous key values so don't use
			//storing the value
			edit.putString(Constants.codeEnableKey, setCodeEnable.getText().toString().trim());
			Log.i(Constants.APP_TAG,"is set as enable code"+setCodeEnable.getText().toString().trim() );
			edit.commit();	
			msgBox.setMessage(Constants.enableMsgBody);
			msgBox.setTitle(Constants.enableMsgTitle);
			
			}
			msgBox.setPositiveButton("OK", null);
			msgBox.setCancelable(true);
			msgBox.create().show();
			finderCode = preferences.getString(Constants.codeEnableKey,Constants.defaultCodeEnable);
			setCodeEnable.setText(finderCode);
			setCodeEnable.setSelection(finderCode.length());
			
		}else if(v.getId()==R.id.setButtonDisable){
			if(setCodeEnable.getText().toString().trim().equalsIgnoreCase(setCodeDisable.getText().toString().trim()))
			{
			msgBox.setMessage(Constants.disableMsgBody);
			msgBox.setTitle(Constants.disableMsgTitle);
			
			}else{
			preferences = PreferenceManager.getDefaultSharedPreferences(this);
			edit = preferences.edit();
			//to clear previous values
			//edit.clear();it will clear previous key values so don't use
			//storing the value
			edit.putString(Constants.codeDisableKey, setCodeDisable.getText().toString().trim());
			Log.i(Constants.APP_TAG,"is set as disable code"+setCodeDisable.getText().toString().trim() );
			edit.commit();	
			msgBox.setMessage(Constants.enableMsgBody);
			msgBox.setTitle(Constants.enableMsgTitle);
			}
			msgBox.setPositiveButton("OK", null);
			msgBox.setCancelable(true);
			msgBox.create().show();
			disablerCode = preferences.getString(Constants.codeDisableKey,Constants.defaultCodeDisable); 
			setCodeDisable.setText(disablerCode);
			setCodeDisable.setSelection(disablerCode.length());
			


		}else if(v.getId()==R.id.credits){
			intent1=new Intent(MainActivity.this,CreditsActivity.class);
			startActivity(intent1);
			

		}
		else if(v.getId()==R.id.help){
			intent1=new Intent(MainActivity.this,HelpActivity.class);
			startActivity(intent1);
			

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onDestroy(){
		super.onDestroy();
	}

}
