package com.example.final_project_mobile_app_group_2_cohort_2;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends Fragment {
    private EditText name_edittext, email_edittext, password_edittext;
    public static Database database;



    public SignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        database = new Database(getActivity());

        name_edittext = view.findViewById(R.id.name);
        email_edittext = view.findViewById(R.id.email);
        password_edittext = view.findViewById(R.id.password);

        Button signup_btn = view.findViewById(R.id.signup_btn);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateInput()){
                    database.addUser(name_edittext.getText().toString(), email_edittext.getText().toString(),password_edittext.getText().toString());
                    Toast toast = Toast.makeText(getActivity(), "Login to your Account", Toast.LENGTH_SHORT);
                    toast.show();

                    SignUp_Login.fragmentManager.beginTransaction().replace(R.id.signup_login_container, new Login_(),null).addToBackStack(null).commit();
                }

            }
        });

        return view;
    }

    public boolean validateInput(){
//        if(name_edittext.getText().toString().isEmpty()){
//            name_edittext.setError("What's your Name?");
//            return false;
//        }
//
//        if(email_edittext.getText().toString().isEmpty()){
//            email_edittext.setError("What's your Email");
//            return false;
//        }
//
//        if(password_edittext.getText().toString().isEmpty()){
//            password_edittext.setError("Set a Password");
//            return false;
//        }

        return true;
    }
}
