package com.example.final_project_mobile_app_group_2_cohort_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;


public class Main_Page extends AppCompatActivity {


    public static FragmentManager fragmentManager_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__page);

        fragmentManager_main = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager_main.beginTransaction();
        Home_Page_ home_page = new Home_Page_();
        ft.add(R.id.main_page_container, home_page, null);
        ft.commit();



    }
}
