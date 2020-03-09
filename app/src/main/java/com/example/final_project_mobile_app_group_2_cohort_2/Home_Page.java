package com.example.final_project_mobile_app_group_2_cohort_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Home_Page extends AppCompatActivity {
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);

        ImageView thumbnail = findViewById(R.id.thumbnail);
        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();
                Shoe_Details shoe_details = new Shoe_Details();

                if(findViewById(R.id.main_container) != null) {
                    if (savedInstanceState != null) {
                        return;
                    }


                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.main_container, shoe_details, null).commit();
                }

            }
        });
    }
}