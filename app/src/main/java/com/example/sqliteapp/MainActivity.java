package com.example.sqliteapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText nameEditText, surnameEditText, marksEditText;
    Button addBtn;
    Button dbBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);


        nameEditText = findViewById(R.id.nameEditText);
        surnameEditText= findViewById(R.id.surnameEditText);
        marksEditText = findViewById(R.id.marksEditText);
        addBtn = findViewById(R.id.addBtn);
        dbBtn = findViewById(R.id.dbBtn);
        addBtn();
        viewAll();
    }
    public void addBtn(){
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.instertData(nameEditText.getText().toString(),surnameEditText.getText().toString(),
                        marksEditText.getText().toString());
                if(isInserted==true)
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();


            }
        });
    }

    public void viewAll(){
        dbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if(res.getCount()==0){
                    //show message
                    showMessage("Error","Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToLast()){
                    buffer.append("Id :"+ res.getString(0)+"\n");
                    buffer.append("Name :"+ res.getString(0)+"\n");
                    buffer.append("Surname :"+ res.getString(0)+"\n");
                    buffer.append("Marks :"+ res.getString(0)+"\n\n");
                }
                //show all data
                showMessage("Data",buffer.toString());
            }
        });
    }
     public void showMessage (String title,String Message){
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setCancelable(true);
         builder.setMessage(Message);
         builder.show();
     }
}
