package com.skincare.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.skincare.R;
import com.skincare.utilities.Loader;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    List<CommentsModel> list;
    Context context;
    FirebaseFirestore db;
    FirebaseUser currentUser;
    ProgressDialog loader;

    public CommentsAdapter(List<CommentsModel> list, Context context) {
        this.list = list;
        this.context = context;
        db = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comments, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommentsModel item = list.get(position);

        holder.tv_comment.setText(item.getComment());
        holder.btn_delete.setOnClickListener(v -> {
            loader = Loader.show(context);
            db.collection("Users")
                    .document(currentUser.getUid())
                    .collection("comments")
                    .document(item.getId())
                    .delete()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            list.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Comment Deleted",
                                    Toast.LENGTH_LONG).show();
                            String count = "Comments (" + list.size() + ")";
                            ProductDetailsFragment.tv_comments_count.setText(count);
                            if (loader != null && loader.isShowing())
                                loader.dismiss();
                        }
                    });
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_comment;
        ImageButton btn_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_comment = itemView.findViewById(R.id.tv_comment);
            btn_delete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
