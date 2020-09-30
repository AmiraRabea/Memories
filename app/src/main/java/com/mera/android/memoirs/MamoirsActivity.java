package com.mera.android.memoirs;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.mera.android.memoirs.Data.MaMController;
import com.mera.android.memoirs.Data.Mamoir;

import java.util.Calendar;

public class MamoirsActivity extends AppCompatActivity {
    EditText edt_address, edt_topic;
    ImageButton btn_save;
    TextView tv_date;
    MaMController mamController;
    String date;
    int lastId = 0;
    long id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mamoirs);

            // The title of this activity
        this.setTitle("Memories");

        Intent extras = getIntent();
        id = extras.getLongExtra("id",0);

            // Reference UI Components by id
        edt_address = findViewById(R.id.edt_address);
        edt_topic = findViewById(R.id.edt_topic);
        tv_date = findViewById(R.id.tv_date);
        btn_save = findViewById(R.id.imageButton);
        
            // Declare The calendar jar
        Calendar calendar = Calendar.getInstance();
        String CurrentDate = java.text.DateFormat.getDateInstance(java.text.DateFormat.FULL).format(calendar.getTime());
        tv_date.setText(CurrentDate);
        
           // Create object from MaMController constructor
        mamController = new MaMController(this);
        mamController.open();       // open object

             // Declare object from cursor
        Cursor cc = mamController.selectOneMamoirs(id);
      // Toast.makeText(getBaseContext(),"id "+id,Toast.LENGTH_LONG).show();

        // Check if cursor does moveToFirst() method
        if(cc.moveToFirst())
        {
                // set set data in EditTexts
            edt_address.setText(cc.getString(1));
            edt_topic.setText(cc.getString(2));

           // Toast.makeText(getBaseContext(),id+"\n"+cc.getString(1)+"\n"+cc.getString(2),Toast.LENGTH_LONG).show();
        }

            // The action of save Button
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long index = mamController.insertMamoirs(edt_address.getText().toString(),
                        edt_topic.getText().toString(),
                        tv_date.getText().toString());
                lastId = (int) index;
                if (index != -1) {
                    Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                }
                Intent intent=new Intent(MamoirsActivity.this,ListVeiwActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

        // Override onCreateoptionsMenu method
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

        // Override onOptionsItemSelected(MenuItem item) method
    public boolean onOptionsItemSelected(MenuItem item) {

        // Check if selected item id == delete id , to delete item
        if (item.getItemId() == R.id.delete) {
            int index=mamController.deleteMamoirs(id);

            if(index!=-1){
                Toast.makeText(getApplicationContext(),"Data Deleted",Toast.LENGTH_SHORT).show();
            }
            Intent intent=new Intent(MamoirsActivity.this,ListVeiwActivity.class);
            startActivity(intent);

        }
        // Check if selected item id == updateid , to update item
        else if (item.getItemId() == R.id.update) {
            int index=mamController.updateMamoirs(id,edt_address.getText().toString(),
                    edt_topic.getText().toString(),
                    tv_date.getText().toString());

            if(index!=-1){
                Toast.makeText(getApplicationContext(),"Data Updated",Toast.LENGTH_SHORT).show();
            }

            Intent intent=new Intent(MamoirsActivity.this,ListVeiwActivity.class);
            startActivity(intent);

        }
        // if selected item id is == sharing id
        else if (item.getItemId() == R.id.sharing) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String shareBody = "Your Body here";
            String shareSub = "Your Subject here";

            intent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
            intent.putExtra(Intent.EXTRA_TEXT, shareBody);

                // Start the Share Using dialog
            startActivity(intent.createChooser(intent, "Share Using"));
        }
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mamController.close();
    }
}
