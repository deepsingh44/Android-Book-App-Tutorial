package com.example.deep.androidtutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deep.androidtutorial.database.UserDao;
import com.example.deep.androidtutorial.model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText temail, tpass;
    private String email, pass;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDao = new UserDao(this);
        setContentView(R.layout.activity_login);
        temail = findViewById(R.id.loginemail);
        tpass = findViewById(R.id.loginpass);
    }

    public void loginCodeHere(View view) {
        if (valid()) {
            User user=userDao.login(email,pass);
            if(user!=null){
                Intent in = new Intent(this, HomePage.class);
                startActivity(in);
                finish();
            }else{
                Toast.makeText(this, "Invalid User email or password", Toast.LENGTH_SHORT).show();
            }

        }


    }

    private boolean valid() {
        email = temail.getText().toString();
        pass = tpass.getText().toString();
        if (TextUtils.isEmpty(email)) {
            temail.setError("Enter Email");
            temail.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(pass)) {
            tpass.setError("Enter Password");
            tpass.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public void goToRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }
}