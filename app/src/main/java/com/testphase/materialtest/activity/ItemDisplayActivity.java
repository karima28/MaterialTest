package com.testphase.materialtest.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.testphase.materialtest.R;
import com.testphase.materialtest.adapter.AdapterListThings;
import com.testphase.materialtest.database.DbHelper;
import com.testphase.materialtest.database.ProductDatabase;
import com.testphase.materialtest.logging.L;
import com.testphase.materialtest.pojo.Thing;

import java.util.ArrayList;

/**
 * Created by deea on 18/01/16.
 */
public class ItemDisplayActivity extends AppCompatActivity implements View.OnClickListener{

    //private Toolbar toolbar;

    TextView TextViewLongDescription;
    TextView TextViewGoodnessValue;
    TextView TextItemName;
    TextView TextItemShortDescription;
    TextView TextItemLongDescription;
    TextView TextItemGoodnessValue;

    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/

        Long itemid = 0L;
        String name = "";
        String sdesc = "";
        String ldesc = "";
        Double gvalue = 0.0;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            itemid = extras.getLong("KEY_EXTRA_ID");
            name = extras.getString("KEY_EXTRA_NAME");
            sdesc = extras.getString("KEY_EXTRA_SDESC");
            ldesc = extras.getString("KEY_EXTRA_LDESC");
            gvalue = extras.getDouble("KEY_EXTRA_GVALUE");
        }


        Thing thing = new Thing(itemid, name, sdesc, ldesc, gvalue);

        setContentView(R.layout.activity_display_item);

        TextViewLongDescription = (TextView) findViewById(R.id.TextViewLongDescription);
        TextViewGoodnessValue = (TextView) findViewById(R.id.TextViewGoodnessValue);
        TextItemName = (TextView) findViewById(R.id.TextItemName);
        TextItemShortDescription = (TextView) findViewById(R.id.TextItemShortDescription);
        TextItemLongDescription = (TextView) findViewById(R.id.TextItemLongDescription);
        TextItemGoodnessValue = (TextView) findViewById(R.id.TextItemGoodnessValue);


        deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(this);

        TextItemName.setText(name);
        TextItemShortDescription.setText(sdesc);
        TextItemLongDescription.setText(ldesc);
        TextItemGoodnessValue.setText(Double.toString(gvalue));


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_sub, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            L.t(this, "You have clicked " + item.getTitle());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.deleteItem)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ProductDatabase mProductDatabase = new ProductDatabase(getApplicationContext());
                        mProductDatabase.deleteThing(getIntent().getExtras().getLong("KEY_EXTRA_ID"));
                        Toast.makeText(getApplicationContext(), "Successfully deleted item ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        AlertDialog d = builder.create();
        d.setTitle("Delete Item?");
        d.show();
    }
}
