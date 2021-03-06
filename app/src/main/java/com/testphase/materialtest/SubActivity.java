package com.testphase.materialtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.testphase.materialtest.network.PostRequest;

public class SubActivity extends AppCompatActivity implements View.OnClickListener{

    EditText EditTextName;
    EditText EditTextDescription;
    EditText EditTextColour;
    EditText EditTextPrice;
    EditText EditTextCategory;

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        EditTextName = (EditText) findViewById(R.id.EditTextName);
        EditTextDescription = (EditText) findViewById(R.id.EditTextDescription);
        EditTextColour = (EditText) findViewById(R.id.EditTextColour);
        EditTextPrice = (EditText) findViewById(R.id.EditTextPrice);
        EditTextCategory = (EditText) findViewById(R.id.EditTextCategory);

        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);


        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }


    @Override
    public void onClick(View view) {
        persistItem();
        return;
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

        PostRequest postRequest = new PostRequest();

        postRequest.newInstance(EditTextName.getText().toString(), EditTextDescription.getText().toString(), EditTextColour.getText().toString(),
                Double.parseDouble(EditTextPrice.getText().toString()), EditTextCategory.getText().toString());

        Toast.makeText(getApplicationContext(), "Item added successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}
