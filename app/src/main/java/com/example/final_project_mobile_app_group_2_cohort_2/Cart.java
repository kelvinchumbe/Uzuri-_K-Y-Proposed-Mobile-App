package com.example.final_project_mobile_app_group_2_cohort_2;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class Cart extends Fragment {
    RecyclerView recyclerview_cart;
    private FirebaseFirestore db;
    private CartRecyclerAdapter cartRecyclerAdapter;
    Button proceed;

    public Cart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        db = FirebaseFirestore.getInstance();
        recyclerview_cart = view.findViewById(R.id.recyclerview_cart);
        proceed= view.findViewById(R.id.proceedcheckout);

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation_cart);
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
                        Main_Page.fragmentManager_main.beginTransaction().replace(R.id.main_page_container, new Cart(), null).addToBackStack(null).commit();
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

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main_Page.fragmentManager_main.beginTransaction().replace(R.id.main_page_container, new Delivery_Details(), null).addToBackStack(null).commit();

            }
        });

        return view;
    }

    public void initRecyclerView(){
        Query querycart = db.collection("cartlist");

        FirestoreRecyclerOptions options = new FirestoreRecyclerOptions.Builder<Cart_Product>()
                .setQuery(querycart, Cart_Product.class)
                .build();

        cartRecyclerAdapter = new CartRecyclerAdapter(options);

        recyclerview_cart.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerview_cart.setAdapter(cartRecyclerAdapter);

        cartRecyclerAdapter.startListening();
    }


    @Override
    public void onStop() {
        super.onStop();
        if(cartRecyclerAdapter != null){
            cartRecyclerAdapter.stopListening();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(cartRecyclerAdapter != null){
            cartRecyclerAdapter.startListening();
        }

        initRecyclerView();
    }


}
