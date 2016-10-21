package com.example.amirmaharjan.finalrss.helper;

import android.app.Activity;
import android.graphics.Typeface;

/**
 * Created by Amir Maharjan on 10/21/2016.
 */

public class Fonthelper {
    Activity activity;
    private Typeface fontbold;
    private Typeface fontheavy;
    private Typeface fontlight;
    private Typeface fontmedium;
    private Typeface fontregular;

    public Fonthelper(Activity activity)
    {
        this.activity = activity;
        this.fontlight = Typeface.createFromAsset(this.activity.getAssets(), "fonts/Raleway-Light.ttf");
        this.fontheavy = Typeface.createFromAsset(this.activity.getAssets(), "fonts/Raleway-Heavy.ttf");
        this.fontbold = Typeface.createFromAsset(this.activity.getAssets(), "fonts/Raleway-Bold.ttf");
        this.fontmedium = Typeface.createFromAsset(this.activity.getAssets(), "fonts/Raleway-Medium.ttf");
        this.fontregular = Typeface.createFromAsset(this.activity.getAssets(), "fonts/Raleway-Regular.ttf");
    }

    public Typeface getDefaultFont(){
        return this.fontregular;
    }

    public Typeface getDefaultFont(String type){
        if(type.equalsIgnoreCase("light"))
            return this.fontlight;
        else if(type.equalsIgnoreCase("regular"))
            return this.fontregular;
        else if(type.equalsIgnoreCase("medium"))
            return this.fontmedium;
        else if(type.equalsIgnoreCase("bold"))
            return this.fontbold;
        else
            return this.fontheavy;


    }


}
