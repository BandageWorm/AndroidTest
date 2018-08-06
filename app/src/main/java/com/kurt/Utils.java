package com.kurt;

import android.graphics.Color;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import org.xml.sax.XMLReader;

/**
 * Created by kurtg on 18/2/3.
 */

public class Utils {


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
