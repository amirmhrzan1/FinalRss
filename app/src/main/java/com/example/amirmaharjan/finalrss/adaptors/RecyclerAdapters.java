package com.example.amirmaharjan.finalrss.adaptors;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amirmaharjan.finalrss.R;
import com.example.amirmaharjan.finalrss.helper.Fonthelper;
import com.example.amirmaharjan.finalrss.model.LatestNews;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Amir Maharjan on 10/21/2016.
 */

public class RecyclerAdapters extends RecyclerView.Adapter<RecyclerAdapters.MyViewHolder> {

    ArrayList<LatestNews> latestNews;
    Context context;
   // Fonthelper fonthelper;


    public RecyclerAdapters(Context context, ArrayList<LatestNews> latestNews){
        this.latestNews=latestNews;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
     //   Fonthelper fonthelper = new Fonthelper((Activity) context);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        LatestNews current = latestNews.get(position);
        holder.title.setText(current.getTitle());
       // holder.title.setTypeface(fonthelper.getDefaultFont("bold"));
        holder.description.setText(current.getDescription());
       // holder.description.setTypeface(fonthelper.getDefaultFont("normal"));
        holder.link.setText(current.getLink());
      //  holder.link.setTypeface(fonthelper.getDefaultFont("heavy"));
        holder.publishdate.setText(current.getPublishdate());
       // holder.publishdate.setTypeface(fonthelper.getDefaultFont("bold"));
        Picasso.with(context).load(current.getThumb()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return latestNews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, description, publishdate, link;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);

            description=(TextView)itemView.findViewById(R.id.description);
            publishdate=(TextView)itemView.findViewById(R.id.publishdate);
            link = (TextView)itemView.findViewById(R.id.link);
            image=(ImageView)itemView.findViewById(R.id.imageview);
        }
    }
}
