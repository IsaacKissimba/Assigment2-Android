package com.example.bookstore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity {
    EditText name, isbn, dor;
    Button insert, delete, view, update;
    private Button Profile;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        name = findViewById(R.id.name);
        isbn = findViewById(R.id.ISBN);
        dor = findViewById(R.id.dor);

        Profile = findViewById(R.id.profile);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        db = new DataBase(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String isbnTXT = isbn.getText().toString();
                String dorTXT = dor.getText().toString();

                Boolean checkinsertdata = db.insertuserdata(nameTXT, isbnTXT, dorTXT);
                if(checkinsertdata==true)
                    Toast.makeText(Activity3.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Activity3.this, "New Entry not inserted", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String isbnTXT = isbn.getText().toString();
                String dorTXT = dor.getText().toString();

                Boolean checkupdatedata = db.updateuserdata(nameTXT, isbnTXT, dorTXT);
                if(checkupdatedata==true)
                    Toast.makeText(Activity3.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Activity3.this, "New Entry not Updated", Toast.LENGTH_SHORT).show();
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                Boolean checkdeletedata = db.deletedata(nameTXT);
                if(checkdeletedata==true)
                    Toast.makeText(Activity3.this, "Entry deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Activity3.this, "Entry not deleted", Toast.LENGTH_SHORT).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getdata();
                if(res.getCount()==0){
                    Toast.makeText(Activity3.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Name of The book:"+res.getString(0)+"\n");
                    buffer.append("Isbn Number:"+res.getString(1)+"\n");
                    buffer.append("Date of Release:"+res.getString(2)+"\n\n");

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(Activity3.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();


            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity3.this,Activity4.class);
                startActivity(intent);

            }
        });


    }

}