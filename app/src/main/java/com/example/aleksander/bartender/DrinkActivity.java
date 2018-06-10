package com.example.aleksander.bartender;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DrinkActivity extends AppCompatActivity {
    EditText edtNyNavn, edtNyBlandevannNavn, edtNyEkstraNavn, edtNySpritType, edtNySpritMengde, edtNyInstruksjoner;
//    Button btnAddSprit, btnRemSprit, btnAddBlandevann, btnRemBlandevann, btnAddEkstra, btnRemEkstra;
    Spinner spnrNySprit, spnrNyBlandevann, spnrNyEkstra, spnrNyIs, spnrNyGlass;
    ArrayAdapter<Sprit> spritadapter;
    ArrayAdapter<Blandevann> blandevannadapter;
    ArrayAdapter<String> ekstraadapter;
    ArrayAdapter<Is> isadapter;
    ArrayAdapter<Glass> glassadapter;
    ArrayList<Sprit> spritarray;
    ArrayList<Blandevann> blandevannarray;
    ArrayList<String> ekstraarray;
    ArrayList<Is> isarray;
    ArrayList<Glass> glassarray;
    DrinkDatabase drinkDatabase, drinkDatabase2;
    SQLiteDatabase drinkdb, drinkdb2;
    Cursor cursor;
    ImageButton btnAddSpritPlus, btnRemSpritMinus, btnAddBlandevannPlus, btnRemBlandevannMinus, btnAddEkstraPlus, btnRemEkstraMinus;
    TextView txtDrinkTittel;
    ImageView imgGlassType;
    String valgtglass = "tilfeldig";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.aleksander.bartender.R.layout.activity_drink);
//        definerer edit-tekstfelt
        edtNyNavn = (EditText) findViewById(com.example.aleksander.bartender.R.id.edtNyNavn);
        edtNyBlandevannNavn = (EditText) findViewById(com.example.aleksander.bartender.R.id.edtNyBlandevannNavn);
        edtNyEkstraNavn = (EditText) findViewById(R.id.edtNyEkstraNavn);
        edtNySpritType = (EditText) findViewById(com.example.aleksander.bartender.R.id.edtNySpritType);
        edtNySpritMengde = (EditText) findViewById(com.example.aleksander.bartender.R.id.edtNySpritMengde);
        edtNyInstruksjoner = (EditText) findViewById(com.example.aleksander.bartender.R.id.edtNyInstruksjoner);
//        definerer knapper (bildeknapper)
        btnAddSpritPlus = (ImageButton) findViewById(com.example.aleksander.bartender.R.id.btnAddSpritPlus);
        btnRemSpritMinus = (ImageButton) findViewById(com.example.aleksander.bartender.R.id.btnRemSpritMinus);
        btnAddBlandevannPlus = (ImageButton) findViewById(com.example.aleksander.bartender.R.id.btnAddBlandevannPlus);
        btnRemBlandevannMinus = (ImageButton) findViewById(com.example.aleksander.bartender.R.id.btnRemBlandevannMinus);
        btnAddEkstraPlus = (ImageButton) findViewById(com.example.aleksander.bartender.R.id.btnAddEkstraPlus);
        btnRemEkstraMinus = (ImageButton) findViewById(com.example.aleksander.bartender.R.id.btnRemEkstraMinus);
//        definerer spinnere
        spnrNySprit = (Spinner) findViewById(com.example.aleksander.bartender.R.id.spnrNySprit);
        spnrNyBlandevann = (Spinner) findViewById(com.example.aleksander.bartender.R.id.spnrNyBlandevann);
        spnrNyEkstra = (Spinner) findViewById(com.example.aleksander.bartender.R.id.spnrNyEkstra);
        spnrNyIs = (Spinner) findViewById(com.example.aleksander.bartender.R.id.spnrNyIs);
        spnrNyGlass = (Spinner) findViewById(com.example.aleksander.bartender.R.id.spnrNyGlass);
