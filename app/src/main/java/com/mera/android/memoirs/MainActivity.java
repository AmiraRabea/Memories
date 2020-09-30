package com.mera.android.memoirs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button btn_login;
    TextView  tv_changepassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            // The title of this activity
        this.setTitle("Log in");

            //Reference UI components by id
        tv_changepassword=findViewById(R.id.edt_changepassword);
        final EditText edt_password;
        edt_password=findViewById(R.id.edt_password);
        btn_login=findViewById(R.id.btn_login);

             // save pasward by SharedPerferences, default value is "0000"
        SharedPreferences prefs=getSharedPreferences("user", Context.MODE_PRIVATE);
        String getPass=prefs.getString("pass","");
        if(getPass.equals(""))
        {
            //Toast.makeText(this,"fr",Toast.LENGTH_LONG).show();
            SharedPreferences.Editor editor=getSharedPreferences("user", Context.MODE_PRIVATE).edit();
            editor.putString("pass","0000");
            editor.apply();
        }

            // The action on TextView tv_changepassword
        tv_changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ChangPasswordActivity.class);

                startActivity(intent);
            }
        });

         //The action on Log in Button
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    // check if edt_password isn't empty
                if (!edt_password.getText().toString().isEmpty()){
                    SharedPreferences prefs=getSharedPreferences("user", Context.MODE_PRIVATE);
                    String getPass=prefs.getString("pass","");
                        // Nested check if the pass was saved ,equals with the user enters
                    if(getPass.equals(edt_password.getText().toString())) {
                        Intent intent = new Intent(MainActivity.this, ListVeiwActivity.class);
                        startActivity(intent);
                        finish();
                    }
                        // Else, Told me "Password not Correct"
                    else
                    {
                        Toast.makeText(MainActivity.this, "Password not Correct", Toast.LENGTH_SHORT).show();

                    }
                }
                    // If edt_password is empty , Told me "Enter Right Password"
                else {
                    Toast.makeText(MainActivity.this, "Enter Right Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
