package com.example.trianglesqrtnumbercheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static class Number{
        public int number;
        Number(){
            number = 0;
        }

        public boolean isTriangle(){
            int x = 1;
            int triangleNumber = 1;
            while(number > triangleNumber){
                ++x;
                triangleNumber = triangleNumber + x;
            }
            return triangleNumber == number;
        }

        public boolean isSquare(){
            double sq = Math.sqrt(number);
            return sq == Math.floor(sq);
        }
    }

    public void makeToast(int number, String string){
        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this);
        long rowId = myDatabaseHelper.insertData(number, string);//insert into database
        if(rowId == -1){
            Toast.makeText(this, "data insert unsuccessful", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    public void triangleChecker(View view){
        EditText editText = findViewById(R.id.editTextInputNumber);
        Number myNumber = new Number();
        myNumber.number = Integer.parseInt(editText.getText().toString());
        if(editText.getText().toString().isEmpty()){
            makeToast(myNumber.number, "Please enter a number");
        }
        else if(myNumber.isSquare() && myNumber.isTriangle()){
            makeToast(myNumber.number, "this is square and triangle number");
        }
        else if(myNumber.isTriangle()){
            makeToast(myNumber.number,"this is triangle number");
        }
        else if(myNumber.isSquare()){
            makeToast(myNumber.number,"this is square number");
        }
        else{
            makeToast(myNumber.number,"this number is neither triangle nor a square");
        }

        Log.i("triangleApp", editText.getText().toString());
    }


    //For Database
    MyDatabaseHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for history page
        Button button = findViewById(R.id.history);
        button.setOnClickListener(v -> openHistory());

        myDatabaseHelper = new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
    }

    public void openHistory(){
        Intent intent = new Intent(MainActivity.this, ShowHistory.class);
        startActivity(intent);
    }
}