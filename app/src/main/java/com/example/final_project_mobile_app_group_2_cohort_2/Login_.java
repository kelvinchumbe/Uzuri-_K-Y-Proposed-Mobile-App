package com.example.final_project_mobile_app_group_2_cohort_2;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login_ extends Fragment {
    private EditText email_edittext, password_edittext;


    public Login_() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_, container, false);

        email_edittext = view.findViewById(R.id.login_email);
        password_edittext = view.findViewById(R.id.login_pass);
        Button login = view.findViewById(R.id.login_btn);
        TextView forgot_pass = view.findViewById(R.id.forgot_pass);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInput()){
                    final Intent mainpage = new Intent(getActivity(), Main_Page.class);
                    startActivity(mainpage);
                    Toast toast = Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT);
                    toast.show();
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

    public boolean validateInput(){

//        if(email_edittext.getText().toString().isEmpty()){
//            email_edittext.setError("What's your Email");
//            return false;
//        }
//
//        if(password_edittext.getText().toString().isEmpty()){
//            password_edittext.setError("Set a Password");
//            return false;
//        }
//
//        boolean results = SignUp.database.getUser(email_edittext.getText().toString().trim(), password_edittext.getText().toString().trim());
//
//        if(!results){
//            Toast toast = Toast.makeText(getActivity(), "Email or Password does not exist", Toast.LENGTH_SHORT);
//            toast.show();
//
//            return false;
//        }

        return true;
    }

    public void create_alertDialog(String title, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.show();
    }

}
