package com.example.final_project_mobile_app_group_2_cohort_2;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Delivery_Details extends Fragment {
    Button near;


    public Delivery_Details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delivery__details, container, false);

        near = view.findViewById(R.id.nearcomplete);

        near.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main_Page.fragmentManager_main.beginTransaction().replace(R.id.main_page_container, new Payment_Details(), null).addToBackStack(null).commit();
            }
        });

        return view;
    }

}
