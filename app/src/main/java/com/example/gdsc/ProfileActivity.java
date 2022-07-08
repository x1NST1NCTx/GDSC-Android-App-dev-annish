package com.example.gdsc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.gdsc.databinding.FragmentHomeBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    ImageView profile_image;
    TextView profile_display_name,profile_email;
    Button sign_out_button;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Bundle extras = getIntent().getExtras();

        profile_image = findViewById(R.id.profile_image);
        profile_display_name = findViewById(R.id.profile_display_name);
        profile_email = findViewById(R.id.profile_email);
        sign_out_button = findViewById(R.id.sign_out_button);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();




        Glide.with(getApplicationContext()).load(extras.get("Photo")).into(profile_image);
        Log.i("name",extras.get("Name").toString());
        profile_display_name.setText(getString(R.string.name) +  extras.get("Name").toString());
        profile_email.setText(getString(R.string.email) + extras.get("Email").toString());


        sign_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("Sign Out","sign out");
                mAuth.signOut();
                Intent intent = new Intent(getApplicationContext(),SignInActivity.class);
                mGoogleSignInClient.signOut();
                startActivity(intent);

            }
        });

    }
}