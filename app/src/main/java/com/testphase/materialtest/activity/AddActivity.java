package com.testphase.materialtest.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.testphase.materialtest.R;
import com.testphase.materialtest.database.ProductDatabase;
import com.testphase.materialtest.layout.PrimaryListFragment;
import com.testphase.materialtest.logging.L;
import com.testphase.materialtest.pojo.Thing;

import java.util.ArrayList;

/**
 * Created by deea on 15/01/16.
 */
public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    ProductDatabase mProductDatabase;

    ArrayList<Thing> listThings = new ArrayList<>();

    EditText EditTextName;
    EditText EditTextShortDescription;
    EditText EditTextLongDescription;
    EditText EditTextGoodnessValue;

    Button saveButton;

    int itemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //itemID = getIntent().getIntExtra(PrimaryListFragment.KEY_EXTRA_CONTACT_ID, 0);

        setContentView(R.layout.activity_sub);

        EditTextName = (EditText) findViewById(R.id.EditTextName);
        EditTextShortDescription = (EditText) findViewById(R.id.EditTextShortDescription);
        EditTextLongDescription = (EditText) findViewById(R.id.EditTextLongDescription);
        EditTextGoodnessValue = (EditText) findViewById(R.id.EditTextGoodnessValue);

        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);


        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.saveButton) {
            persistItem();
        }
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
            Toast.makeText(this, "You have clicked " + item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void persistItem() {


        String name = EditTextName.getText().toString();
        String sdesc = EditTextShortDescription.getText().toString();
        String ldesc = EditTextLongDescription.getText().toString();
        Integer gvalue = Integer.parseInt(EditTextGoodnessValue.getText().toString());

        Thing thing = new Thing(name, sdesc, ldesc, gvalue);

        mProductDatabase = new ProductDatabase(this);
        mProductDatabase.insertItem(thing);

        L.T(getApplicationContext(), "Item " + name + " added");


        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }


}


