package com.example.aleksander.bartender;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    DrawerLayout drawerlayout;
    TextView txtNavn, txtSprit, txtBlandevann, txtIs, txtGlass, txtEkstra, txtInstruksjoner;
    ImageView imgHide, imgClose, imgGlass;
    RatingBar ratingBar;
    DrinkDatabase drinkDatabase;
    SQLiteDatabase drinkdb;
    Cursor cursor;
    int drinkid = -1, currentdrinklist;
    ArrayList<Sprit> spritliste;
    ArrayList<Blandevann> blandevannliste;
    ArrayList<String> ekstraliste;
    String output;
    Intent drinkactivity;
    RelativeLayout rloMainEkstra, rloMainBlandevann, rloMainIs;
    LinearLayout lloMainInstruksjoner;
    TabLayout tabLayout;
    ScrollView scrollView;
    String currentdrink = "Ingen valgt";
    ArrayList<String> glassliste;
    String glasstype;

    @Override
    public void onBackPressed() {
        if(drawerlayout.isDrawerOpen(GravityCompat.END)){
            drawerlayout.closeDrawer(GravityCompat.END);
        } else {
            moveTaskToBack(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(com.example.aleksander.bartender.R.menu.menu, menu);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mitNy:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Ny drink");
                alertDialog.setMessage("Ønsker du å opprette en ny drink?");
                alertDialog.setIcon(R.drawable.ic_action_add);
                //Setter hendelse til positivt (ja) svar
                alertDialog.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        drinkactivity = new Intent(getApplicationContext(), DrinkActivity.class);
                        startActivity(drinkactivity);
                    }
                });

                //Setter hendelse til negativt (nei) svar
                alertDialog.setNegativeButton("Nei", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
                return true;
            case R.id.mitSlett:
                if(drinkid != -1){
                    AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(this);
                    alertDialog2.setTitle("Slett drink");
                    alertDialog2.setMessage("Er du sikker på at du vil fjerne " + currentdrink + " fra listene?");
                    alertDialog2.setIcon(R.drawable.ic_action_cancel);
                    //Setter hendelse til positivt (ja) svar
                    alertDialog2.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            drinkdb.delete("tblDrink", "_id=?", new String[]{String.valueOf(drinkid)});
                            drinkdb.delete("tblSprit", "drink_id=?", new String[]{String.valueOf(drinkid)});
                            drinkdb.delete("tblBlandevann", "drink_id=?", new String[]{String.valueOf(drinkid)});
                            drinkdb.delete("tblEkstra", "drink_id=?", new String[]{String.valueOf(drinkid)});
                            Toast.makeText(MainActivity.this, "Drink fjernet", Toast.LENGTH_LONG).show();
                            setDrinkVerdier(-1, currentdrinklist);
                        }
                    });

                    //Setter hendelse til negativt (nei) svar
                    alertDialog2.setNegativeButton("Nei", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alertDialog2.show();
                } else {
                    AlertDialog alertDialogMessageSlett = new AlertDialog.Builder(this).create();
                    alertDialogMessageSlett.setTitle("Velg drink");
                    alertDialogMessageSlett.setMessage("Velg en drink fra listene først");
                    alertDialogMessageSlett.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialogMessageSlett.show();
                }
                return true;

            case R.id.mitRediger:
                if(drinkid != -1){
                    AlertDialog.Builder alertDialog3 = new AlertDialog.Builder(this);
                    alertDialog3.setTitle("Endre drink");
                    alertDialog3.setMessage("Vil du gjøre endringer til " + currentdrink + "?");
                    alertDialog3.setIcon(R.drawable.ic_action_edit);
                    //Setter hendelse til positivt (ja) svar
                    alertDialog3.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            drinkactivity = new Intent(MainActivity.this, DrinkActivity.class);
                            drinkactivity.putExtra("id", drinkid);
                            Toast.makeText(MainActivity.this, "Drink kan nå endres", Toast.LENGTH_LONG).show();
                            startActivity(drinkactivity);
                        }
                    });

                    //Setter hendelse til negativt (nei) svar
                    alertDialog3.setNegativeButton("Nei", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alertDialog3.show();
                } else {
                    AlertDialog alertDialogMessageSlett = new AlertDialog.Builder(this).create();
                    alertDialogMessageSlett.setTitle("Velg drink");
                    alertDialogMessageSlett.setMessage("Velg en drink fra listene først");
                    alertDialogMessageSlett.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialogMessageSlett.show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.aleksander.bartender.R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(com.example.aleksander.bartender.R.id.tab_layout);
        drinkDatabase = new DrinkDatabase(this);
        drinkdb = drinkDatabase.getWritableDatabase();
        drawerlayout = (DrawerLayout) findViewById(com.example.aleksander.bartender.R.id.drawer_layout);
        imgHide = (ImageView) findViewById(com.example.aleksander.bartender.R.id.imgHide);
        imgClose = (ImageView) findViewById(R.id.imgClose);
        viewPager = (ViewPager) findViewById(com.example.aleksander.bartender.R.id.vpager);
        txtNavn = (TextView) findViewById(com.example.aleksander.bartender.R.id.txtName);
        txtSprit = (TextView) findViewById(com.example.aleksander.bartender.R.id.txtSprit);
        txtBlandevann = (TextView) findViewById(com.example.aleksander.bartender.R.id.txtBlandevann);
        txtEkstra = (TextView) findViewById(com.example.aleksander.bartender.R.id.txtEkstra);
        txtIs = (TextView) findViewById(com.example.aleksander.bartender.R.id.txtIs);
        txtGlass = (TextView) findViewById(com.example.aleksander.bartender.R.id.txtGlass);
        txtInstruksjoner = (TextView) findViewById(com.example.aleksander.bartender.R.id.txtInstruksjoner);
        spritliste = new ArrayList<>();
        blandevannliste = new ArrayList<>();
        ekstraliste = new ArrayList<>();
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        rloMainEkstra = (RelativeLayout) findViewById(R.id.rloMainEkstra);
        rloMainBlandevann = (RelativeLayout) findViewById(R.id.rloMainBlandevann);
        rloMainIs = (RelativeLayout) findViewById(R.id.rloMainIs);
        lloMainInstruksjoner = (LinearLayout) findViewById(R.id.lloMainInstruksjoner);
        scrollView = (ScrollView) findViewById(R.id.scrollmain);
        glassliste = new ArrayList<>();
        imgGlass = (ImageView) findViewById(R.id.imgGlass);
        viewPager.setAdapter(new FragPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.closeDrawer(GravityCompat.END);
            }
        });
        drawerlayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                drawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                drinkid = -1;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    }

    public class FragPagerAdapter extends FragmentPagerAdapter {

        public FragPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Bundle searchbundle = new Bundle();
                    searchbundle.putInt("id", drinkid);
                    TabFragmentSearchList tabFragmentSearchList = new TabFragmentSearchList();
                    tabFragmentSearchList.setArguments(searchbundle);
                    return tabFragmentSearchList;
                case 1:
                    Bundle shotbundle = new Bundle();
                    shotbundle.putInt("id", drinkid);
                    TabFragmentShotList tabFragmentShotList = new TabFragmentShotList();
                    tabFragmentShotList.setArguments(shotbundle);
                    return tabFragmentShotList;
                case 2:
                default:
                    Bundle favoritebundle = new Bundle();
                    favoritebundle.putInt("id", drinkid);
                    TabFragmentFavoriteList tabFragmentFavoriteList = new TabFragmentFavoriteList();
                    tabFragmentFavoriteList.setArguments(favoritebundle);
                    return tabFragmentFavoriteList;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Drinker";
                case 1:
                    return "Shots";
                case 2:
                default:
                    return "Favoritter";
            }
        }
    }

    public void setDrinkVerdier(int id, int currentpage){
        drinkid = id;
        currentdrinklist = currentpage;
        imgHide.setVisibility(View.GONE);
        scrollView.smoothScrollTo(0,0);
        drawerlayout.openDrawer(GravityCompat.END);
        spritliste.clear();
        blandevannliste.clear();
        ekstraliste.clear();
        glassliste.clear();
        cursor = drinkdb.query("tblDrink", new String[]{"_id", "drinkName", "isType", "glassType", "instruksjoner", "rating"}, "_id=?", new String[]{String.valueOf(drinkid)}, null, null, null);
        while (cursor.moveToNext()) {
            txtNavn.setText(cursor.getString(1));
            txtIs.setText(cursor.getString(2));
            txtGlass.setText(cursor.getString(3));
            txtInstruksjoner.setText(cursor.getString(4));
            ratingBar.setRating(Float.parseFloat(cursor.getString(5)));
        }

        glasstype = txtGlass.getText().toString();
        if(glasstype.toLowerCase().equals("highball")){
            imgGlass.setImageResource(R.drawable.highball);
        } else if(glasstype.toLowerCase().equals("coupe")){
            imgGlass.setImageResource(R.drawable.coupe);
        } else if(glasstype.toLowerCase().equals("martini")){
            imgGlass.setImageResource(R.drawable.martini);
        } else if(glasstype.toLowerCase().equals("cocktail")){
            imgGlass.setImageResource(R.drawable.cocktail);
        } else if(glasstype.toLowerCase().equals("lowball")){
            imgGlass.setImageResource(R.drawable.lowball);
        } else if(glasstype.toLowerCase().equals("hurricane")){
            imgGlass.setImageResource(R.drawable.hurricane);
        } else if(glasstype.toLowerCase().equals("shotglass")){
            imgGlass.setImageResource(R.drawable.shotglass);
        } else if(glasstype.toLowerCase().equals("flute")){
            imgGlass.setImageResource(R.drawable.flute);
        } else if(glasstype.toLowerCase().equals("vinglass")){
            imgGlass.setImageResource(R.drawable.vinglass);
        } else {
            imgGlass.setImageResource(R.drawable.tilfeldig);
        }

        currentdrink = "\""+txtNavn.getText().toString()+"\"";
        cursor.close();
        cursor = drinkdb.query("tblSprit", new String[]{"_id", "drink_id", "spritType", "spritMengde"}, "drink_id=?", new String[]{String.valueOf(drinkid)}, null, null, null);
        while (cursor.moveToNext()) {
            spritliste.add(new Sprit(cursor.getString(2), cursor.getString(3)));
        }
        cursor.close();
        output = "";
        for (Sprit s : spritliste) {
            output += s.toString() + "\n";
        }
        if (output.length() < 1) {
            output = "Ingen sprit";
        }
        txtSprit.setText(output.trim());
        cursor = drinkdb.query("tblBlandevann", new String[]{"_id", "drink_id", "blandevannName"}, "drink_id=?", new String[]{String.valueOf(drinkid)}, null, null, null);
        while (cursor.moveToNext()) {
            blandevannliste.add(new Blandevann(cursor.getString(2)));
        }
        cursor.close();
        output = "";
        for (Blandevann b : blandevannliste) {
            output += b.toString() + "\n";
        }
        if (output.length() < 1) {
            output = "Ingen blandevann";
        }
        txtBlandevann.setText(output.trim());
        cursor = drinkdb.query("tblEkstra", new String[]{"_id", "drink_id", "ekstraName"}, "drink_id=?", new String[]{String.valueOf(drinkid)}, null, null, null);
        while (cursor.moveToNext()) {
            ekstraliste.add(cursor.getString(2));
        }
        cursor.close();
        output = "";
        for (String s : ekstraliste) {
            output += s + "\n";
        }
        if (output.length() < 1) {
            output = "Ingen";
        }
        txtEkstra.setText(output.trim());

        if (txtEkstra.getText().toString().equals("Ingen")) {
            rloMainEkstra.setVisibility(View.GONE);
        } else {
            rloMainEkstra.setVisibility(View.VISIBLE);
        }
        if (txtBlandevann.getText().toString().equals("Ingen blandevann")) {
            rloMainBlandevann.setVisibility(View.GONE);
        } else {
            rloMainBlandevann.setVisibility(View.VISIBLE);
        }
        if (txtIs.getText().toString().equals("Ingen")) {
            rloMainIs.setVisibility(View.GONE);
        } else {
            rloMainIs.setVisibility(View.VISIBLE);
        }
        if (txtInstruksjoner.getText().toString().equals("Ingen instruksjoner angitt")) {
            lloMainInstruksjoner.setVisibility(View.GONE);
        } else {
            lloMainInstruksjoner.setVisibility(View.VISIBLE);
        }

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ContentValues values = new ContentValues();
                values.put("rating", String.valueOf(rating));
                drinkdb.update("tblDrink", values, "_id=?", new String[]{String.valueOf(drinkid)});
                viewPager.setAdapter(new FragPagerAdapter(getSupportFragmentManager()));
                tabLayout.setupWithViewPager(viewPager);
                if(viewPager.getCurrentItem() != currentdrinklist){
                    viewPager.setCurrentItem(currentdrinklist);
                }
            }
        });
        if(viewPager.getCurrentItem() != currentpage){
            viewPager.setCurrentItem(currentpage);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        cursor.close();
        drinkdb.close();
    }
}
