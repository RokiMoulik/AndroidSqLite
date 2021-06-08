package com.example.trianglesqrtnumbercheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowHistory extends AppCompatActivity {
    private MyDatabaseHelper myDatabaseHelper;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);

        listView =  findViewById(R.id.listViewId);
        myDatabaseHelper = new MyDatabaseHelper(ShowHistory.this);

        displayData();
    }

    public void displayData(){
        ArrayList<String>listData = new ArrayList<>();

        Cursor cursor = myDatabaseHelper.displayAllData();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data in the database", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                listData.add(cursor.getString(1) + " \t " + cursor.getString(2));
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.single_item, R.id.textViewId, listData);
        listView.setAdapter(arrayAdapter);
       // listView.setOnItemClickListener(listView.getOnItemClickListener());
    }
}