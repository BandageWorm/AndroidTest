package com.qiming.kurtapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.bluelinelabs.logansquare.LoganSquare;
import com.qiming.kurtapp.entity.Example;

import org.xml.sax.XMLReader;

import java.io.InputStream;

/**
 * Created by kurtg on 18/2/3.
 */

public class Utils {

    public static Example getJsonExp(Context context) {
        AssetManager am = context.getAssets();
        try {
            InputStream is = am.open("example.json");
            StringBuilder sb = new StringBuilder();
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            String json = new String(buffer, "utf-8");
            is.close();
            Example example = LoganSquare.parse(json, Example.class);
            return example;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CharSequence highlightTitle(String title) {
        CharSequence res = Html.fromHtml("(" + title + ")", null, new Html.TagHandler() {
            private int sIndex = 0;
            private  int eIndex = 0;

            @Override
            public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
                if (tag.toLowerCase().equals("start")){
                    if (opening) {
                        sIndex = output.length();
                    }
                    else{
                        eIndex = output.length();
                        output.setSpan(new ForegroundColorSpan(Color.parseColor("#fdad00")), sIndex, eIndex
                                , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
            }
        });
        return res.subSequence(1, res.length() - 1);
    }

}
