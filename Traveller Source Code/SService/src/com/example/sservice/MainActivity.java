package com.example.sservice;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


public class MainActivity extends Activity {
	
	Button startS,stopS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        startS = (Button) findViewById(R.id.start);
        stopS  = (Button) findViewById(R.id.stop);
        
    }

    
}
