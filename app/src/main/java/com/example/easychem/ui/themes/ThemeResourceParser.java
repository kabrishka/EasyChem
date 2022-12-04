package com.example.easychem.ui.themes;

import org.xmlpull.v1.XmlPullParser;
import java.util.ArrayList;

public class ThemeResourceParser {
    private ArrayList<Theme> themes;
    private String title;

    public ThemeResourceParser(){
        themes = new ArrayList<>();
    }

    public ArrayList<Theme> getThemes(){
        return  themes;
    }

    public String getTitle() { return title; }

    public boolean parse(XmlPullParser xpp){
        boolean status = true;
        Theme currentTheme = null;
        boolean inEntry = false;
        String textValue = "";

        try{
            int eventType = xpp.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){

                String tagName = xpp.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if("substance".equalsIgnoreCase(tagName)){
                            inEntry = true;
                            currentTheme = new Theme();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(inEntry){
                            if("substance".equalsIgnoreCase(tagName)){
                                title = tagName;
                                themes.add(currentTheme);
                                inEntry = false;
                            } else if("title".equalsIgnoreCase(tagName)){
                                currentTheme.setTitle(textValue);
                            } else if("content".equalsIgnoreCase(tagName)){
                                currentTheme.setContent(textValue);
                            }
                        }
                        break;
                    default:
                }
                eventType = xpp.next();
            }
        }
        catch (Exception e){
            status = false;
            e.printStackTrace();
        }
        return  status;
    }
}
