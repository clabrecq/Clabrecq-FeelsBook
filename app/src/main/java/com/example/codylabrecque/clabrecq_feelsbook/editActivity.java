package com.example.codylabrecque.clabrecq_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
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

public class editActivity extends AppCompatActivity {
    private List text = new ArrayList<String>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        Intent edit = getIntent();
        final String message = edit.getStringExtra(EXTRA_MESSAGE);
        Button editButton = findViewById(R.id.button4);
        this.text = loadFromFile();
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.editText2);
                String[] values = message.split("-");
                Intent editIntent = new Intent(getApplicationContext(), addActivity.class);
                String edited = editText.getText().toString();
                String[] editValues = edited.split("-");

                String editedMadisonSquareGarden = values[0] + "-" + editValues[0] + "-" + editValues[1];
                editIntent.putExtra(EXTRA_MESSAGE, editedMadisonSquareGarden);

                for (int i = 0; i <= text.size() - 1; i+=1) {
                    if (text.get(i).equals(message)) {
                        text.remove(i);
                        //text.add(editedMadisonSquareGarden);
                        saveInFile2(text, new Date(System.currentTimeMillis()));

                    }


                }
                startActivity(editIntent);
            }
        });




    }
    private List <String> loadFromFile() {
        List<String> history = new ArrayList<String>();
        try {
            FileInputStream fis = openFileInput(addActivity.FILENAME);
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
    private void saveInFile2 (List text, Date date){
        try {


            FileOutputStream fos = openFileOutput(addActivity.FILENAME, Context.MODE_PRIVATE);


            for (int i = 0; i < text.size(); i += 1) {
                Object message = text.get(i);

                fos.write(new String(message + "\n").getBytes());
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


