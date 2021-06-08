package com.example.trianglesqrtnumbercheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowHistory extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Integer> _number = new ArrayList<>();
    ArrayList<String> _type = new ArrayList<>();
    MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);
        recyclerView = findViewById(R.id.recylerviewId);

        myDatabaseHelper = new MyDatabaseHelper(this);
        CustomAdapter customAdapter = new CustomAdapter(this, _number, _type);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowHistory.this));


        Cursor cursor = myDatabaseHelper.displayData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                _number.add(cursor.getInt(1));
                _type.add(cursor.getString(2));
            }
        }

    }
}