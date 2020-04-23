package com.example.sos;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class InfoCardHandler {

    private List<InfoAdapter> infoAdapters;
    private ViewPager infoViewPager;
    private InfoSlider infoSliderAdapter;
    private TextView pageIndicators[];
    private View view;
    private Context context;
    private LinearLayout linearDots;

    //declare constants
    final int bullet_size = 40;

    public InfoCardHandler(View view, Context context){
        //set variable values
        this.view = view;
        this.context = context;
        infoViewPager = view.findViewById(R.id.infoViewPager);
        linearDots = view.findViewById(R.id.linearDots);
    }

    private void syncDots(int currentCardPosition) {
        //set number of dots equal to number of info cards
        pageIndicators = new TextView[infoAdapters.size()];
        //remove previous added bullets from the layout
        linearDots.removeAllViews();

        for (int i = 0; i < infoAdapters.size(); i++) {
            //create new textview to be displayed
            pageIndicators[i] = new TextView(context);
            //set the bullets or circles to the text view
            //next line will convert to html
            pageIndicators[i].setText(Html.fromHtml("&#8226"));
            pageIndicators[i].setTextSize(bullet_size); //set the font size of the bullets
            //now set the color to dark for the dot which is currently active
            if (i == currentCardPosition) {
                pageIndicators[i].setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            } else { //set lighter color for inactive dots
                pageIndicators[i].setTextColor(ContextCompat.getColor(context, R.color.colorLight));
            }
            linearDots.addView(pageIndicators[i]);
        }

    }
    public void showInfoScreen(){
        //create a list of info cards
        createInfoList();
        infoSliderAdapter = new InfoSlider(context,infoAdapters);
        infoViewPager.setAdapter(infoSliderAdapter);
        syncDots(0);
    }
    private void createInfoList(){
        infoAdapters = new ArrayList<InfoAdapter>();
        InfoAdapter infoAdapter = new InfoAdapter();
        //add items to the list that will be displayed on first launch for user info
        infoAdapter.setImage(R.drawable.sos_info_screen);
        infoAdapter.setTitle(context.getString(R.string.sos_info_title));
        infoAdapter.setSubtitle(context.getString(R.string.sos_info_subtitle));
        infoAdapters.add(infoAdapter);

        infoAdapter = new InfoAdapter();
        infoAdapter.setImage(R.drawable.contact_info_screen);
        infoAdapter.setTitle(context.getString(R.string.contact_info_title));
        infoAdapter.setSubtitle(context.getString(R.string.contact_info_subtitle));
        infoAdapters.add(infoAdapter);

        infoAdapter = new InfoAdapter();
        infoAdapter.setImage(R.drawable.shake_info_screen);
        infoAdapter.setTitle(context.getString(R.string.shake_info_title));
        infoAdapter.setSubtitle(context.getString(R.string.shake_info_subtitle));
        infoAdapters.add(infoAdapter);
    }
    public void setInfoCardsHandler(){
        infoViewPager.addOnPageChangeListener(infoPagehandler);
    }
    /*create a handler which will check which card has been selected
    so that the dots are being synced with the selected page
    */
    ViewPager.OnPageChangeListener infoPagehandler = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            syncDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
