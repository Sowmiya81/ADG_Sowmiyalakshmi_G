package com.example.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText et_email,et_password;
    private Button btn_login,btn_sign_up;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_email_screen);
        getSupportActionBar().hide();
        et_email = (EditText) findViewById(R.id.editText_Email);
        et_password = (EditText) findViewById(R.id.editText_Password);
        btn_login = (Button) findViewById(R.id.button_login);
        btn_sign_up = (Button) findViewById(R.id.button_signup);
        mAuth = FirebaseAuth.getInstance();

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign_up = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(sign_up);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

    }
    private void userLogin()
    {
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if(email.isEmpty() && password.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Invalid Input",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(email.isEmpty())
        {
            et_email.requestFocus();
            et_email.setError("Email is required");
            Toast.makeText(getApplicationContext(),"Enter Email",Toast.LENGTH_SHORT).show();
        }
        else if(password.isEmpty())
        {
            et_password.requestFocus();
            et_password.setError("Password is required");

            Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
        }
        else if(!(Patterns.EMAIL_ADDRESS.matcher(email).matches()))
        {
            Toast.makeText(getApplicationContext(),"Invalid Email entered",Toast.LENGTH_SHORT).show();
            et_email.setError("Invalid Email");
            et_email.requestFocus();
            return;
        }
        else if(password.length()<6)
        {
            Toast.makeText(getApplicationContext(),"Password Length too short",Toast.LENGTH_SHORT).show();
            et_password.setError("Password Length too short");
            et_password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    //SharedPreferences sp = getSharedPreferences("DATA",MODE_PRIVATE);
                    //SharedPreferences.Editor ed = sp.edit();
                    //ed.putBoolean("isSignedIn",true);
                    //ed.commit();

                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                    Intent login = new Intent(LoginActivity.this,BottomNavActivity.class);
                    login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(login);
                    finish();
                }
                else
                    {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
}
