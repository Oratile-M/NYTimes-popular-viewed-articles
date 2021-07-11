package com.example.myapplication;

import android.widget.ImageView;

public class Article {

    String title;
    String byline;
    String url;
    String imageUrl;
    String publishedDate;
    String summary;
    ImageView imageView;

    public Article(String title, String byline, String url, String imageUrl, String publishedDate, String summary, ImageView imageView) {
        this.title = title;
        this.byline = byline;
        this.url = url;
        this.imageUrl = imageUrl;
        this.publishedDate = publishedDate;
        this.summary = summary;
        this.imageView = imageView;
    }
}
