package com.example.codylabrecque.clabrecq_feelsbook;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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


public class addActivity extends AppCompatActivity {
    public static String FILENAME = "data";
    private List text = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        saveInFile(message, new Date(System.currentTimeMillis()));

        this.text = loadFromFile();
        Button add_button = findViewById(R.id.button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_emotion = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(add_emotion);
            }
        });


        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, text);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);



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
    }






