package com.example.final_project_mobile_app_group_2_cohort_2;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends Fragment {
    private EditText name_edittext, email_edittext, password_edittext;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    String userID;
    String TAG = "Sign Up";


    public SignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        name_edittext = view.findViewById(R.id.name);
        email_edittext = view.findViewById(R.id.email);
        password_edittext = view.findViewById(R.id.password);

        Button signup_btn = view.findViewById(R.id.signup_btn);


        // Define progressBar in xml and find by ID here
        progressBar = view.findViewById(R.id.loading_spinner);

        if(mAuth.getCurrentUser() != null){
//            startActivity(new Intent(getContext(), MainActivity.class));
//            MainActivity.fragmentManager.beginTransaction().replace(R.id.signup_login_container, new Login_(),null).addToBackStack(null).commit();

        }



        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = email_edittext.getText().toString();
                final String password = password_edittext.getText().toString();
                final String  name = name_edittext.getText().toString();

                if(validateInput(name, email, password)){
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.VISIBLE);
                            if(task.isSuccessful()){

                                FirebaseUser fuser = mAuth.getCurrentUser();
                                fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getContext(), "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
                                    }
                                });

                                Toast.makeText(getContext(), "Account Created", Toast.LENGTH_SHORT).show();

                                userID = mAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = db.collection("users").document(userID);
                                Map<String,Object> user = new HashMap<>();
                                user.put("name",name);
                                user.put("email",email);

                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "onSuccess: User created: "+ userID);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure: " + e.toString());
                                    }
                                });

                                MainActivity.fragmentManager.beginTransaction().replace(R.id.signup_login_container, new Login_(),null).addToBackStack(null).commit();
                            }
                            else{
                                Toast.makeText(getContext(), "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }

                }
        });

        return view;
    }

    public boolean validateInput(String name, String email, String password){
        if(name.isEmpty()){
            name_edittext.setError("What's your Name?");
            return false;
        }

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
