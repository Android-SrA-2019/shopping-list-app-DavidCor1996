package com.example.david.shoppinglist;
//David Cormier
//WMAD SR A

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Class name for Log tag
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    // Unique tag required for the intent extra
    public static final String EXTRA_MESSAGE
            = "com.example.david.extra.MESSAGE";

    public static final String counter
            = "com.example.david.extra.Count";
    // Unique tag for the intent reply
    public static final int TEXT_REQUEST = 1;

    private ShoppingList[] items;
    private ArrayList<TextView> textViews;

    //Old Code
//    private TextView item1;
//    private TextView item2;
//    private TextView item3;
//    private TextView item4;
//    private TextView item5;
//    private TextView item6;
//    private TextView item7;
//    private TextView item8;
//    private TextView item9;
//    private TextView item10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//More Old Code


//        // Initialize all the view variables.
//        mMessageEditText = findViewById(R.id.editText_main);
//        mReplyHeadTextView = findViewById(R.id.text_header_reply);
//        mReplyTextView = findViewById(R.id.text_message_reply);

//        item1 = findViewById(R.id.item1);
//        item2 = findViewById(R.id.item2);
//        item3 = findViewById(R.id.item3);
//        item4 = findViewById(R.id.item4);
//        item5 = findViewById(R.id.item5);
//        item6 = findViewById(R.id.item6);
//        item7 = findViewById(R.id.item7);
//        item8 = findViewById(R.id.item8);
//        item9 = findViewById(R.id.item9);
//        item10 = findViewById(R.id.item10);



        items = new ShoppingList[10];
        textViews = new ArrayList<>(10);

        findViews(findViewById(android.R.id.content));


        if (savedInstanceState != null) {
            for (int i = 1; i < 11; i++) {
                if (savedInstanceState.getString("name" + i) == null) {
                    break;
                }
                items[i] = new ShoppingList(savedInstanceState.getString("name" + i),savedInstanceState.getInt("amount" + i));
                String output = items[i].getCount() + " " + items[i].getItem();
                textViews.get(i).setText(output);
            }

        }
    }

    public void findViews(View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    // Continue to call method to find textviews
                    findViews(child);
                }
            } else if (v instanceof TextView) {
                textViews.add((TextView) v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable("ShoppingList", items);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        items = (ShoppingList[]) savedInstanceState.getSerializable("ShoppingList");
    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, AddItem.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Test for the right intent reply.
        if (requestCode == TEXT_REQUEST) {
            // Test to make sure the intent reply result was good.
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(AddItem.groceryItem);


                for (int i = 0; i < 11; i++) {
                    if (items[i] == null) {
                        items[i] = (new ShoppingList(reply, 1));
                    } else {
                        items[i].setCount(items[i].getCount() + 1);
                    }
                    if (items[i].getItem().equals(reply)) {
                        String output = items[i].getCount() + " " + items[i].getItem();
                        textViews.get(i + 1).setText(output);
                        break;

                    }


                }


            }
        }
    }
}


