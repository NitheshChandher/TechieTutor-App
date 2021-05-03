package com.nitzcreation.techietutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView t1;
    TextView t2;
    TextView t3;
    Button a;
    Button b;
    Button c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(TextView)findViewById(R.id.textview);
        t2=(TextView)findViewById(R.id.textview1);
        t3=(TextView)findViewById(R.id.textView2);
        a=(Button)findViewById(R.id.button);
        b=(Button)findViewById(R.id.button2);
        c=(Button)findViewById(R.id.button3);
        t1.setText("Welcome to TechieTutor");
        t2.setText("already existing user ?");
        t3.setText("Register for");

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(MainActivity.this,Tutor.class);
                startActivity(i1);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(MainActivity.this,Student.class);
                startActivity(i2);
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(MainActivity.this,Login.class);
                startActivity(i3);
            }
        });



    }
}
