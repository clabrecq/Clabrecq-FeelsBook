//The loadFromFile, saveInFile and saveInFile2 methods in all activities are all heavily based on an older version of lonelyTwitter produced
//by Joshua Charles Campbell. I have reference him and his github in my README.md file.

package com.example.codylabrecque.clabrecq_feelsbook;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.codylabrecque.clabrecq_feelsbook.MainActivity.EXTRA_MESSAGE;


public class addActivity extends AppCompatActivity {
    public static String FILENAME = "data";
    private List text = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);
        saveInFile(message, new Date(System.currentTimeMillis()));

        this.text = loadFromFile();
        Button add_button = findViewById(R.id.button);
        //sends you back to the main activity
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_emotion = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(add_emotion);
            }
        });

        //array adapter to help create a listview
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, text);
        final ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        //this code builds the alert window
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!isFinishing()){
                            new AlertDialog.Builder(addActivity.this)
                                    .setTitle("Edit message")
                                    .setMessage("Do you want to edit or delete this entry?")
                                    .setCancelable(false)
                                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                        @Override
                                        //removes the selected item when delte is hit
                                        public void onClick(DialogInterface dialog, int which) {
                                            text.remove(position);

                                            adapter.notifyDataSetChanged();
                                            saveInFile2(text, new Date(System.currentTimeMillis()));
                                        }
                                    })
                                    .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                                        @Override
                                        //sends you to the editActivity
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent edit = new Intent(getApplicationContext(), editActivity.class);
                                            String message = text.get(position).toString();
                                            edit.putExtra(EXTRA_MESSAGE, message);


                                            startActivity(edit);
                                        }
                                    })
                                    .show();

                        }
                    }
                });

            }
        });


    }
    private List <String> loadFromFile() {
        List<String> history = new ArrayList<String>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = in.readLine();
            while (line != null) {
                history.add(line);
                line = in.readLine();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return history;
    }
    private void saveInFile(String text, Date date) {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
            fos.write(new String( text + "\n").getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //when an edit is made this method rewrites the entire file based and the new list of information it has
    private void saveInFile2(List text, Date date) {
        try {


            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);


            for(int i = 0; i < text.size(); i+= 1){
                Object message = text.get(i);

                fos.write(new String( message + "\n").getBytes());
            }

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    }






