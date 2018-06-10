package com.example.aleksander.bartender;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentMain extends Fragment {
    TextView txtNavn, txtSprit, txtBlandevann, txtIs, txtGlass, txtEkstra, txtInstruksjoner;
    RatingBar ratingBar;
    DrinkDatabase drinkDatabase;
    SQLiteDatabase drinkdb;
    Cursor cursor;
    int drinkid;
    ArrayList<Sprit> spritliste;
    ArrayList<Blandevann> blandevannliste;
    ArrayList<String> ekstraliste;
    String output;
    Intent refresh, drinkactivity;
    RelativeLayout rloMainEkstra, rloMainBlandevann, rloMainIs;
    LinearLayout lloMainInstruksjoner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container, false);

//        instansierer variabler
        drinkDatabase = new DrinkDatabase(this.getContext());
        drinkdb = drinkDatabase.getWritableDatabase();
//        imgHide = (ImageView) v.findViewById(com.example.aleksander.bartender.R.id.imgHide);
        txtNavn = (TextView) v.findViewById(com.example.aleksander.bartender.R.id.txtName);
        txtSprit = (TextView) v.findViewById(com.example.aleksander.bartender.R.id.txtSprit);
        txtBlandevann = (TextView) v.findViewById(com.example.aleksander.bartender.R.id.txtBlandevann);
        txtEkstra = (TextView) v.findViewById(com.example.aleksander.bartender.R.id.txtEkstra);
        txtIs = (TextView) v.findViewById(com.example.aleksander.bartender.R.id.txtIs);
        txtGlass = (TextView) v.findViewById(com.example.aleksander.bartender.R.id.txtGlass);
        txtInstruksjoner = (TextView) v.findViewById(com.example.aleksander.bartender.R.id.txtInstruksjoner);
        spritliste = new ArrayList<>();
        blandevannliste = new ArrayList<>();
        ekstraliste = new ArrayList<>();
        ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);
        rloMainEkstra = (RelativeLayout) v.findViewById(R.id.rloMainEkstra);
        rloMainBlandevann = (RelativeLayout) v.findViewById(R.id.rloMainBlandevann);
        rloMainIs = (RelativeLayout) v.findViewById(R.id.rloMainIs);
//        imgShowList = (ImageView) v.findViewById(com.example.aleksander.bartender.R.id.imgShowList);
        lloMainInstruksjoner = (LinearLayout) v.findViewById(R.id.lloMainInstruksjoner);
//        henter drink id fra getIntent
        if(getArguments() == null || drinkid == -2){
//            imgHide.setVisibility(View.VISIBLE);
//            Toast.makeText(getContext(), String.valueOf(getArguments().getInt("id", 1)), Toast.LENGTH_SHORT).show();
        } else {
            drinkid = getArguments().getInt("id", 1);
            Toast.makeText(getContext(), String.valueOf(getArguments().getInt("id", 1)), Toast.LENGTH_SHORT).show();
//        henter data fra cursor og setter det inn i tekstfelter
            cursor = drinkdb.query("tblDrink", new String[]{"_id", "drinkName", "isType", "glassType", "instruksjoner", "rating"}, "_id=?", new String[]{String.valueOf(drinkid)}, null, null, null);
//        sjekker om cursor er tom
            while (cursor.moveToNext()) {
                txtNavn.setText(cursor.getString(1));
                txtIs.setText(cursor.getString(2));
                txtGlass.setText(cursor.getString(3));
                txtInstruksjoner.setText(cursor.getString(4));
                ratingBar.setRating(Float.parseFloat(cursor.getString(5)));
            }
            cursor = drinkdb.query("tblSprit", new String[]{"_id", "drink_id", "spritType", "spritMengde"}, "drink_id=?", new String[]{String.valueOf(drinkid)}, null, null, null);
            while (cursor.moveToNext()) {
                spritliste.add(new Sprit(cursor.getString(2), cursor.getString(3)));
            }
            output = "";
            for (Sprit s : spritliste) {
                output += s.toString() + "\n";
            }
            if (output.length() < 1) {
                output = "Ingen sprit";
            }
            txtSprit.setText(output);
            cursor = drinkdb.query("tblBlandevann", new String[]{"_id", "drink_id", "blandevannName"}, "drink_id=?", new String[]{String.valueOf(drinkid)}, null, null, null);
            while (cursor.moveToNext()) {
                blandevannliste.add(new Blandevann(cursor.getString(2)));
            }
            output = "";
            for (Blandevann b : blandevannliste) {
                output += b.toString() + "\n";
            }
            if (output.length() < 1) {
                output = "Ingen blandevann";
            }
            txtBlandevann.setText(output);
            cursor = drinkdb.query("tblEkstra", new String[]{"_id", "drink_id", "ekstraName"}, "drink_id=?", new String[]{String.valueOf(drinkid)}, null, null, null);
            while (cursor.moveToNext()) {
                ekstraliste.add(cursor.getString(2));
            }
            output = "";
            for (String s : ekstraliste) {
                output += s + "\n";
            }
            if (output.length() < 1) {
                output = "Ingen";
            }
            txtEkstra.setText(output);
//        oppretter intent til seg selv
//            refresh = new Bundle(this.getContext(), this.getClass());
//            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//                @Override
//                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
////                oppdaterer rating på drinken med update til databasen og starter seg selv om igjen
//                    ContentValues values = new ContentValues();
//                    values.put("rating", String.valueOf(rating));
//                    drinkdb.update("tblDrink", values, "_id=?", new String[]{String.valueOf(drinkid)});
//                    refresh.putExtra("id", drinkid);
//                    startActivity(refresh);
//                }
//            });

            if (txtEkstra.getText().toString().equals("Ingen")) {
                rloMainEkstra.setVisibility(View.GONE);
            }
            if (txtBlandevann.getText().toString().equals("Ingen blandevann")) {
                rloMainBlandevann.setVisibility(View.GONE);
            }
            if (txtIs.getText().toString().equals("Ingen")) {
                rloMainIs.setVisibility(View.GONE);
            }
            if (txtInstruksjoner.getText().toString().equals("Ingen instruksjoner angitt")) {
                lloMainInstruksjoner.setVisibility(View.GONE);
            }
        }
        return v;
    }

}