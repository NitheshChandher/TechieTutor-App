package com.nitzcreation.techietutor;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText a1,b1;
    Button signin;
    String email,password,temail,tpassword,semail,spassword;
    SQLiteDatabase db;
    Cursor c1,c2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        a1=(EditText)findViewById(R.id.editText2);
        b1=(EditText)findViewById(R.id.editText3);
        signin=(Button)findViewById(R.id.button4);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=a1.getText().toString();
                password=b1.getText().toString();

               db = openOrCreateDatabase("techietutor.db", MODE_PRIVATE, null);
                c1=db.rawQuery("Select * from tutor where email='"+email+"'",null);
                c2=db.rawQuery("Select * from student where email='"+email+"'",null);
                if(c2.getCount()==0 && c1.getCount()==0){
                    Toast.makeText(getApplicationContext(), "ID :"+email+" "+"NotFound",Toast.LENGTH_LONG).show();
                }
                while(c1.moveToNext())
                {
                    temail=c1.getString(1);
                    tpassword=c1.getString(2);
                    if(email.equals(temail)&&password.equals(tpassword))
                    {
                        Bundle bundle=new Bundle();
                        bundle.putString("b1",email);
                        Intent i6 =new Intent(Login.this,Tutor_Prof.class);
                        i6.putExtras(bundle);
                        startActivity(i6);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Incorrect Password",Toast.LENGTH_LONG).show();
                    }
                }
                while(c2.moveToNext()) {
                    semail = c2.getString(1);
                    spassword = c2.getString(2);
                    if(email.equals(semail)&&password.equals(spassword))
                    {
                        Bundle bundle1=new Bundle();
                        bundle1.putString("a1",email);
                        Intent i7 = new Intent(Login.this,Student_Prof.class);
                        i7.putExtras(bundle1);
                        startActivity(i7);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Incorrect Password",Toast.LENGTH_LONG).show();
                    }
                }

                c2.close();
               db.close();
            }
        });
    }
}
