package com.peppeuz.geolocalizzazione;


import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
        //Check if GPS is enabled
        boolean gps = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        
        if (!gps) {
        	//If GPS is disabled, do the stuff you want here
        	
        	//I choose to show a alert dialog
        	new AlertDialog.Builder(this).setTitle("GPS Disabilitato").create().show();
        	
        	//Set the provider_txt text
        	TextView providerText = (TextView) findViewById(R.id.provider_txt);
        	providerText.setText("Sto usando la connessione dati");

            //Start checking the user location base on the network
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    60000, // 1 minute interval between updates
                    100, // 100 meters between updates
                    new LocationListener() {

    					@Override
    					public void onLocationChanged(Location location) {
    						//After each update, this function is called
    						
    						//Show latitude and longitude
    			        	TextView latitudine = (TextView) findViewById(R.id.latitude_txt);
    			        	TextView longitudine = (TextView) findViewById(R.id.longitude_txt);

    			        	latitudine.setText("La latitudine è: "+String.valueOf(location.getLatitude()));
    			        	longitudine.setText("La longitudine è: "+String.valueOf(location.getLongitude()));
    					}

    					@Override
    					public void onProviderDisabled(String provider) {}

    					@Override
    					public void onProviderEnabled(String provider) {}

    					@Override
    					public void onStatusChanged(String provider, int status, Bundle extras) {}
            	
            });
        } else {
        	//Do the stuff you here when GPS is available
        	
        	//Set the provider_txt text
        	TextView providerText = (TextView) findViewById(R.id.provider_txt);
        	providerText.setText("Sto usando il GPS");

            //Start checking the user location base on GPS
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    60000, // 1 minute interval between updates
                    100, // 100 meters between updates
                    new LocationListener() {

    					@Override
    					public void onLocationChanged(Location location) {
    						//After each update, this function is called
    						
    						//Show latitude and longitude
    			        	TextView latitudine = (TextView) findViewById(R.id.latitude_txt);
    			        	TextView longitudine = (TextView) findViewById(R.id.longitude_txt);

    			          	latitudine.setText("La latitudine è: "+String.valueOf(location.getLatitude()));
    			        	longitudine.setText("La longitudine è: "+String.valueOf(location.getLongitude()));
    					
    					}

    					@Override
    					public void onProviderDisabled(String provider) {}

    					@Override
    					public void onProviderEnabled(String provider) {}

    					@Override
    					public void onStatusChanged(String provider, int status, Bundle extras) {}
            	
            });
        }
    }


}
