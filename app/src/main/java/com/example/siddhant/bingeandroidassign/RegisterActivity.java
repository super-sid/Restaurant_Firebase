package com.example.siddhant.bingeandroidassign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private MaterialButton reg_new_user;
    private TextInputEditText reg_disp_name, reg_email, reg_pswd;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        reg_new_user = findViewById(R.id.register_button);
        reg_disp_name = findViewById(R.id.new_name);
        reg_email = findViewById(R.id.new_email);
        reg_pswd = findViewById(R.id.new_pswd);
        progressBar = findViewById(R.id.progress_bar);

        reg_new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String display_name = reg_disp_name.getText().toString();
                String email = reg_email.getText().toString();
                String pswd = reg_pswd.getText().toString();

                if (!TextUtils.isEmpty(display_name) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(pswd)){
                    progressBar.setVisibility(View.VISIBLE);
                    register_user(display_name, email, pswd);
                }
            }
        });
    }

    private void register_user(String display_name, String email, String pswd){
        mAuth.createUserWithEmailAndPassword(email, pswd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressBar.setVisibility(View.INVISIBLE);
                            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                            startActivity(intent);
                            Toast.makeText(RegisterActivity.this,"New User Registered",Toast.LENGTH_SHORT).show();
                            finish();
                        } else{
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "Cannot SignIn",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
