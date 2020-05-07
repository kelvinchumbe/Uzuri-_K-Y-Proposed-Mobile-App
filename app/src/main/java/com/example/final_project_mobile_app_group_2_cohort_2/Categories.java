package com.example.final_project_mobile_app_group_2_cohort_2;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class Categories extends Fragment {
    RecyclerView recyclerview_categories;
    private FirestoreRecyclerAdapter firestoreRecyclerAdapter;
    private FirebaseFirestore db;



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

        Query querycategories = db.collection("products");

        FirestoreRecyclerOptions<Products> options = new FirestoreRecyclerOptions.Builder<Products>()
                .setQuery(querycategories, Products.class)
                .build();

        firestoreRecyclerAdapter = new FirestoreRecyclerAdapter<Products, ProductsViewHolder>(options) {
            @NonNull
            @Override
            public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_category_item, parent, false);
                return new ProductsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ProductsViewHolder holder, int position, @NonNull Products model) {
                holder.name.setText(model.getName());
                holder.price.setText("$" + String.valueOf(model.getPrice()));
//                Glide.with(getContext()).load(model.getImage()).into(holder.product_image);
                Picasso.get().load(model.getImage()).into(holder.product_image);

            }

            // View Holder
        };

        recyclerview_categories.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerview_categories.setAdapter(firestoreRecyclerAdapter);

        return view;
    }

    private class ProductsViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView price;
        private ImageView product_image;

        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            product_image = itemView.findViewById(R.id.product_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Main_Page.fragmentManager_main.beginTransaction().replace(R.id.main_page_container, new Shoe_Details(), null).addToBackStack(null).commit();
                }
            });

        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(firestoreRecyclerAdapter != null){
            firestoreRecyclerAdapter.stopListening();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        if(firestoreRecyclerAdapter != null){
            firestoreRecyclerAdapter.startListening();
        }

    }

}
