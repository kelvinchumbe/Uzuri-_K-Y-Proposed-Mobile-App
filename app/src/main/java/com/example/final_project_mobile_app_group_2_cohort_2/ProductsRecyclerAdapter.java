package com.example.final_project_mobile_app_group_2_cohort_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class ProductsRecyclerAdapter extends FirestoreRecyclerAdapter<Products, ProductsRecyclerAdapter.ProductsViewHolder> {

    public ProductsRecyclerAdapter(@NonNull FirestoreRecyclerOptions<Products> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductsViewHolder holder, final int position, @NonNull final Products model) {
        holder.name.setText(model.getName());
        holder.price.setText("$" + String.valueOf(model.getPrice()));
        holder.rating.setText(String.valueOf(model.getRating()));
//                Glide.with(getContext()).load(model.getImage()).into(holder.product_image);
        Picasso.get().load(model.getImage()).into(holder.product_image);

        final Shoe_Details shoe_details = new Shoe_Details();
        Bundle bundle = new Bundle();
        bundle.putString("name",model.getName());
        bundle.putString("category",model.getCategory());
        bundle.putString("description",model.getDescription());
        bundle.putString("rating",String.valueOf(model.getRating()));
        bundle.putString("price",String.valueOf(model.getPrice()));
        bundle.putString("imageUrl",String.valueOf(model.getImage()));
        bundle.putString("pId", String.valueOf(position));
        shoe_details.setArguments(bundle);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main_Page.fragmentManager_main.beginTransaction().replace(R.id.main_page_container, shoe_details, null).addToBackStack(null).commit();
            }
        });
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_category_item, parent, false);
        return new ProductsViewHolder(view);
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView price;
        private TextView rating;
        private ImageView product_image;


        public ProductsViewHolder (@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            product_image = itemView.findViewById(R.id.product_image);
            rating = itemView.findViewById(R.id.rating);

        }
    }
}
