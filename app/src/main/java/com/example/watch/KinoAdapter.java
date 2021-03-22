package com.example.watch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class KinoAdapter extends RecyclerView.Adapter<KinoAdapter.ViewHolder>
{
    private final static String URL = "http://cinema.areas.su/up/images/";
    private List<KinoParam> mKino;
    private Context context;

    KinoAdapter(List<KinoParam> Kino)
    {
        this.mKino = Kino;
    }

    @Override
    public KinoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        KinoParam kinoParam = mKino.get(position);
        Picasso.with(context)
                .load(URL + kinoParam.getPoster())
                .resize(200, 300)
                .into(holder.kinoImageView);
    }

    @Override
    public int getItemCount()
    {
        if(mKino == null)
        {
            return 0;
        }
        return mKino.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView kinoImageView;
        ViewHolder(View itemView)
        {
            super(itemView);
            kinoImageView = itemView.findViewById(R.id.imageView);
        }
    }
}