//        definerer arraylister
        spritarray = new ArrayList<>();
        blandevannarray = new ArrayList<>();
        ekstraarray = new ArrayList<>();
        isarray = new ArrayList<>(Arrays.asList(Is.values()));
        glassarray = new ArrayList<>(Arrays.asList(Glass.values()));
//        definerer textview
        txtDrinkTittel = (TextView) findViewById(R.id.txtDrinkTittel);
//        definerer imageview
        imgGlassType = (ImageView) findViewById(R.id.imgGlassType);
//        definerer databasen
        drinkDatabase = new DrinkDatabase(this);
        drinkdb = drinkDatabase.getWritableDatabase();

//        legger is-typer i spinner
        isadapter = new ArrayAdapter<Is>(this, com.example.aleksander.bartender.R.layout.custom_spinner, isarray);
        spnrNyIs.setAdapter(isadapter);
//        legger glass-typer i spinner
        glassadapter = new ArrayAdapter<Glass>(this, com.example.aleksander.bartender.R.layout.custom_spinner, glassarray);
        spnrNyGlass.setAdapter(glassadapter);
//        fyllter felter hvis drink skal redigeres
        fyllFelterFraIntent();

        if(valgtglass.toLowerCase().equals("highball")){
            imgGlassType.setImageResource(R.drawable.highball);
        } else if(valgtglass.toLowerCase().equals("coupe")){
            imgGlassType.setImageResource(R.drawable.coupe);
        } else if(valgtglass.toLowerCase().equals("martini")){
            imgGlassType.setImageResource(R.drawable.martini);
        } else if(valgtglass.toLowerCase().equals("cocktail")){
            imgGlassType.setImageResource(R.drawable.cocktail);
        } else if(valgtglass.toLowerCase().equals("lowball")){
            imgGlassType.setImageResource(R.drawable.lowball);
        } else if(valgtglass.toLowerCase().equals("hurricane")){
            imgGlassType.setImageResource(R.drawable.hurricane);
        } else if(valgtglass.toLowerCase().equals("shotglass")){
            imgGlassType.setImageResource(R.drawable.shotglass);
        } else if(valgtglass.toLowerCase().equals("flute")){
            imgGlassType.setImageResource(R.drawable.flute);
        } else if(valgtglass.toLowerCase().equals("vinglass")){
            imgGlassType.setImageResource(R.drawable.vinglass);
        } else {
            imgGlassType.setImageResource(R.drawable.tilfeldig);
        }

