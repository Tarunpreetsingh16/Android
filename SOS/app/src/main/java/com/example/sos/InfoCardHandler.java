package com.example.sos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Html;
import android.view.View;
import android.widget.Button;
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
    private Button btnPrevious;
    private Button btnNext;

    //declare global variables
    private int currentCardNumber = 0;
    //declare constants
    final int bullet_size = 40;

    public InfoCardHandler(View view, Context context){
        //set variable values
        this.view = view;
        this.context = context;
        infoViewPager = view.findViewById(R.id.infoViewPager);
        linearDots = view.findViewById(R.id.linearDots);
        //initialize Buttons
        btnNext = view.findViewById(R.id.btnNext);
        btnPrevious= view.findViewById(R.id.btnPrevious);
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
            //store current card position to use it in button functionality
            currentCardNumber = position;
            //check which page is selected then change the attributes of buttons
            if(position == 0){//when first info card is being displayed
                btnPrevious.setVisibility(View.INVISIBLE);
                btnNext.setText(R.string.next);
            }
            //when last is being displayed
            else if(position == infoAdapters.size() - 1){
                btnPrevious.setVisibility(View.VISIBLE);
                btnNext.setText(R.string.finish);
            }
            else{
                btnPrevious.setVisibility(View.VISIBLE);
                btnNext.setText(R.string.next);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    public void setupSupplementary(){
        //when next button is clicked
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if not on last info card
                if(btnNext.getText() == context.getString(R.string.next)){
                    infoViewPager.setCurrentItem(currentCardNumber + 1);
                }
                else{
                    //store that app has already been launched previously
                    SharedPreferences sharedPref = context.getSharedPreferences(
                            context.getString(R.string.app_pref), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean(context.getString(R.string.first_launch_check),true);
                    editor.commit();
                    //this is used to stop showing info screen again and again
                    //go to next screen
                    Intent landingScreen = new Intent(context,LandingScreen.class);
                    landingScreen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(landingScreen);
                    ((Activity)context).finish();
                }
            }
        });
        //when previous button is clicked
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoViewPager.setCurrentItem(currentCardNumber - 1);
            }
        });
    }
}
