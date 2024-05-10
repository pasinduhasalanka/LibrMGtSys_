package com.example.LibraryMGtSys;

import android.content.Intent;
import android.database.Cursor;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class memberLogin extends AppCompatActivity {

    private Button button;
    EditText UserName, Password;
    Button login;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login);

        button = findViewById(R.id.btnback2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity();
            }
        });
        dbManager = new DBManager(this);
        dbManager.open();

        UserName = findViewById(R.id.txtUserName2);
        Password = findViewById(R.id.txtpass2);
        login = findViewById(R.id.login2);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserName.getText().toString().isEmpty() || Password.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Fields can't be null", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean valid;

                   if(valid = true)
                    {
                        Cursor cursor = dbManager.Select("select * from Member where UserName='" + UserName.getText() +
                                "' AND Password='" + Password.getText() + "'");

                        if (cursor.moveToNext()) {

                            Toast.makeText(getApplicationContext(), "Succesfully logged as Member", Toast.LENGTH_SHORT).show();
                            Intent patient = new Intent(getApplicationContext(), memberMenu.class);
                            startActivity(patient);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }

        });

    }

    public void MainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
