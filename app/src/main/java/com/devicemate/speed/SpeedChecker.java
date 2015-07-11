package com.devicemate.speed;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.devicemate.R;

import java.util.Formatter;
import java.util.Locale;

public class SpeedChecker extends Activity implements IBaseGpsListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_mate_speed);
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		this.updateSpeed(null);
		
		CheckBox chkUseMetricUntis = (CheckBox) this.findViewById(R.id.chkMetricUnits);
		chkUseMetricUntis.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				SpeedChecker.this.updateSpeed(null);
			}
		});
	}
	
	public void finish()
	{
		super.finish();
		System.exit(0);
	}

	private void updateSpeed(CLocation location) {
		// TODO Auto-generated method stub
		float nCurrentSpeed = 0;
		
		if(location != null)
		{
			location.setUseMetricunits(this.useMetricUnits());
			nCurrentSpeed = location.getSpeed();
		}
		
		Formatter fmt = new Formatter(new StringBuilder());
		fmt.format(Locale.US, "%5.1f", nCurrentSpeed);
		String strCurrentSpeed = fmt.toString();
		strCurrentSpeed = strCurrentSpeed.replace(' ', '0');
		
		String strUnits = "miles/hour";
		if(this.useMetricUnits())
		{
			strUnits = "meters/second";
		}
		
		TextView txtCurrentSpeed = (TextView) this.findViewById(R.id.txtCurrentSpeed);
		txtCurrentSpeed.setText(strCurrentSpeed + " " + strUnits);
	}

	private boolean useMetricUnits() {
		// TODO Auto-generated method stub
		CheckBox chkUseMetricUnits = (CheckBox) this.findViewById(R.id.chkMetricUnits);
		return chkUseMetricUnits.isChecked();
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		if(location != null)
		{
			CLocation myLocation = new CLocation(location, this.useMetricUnits());
			this.updateSpeed(myLocation);
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGpsStatusChanged(int event) {
		// TODO Auto-generated method stub
		
	}
	
	

}
