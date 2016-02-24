package com.testphase.materialtest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.testphase.materialtest.R;
import com.testphase.materialtest.database.ProductDatabase;
import com.testphase.materialtest.logging.L;
import com.testphase.materialtest.pojo.Product;


/**
 * Created by deea on 15/01/16.
 */
public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    ProductDatabase mProductDatabase;

    private Toolbar toolbar;

    EditText EditTextName;
    EditText EditTextShortDescription;
    EditText EditTextLongDescription;

    TextView TextViewDefaultGoodnessValue;

    Button saveButton;

    //Default Goodness Value of a new product added
    public static final int DEFAULTGVALUE = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_product);

        EditTextName = (EditText) findViewById(R.id.EditTextName);
        EditTextShortDescription = (EditText) findViewById(R.id.EditTextShortDescription);
        EditTextLongDescription = (EditText) findViewById(R.id.EditTextLongDescription);
        //EditTextGoodnessValue = (EditText) findViewById(R.id.EditTextGoodnessValue);
        TextViewDefaultGoodnessValue = (TextView) findViewById(R.id.TextViewDefaultGoodnessValue);

        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.saveButton) {
            String name = EditTextName.getText().toString();
            String sdesc = EditTextShortDescription.getText().toString();
            String ldesc = EditTextLongDescription.getText().toString();

            //Default Goodness Value is set on adding a new product
            Integer gvalue = DEFAULTGVALUE;

            Product product = new Product(name, sdesc, ldesc, gvalue);

            mProductDatabase = new ProductDatabase(this);
            mProductDatabase.insertProduct(product);


            L.T(getApplicationContext(), "Item " + name + " added");

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
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



}


