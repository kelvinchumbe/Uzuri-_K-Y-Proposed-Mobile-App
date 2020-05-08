package com.example.final_project_mobile_app_group_2_cohort_2;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.Objects;


public class Profile extends Fragment {
    TextView user_name, user_email;
    Button update_profile, logout;
    EditText edit_email, edit_password;

    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser user;
    String userID;
    ListenerRegistration listenerRegistration;


    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        user_email = view.findViewById(R.id.user_email);
        user_name = view.findViewById(R.id.user_name);
        update_profile = view.findViewById(R.id.update_profile);
        edit_password = view.findViewById(R.id.edit_password);
        logout = view.findViewById(R.id.logout);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser();
        userID = user.getUid();

        DocumentReference documentReference = db.collection("users").document(userID);
        listenerRegistration = documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    user_name.setText(documentSnapshot.getString("name"));
                    user_email.setText(documentSnapshot.getString("email"));

                }else {
                    Log.d("tag", "onEvent: Document does not exists");
                }
            }
        });

        update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = edit_email.getText().toString();
                final String password = edit_password.getText().toString();

                if(!password.isEmpty()){
                    user.updatePassword(password).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getActivity(), "Password Updated Successfully.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Password Update Failed.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

        }});

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();//logout
                MainActivity.fragmentManager.beginTransaction().replace(R.id.signup_login_container, new Login_(),null).addToBackStack(null).commit();
            }
        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();

    }

}
