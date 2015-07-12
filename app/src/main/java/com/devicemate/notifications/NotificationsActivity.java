package com.devicemate.notifications;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.devicemate.R;
import com.devicemate.core.FdActivity;


public class NotificationsActivity extends Activity {

	private Button button;
	private SeekBar seekBar ;
	private EditText editText ;
    private TextView mInfoType;
    private boolean saved;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_mate_notifications);

        saved = false;

		seekBar = (SeekBar) findViewById(R.id.myseekBar);
		editText = (EditText) findViewById(R.id.progressValue);

        //todo process
        SharedPreferences sharedPref = getSharedPreferences("com.DriveMate", MODE_PRIVATE);

        if(sharedPref.getInt(getString(R.string.isMaxSpeed), 0)==1) {
            mInfoType= (TextView)findViewById(R.id.textView5);
            mInfoType.setText("Max speed ");
        }
        else if(sharedPref.getInt(getString(R.string.isContinuousBlind), 0)==1) {
            mInfoType= (TextView)findViewById(R.id.textView5);
            mInfoType.setText("Continuous Blind");
        }
        else if(sharedPref.getInt(getString(R.string.isMaxBlinkRate), 0)==1) {
            mInfoType= (TextView)findViewById(R.id.textView5);
            mInfoType.setText("Max Blink Rate");
        }
        else if(sharedPref.getInt(getString(R.string.isNormalReturn), 0)==1) {
            mInfoType= (TextView)findViewById(R.id.textView5);
            mInfoType.setText("Normal Return");
        }


	}

	public void addListenerOnButton() {

		final Context context = this;

		button = (Button) findViewById(R.id.nextbutton);

		button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (saved) {
                    Intent intent = new Intent(context, FdActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    showDialog();
                }
            }

        });
	}

    public void showDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("You need to set the time first.")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    public void saveMessage(View view){

        EditText editText = (EditText)findViewById(R.id.speedField);
        String speedText = editText.getText().toString();

        float metersPerSecond =( Float.parseFloat(speedText) * 5f) /18f;

        SharedPreferences sharedPreferences = getSharedPreferences("com.DriveMate",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(R.string.delayKey),seekBar.getProgress());
        editor.commit();
        editor.putFloat(getString(R.string.speedValues), metersPerSecond);
        editor.commit();

        saved =true;

    }

}