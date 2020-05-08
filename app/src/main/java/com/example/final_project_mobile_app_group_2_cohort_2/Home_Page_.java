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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Page_ extends Fragment {
    private RecyclerView recyclerview_trends;
    RecyclerView recyclerview_popular;
    private FirestoreRecyclerAdapter firestoreRecyclerAdapter;
    private FirestoreRecyclerAdapter firestoreRecyclerAdapter_popular;
    private FirebaseFirestore db;
    private ProductsHomeRecyclerAdapter productsHomeRecyclerAdapter_trends;
    private ProductsHomeRecyclerAdapter productsHomeRecyclerAdapter_popular;


    public Home_Page_() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home__page_, container, false);

       db = FirebaseFirestore.getInstance();

        recyclerview_trends = view.findViewById(R.id.recyclerview_trends);
        recyclerview_popular = view.findViewById(R.id.recyclerview_popular);


        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation);
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

    public void initRecyclerViews(){
        Query querytrends = db.collection("products").orderBy("name").limit(5);
        Query querypopular = db.collection("products").orderBy("rating").limit(5);

        FirestoreRecyclerOptions<Products> optionstrends = new FirestoreRecyclerOptions.Builder<Products>()
                .setQuery(querytrends, Products.class)
                .build();

        FirestoreRecyclerOptions<Products> optionspopular = new FirestoreRecyclerOptions.Builder<Products>()
                .setQuery(querypopular, Products.class)
                .build();

        productsHomeRecyclerAdapter_trends = new ProductsHomeRecyclerAdapter(optionstrends);
        productsHomeRecyclerAdapter_popular = new ProductsHomeRecyclerAdapter(optionspopular);

        recyclerview_trends.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerview_trends.setAdapter(productsHomeRecyclerAdapter_trends);

        recyclerview_popular.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerview_popular.setAdapter(productsHomeRecyclerAdapter_popular);

        productsHomeRecyclerAdapter_trends.startListening();
        productsHomeRecyclerAdapter_popular.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        if(firestoreRecyclerAdapter != null && firestoreRecyclerAdapter_popular != null){
            firestoreRecyclerAdapter.stopListening();
            firestoreRecyclerAdapter_popular.stopListening();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        if(firestoreRecyclerAdapter != null && firestoreRecyclerAdapter_popular != null){
            firestoreRecyclerAdapter.startListening();
            firestoreRecyclerAdapter_popular.startListening();
        }

        initRecyclerViews();
    }

}
