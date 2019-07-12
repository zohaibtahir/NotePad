package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.notepad.performSave.myList;

public class ListViewItems extends AppCompatActivity {

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_items);
        
        listview = findViewById(R.id.listview);
        localAdapter adapter = new localAdapter(getApplicationContext(),myList);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("title",myList.get(i).getTitleText());
                intent.putExtra("note",myList.get(i).getNoteText());
                intent.putExtra("INDEX",i);
                startActivity(intent);
            }
        });

    }
}
