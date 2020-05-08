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
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Shoe_Details extends Fragment {
    ImageView prod_img;
    TextView prod_name, prod_price, prod_category, prod_description;
    RatingBar prod_rating;
    Button addToCart;

    String name, price, description, category, rating, imageUrl, pId;
    final String TAG = "Cart Item";

    FirebaseFirestore db;

    public Shoe_Details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_shoe__details, container, false);

        db = FirebaseFirestore.getInstance();

        prod_price = view.findViewById(R.id.prod_price);
        prod_name = view.findViewById(R.id.prod_name);
        prod_category = view.findViewById(R.id.prod_categories);
        prod_description = view.findViewById(R.id.prod_description);
        prod_img = view.findViewById(R.id.prodImg);
        prod_rating = view.findViewById(R.id.prod_rating);
        addToCart =view.findViewById(R.id.addToCart);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle arguments = getArguments();

        if (arguments!=null){
            name = arguments.get("name").toString();
            price = arguments.get("price").toString();
            description = arguments.get("description").toString();
            category = arguments.get("category").toString();
            rating = arguments.get("rating").toString();
            imageUrl = arguments.get("imageUrl").toString();
            pId = arguments.get("pId").toString();

            prod_name.setText(name);
            prod_price.setText("$" + price);
            prod_description.setText(description);
            prod_category.setText(category);
            prod_rating.setRating(Float.parseFloat(rating));
            Picasso.get().load(imageUrl).into(prod_img);
        }

        final Map<String, Object> cartitem = new HashMap<>();
        cartitem.put("name", name);
        cartitem.put("price", Float.parseFloat(price));
        cartitem.put("category", category);
        cartitem.put("imageUrl", imageUrl);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("cartlist").document(pId)
                        .set(cartitem)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error writing document", e);
                            }
                        });

                Main_Page.fragmentManager_main.beginTransaction().replace(R.id.main_page_container, new Cart(), null).addToBackStack(null).commit();

            }
        });

    }
}
