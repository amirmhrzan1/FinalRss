package com.example.amirmaharjan.finalrss.Parsing;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.amirmaharjan.finalrss.adaptors.RecyclerAdapters;
import com.example.amirmaharjan.finalrss.model.LatestNews;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Amir Maharjan on 10/21/2016.
 */

public class ReadRss extends AsyncTask<String,Void,Void> {


    Context context;
    private String address;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;

    ArrayList<LatestNews> latestNews;
    URL url;

    public ReadRss(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");

    }



    @Override
    protected void onPreExecute() {
       progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... params) {
        address = params[0];
        ProcessXml(getdata(address));

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        RecyclerAdapters adapter = new RecyclerAdapters(context,latestNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }



    private void ProcessXml(Document data) {
        if (data != null) {
            latestNews = new ArrayList<>();
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);
            NodeList items = channel.getChildNodes();
            Log.d("number", "" + items.getLength());
            for (int i = 0; i < items.getLength(); i++) {
                Node currentchild = items.item(i);
                if (currentchild.getNodeName().equalsIgnoreCase("item")) {
                    LatestNews item = new LatestNews();
                    NodeList itemchild = currentchild.getChildNodes();
                    for (int j = 0; j < itemchild.getLength(); j++) {
                        Node current = itemchild.item(j);
                        if (current.getNodeName().equalsIgnoreCase("title")) {
                            item.setTitle(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("description")) {
                            item.setDescription(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("link")) {
                            item.setLink(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("thumb")) {
                            item.setThumb(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("pubDate")) {
                            item.setPublishdate(current.getTextContent());
                        }

                    }
                    latestNews.add(item);

                    Log.d("itemtitle", item.getTitle());
                    Log.d("itemDescription", item.getDescription());
                    Log.d("itemlink", item.getLink());
                    Log.d("itempubDate", item.getPublishdate());
                    Log.d("itemthumb", item.getThumb());
                }


            }

        }


    }

    public Document getdata(String address){
        try {
            url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            InputStream inputStream = conn.getInputStream();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document xmldocument = documentBuilder.parse(inputStream);
            return xmldocument;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
