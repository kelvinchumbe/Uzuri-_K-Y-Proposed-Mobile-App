package com.example.final_project_mobile_app_group_2_cohort_2;



import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class Shoe_Details extends Fragment {
    ImageView prod_img;
    TextView prod_name, prod_price, prod_category, prod_description;
    RatingBar prod_rating;


    public Shoe_Details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_shoe__details, container, false);

        prod_price = view.findViewById(R.id.prod_price);
        prod_name = view.findViewById(R.id.prod_name);
        prod_category = view.findViewById(R.id.prod_categories);
        prod_description = view.findViewById(R.id.prod_description);
        prod_img = view.findViewById(R.id.prodImg);
        prod_rating = view.findViewById(R.id.prod_rating);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        Bundle arguments = getArguments();

        if (arguments!=null){
            String name = arguments.get("name").toString();
            String price = arguments.get("price").toString();
            String description = arguments.get("description").toString();
            String category = arguments.get("category").toString();
            String rating = arguments.get("rating").toString();
            String imageUrl = arguments.get("imageUrl").toString();

            prod_name.setText(name);
            prod_price.setText("$" + price);
            prod_description.setText(description);
            prod_category.setText(category);
            prod_rating.setRating(Float.parseFloat(rating));
            Picasso.get().load(imageUrl).into(prod_img);
        }

    }
}
