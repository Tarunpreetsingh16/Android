package com.example.sos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class InfoSlider extends PagerAdapter {

    Context context;
    //list to store all information cards
    List<InfoAdapter> infoAdapter = new ArrayList<InfoAdapter>();

    public InfoSlider(Context context,List<InfoAdapter> infoAdapter){
        this.context = context;
        this.infoAdapter = infoAdapter;
    }
    @Override
    public int getCount() {
        return infoAdapter.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //get the inflator to convert the view to object in memory
        LayoutInflater inflator = (LayoutInflater) context.getSystemService((context.LAYOUT_INFLATER_SERVICE));
        //inflate / convert the layout into an object
        View view = inflator.inflate(R.layout.info_screen,container,false);

        //now find ui elements to be used by our application
        ImageView image = view.findViewById(R.id.imgInfoScreen);
        TextView title = view.findViewById(R.id.txtTitle);
        TextView subtitle = view.findViewById(R.id.txtSubtitle);

        //set the data to the ui elements
        image.setImageResource(infoAdapter.get(position).getImage());
        title.setText(infoAdapter.get(position).getTitle());
        subtitle.setText(infoAdapter.get(position).getSubtitle());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //remove or destroy old info card that as being displayed
        container.removeView((RelativeLayout)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }
}
