package com.notification_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title=(TextView)findViewById(R.id.title);
        /*from here background service starts*/
        startService(new Intent(this,Notification_Service.class));

        /*here we check which apk is running*/

        if(Utility.FLAVOUR_TYPE==Utility.Variant.PRODUCTION)
        {
            title.setText(getResources().getString(R.string.production));
//            Toast.makeText(MainActivity.this, "Production", Toast.LENGTH_SHORT).show();
        }
        else
        {
            title.setText(getResources().getString(R.string.qa));
//            Toast.makeText(MainActivity.this, "QA", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
