package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=(EditText) findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        repassword=(EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin=(Button)findViewById(R.id.btnsigin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass= repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(MainActivity.this,"Please fill this field",Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(MainActivity.this,"Succefully Registered", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Activity3.class);
                                startActivity(intent);

                            }else{
                                Toast.makeText(MainActivity.this,"Registration fail", Toast.LENGTH_SHORT).show();

                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this,"User already Have an account", Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        Toast.makeText(MainActivity.this,"Password not corrcet", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });

    }
}