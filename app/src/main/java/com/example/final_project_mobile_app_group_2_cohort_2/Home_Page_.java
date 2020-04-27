package com.example.final_project_mobile_app_group_2_cohort_2;


import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Page_ extends Fragment {


    public Home_Page_() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home__page_, container, false);

        Map<String,String> products = new HashMap<>();
        products.put("prod1", "img1");
        products.put("prod2","img2");
        products.put("prod3","img3");
        products.put("prod4","img4");
        products.put("prod5","img5");
        products.put("prod6","img6");
        products.put("prod7","img7");
        products.put("prod8","img8");
        products.put("prod9","img9");
        products.put("prod10","img10");

        Map<String,String[]> category = new HashMap<>();
        category.put("cat1",new String[]{"prod1","prod2","prod3","prod4","prod5"});
        category.put("cat2",new String[]{"prod6","prod7","prod8","prod9","prod10"});

        RecyclerView recyclerview_trends = view.findViewById(R.id.recyclerview_trends);
        recyclerview_trends.setHasFixedSize(true);

        LinearLayoutManager  linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerview_trends.setLayoutManager(linearLayoutManager);

        CardView card1 = view.findViewById(R.id.card1);




        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main_Page.fragmentManager_main.beginTransaction().replace(R.id.main_page_container, new Shoe_Details(), null).addToBackStack(null).commit();
            }
        });

        return view;
    }

}
