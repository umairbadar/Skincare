package com.skincare.fragments;

public class CommentsModel {

    private String id;
    private String comment;
    private long product_id;

    public CommentsModel(String id, String comment, long product_id) {
        this.id = id;
        this.comment = comment;
        this.product_id = product_id;
    }

    public String getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public long getProduct_id() {
        return product_id;
    }
}
