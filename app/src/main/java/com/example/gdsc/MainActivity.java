package com.example.gdsc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;

import com.example.gdsc.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_events, R.id.navigation_faq)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main2);

        NavigationUI.setupWithNavController(binding.navView, navController);

        profile = findViewById(R.id.profile);

        Bundle extras = getIntent().getExtras();

        Glide.with(getApplicationContext()).load(extras.get("Photo")).into(profile);

        Log.i("name",extras.get("Name").toString());

        profile.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("Name",extras.get("Name").toString());
            intent.putExtra("Photo",extras.get("Photo").toString());
            intent.putExtra("Email",extras.get("Email").toString());
            startActivity(intent);

        });




    }


}