package com.testphase.materialtest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.testphase.materialtest.R;
import com.testphase.materialtest.database.ProductDatabase;
import com.testphase.materialtest.logging.L;
import com.testphase.materialtest.pojo.Product;


/**
 * The ItemDisplayActivity is the activity that displays the detailed view of the Item that is selected
 */
public class ItemDisplayActivity extends MainActivity implements View.OnClickListener {

    TextView TextViewLongDescription;
    TextView TextViewGoodnessValue;
    TextView TextItemName;
    TextView TextItemShortDescription;
    TextView TextItemLongDescription;
    TextView TextItemGoodnessValue;

    Button likeButton;
    Button dislikeButton;
    Button neutralButton;

    private Product product;

    //The level of impact of each action (like, dislike or no reaction) on the goodnessvalue
    public static final double GValueLikeAction = 1.6;
    public static final double GValueDislikeAction = 0.6;
    public static final double GValueNeutralAction = 0.9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_product);

        Long itemid = 0L;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            itemid = extras.getLong("KEY_EXTRA_PRODUCT_ID");
        }

        ProductDatabase mProductDatabase = new ProductDatabase(getApplicationContext());

        this.product = mProductDatabase.getProduct(itemid);

        TextViewLongDescription = (TextView) findViewById(R.id.TextViewLongDescription);
        TextViewGoodnessValue = (TextView) findViewById(R.id.TextViewGoodnessValue);
        TextItemName = (TextView) findViewById(R.id.TextItemName);
        TextItemShortDescription = (TextView) findViewById(R.id.TextItemShortDescription);
        TextItemLongDescription = (TextView) findViewById(R.id.TextItemLongDescription);
        TextItemGoodnessValue = (TextView) findViewById(R.id.TextItemGoodnessValue);


        likeButton = (Button) findViewById(R.id.likeButton);
        likeButton.setOnClickListener(this);
        dislikeButton = (Button) findViewById(R.id.dislikeButton);
        dislikeButton.setOnClickListener(this);
        neutralButton = (Button) findViewById(R.id.neutralButton);
        neutralButton.setOnClickListener(this);

        TextItemName.setText(product.getName());
        TextItemShortDescription.setText(product.getShortDescription());
        TextItemLongDescription.setText(product.getLongdescription());
        TextItemGoodnessValue.setText(Integer.toString(product.getGoodnessValue()));

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

    /**
     * The corresponding responses on clicking one of the buttons
     * @param v the view
     */
    @Override
    public void onClick(View v) {
        ProductDatabase mProductDatabase = new ProductDatabase(getApplicationContext());

        switch (v.getId()) {
            case R.id.likeButton:
                mProductDatabase.insertProduct(product,"favorites");
                L.m("TEST LIKE: \n" + Integer.toString(product.getGoodnessValue()));
                product.updateGoodnessValue(GValueLikeAction);
                L.m("TEST DISLIKE: \n" + Integer.toString(product.getGoodnessValue()));
                //L.m(Integer.toString(product.getGoodnessValue()));

                L.t(getApplicationContext(), "Product added to Favorites");
            break;

            case R.id.dislikeButton:
                product.updateGoodnessValue(GValueDislikeAction);
                //L.m(Integer.toString(product.getGoodnessValue()));
            break;

            case R.id.neutralButton:
                product.updateGoodnessValue(GValueNeutralAction);
                //L.m(Integer.toString(product.getGoodnessValue()));
            break;
        }

        mProductDatabase.updateProductGValue(product.getId(),product.getGoodnessValue());
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
