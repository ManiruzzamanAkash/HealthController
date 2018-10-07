package com.akash.healthcontroller;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    ListView historyListView;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyListView = (ListView) findViewById(R.id.listViewHistory);

        myDB = new DatabaseHelper(this);

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getListContents();

        if(data.getCount() == 0){
            Toast.makeText(HistoryActivity.this, "Nothing Found", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(HistoryActivity.this, data.getCount()+" Data histories available", Toast.LENGTH_SHORT).show();
            while (data.moveToNext()){
                theList.add(data.getString(1));
                listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                historyListView.setAdapter(listAdapter);
            }

        }

        historyListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final String selectedFromList =(historyListView.getItemAtPosition(position).toString());
//                Toast.makeText(HistoryActivity.this, selectedFromList, Toast.LENGTH_LONG).show();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(HistoryActivity.this);
                alertDialog.setMessage("Do you want to delete?");
                alertDialog.setNegativeButton("No", null);
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try{
                            if(myDB.deleteHistory(selectedFromList)){
                                listAdapter.notifyAll();
                                historyListView.removeViewAt(position);
                                Toast.makeText(HistoryActivity.this, selectedFromList, Toast.LENGTH_LONG).show();
                            }else{

                            }

                        }catch (Exception e){
                            Toast.makeText(HistoryActivity.this, "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                });
                alertDialog.show();

                return false;
            }
        });




    }
}
