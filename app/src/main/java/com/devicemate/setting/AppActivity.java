package com.devicemate.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;


import com.devicemate.R;
import com.devicemate.core.FdActivity;


public class AppActivity extends Activity {

	Button button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_mate_setting);
		addListenerOnButton();
	}

	public void addListenerOnButton() {

		final Context context = this;

		button = (Button) findViewById(R.id.button1);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(context, FdActivity.class);
                startActivity(intent);   

			}

		});

	}

}