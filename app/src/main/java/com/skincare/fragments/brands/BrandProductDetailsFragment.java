package com.skincare.fragments.brands;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.skincare.R;
import com.squareup.picasso.Picasso;

public class BrandProductDetailsFragment extends Fragment implements View.OnClickListener {

    private String name = "", desc = "", image_url = "", product_url = "", ingredients = "";
    private long product_id;

    private NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            product_id = getArguments().getLong("id");
            name = getArguments().getString("name");
            desc = getArguments().getString("desc");
            image_url = getArguments().getString("image_url");
            product_url = getArguments().getString("product_url");
            ingredients = getArguments().getString("ingredients");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_brand_product_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        //Displaying data...
        ImageView img_brand_product = view.findViewById(R.id.img_brand_product);
        Picasso.get().load(image_url).placeholder(R.drawable.no_image).into(img_brand_product);

        TextView tv_product_name = view.findViewById(R.id.tv_product_name);
        tv_product_name.setText(name);

        TextView tv_product_desc = view.findViewById(R.id.tv_product_desc);
        tv_product_desc.setText(desc);

        TextView tv_product_ingredients = view.findViewById(R.id.tv_product_ingredients);
        tv_product_ingredients.setText(ingredients);

        Button btn_details = view.findViewById(R.id.btn_details);
        btn_details.setOnClickListener(this);

        Button btn_buy_now = view.findViewById(R.id.btn_buy_now);
        btn_buy_now.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.btn_details) {
            Bundle args = new Bundle();
            args.putLong("id", product_id);
            navController.navigate(R.id.action_nav_brand_product_details_to_nav_product_details, args);
        } else if (id == R.id.btn_buy_now) {
            Uri uri = Uri.parse(product_url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }
}