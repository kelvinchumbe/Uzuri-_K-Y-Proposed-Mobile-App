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

public class CartRecyclerAdapter extends FirestoreRecyclerAdapter<Cart_Product, CartRecyclerAdapter.CartProductViewHolder> {

    public CartRecyclerAdapter(@NonNull FirestoreRecyclerOptions<Cart_Product> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CartRecyclerAdapter.CartProductViewHolder holder, int position, @NonNull Cart_Product model) {
        holder.name.setText(model.getName());
        holder.price.setText("$" + String.valueOf(model.getPrice()));
        holder.category.setText(model.getCategory());
//        holder.quantity.setText(model.getQuantity());
        Picasso.get().load(model.getImageUrl()).into(holder.prodImage);

        final Shoe_Details shoe_details = new Shoe_Details();
//        Bundle bundle = new Bundle();
//        bundle.putString("name",model.getName());
//        bundle.putString("category",model.getCategory());
//        bundle.putString("description",model.getDescription());
//        bundle.putString("rating",String.valueOf(model.getRating()));
//        bundle.putString("price",String.valueOf(model.getPrice()));
//        bundle.putString("imageUrl",String.valueOf(model.getImage()));
//        shoe_details.setArguments(bundle);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main_Page.fragmentManager_main.beginTransaction().replace(R.id.main_page_container, shoe_details, null).addToBackStack(null).commit();
            }
        });
    }

    @NonNull
    @Override
    public CartRecyclerAdapter.CartProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_product_item, parent, false);
        return new CartProductViewHolder(view);
    }

    class CartProductViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView price;
        private TextView category;
        private TextView quantity;
        private ImageView prodImage;


        public CartProductViewHolder (@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.cartitem_name);
            price = itemView.findViewById(R.id.cartitem_price);
            category = itemView.findViewById(R.id.cartitem_category);
            quantity = itemView.findViewById(R.id.cartitem_quantity);
            prodImage = itemView.findViewById(R.id.cartitem_image);

        }
    }
}