//        endrer drinkbilde etter hva som vises i spinner
        spnrNyGlass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valgtglass = spnrNyGlass.getSelectedItem().toString();
                if(valgtglass.toLowerCase().equals("highball")){
                    imgGlassType.setImageResource(R.drawable.highball);
                } else if(valgtglass.toLowerCase().equals("coupe")){
                    imgGlassType.setImageResource(R.drawable.coupe);
                } else if(valgtglass.toLowerCase().equals("martini")){
                    imgGlassType.setImageResource(R.drawable.martini);
                } else if(valgtglass.toLowerCase().equals("cocktail")){
                    imgGlassType.setImageResource(R.drawable.cocktail);
                } else if(valgtglass.toLowerCase().equals("lowball")){
                    imgGlassType.setImageResource(R.drawable.lowball);
                } else if(valgtglass.toLowerCase().equals("hurricane")){
                    imgGlassType.setImageResource(R.drawable.hurricane);
                } else if(valgtglass.toLowerCase().equals("shotglass")){
                    imgGlassType.setImageResource(R.drawable.shotglass);
                } else if(valgtglass.toLowerCase().equals("flute")){
                    imgGlassType.setImageResource(R.drawable.flute);
                } else if(valgtglass.toLowerCase().equals("vinglass")){
                    imgGlassType.setImageResource(R.drawable.vinglass);
                } else {
                    imgGlassType.setImageResource(R.drawable.tilfeldig);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        oppretter lytterer til add-knapper
        btnAddSpritPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtNySpritType.getText().toString().equals("") && !edtNySpritMengde.getText().toString().equals("")){
                    Collections.reverse(spritarray);
                    spritarray.add(new Sprit(
                            edtNySpritType.getText().toString().substring(0,1).toUpperCase()+edtNySpritType.getText().toString().substring(1,edtNySpritType.getText().toString().length()).toLowerCase(),
                            edtNySpritMengde.getText().toString().substring(0,1).toUpperCase()+edtNySpritMengde.getText().toString().substring(1,edtNySpritMengde.getText().toString().length()).toLowerCase()));
                    Collections.reverse(spritarray);
                    spritadapter = new ArrayAdapter<>(DrinkActivity.this, com.example.aleksander.bartender.R.layout.custom_spinner, spritarray);
                    spnrNySprit.setAdapter(spritadapter);
                    edtNySpritType.setText("");
                    edtNySpritMengde.setText("");
                    edtNySpritType.setFocusableInTouchMode(true);
                    edtNySpritType.requestFocus();
                    View view = DrinkActivity.this.getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(DrinkActivity.this).create();
                    alertDialog.setTitle("Sprit");
                    alertDialog.setMessage("Sprit trenger navn og mengde");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });
        btnAddBlandevannPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtNyBlandevannNavn.getText().toString().equals("")){
                    Collections.reverse(blandevannarray);
                    blandevannarray.add(new Blandevann(
                            edtNyBlandevannNavn.getText().toString().substring(0,1).toUpperCase()+edtNyBlandevannNavn.getText().toString().substring(1,edtNyBlandevannNavn.getText().toString().length()).toLowerCase()));
                    Collections.reverse(blandevannarray);
                    blandevannadapter = new ArrayAdapter<>(DrinkActivity.this, com.example.aleksander.bartender.R.layout.custom_spinner, blandevannarray);
                    spnrNyBlandevann.setAdapter(blandevannadapter);
                    edtNyBlandevannNavn.setText("");
                    edtNyBlandevannNavn.setFocusableInTouchMode(true);
                    edtNyBlandevannNavn.requestFocus();
                    View view = DrinkActivity.this.getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(DrinkActivity.this).create();
                    alertDialog.setTitle("Blandevann");
                    alertDialog.setMessage("Blandevann må fylles ut");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });
        btnAddEkstraPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtNyEkstraNavn.getText().toString().equals("")){
                    Collections.reverse(ekstraarray);
                    ekstraarray.add(edtNyEkstraNavn.getText().toString().substring(0,1).toUpperCase()+edtNyEkstraNavn.getText().toString().substring(1,edtNyEkstraNavn.getText().toString().length()).toLowerCase());
                    Collections.reverse(ekstraarray);
                    ekstraadapter = new ArrayAdapter<>(DrinkActivity.this, com.example.aleksander.bartender.R.layout.custom_spinner, ekstraarray);
                    spnrNyEkstra.setAdapter(ekstraadapter);
                    edtNyEkstraNavn.setText("");
                    edtNyEkstraNavn.setFocusableInTouchMode(true);
                    edtNyEkstraNavn.requestFocus();
                    View view = DrinkActivity.this.getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(DrinkActivity.this).create();
                    alertDialog.setTitle("Ekstra ingrediens");
                    alertDialog.setMessage("Ekstra ingrediens må fylles ut");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
            }
            }
        });
