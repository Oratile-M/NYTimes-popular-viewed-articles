package com.example.myapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    List<Article> articleList;

    public ArticleAdapter (List<Article> articleList) {
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View articleItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_row, parent, false);
        return new ArticleViewHolder(articleItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.title.setText(article.title);
        holder.summary.setText(article.summary);
        holder.url = article.url;
        Picasso.get().load(article.imageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder{
        TextView title, summary;
        ImageView imageView;
        LinearLayout parent;
        String url;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            title = itemView.findViewById(R.id.articleTitle);
            summary = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), ArticlePageView.class);
                    intent.putExtra("url", url);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
