package com.example.final_project_mobile_app_group_2_cohort_2;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class Forgot_Pass extends Fragment {
    FirebaseAuth mAuth;
    EditText forgot_email;
    Button forgot_btn;


    public Forgot_Pass() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forgot__pass, container, false);

        mAuth = FirebaseAuth.getInstance();
        forgot_btn = view.findViewById(R.id.forgot_btn);
        forgot_email = view.findViewById(R.id.forgot_email);

        forgot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = forgot_email.getText().toString();

                if(validateInput(email)){
                    mAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getContext(), "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                MainActivity.fragmentManager.beginTransaction().replace(R.id.signup_login_container, new Login_(),null).addToBackStack(null).commit();
            }
        });



        return view;
    }

    public boolean validateInput(String email){
        if(email.isEmpty()){
            forgot_email.setError("What's your Email");
            return false;
        }

        return true;
    }

}
