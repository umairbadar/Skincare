package com.skincare.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.skincare.R;
import com.squareup.picasso.Picasso;

public class ProductDetailsFragment extends Fragment {

    private String name = "", desc = "", image_url = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){
            name = getArguments().getString("name");
            desc = getArguments().getString("desc");
            image_url = getArguments().getString("image_url");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView img_product = view.findViewById(R.id.img_product);
        Picasso.get().load(image_url).placeholder(R.drawable.no_image).into(img_product);

        /*TextView tv_product_name = view.findViewById(R.id.tv_product_name);
        tv_product_name.setText(name);*/
    }
}