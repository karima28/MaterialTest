package com.testphase.materialtest.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.testphase.materialtest.R;
import com.testphase.materialtest.database.ProductDatabase;
import com.testphase.materialtest.layout.PrimaryListFragment;


/**
 * Created by deea on 15/01/16.
 */
public class EditOrDelete extends AppCompatActivity implements View.OnClickListener{

    ProductDatabase mProductDatabase;

    EditText EditTextName;
    EditText EditTextShortDescription;
    EditText EditTextLongDescription;
    EditText EditTextGoodnessValue;

    LinearLayout buttonLayout;
    Button editButton, deleteButton;

    int itemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //itemID = getIntent().getIntExtra(PrimaryListFragment.KEY_EXTRA_CONTACT_ID, 0);

        setContentView(R.layout.activity_edit_or_delete);
        EditTextName = (EditText) findViewById(R.id.EditTextName);
        EditTextShortDescription = (EditText) findViewById(R.id.EditTextShortDescription);
        EditTextLongDescription = (EditText) findViewById(R.id.EditTextLongDescription);
        EditTextGoodnessValue = (EditText) findViewById(R.id.EditTextGoodnessValue);


        buttonLayout = (LinearLayout) findViewById(R.id.buttonLayout);
        editButton = (Button) findViewById(R.id.editButton);
        editButton.setOnClickListener(this);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(this);



            /*Cursor rs = mProductDatabase.readItems(itemID);
            rs.moveToFirst();
            String itemName = rs.getString(rs.getColumnIndex(ItemContract.Table1.ITEM_COLUMN_NAME));
            String itemDescription = rs.getString(rs.getColumnIndex(ItemContract.Table1.ITEM_COLUMN_SHORT_DESC));

            if (!rs.isClosed()) {
                rs.close();
            }

            EditTextName.setText(itemName);
            EditTextName.setFocusable(false);
            EditTextName.setClickable(false);

            EditTextShortDescription.setText(itemDescription);
            EditTextShortDescription.setFocusable(false);
            EditTextShortDescription.setClickable(false);*/

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editButton:
                buttonLayout.setVisibility(View.GONE);

                EditTextName.setEnabled(true);
                EditTextName.setFocusableInTouchMode(true);
                EditTextName.setClickable(true);

                EditTextShortDescription.setEnabled(true);
                EditTextShortDescription.setFocusableInTouchMode(true);
                EditTextShortDescription.setClickable(true);
                return;

            case R.id.deleteButton:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.delete)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), PrimaryListFragment.class);
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

    public void persistItem() {
        mProductDatabase = new ProductDatabase(this);
        /*if(itemID > 0) {
            if(dbHelper.updateItem(itemID, EditTextName.getText().toString(),
                    EditTextShortDescription.getText().toString(), EditTextLongDescription.getText().toString(), Integer.getInteger(EditTextGoodnessValue.toString()))) {
                Toast.makeText(getApplicationContext(), "Item updated successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), PrimaryListFragment.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Item update failed", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            if(deleteButton(EditTextName.getText().toString(),
                    EditTextShortDescription.getText().toString(), EditTextLongDescription.getText().toString(), Integer.getInteger(EditTextGoodnessValue.toString()))) {
                Toast.makeText(getApplicationContext(), "Item added successfully", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Could not add item", Toast.LENGTH_SHORT).show();
            }*/
            Intent intent = new Intent(getApplicationContext(), PrimaryListFragment.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
    }
}



