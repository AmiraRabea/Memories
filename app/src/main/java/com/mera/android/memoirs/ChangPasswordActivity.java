package com.mera.android.memoirs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mera.android.memoirs.Data.MaMController;

public class ChangPasswordActivity extends AppCompatActivity {
    Button btn_ok;
    EditText currnt, New;
    int id=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chang_password);

            // The title of this activity
        this.setTitle("Change password");
            // Reference UI components by id
        btn_ok=findViewById(R.id.btn_ok);
        currnt= findViewById(R.id.edt_current);
        New = findViewById(R.id.edt_new);

            // The action of ok button
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs=getSharedPreferences("user", Context.MODE_PRIVATE);
                String getPass=prefs.getString("pass","");
                    //Check if the enterd password equals current passward
                if(getPass.equals(currnt.getText().toString())) {
                    SharedPreferences.Editor editor = getSharedPreferences("user", Context.MODE_PRIVATE).edit();
                    editor.putString("pass", New.getText().toString());
                    editor.apply();
                    Intent intent = new Intent(ChangPasswordActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(ChangPasswordActivity.this,
                            "current password not correct", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
