package com.example.aleksander.bartender;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Aleksander on 14.02.2017.
 */

public class Custom_ListView_Adapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> drinknavn, drinkinnhold, ratinglist, glassliste;
    private ArrayList<Integer> idlist;
    private LayoutInflater layoutInflater;
    private int pos = 0;
    int currentListPos = -1;
    String glass;

    public Custom_ListView_Adapter(Context context, ArrayList<String> drinknavn, ArrayList<String> drinkinnhold, ArrayList<String> ratinglist, ArrayList<Integer> idlist, int currentListPos, ArrayList<String> glassliste) {
        this.context = context;
        this.drinknavn = drinknavn;
        this.drinkinnhold = drinkinnhold;
        this.ratinglist = ratinglist;
        this.idlist = idlist;
        this.currentListPos = currentListPos;
        this.glassliste = glassliste;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return drinknavn.size();
    }

    @Override
    public Object getItem(int position) {
        return drinknavn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public int getPosition(){
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.adapter_custom, null);

        pos = position;
        TextView txtHoved =(TextView)convertView.findViewById(com.example.aleksander.bartender.R.id.txtHoved);
        TextView txtSub =(TextView)convertView.findViewById(com.example.aleksander.bartender.R.id.txtSub);
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar2);
        ImageView imgDrink = (ImageView) convertView.findViewById(R.id.imgDrink);
//        1 = posisjonen i listen, 2 = id i databasen
//        convertView.setTag(1, pos);
        convertView.setTag(idlist.get(position));

        if(currentListPos!=-1 && currentListPos==idlist.get(position)){
            convertView.setBackgroundColor(Color.parseColor("#6BB0E5"));
        }

        if(glassliste.get(position).toLowerCase().equals("highball")){
            imgDrink.setImageResource(R.drawable.highball);
        } else if(glassliste.get(position).toLowerCase().equals("coupe")){
            imgDrink.setImageResource(R.drawable.coupe);
        } else if(glassliste.get(position).toLowerCase().equals("martini")){
            imgDrink.setImageResource(R.drawable.martini);
        } else if(glassliste.get(position).toLowerCase().equals("cocktail")){
            imgDrink.setImageResource(R.drawable.cocktail);
        } else if(glassliste.get(position).toLowerCase().equals("lowball")){
            imgDrink.setImageResource(R.drawable.lowball);
        } else if(glassliste.get(position).toLowerCase().equals("hurricane")){
            imgDrink.setImageResource(R.drawable.hurricane);
        } else if(glassliste.get(position).toLowerCase().equals("shotglass")){
            imgDrink.setImageResource(R.drawable.shotglass);
        } else if(glassliste.get(position).toLowerCase().equals("flute")){
            imgDrink.setImageResource(R.drawable.flute);
        } else if(glassliste.get(position).toLowerCase().equals("vinglass")){
            imgDrink.setImageResource(R.drawable.vinglass);
        } else {
            imgDrink.setImageResource(R.drawable.tilfeldig);
        }


        txtHoved.setText(drinknavn.get(position));
        txtSub.setText(drinkinnhold.get(position));
        ratingBar.setRating(Float.parseFloat(ratinglist.get(position)));
        return convertView;
    }
}
