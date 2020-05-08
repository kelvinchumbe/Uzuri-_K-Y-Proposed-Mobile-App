package com.example.final_project_mobile_app_group_2_cohort_2;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login_ extends Fragment {
    private EditText email_edittext, password_edittext;
    TextView forgot_pass;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    Button login;

    public Login_() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_, container, false);

        mAuth = FirebaseAuth.getInstance();

        email_edittext = view.findViewById(R.id.login_email);
        password_edittext = view.findViewById(R.id.login_pass);
        login = view.findViewById(R.id.login_btn);
        forgot_pass = view.findViewById(R.id.forgot_pass);
        progressBar = view.findViewById(R.id.loading_spinner_login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = email_edittext.getText().toString();
                final String password = password_edittext.getText().toString();

                if(validateInput(email, password)){
                    progressBar.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getContext(), "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getActivity(), Main_Page.class));
                            }else {
                                Toast.makeText(getContext(), "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }

                        }
                    });

                    forgot_pass.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MainActivity.fragmentManager.beginTransaction().replace(R.id.signup_login_container, new Login_(),null).addToBackStack(null).commit();
                        }
                    });

                }
            }
        });


        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.signup_login_container, new Forgot_Pass(), null).addToBackStack(null).commit();
            }
        });


        return view;
    }

    public boolean validateInput(String email, String password){
        if(email.isEmpty()){
            email_edittext.setError("What's your Email");
            return false;
        }

        if(password.isEmpty()){
            password_edittext.setError("Set a Password");
            return false;
        }

        return true;
    }
}
