package com.nitzcreation.techietutor;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Student_Prof extends AppCompatActivity {
    TextView a,b,c,tutor;
    Spinner d,e,f;
    Button submit;
    String grade,subject,location,email,name,tname,taddress,tmobile,tgender,tsalary;
    String d1[]={"Select class","Class 9","Class 10","Class 11","Class 12"};
    String e1[]={"Select Subject","Maths","English","Physics","Chemistry","Biology","Computer Science","IIT-JEE","NEET","Olympiad","NTSE","Engineering Entrance Exam"};
    String f1[]={"Select Location","Chennai","Madurai","Erode","Coimbatore","Trichy","Salem","Tanjore","Vellore"};
    ArrayAdapter <String> d2;
    ArrayAdapter <String> e2;
    ArrayAdapter <String> f2;
    SQLiteDatabase db;
    Cursor c1,c2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_prof);
        a=(TextView)findViewById(R.id.textview);
        b=(TextView)findViewById(R.id.textView3);
        c=(TextView)findViewById(R.id.textView4);
        tutor=(TextView)findViewById(R.id.textView9);
        d=(Spinner)findViewById(R.id.spinner5);
        e=(Spinner)findViewById(R.id.spinner10);
        f=(Spinner)findViewById(R.id.spinner6);
        submit=(Button)findViewById(R.id.button7);
        d2=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,d1);
        e2=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,e1);
        f2=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,f1);
        d.setAdapter(d2);
        d.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg, View view, int position, long id) {
                grade=arg.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        e.setAdapter(e2);
        e.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg, View view, int position, long id) {
                subject=arg.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        f.setAdapter(f2);
        f.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg, View view, int position, long id) {
                location=arg.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        c.setText("Search Tutor Below:");
        Bundle bundle=getIntent().getExtras();
        email=bundle.getString("a1");
        db = openOrCreateDatabase("techietutor.db", MODE_PRIVATE, null);
        c1=db.rawQuery("Select * from student where email='"+email+"'",null);

        while(c1.moveToNext())
        {
            name=c1.getString(0);
            a.setText(name);
            b.setText("Hello "+name+"");

        }

        db.close();
        c1.close();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openOrCreateDatabase("techietutor.db",MODE_PRIVATE,null);
                c2=db.rawQuery("Select * from tutor where subject='"+subject+"' and location='"+location+"' and class='"+grade+"'",null);
                if(c2.getCount()==0)
                {
                    tutor.setText("Tutor Not Found");
                }
                while(c2.moveToNext())
                {
                    tname=c2.getString(0);
                    tgender=c2.getString(6);
                    tmobile=c2.getString(5);
                    taddress=c2.getString(4);
                    tsalary=c2.getString(10);

                    tutor.setText("Tutor:"+tname+" "+tgender+" "+taddress+" "+tmobile+" "+tsalary+" \n");
                }
                db.close();
                c2.close();
            }
        });
    }
}