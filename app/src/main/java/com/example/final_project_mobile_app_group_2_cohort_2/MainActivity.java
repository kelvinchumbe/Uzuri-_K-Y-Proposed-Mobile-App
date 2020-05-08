package com.example.final_project_mobile_app_group_2_cohort_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db;
    FirebaseAuth mAuth;


    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        fragmentManager = getSupportFragmentManager();
        Button signup_choice = findViewById(R.id.signup_choice);
        Button login_choice = findViewById(R.id.login_choice);

        signup_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                SignUp signUp= new SignUp();
                ft.add(R.id.signup_login_container, signUp, null);
                ft.commit();
            }
        });

        login_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = fragmentManager.beginTransaction();
                Login_ login_= new Login_();
                ft.add(R.id.signup_login_container, login_, null);
                ft.commit();
            }
        });


    }
}
