package com.testphase.materialtest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.testphase.materialtest.R;
import com.testphase.materialtest.adapter.AdapterListThings;
import com.testphase.materialtest.database.ProductDatabase;
import com.testphase.materialtest.logging.L;
import com.testphase.materialtest.pojo.Thing;

import java.util.ArrayList;

/**
 * Created by deea on 18/01/16.
 */
public class ItemDisplayActivity extends AppCompatActivity{

    ProductDatabase mProductDatabase;

    //ArrayList<Thing> listThings = new ArrayList<>();

    TextView TextViewName;
    TextView TextViewShortDescription;
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

        String name = "";
        String sdesc = "";
        String ldesc = "";
        Double gvalue = 0.0;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("KEY_EXTRA_NAME");
            sdesc = extras.getString("KEY_EXTRA_SDESC");
            ldesc = extras.getString("KEY_EXTRA_LDESC");
            gvalue = extras.getDouble("KEY_EXTRA_GVALUE");
        }


        setContentView(R.layout.activity_display_item);

        TextViewName = (TextView) findViewById(R.id.TextViewName);
        TextViewShortDescription = (TextView) findViewById(R.id.TextViewShortDescription);
        TextViewLongDescription = (TextView) findViewById(R.id.TextViewLongDescription);
        TextViewGoodnessValue = (TextView) findViewById(R.id.TextViewGoodnessValue);
        TextItemName = (TextView) findViewById(R.id.TextItemName);
        TextItemShortDescription = (TextView) findViewById(R.id.TextItemShortDescription);
        TextItemLongDescription = (TextView) findViewById(R.id.TextItemLongDescription);
        TextItemGoodnessValue = (TextView) findViewById(R.id.TextItemGoodnessValue);


        deleteButton = (Button) findViewById(R.id.deleteButton);


        //Thing thing = new Thing();

        //deleteButton.setOnClickListener(this);


        /*String name = thing.getName();
        String shortDescription = thing.getShortDescription();
        String longDescription = thing.getLongdescription();
        double goodnessValue = thing.getGoodnessValue();*/

        TextItemName.setText(name);
        TextItemShortDescription.setText(sdesc);
        TextItemLongDescription.setText(ldesc);
        TextItemGoodnessValue.setText(Double.toString(gvalue));

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

    }


    /*@Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.deleteButton) {
            mProductDatabase.deleteItem(thing.getName());
        }
    }*/

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


}
