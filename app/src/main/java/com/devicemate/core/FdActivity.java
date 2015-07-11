package com.devicemate.core;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.devicemate.R;


public class FdActivity extends Activity {

	Button button;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_mate_core);
	}

}