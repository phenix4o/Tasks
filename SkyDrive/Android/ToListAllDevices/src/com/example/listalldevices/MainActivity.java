package com.example.listalldevices;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	private BluetoothAdapter adapter;
	private List<String> list;
	private ArrayAdapter<String> ad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		adapter = BluetoothAdapter.getDefaultAdapter();
		list = new ArrayList<String>();
	
		ListView bla = (ListView) findViewById(R.id.list);
		ad = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list);
		bla.setAdapter(ad);
		adapter.startDiscovery();
		final BroadcastReceiver deviceReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				String action = intent.getAction();
				// When discovery finds a device
				if (BluetoothDevice.ACTION_FOUND.equals(action)) {
					// Get the BluetoothDevice object from the Intent
					BluetoothDevice device = intent
							.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
					list.add(device.getName().toString());
					ad.notifyDataSetChanged();
				}

			}
		};
		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND); 
		filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		registerReceiver(deviceReceiver, filter); 

	}

}
