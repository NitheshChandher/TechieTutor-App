package com.nitzcreation.techietutor;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Tutor extends AppCompatActivity {
    EditText a,b,c,d,e,f;
    Button submit;
    RadioGroup g;
    RadioButton m,fm;
    Spinner u,v,w,x;
    String u3,v3,w3,x3,gender;
    String u1[]={"Select class","Class 9","Class 10","Class 11","Class 12"};
    String v1[]={"Select Subject","Maths","English","Physics","Chemistry","Biology","Computer Science","IIT-JEE","NEET","Olympiad","NTSE","Engineering Entrance Exam"};
    String w1[]={"Select Location","Chennai","Madurai","Erode","Coimbatore","Trichy","Salem","Tanjore","Vellore"};
    String x1[]={"Select Salary per Month","1000","1500","2000","2500","3000","3500","4000","4500","5000"};
    String name,email,password,cpassword,postal,mobile;
    ArrayAdapter<String> u2;
    ArrayAdapter<String> v2;
    ArrayAdapter<String> w2;
    ArrayAdapter<String> x2;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor);
        a=(EditText)findViewById(R.id.editText7);
        b=(EditText)findViewById(R.id.editText10);
        c=(EditText)findViewById(R.id.editText11);
        d=(EditText)findViewById(R.id.editText12);
        e=(EditText)findViewById(R.id.editText15);
        f=(EditText)findViewById(R.id.editText14);
        g=(RadioGroup)findViewById(R.id.radioGroup);
        m=(RadioButton)findViewById(R.id.radioButton1);
        fm=(RadioButton)findViewById(R.id.radioButton2);
        u=(Spinner)findViewById(R.id.spinner6);
        v=(Spinner)findViewById(R.id.spinner7);
        w=(Spinner)findViewById(R.id.spinner8);
        x=(Spinner)findViewById(R.id.spinner9);
        submit=(Button)findViewById(R.id.button6);
        u2=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,u1);
        v2=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,v1);
        w2=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,w1);
        x2=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,x1);

        u.setAdapter(u2);
        u.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg, View view, int position, long id) {
                u3=arg.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        v.setAdapter(v2);
        v.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg, View view, int position, long id) {
                v3=arg.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        w.setAdapter(w2);
        w.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg, View view, int position, long id) {
                w3=arg.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        x.setAdapter(x2);
        x.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg, View view, int position, long id) {
                x3=arg.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=a.getText().toString();
                email=b.getText().toString();
                password=c.getText().toString();
                cpassword=d.getText().toString();
                postal=e.getText().toString();
                mobile=f.getText().toString();

                if(m.isChecked()==true)
                {
                     gender = "male";
                }
                if(fm.isChecked()==true)
                {
                     gender = "female";
                }
                if(mobile.length()==10) {
                    if (password.equals(cpassword)) {
                        if(postal.length()==6) {
                            db = openOrCreateDatabase("techietutor.db", MODE_PRIVATE, null);
                            db.execSQL("create table if not exists tutor(name varchar,email varchar,password varchar,currentpassword varchar,postaladdress varchar,mobile varchar,gender varchar,class varchar,subject varchar,location varchar,salary varchar)");
                            db.execSQL("insert into tutor values('" + name + "','" + email + "','" + password + "','" + cpassword + "','" + postal + "','" + mobile + "','" + gender + "','" + u3 + "','" + v3 + "','" + w3 + "','" + x3 + "');");
                            Toast.makeText(Tutor.this, "Done", Toast.LENGTH_SHORT).show();
                            Intent i4 = new Intent(Tutor.this, Login.class);
                            startActivity(i4);
                            db.close();
                        }
                        else
                        {
                            Toast.makeText(Tutor.this,"Incorrect Postal Address",Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(Tutor.this,"Password and Check password does not match",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(Tutor.this,"Incorrect Mobile Number",Toast.LENGTH_LONG).show();
                }

            }
        });



    }
}