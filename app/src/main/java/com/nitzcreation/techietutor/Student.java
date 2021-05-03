package com.nitzcreation.techietutor;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.MediaStore;
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

public class Student extends AppCompatActivity {
    EditText a,b,c,d,e;
    Button submit;
    RadioGroup f;
    RadioButton male;
    RadioButton female;
    Spinner u,v,w;
    String u1[]={"Select class","Class 9","Class 10","Class 11","Class 12"};
    String v1[]={"Select Subject","Maths","English","Physics","Chemistry","Biology","Computer Science","IIT-JEE","NEET","Olympiad","NTSE","Engineering Entrance Exam"};
    String w1[]={"Select Location","Chennai","Madurai","Erode","Coimbatore","Trichy","Salem","Tanjore","Vellore"};
    String name,email,password,cpassword,mobile,gender,u3,v3,w3;
    ArrayAdapter <String> u2;
    ArrayAdapter<String> v2;
    ArrayAdapter<String> w2;
    SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);

        a=(EditText)findViewById(R.id.editText);
        f=(RadioGroup)findViewById(R.id.radioGroup);
        male=(RadioButton)findViewById(R.id.radioButton1);
        female=(RadioButton)findViewById(R.id.radioButton2);
        b=(EditText)findViewById(R.id.editText4);
        c=(EditText)findViewById(R.id.editText5);
        d=(EditText)findViewById(R.id.editText6);
        e=(EditText)findViewById(R.id.editText8);
        u=(Spinner)findViewById(R.id.spinner);
        v=(Spinner)findViewById(R.id.spinner2);
        w=(Spinner)findViewById(R.id.spinner3);
        submit=(Button)findViewById(R.id.button5);
        u2=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,u1);
        v2=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,v1);
        w2=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,w1);
        u.setAdapter(u2);
        v.setAdapter(v2);
        w.setAdapter(w2);
        u.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg, View view, int position, long id) {
                u3=arg.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        v.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg, View view, int position, long id) {
                v3=arg.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        w.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg, View view, int position, long id) {
                w3=arg.getItemAtPosition(position).toString();
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
                            mobile=e.getText().toString();


                            if (male.isChecked() == true) {
                                gender = "male";
                            }
                            if (female.isChecked() == true) {
                                gender = "female";
                            }
                            if(mobile.length()==10) {
                                if (password.equals(cpassword)) {

                                    db = openOrCreateDatabase("techietutor.db", MODE_PRIVATE, null);
                                    db.execSQL("create table if not exists student(name varchar,email varchar,password varchar,currentpassword varchar,mobile varchar,gender varchar,class varchar,subject varchar,location varchar)");

                                    db.execSQL("insert into student values('" + name + "','" + email + "','" + password + "','" + cpassword + "','" + mobile + "','" + gender + "','" + u3 + "','" + v3 + "','" + w3 + "');");
                                    Toast.makeText(Student.this, "Done", Toast.LENGTH_LONG).show();
                                    db.close();
                                    Intent i5 = new Intent(Student.this, Login.class);
                                    startActivity(i5);

                                }
                                else
                                {
                                    Toast.makeText(Student.this,"Password and Check password does not match",Toast.LENGTH_LONG).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(Student.this,"Incorrect Mobile Number",Toast.LENGTH_LONG).show();
                            }
                            }




                    });




    }
}