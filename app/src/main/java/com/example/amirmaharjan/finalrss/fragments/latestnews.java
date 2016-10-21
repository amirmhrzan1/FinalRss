package com.example.amirmaharjan.finalrss.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amirmaharjan.finalrss.Parsing.ReadRss;
import com.example.amirmaharjan.finalrss.R;
import com.example.amirmaharjan.finalrss.adaptors.RecyclerAdapters;
import com.example.amirmaharjan.finalrss.model.LatestNews;

import java.util.ArrayList;

/**
 * Created by Amir Maharjan on 10/21/2016.
 */

public class latestnews extends Fragment{
    String url ="http://www.liverpoolfc.com/news.rss";
    boolean state = false;
    ArrayList<LatestNews> latestNews;

    ReadRss networktask;

    View v;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        state = isVisibleToUser;
        Log.d("Statefragment1",""+state);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment,container,false);

            RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.recyclerview);
            networktask=new ReadRss(getActivity(),recyclerView);
            networktask.execute(url);




        return v;
    }


}
