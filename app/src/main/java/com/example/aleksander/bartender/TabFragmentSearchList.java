package com.example.aleksander.bartender;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TabFragmentSearchList extends Fragment {
    ListView drinklist;
    Intent intent;
    ArrayList<Integer> idlist, idsearch;
    ArrayList<String> hovedliste, subliste, ratinglist, hovedsearch, subsearch, ratingsearch, glassliste, glasssearch;
    Custom_ListView_Adapter customadapter;
    DrinkDatabase drinkDatabase;
    SQLiteDatabase drinkdb;
    Cursor cursor, subcursor;
    int itemid, currentListPos;
    String spritoutput, blandevannoutput, og;
    SearchView searchView;
    TextView txtIngenTreff;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.searchlist_tabfragment, container, false);

        intent = new Intent(this.getContext(), MainActivity.class);
        drinkDatabase = new DrinkDatabase(this.getContext());
        drinkdb = drinkDatabase.getReadableDatabase();
        hovedliste = new ArrayList<>();
        subliste = new ArrayList<>();
        idlist = new ArrayList<>();
        ratinglist = new ArrayList<>();
        hovedsearch = new ArrayList<>();
        subsearch = new ArrayList<>();
        ratingsearch = new ArrayList<>();
        idsearch = new ArrayList<>();
        drinklist = (ListView) v.findViewById(R.id.lstDrink);
        searchView = (SearchView) v.findViewById(R.id.searchView);
        txtIngenTreff = (TextView) v.findViewById(R.id.txtIngenTreff);
        glassliste = new ArrayList<>();
        glasssearch = new ArrayList<>();

//        lager søkefunksjon
        searchView.setQueryHint("Søk...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchDrinks(searchView.getQuery());
                return false;
            }
        });

        int searchCloseButtonId = searchView.getContext().getResources()
                .getIdentifier("android:id/search_close_btn", null, null);
        ImageView closeButton = (ImageView) this.searchView.findViewById(searchCloseButtonId);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setQuery("", false);
                searchView.clearFocus();
            }
        });

//        skriver cursor data til arraylister
        cursor = drinkdb.query("tblDrink", new String[]{"_id", "drinkName", "glassType", "rating"}, "glassType!=?", new String[]{Glass.Shotglass.toString()}, null, null, "drinkName ASC");
        while(cursor.moveToNext()){
            idlist.add(cursor.getInt(0));
            itemid = cursor.getInt(0);
            hovedliste.add(cursor.getString(1));
            glassliste.add(cursor.getString(2));
            ratinglist.add(cursor.getString(3));
            subcursor = drinkdb.query("tblSprit", new String[]{"_id", "drink_id", "spritType"}, "drink_id=?", new String[]{String.valueOf(itemid)}, null, null, null);
            spritoutput = "";
            while(subcursor.moveToNext()){
                spritoutput += subcursor.getString(2)+", ";
            }
            subcursor.close();
            subcursor = drinkdb.query("tblBlandevann", new String[]{"_id", "drink_id", "blandevannName"}, "drink_id=?", new String[]{String.valueOf(itemid)}, null, null, null);
            blandevannoutput = "";
            og = "";
            while(subcursor.moveToNext()){
                blandevannoutput += subcursor.getString(2)+", ";
            }
            subcursor.close();
            if(spritoutput.length()>=2){
                spritoutput = spritoutput.substring(0, spritoutput.length()-2);
            } else {
                spritoutput = "Ingen";
            }
            if(blandevannoutput.length()>=2){
                blandevannoutput = blandevannoutput.substring(0, blandevannoutput.length()-2);
                og = " og ";
            }
            subliste.add(spritoutput+og+blandevannoutput);
        }
        cursor.close();

//        hvis liste er tom vil søkefunksjon være utilgjengelig
        if(hovedliste.size()==0){
            searchView.setInputType(InputType.TYPE_NULL);
            txtIngenTreff.setVisibility(View.VISIBLE);
            txtIngenTreff.setText("Listen er tom");
        }

//        legger arraylister med data inn i adapter
        customadapter = new Custom_ListView_Adapter(v.getContext(), hovedliste, subliste, ratinglist, idlist, currentListPos, glassliste);
//        legger adapter til listview
        drinklist.setAdapter(customadapter);

        drinklist.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity)getActivity()).setDrinkVerdier(Integer.parseInt(view.getTag().toString()), 0);
                searchView.clearFocus();
            }
        });
        return v;
    }

    public void searchDrinks(CharSequence bokstaver){
        hovedsearch.clear();
        subsearch.clear();
        ratingsearch.clear();
        idsearch.clear();
        glasssearch.clear();
        for(String s : hovedliste){
            if(s.toLowerCase().contains(bokstaver.toString().toLowerCase())){
                int index = hovedliste.indexOf(s);
                idsearch.add(idlist.get(index));
                hovedsearch.add(hovedliste.get(index));
                subsearch.add(subliste.get(index));
                ratingsearch.add(ratinglist.get(index));
                glasssearch.add(glassliste.get(index));
            }
        }
        for(String s : subliste){
            if(s.toLowerCase().contains(bokstaver.toString().toLowerCase())){
                int index = subliste.indexOf(s);
                if(!hovedsearch.contains(hovedliste.get(index))){
                    idsearch.add(idlist.get(index));
                    hovedsearch.add(hovedliste.get(index));
                    subsearch.add(subliste.get(index));
                    ratingsearch.add(ratinglist.get(index));
                    glasssearch.add(glassliste.get(index));
                }
            }
        }
        if(hovedsearch.isEmpty() && subsearch.isEmpty()){
            txtIngenTreff.setVisibility(View.VISIBLE);
            txtIngenTreff.setText("Ingen treff");
        } else {
            txtIngenTreff.setVisibility(View.GONE);
        }
        if(hovedliste.isEmpty() && subliste.isEmpty()){
            txtIngenTreff.setVisibility(View.VISIBLE);
            txtIngenTreff.setText("Listen er tom");
        }
        customadapter = new Custom_ListView_Adapter(getContext(), hovedsearch, subsearch, ratingsearch, idsearch, currentListPos, glasssearch);
        drinklist.setAdapter(customadapter);
    }

}