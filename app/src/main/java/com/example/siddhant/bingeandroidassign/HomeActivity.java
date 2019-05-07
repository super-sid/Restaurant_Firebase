package com.example.siddhant.bingeandroidassign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.location.aravind.getlocation.GeoLocator;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private MaterialButton bt_signOut, bt_show_rest;
    GoogleApiClient mGoogleApiClient;
    GeoLocator geoLocator;

    TextView latt,longg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        bt_signOut = findViewById(R.id.sign_out_button);
        bt_show_rest = findViewById(R.id.show_rest_button);
        latt = findViewById(R.id.lat);
        longg = findViewById(R.id.longi);

        geoLocator = new GeoLocator(getApplicationContext(),HomeActivity.this);

        double x = geoLocator.getLattitude();
        double y = geoLocator.getLongitude();
        latt.setText(String.valueOf(x));
        longg.setText(String.valueOf(y));

        bt_show_rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showRest = new Intent(getApplicationContext(),ShowRestActivity.class);
                startActivity(showRest);
            }
        });

        bt_signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getApplicationContext(),"Signed Out Successfully",Toast.LENGTH_SHORT).show();
                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }
                        });
                sendToStart();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        if (currentUser == null){
            sendToStart();
        }
    }

    private void sendToStart() {
        Intent intent = new Intent(HomeActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
