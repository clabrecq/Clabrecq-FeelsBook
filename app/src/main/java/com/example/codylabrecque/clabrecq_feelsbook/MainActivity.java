package com.example.codylabrecque.clabrecq_feelsbook;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private int joy = 0;
    private int sadness = 0;
    private int love = 0;
    private int surprise = 0;
    private int anger = 0;
    private int fear = 0;
    public static final String EXTRA_MESSAGE = "com.example.CLABRECQ-FeelsBook.MESSAGE";

    private List numbers = new ArrayList<String>();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        Spinner dropdown = findViewById(R.id.spinner);
        String[] items = new String[]{"Joy","Sadness","Love","Surprise","Anger","Fear"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        loadFromFile();
        updateCount();



        }
    private List<String> loadFromFile() {
        List<String> history = new ArrayList<String>();
        try {
            FileInputStream fis = openFileInput(addActivity.FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = in.readLine();

            while (line != null) {
                history.add(line);
                String[] numbers = line.split("-");
                //String test = numbers[0];
                if (numbers[0].equals("Joy")){
                    joy++;
                }
                if (numbers[0].equals("Surprise")){
                    surprise++;
                }
                if (numbers[0].equals("Sadness")){
                    sadness++;
                }
                if (numbers[0].equals("Fear")){
                    fear++;
                }
                if (numbers[0].equals("Anger")){
                    anger++;
                }
                if (numbers[0].equals("Love")){
                    love++;
                }
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


    public void updateCount(){
        String joyText = "Joy" + " - " + joy;
        TextView joyView = findViewById(R.id.textView2);
        joyView.setText(joyText);
        String surpriseText = "Surprise" + " - " + surprise;
        TextView surpriseView = findViewById(R.id.textView7);
        surpriseView.setText(surpriseText);
        String loveText = "Love" + " - " + love;
        TextView loveView = findViewById(R.id.textView8);
        loveView.setText(loveText);
        String sadnessText = "Sadness" + " - " + sadness;
        TextView sadnessView = findViewById(R.id.textView4);
        sadnessView.setText(sadnessText);
        String angerText = "Anger" + " - " + anger;
        TextView angerView = findViewById(R.id.textView5);
        angerView.setText(angerText);
        String fearText = "Fear" + " - " + fear;
        TextView fearView = findViewById(R.id.textView6);
        fearView.setText(fearText);
    }



    public void onClick(View view) {

        Date currentTime = Calendar.getInstance().getTime();
        Spinner dropdown = findViewById(R.id.spinner);
        String dropText = dropdown.getSelectedItem().toString();
        Intent intent = new Intent(getApplicationContext(), addActivity.class);

        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();
        String MadisonSquareGarden = dropText +"-"+ message +"-"+ currentTime;
        intent.putExtra(EXTRA_MESSAGE, MadisonSquareGarden);
        if (dropText == "Joy"){
            this.joy ++;
            String joyText = dropText + " - " + joy;
            TextView joyView = findViewById(R.id.textView2);
            joyView.setText(joyText);

        }


        if (dropText == "Surprise"){
            this.surprise ++;
            String surpriseText = dropText + " - " + surprise;
            TextView surpriseView = findViewById(R.id.textView7);
            surpriseView.setText(surpriseText);

        }


        if (dropText == "Love"){
            this.love ++;
            String loveText = dropText + " - " + love;
            TextView loveView = findViewById(R.id.textView8);
            loveView.setText(loveText);

        }


        if (dropText == "Sadness"){
            this.sadness ++;
            String sadnessText = dropText + " - " + sadness;
            TextView sadnessView = findViewById(R.id.textView4);
            sadnessView.setText(sadnessText);

        }


        if (dropText == "Anger"){
            this.anger ++;
            String angerText = dropText + " - " + anger;
            TextView angerView = findViewById(R.id.textView5);
            angerView.setText(angerText);

        }


        if (dropText == "Fear"){
            this.fear ++;
            String fearText = dropText + " - " + fear;
            TextView fearView = findViewById(R.id.textView6);
            fearView.setText(fearText);

        }



        startActivity(intent);
    }


}
