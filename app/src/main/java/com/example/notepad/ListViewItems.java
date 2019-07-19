package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.notepad.performSave.myList;

public class ListViewItems extends AppCompatActivity implements AbsListView.MultiChoiceModeListener {

    ListView listview;
    ArrayList<utility> toDeletelist = new ArrayList<>();
    localAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_items);
        
        listview = findViewById(R.id.listview);
        adapter = new localAdapter(getApplicationContext(),myList);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showPopUp(view,i);
                //toDeletelist.add(myList.get(i));
                /*Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("title",myList.get(i).getTitleText());
                intent.putExtra("note",myList.get(i).getNoteText());
                intent.putExtra("INDEX",i);
                startActivity(intent);*/
            }
        });
        registerForContextMenu(listview);
        listview.setChoiceMode(listview.CHOICE_MODE_MULTIPLE_MODAL);
        listview.setMultiChoiceModeListener(this);
    }

    private void showPopUp(View view, final int i) {
        PopupMenu popupMenu = new PopupMenu(this,view);
        MenuInflater infelator = popupMenu.getMenuInflater();
        infelator.inflate(R.menu.context,popupMenu.getMenu());
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.dlt_item:
                        myList.remove(i);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.edit_item:
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtra("title",myList.get(i).getTitleText());
                        intent.putExtra("note",myList.get(i).getNoteText());
                        intent.putExtra("INDEX",i);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_context,menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.dlt_item:
                for(utility note : toDeletelist){
                    myList.remove(note);
                }
                break;
        }
       actionMode.finish();
       return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
            toDeletelist.clear();
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
        if(b){
            toDeletelist.add(myList.get(i));
        }else{
            toDeletelist.remove(myList.get(i));
        }
    }
}
