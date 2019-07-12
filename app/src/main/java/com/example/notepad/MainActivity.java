package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.notepad.performSave.getCurrentDate;
import static com.example.notepad.performSave.myList;

public class MainActivity extends AppCompatActivity {

    ImageView newBtn, saveBtn, listBtn;
    EditText titleBox, noteBox;
    String title = "";
    String note = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newBtn = findViewById(R.id.newImg);
        saveBtn = findViewById(R.id.saveImg);
        listBtn = findViewById(R.id.allImg);
        titleBox = findViewById(R.id.titleBox);
        noteBox = findViewById(R.id.noteBox);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && myList.size()>0) {
            String myTitle = bundle.getString("title");
            titleBox.setText(myTitle);
            String myNote = bundle.getString("note");
            noteBox.setText(myNote);
            int index = bundle.getInt("INDEX");
            utility model = myList.get(index);
            myList.set(index, model);
            myList.remove(index);
        }


        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearNote();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleBox.getText().toString();
                String noteText = noteBox.getText().toString();

                if (!title.isEmpty() && !noteText.isEmpty()) {
                    save_method();
                    clearNote();

                } else {
                    Toast.makeText(getApplicationContext(), "Nothing Enter!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListViewItems.class);
                startActivity(intent);
            }
        });
    }

    public void save_method() {
        utility forSave = new utility();
        title = titleBox.getText().toString();
        forSave.setTitleText(title);
        note = noteBox.getText().toString();
        forSave.setNoteText(note);
        String dateTime = (getCurrentDate());
        forSave.setDateTime(dateTime);
        myList.add(forSave);
        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
    }

    private void clearNote() {
        titleBox.setText("");
        noteBox.setText("");
    }

}
