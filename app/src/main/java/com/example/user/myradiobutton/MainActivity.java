package com.example.user.myradiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView _textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RudioButton _volumControlView = (RudioButton) findViewById(R.id.volume_control_view);
        _volumControlView.invalidate();
        Toast.makeText(getApplicationContext(),String.valueOf( _volumControlView.getCurrentValue()),Toast.LENGTH_SHORT);

    }
}
