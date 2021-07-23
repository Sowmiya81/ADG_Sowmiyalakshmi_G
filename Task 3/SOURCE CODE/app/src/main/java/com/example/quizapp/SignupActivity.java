package com.example.quizapp;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    private EditText et_email_sign,et_password_sign,et_name;
    private Button btn_register,btn_login_sign;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_email_screen);

        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        et_email_sign = (EditText) findViewById(R.id.editText_Email_sign);
        et_password_sign = (EditText) findViewById(R.id.editText_Password_sign);
        et_name= (EditText) findViewById(R.id.editText_name);
        btn_register = (Button) findViewById(R.id.button_register);
        btn_login_sign = (Button) findViewById(R.id.button_login_sign);

        btn_login_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }
    private void registerUser()
    {
        String name = et_name.getText().toString().trim();
        String email = et_email_sign.getText().toString().trim();
        String password = et_password_sign.getText().toString().trim();
        if(!(Patterns.EMAIL_ADDRESS.matcher(email).matches()))
        {
            Toast.makeText(getApplicationContext(),"Invalid Email entered",Toast.LENGTH_SHORT).show();
            et_email_sign.requestFocus();
            et_email_sign.setError("Invalid Email");
            return;
        }
        else if(password.length()<6)
        {
            Toast.makeText(getApplicationContext(),"Password Length too short",Toast.LENGTH_SHORT).show();
            et_password_sign.requestFocus();
            et_password_sign.setError("Password is too short");
            return;
        }
        if(name.isEmpty()){
            et_name.requestFocus();
            et_name.setError("Name is required");
        }
        if(email.isEmpty()){
            et_email_sign.requestFocus();
            et_email_sign.setError("Email is required");
        }
        if(password.isEmpty()){
            et_password_sign.requestFocus();
            et_password_sign.setError("Password is required");
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"User Registered Successfully",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if(task.getException() instanceof FirebaseAuthUserCollisionException)
                {
                    Toast.makeText(getApplicationContext(),"Email has already been registered",Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        Toast.makeText(getApplicationContext(),"Some Error Occurred",Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
    private void reload() { }
}
