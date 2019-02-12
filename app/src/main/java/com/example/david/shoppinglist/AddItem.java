package com.example.david.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddItem extends AppCompatActivity {
    public static final String groceryItem =
            "com.example.android.twoactivities.extra.REPLY";



    /**
     * Initializes the activity.
     *
     * @param savedInstanceState The current state data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
    }


    public void buttonClick(View view) {
        // Get the reply message from the edit text.
        String grocery = (String)((Button)view).getText();
        Intent intent = new Intent();
        intent.putExtra(groceryItem, grocery);
        setResult(RESULT_OK, intent);
        finish();
    }
}
