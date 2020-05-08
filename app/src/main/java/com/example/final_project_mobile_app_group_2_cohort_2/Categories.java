package com.example.final_project_mobile_app_group_2_cohort_2;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class Categories extends Fragment {
    RecyclerView recyclerview_categories;
    private FirebaseFirestore db;
    private ProductsRecyclerAdapter productsRecyclerAdapter;

    public Categories() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        db = FirebaseFirestore.getInstance();
        recyclerview_categories = view.findViewById(R.id.recyclerview_categories);

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation_categories);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Main_Page.fragmentManager_main.beginTransaction().replace(R.id.main_page_container, new Home_Page_(), null).addToBackStack(null).commit();
                        break;
                    case R.id.action_categories:
                        Main_Page.fragmentManager_main.beginTransaction().replace(R.id.main_page_container, new Categories(), null).addToBackStack(null).commit();
                        break;
                    case R.id.action_cart:
                        Toast.makeText(getActivity(), "Cart", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_profile:
                        Main_Page.fragmentManager_main.beginTransaction().replace(R.id.main_page_container, new Profile(), null).addToBackStack(null).commit();

                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + item.getItemId());
                }
                return true;
            }
        });

        return view;
    }


    public void initRecyclerView(){
        Query querycategories = db.collection("products");

        FirestoreRecyclerOptions options = new FirestoreRecyclerOptions.Builder<Products>()
                .setQuery(querycategories, Products.class)
                .build();

        productsRecyclerAdapter = new ProductsRecyclerAdapter(options);

        recyclerview_categories.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerview_categories.setAdapter(productsRecyclerAdapter);

        productsRecyclerAdapter.startListening();
    }


    @Override
    public void onStop() {
        super.onStop();
        if(productsRecyclerAdapter != null){
            productsRecyclerAdapter.stopListening();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(productsRecyclerAdapter != null){
            productsRecyclerAdapter.startListening();
        }

        initRecyclerView();
    }

}
