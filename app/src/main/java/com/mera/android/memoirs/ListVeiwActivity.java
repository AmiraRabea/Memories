package com.mera.android.memoirs;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.mera.android.memoirs.Data.MaMController;

import java.util.ArrayList;

public class ListVeiwActivity extends AppCompatActivity {
    FloatingActionButton btn_FloatingActionButton;
    ListView lv_mamoirs;
    MaMController mamController;
    long id;
    ArrayList<String> mamoirslist = new ArrayList<>();
  public  EditText edt_address,edt_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_veiw);

        // Reference UI Components by id
        edt_address=findViewById(R.id.edt_address);
        edt_text=findViewById(R.id.edt_topic);
        lv_mamoirs = findViewById(R.id.lv_mamoirs);
        btn_FloatingActionButton = findViewById(R.id.floatingActionButton1);


        mamController = new MaMController(this);
        mamController.open();
        mamoirslist = mamController.selectAllMamoris();

            // Identify ArrayAdapter from String type
        ArrayAdapter<String> memoirsAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, mamoirslist);

        lv_mamoirs.setAdapter(memoirsAdapter);

            // The action on floating button , go to anther activity
        btn_FloatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(ListVeiwActivity.this, MamoirsActivity.class);
                    startActivity(intent);
                }
            });

            // The action was happened when user selected item
        lv_mamoirs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                long value = lv_mamoirs.getCount();
                Intent intent = new Intent(getApplicationContext(),MamoirsActivity.class);
                if(lv_mamoirs.getItemIdAtPosition(i)+1 < value)
                intent.putExtra("id",lv_mamoirs.getItemIdAtPosition(i)+1);
                else
                    intent.putExtra("id",value);

                startActivity(intent);
            }
        });
    }
            // Override onCreateoptionsMenu method
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu, menu);
            return true;
        }

            // Override onOptionsItemSelected(MenuItem item) method
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

                // Check if selected item id == delete id , to delete item
            if (item.getItemId() == R.id.delete) {
                int index=mamController.deleteMamoirs(id);

                if(index!=-1){
                    Toast.makeText(getApplicationContext(),"Data Deleted",Toast.LENGTH_SHORT).show();
                }
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

        // Override onBackPressed method , to set ListViewActivity the main activity
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}