//        oppretter lytterer til remove-knapper
        btnRemSpritMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spnrNySprit != null && spnrNySprit.getSelectedItem() != null) {
                    spritarray.remove(spnrNySprit.getSelectedItemPosition());
                    spritadapter = new ArrayAdapter<>(DrinkActivity.this, com.example.aleksander.bartender.R.layout.custom_spinner, spritarray);
                    spnrNySprit.setAdapter(spritadapter);
                } else {
//                    Toast.makeText(DrinkActivity.this, "Tom liste", Toast.LENGTH_SHORT).show();
                    ScrollView scrollView =(ScrollView)findViewById(R.id.mainscroll);
                    Snackbar snackbar = Snackbar.make(scrollView, "Spritlisten er tom", Snackbar.LENGTH_LONG);
                    View view = snackbar.getView();
                    ScrollView.LayoutParams params=(ScrollView.LayoutParams)view.getLayoutParams();
                    params.gravity = Gravity.CENTER;
                    view.setLayoutParams(params);
                    snackbar.show();
                }
            }
        });
        btnRemBlandevannMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spnrNyBlandevann != null && spnrNyBlandevann.getSelectedItem() != null) {
                    blandevannarray.remove(spnrNyBlandevann.getSelectedItemPosition());
                    blandevannadapter = new ArrayAdapter<>(DrinkActivity.this, com.example.aleksander.bartender.R.layout.custom_spinner, blandevannarray);
                    spnrNyBlandevann.setAdapter(blandevannadapter);
                } else {
                    ScrollView scrollView =(ScrollView)findViewById(R.id.mainscroll);
                    Snackbar snackbar = Snackbar.make(scrollView, "Blandevannlisten er tom", Snackbar.LENGTH_LONG);
                    View view = snackbar.getView();
                    ScrollView.LayoutParams params=(ScrollView.LayoutParams)view.getLayoutParams();
                    params.gravity = Gravity.CENTER;
                    view.setLayoutParams(params);
                    snackbar.show();
                }
            }
        });
        btnRemEkstraMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spnrNyEkstra != null && spnrNyEkstra.getSelectedItem() != null) {
                    ekstraarray.remove(spnrNyEkstra.getSelectedItemPosition());
                    ekstraadapter = new ArrayAdapter<>(DrinkActivity.this, com.example.aleksander.bartender.R.layout.custom_spinner, ekstraarray);
                    spnrNyEkstra.setAdapter(ekstraadapter);
                } else {
                    ScrollView scrollView =(ScrollView)findViewById(R.id.mainscroll);
                    Snackbar snackbar = Snackbar.make(scrollView, "Ekstralisten er tom", Snackbar.LENGTH_LONG);
                    View view = snackbar.getView();
                    ScrollView.LayoutParams params=(ScrollView.LayoutParams)view.getLayoutParams();
                    params.gravity = Gravity.CENTER;
                    view.setLayoutParams(params);
                    snackbar.show();
                }
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(com.example.aleksander.bartender.R.menu.menu_add, menu);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mitLagre:
                AlertDialog.Builder alertDialog3 = new AlertDialog.Builder(this);
                alertDialog3.setTitle("Lagre");
                alertDialog3.setMessage("Ønsker du å lagre denne drinken?");
                alertDialog3.setIcon(R.drawable.ic_action_save_green);
                //Setter hendelse til positivt (ja) svar
                alertDialog3.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(!spritarray.isEmpty() && !glassarray.isEmpty() && !isarray.isEmpty() && !edtNyNavn.getText().toString().equals("")) {
                            ContentValues drinkvalues = new ContentValues();
                            ContentValues spritvalues = new ContentValues();
                            ContentValues blandevannvalues = new ContentValues();
                            ContentValues ekstravalues = new ContentValues();
                            drinkDatabase2 = new DrinkDatabase(getApplicationContext());
                            drinkdb2 = drinkDatabase2.getWritableDatabase();
                            String instruksjoner;
                            int id = 0;
                            if (edtNyInstruksjoner.getText().toString().equals("")){
                                instruksjoner = "Ingen instruksjoner angitt";
                            } else {
                                instruksjoner = edtNyInstruksjoner.getText().toString();
                            }
    //                tblDrink verdier
                            drinkvalues.put("drinkName", edtNyNavn.getText().toString().substring(0,1).toUpperCase()+edtNyNavn.getText().toString().substring(1,edtNyNavn.getText().toString().length()).toLowerCase());
                            drinkvalues.put("isType", spnrNyIs.getSelectedItem().toString());
                            drinkvalues.put("glassType", spnrNyGlass.getSelectedItem().toString());
                            drinkvalues.put("instruksjoner", instruksjoner.substring(0,1).toUpperCase()+instruksjoner.substring(1,instruksjoner.length()));
                            if(getIntent().getExtras() == null){
                                drinkvalues.put("rating", "0");
                                drinkdb.insert("tblDrink", null, drinkvalues);
                            } else {
                                drinkdb.update("tblDrink", drinkvalues, "_id=?", new String[]{String.valueOf(getIntent().getIntExtra("id", 0))});
                            }
                            if(getIntent().getExtras() == null) {
                                cursor = drinkdb.query("tblDrink", new String[]{"_id"}, null, null, null, null, "_id DESC");
                                if (cursor.moveToFirst()) {
                                    id = cursor.getInt(0);
                                }
                                cursor.close();
                            } else {
                                id = getIntent().getIntExtra("id", 0);
                            }
    //                tblSprit verdier
                            if(getIntent().getExtras() != null){
                                drinkdb.delete("tblSprit", "drink_id=?", new String[]{String.valueOf(id)});
                            }
                            for(Sprit s : spritarray){
                                spritvalues.put("drink_id", id);
                                spritvalues.put("spritType", s.getType());
                                spritvalues.put("spritMengde", s.getCl_mengde());
                                drinkdb.insert("tblSprit", null, spritvalues);
                            }
    //                tblBlandevann verdier
                            if(getIntent().getExtras() != null){
                                drinkdb.delete("tblBlandevann", "drink_id=?", new String[]{String.valueOf(id)});
                            }
                            if(!blandevannarray.isEmpty()) {
                                for (Blandevann b : blandevannarray) {
                                    blandevannvalues.put("drink_id", id);
                                    blandevannvalues.put("blandevannName", b.getNavn());
                                    drinkdb.insert("tblBlandevann", null, blandevannvalues);
                                }
                            }
    //                tblEkstra verdier
                            if(getIntent().getExtras() != null){
                                drinkdb.delete("tblEkstra", "drink_id=?", new String[]{String.valueOf(id)});
                            }
                            if(!ekstraarray.isEmpty()){
                                for(String s : ekstraarray){
                                    ekstravalues.put("drink_id", id);
                                    ekstravalues.put("ekstraName", s);
                                    drinkdb.insert("tblEkstra", null, ekstravalues);
                                }
                            }
                            String[] clear = {};
                            edtNyNavn.setText("");
                            edtNyInstruksjoner.setText("");
                            edtNySpritType.setText("");
                            edtNySpritMengde.setText("");
                            edtNyBlandevannNavn.setText("");
                            edtNyEkstraNavn.setText("");
                            spnrNySprit.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, clear));
                            spnrNyBlandevann.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, clear));
                            spnrNyEkstra.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, clear));
                            if(getIntent().getExtras() == null){
                                Toast.makeText(getApplicationContext(), "Drink opprettet", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Drink er endret", Toast.LENGTH_SHORT).show();
                            }
                            drinkdb.close();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
    //                    onBackPressed();
                        } else {
                            String feilmelding = "Felter med stjerne må utfylles:\n";
//                    oppretter tilbakemeldinger om manglende felter som må utfylles
                            if(edtNyNavn.getText().toString().equals("")){
                                feilmelding += "- Drinknavn mangler\n";
                            }
                            if(spritarray.isEmpty()){
                                feilmelding += "- Sprit mangler";
                            }
                            if(glassarray.isEmpty()){
                                feilmelding += "\n- Glass mangler";
                            }
                            if(isarray.isEmpty()){
                                feilmelding += "\n- Is mangler";
                            }
                            AlertDialog alertDialog = new AlertDialog.Builder(DrinkActivity.this).create();
                            alertDialog.setTitle("Noe mangler...");
                            alertDialog.setMessage(feilmelding);
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }
                    }
                });

                //Setter hendelse til negativt (nei) svar
                alertDialog3.setNegativeButton("Nei", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog3.show();
                return true;
            case R.id.mitClear:
                AlertDialog.Builder alertDialog4 = new AlertDialog.Builder(this);
                alertDialog4.setTitle("Reset");
                alertDialog4.setMessage("Vil du tømme alle feltene?");
                alertDialog4.setIcon(R.drawable.ic_action_reload);
                //Setter hendelse til positivt (ja) svar
                alertDialog4.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String[] clear = {};
                        edtNyNavn.setText("");
                        edtNyInstruksjoner.setText("");
                        edtNySpritType.setText("");
                        edtNySpritMengde.setText("");
                        edtNyBlandevannNavn.setText("");
                        edtNyEkstraNavn.setText("");
                        spritarray.clear();
                        blandevannarray.clear();
                        ekstraarray.clear();
                        spnrNySprit.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, clear));
                        spnrNyBlandevann.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, clear));
                        spnrNyEkstra.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, clear));
                        Toast.makeText(getApplicationContext(), "Feltene er resat", Toast.LENGTH_SHORT).show();
                        View view = DrinkActivity.this.getCurrentFocus();
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    }
                });

                //Setter hendelse til negativt (nei) svar
                alertDialog4.setNegativeButton("Nei", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog4.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void fyllFelterFraIntent(){
        if(getIntent().getExtras() != null){
//            henter ut drinkinfo til tekstfelter
            txtDrinkTittel.setText("Endre drink");
            cursor = drinkdb.query("tblDrink", new String[]{"_id", "drinkName", "isType", "glassType", "instruksjoner"}, "_id=?", new String[]{String.valueOf(getIntent().getIntExtra("id", 0))}, null, null, null);
            if(cursor.moveToFirst()){
                edtNyNavn.setText(cursor.getString(1));
                spnrNyIs.setSelection(isadapter.getPosition(Is.valueOf(cursor.getString(2))));
                spnrNyGlass.setSelection(glassadapter.getPosition(Glass.valueOf(cursor.getString(3))));
                edtNyInstruksjoner.setText(cursor.getString(4));
            }
            cursor.close();
//            henter ut sprit til tekstfelter
            cursor = drinkdb.query("tblSprit", new String[]{"drink_id", "spritType", "spritMengde"}, "drink_id=?", new String[]{String.valueOf(getIntent().getIntExtra("id", 0))}, null, null, null);
            while(cursor.moveToNext()){
                spritarray.add(new Sprit(cursor.getString(1), cursor.getString(2)));
            }
            cursor.close();
            spritadapter = new ArrayAdapter<>(DrinkActivity.this, com.example.aleksander.bartender.R.layout.custom_spinner, spritarray);
            spnrNySprit.setAdapter(spritadapter);
//            henter ut blandevann til tekstfelter
            cursor = drinkdb.query("tblBlandevann", new String[]{"drink_id", "blandevannName"}, "drink_id=?", new String[]{String.valueOf(getIntent().getIntExtra("id", 0))}, null, null, null);
            while(cursor.moveToNext()){
                blandevannarray.add(new Blandevann(cursor.getString(1)));
            }
            cursor.close();
            blandevannadapter = new ArrayAdapter<>(DrinkActivity.this, com.example.aleksander.bartender.R.layout.custom_spinner, blandevannarray);
            spnrNyBlandevann.setAdapter(blandevannadapter);
//            henter ut ekstra til tekstfelter
            cursor = drinkdb.query("tblEkstra", new String[]{"drink_id", "ekstraName"}, "drink_id=?", new String[]{String.valueOf(getIntent().getIntExtra("id", 0))}, null, null, null);
            while(cursor.moveToNext()){
                ekstraarray.add((cursor.getString(1)));
            }
            cursor.close();
            ekstraadapter = new ArrayAdapter<>(DrinkActivity.this, com.example.aleksander.bartender.R.layout.custom_spinner, ekstraarray);
            spnrNyEkstra.setAdapter(ekstraadapter);
        }
    }
}
