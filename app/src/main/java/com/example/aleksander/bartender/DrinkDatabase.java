package com.example.aleksander.bartender;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Aleksander on 01.03.2017.
 */

public class DrinkDatabase extends SQLiteOpenHelper {
    Context context;
    static final int dbversion = 1;
    static final String dbname = "drinkdb";
    String qryCreateTableDrink = "CREATE TABLE tblDrink(" +
            "'_id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "'drinkName' TEXT," +
            "'isType' TEXT," +
            "'glassType' TEXT," +
            "'instruksjoner' TEXT," +
            "'rating' TEXT);";

    String qryCreateTableSprit = "CREATE TABLE tblSprit(" +
            "'_id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "'drink_id' INTEGER," +
            "'spritType' TEXT," +
            "'spritMengde' TEXT);";

    String qryCreateTableBlandevann = "CREATE TABLE tblBlandevann(" +
            "'_id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "'drink_id' INTEGER," +
            "'blandevannName' TEXT);";

    String qryCreateTableEkstra = "CREATE TABLE tblEkstra(" +
            "'_id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "'drink_id' INTEGER," +
            "'ekstraName' TEXT);";

    public DrinkDatabase(Context context) {
        super(context, dbname, null, dbversion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(qryCreateTableDrink);
        db.execSQL(qryCreateTableSprit);
        db.execSQL(qryCreateTableBlandevann);
        db.execSQL(qryCreateTableEkstra);
        ContentValues drinkvalues = new ContentValues();
        ContentValues spritvalues = new ContentValues();
        ContentValues blandevannvalues = new ContentValues();
        ContentValues ekstravalues = new ContentValues();
//        insert values drink 1
        drinkvalues.put("drinkName", "Screwdriver");
        drinkvalues.put("isType", Is.Isbiter.toString());
        drinkvalues.put("glassType", Glass.Highball.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Ha isbitene i glasset først.\n" +
                        "Hell spriten over isbitene.\n" +
                        "Fyll resten med juice til ønsket mengde.\n" +
                        "Bruk appelsinskive som garnityr på kanten glasset."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 1);
        spritvalues.put("spritType", "Vodka");
        spritvalues.put("spritMengde", "4 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 1);
        blandevannvalues.put("blandevannName", "Appelsinjuice");
        db.insert("tblBlandevann", null, blandevannvalues);

        ekstravalues.put("drink_id", 1);
        ekstravalues.put("ekstraName", "Appelsinskive");
        db.insert("tblEkstra", null, ekstravalues);
//        insert values drink 2
        drinkvalues.put("drinkName", "Sex on the beach");
        drinkvalues.put("isType", Is.Isbiter.toString());
        drinkvalues.put("glassType", Glass.Highball.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Ha ønsket mengde isbiter i glasset først.\n" +
                        "Hell vodka og ferskenlikør over isbitene.\n" +
                        "Fyll resten av glasset med like mengder appelsinjuice og tranebærjuice.\n" +
                        "Bruk appelsinskive som garnityr på kanten av glasset."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 2);
        spritvalues.put("spritType", "Vodka");
        spritvalues.put("spritMengde", "4 cl");
        db.insert("tblSprit", null, spritvalues);

        spritvalues.put("drink_id", 2);
        spritvalues.put("spritType", "Ferskenlikør");
        spritvalues.put("spritMengde", "2 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 2);
        blandevannvalues.put("blandevannName", "Appelsinjuice");
        db.insert("tblBlandevann", null, blandevannvalues);

        blandevannvalues.put("drink_id", 2);
        blandevannvalues.put("blandevannName", "Tranebærjuice");
        db.insert("tblBlandevann", null, blandevannvalues);

        ekstravalues.put("drink_id", 2);
        ekstravalues.put("ekstraName", "Appelsinskive");
        db.insert("tblEkstra", null, ekstravalues);
//        insert values drink 3
        drinkvalues.put("drinkName", "Strawberry daiquiri");
        drinkvalues.put("isType", Is.Knust.toString());
        drinkvalues.put("glassType", Glass.Coupe.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Putt mange isbiter og spriten i blenderen.\n" +
                        "Ha i mange jordbær, litt limejuice og litt grenadine.\n" +
                        "Ha i jordbærsirup/ sukker etter ønske.\n" +
                        "Start blender og miks til det ikke er flere klumper igjen i blandingen."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 3);
        spritvalues.put("spritType", "Bacardi rom");
        spritvalues.put("spritMengde", "4 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 3);
        blandevannvalues.put("blandevannName", "Limejuice");
        db.insert("tblBlandevann", null, blandevannvalues);

        blandevannvalues.put("drink_id", 3);
        blandevannvalues.put("blandevannName", "Grenadine");
        db.insert("tblBlandevann", null, blandevannvalues);

        ekstravalues.put("drink_id", 3);
        ekstravalues.put("ekstraName", "Jordbær (vanlig eller frosne)");
        db.insert("tblEkstra", null, ekstravalues);

        ekstravalues.put("drink_id", 3);
        ekstravalues.put("ekstraName", "Jordbærsirup eller sukker");
        db.insert("tblEkstra", null, ekstravalues);

        ekstravalues.put("drink_id", 3);
        ekstravalues.put("ekstraName", "Blender/ mikser");
        db.insert("tblEkstra", null, ekstravalues);

//        insert values drink 4
        drinkvalues.put("drinkName", "Vodka sunrise");
        drinkvalues.put("isType", Is.Isbiter.toString());
        drinkvalues.put("glassType", Glass.Highball.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Ha isbitene i glasset først.\n" +
                        "Hell spriten over isbitene.\n" +
                        "Fyll med juice til ønsket mengde.\n" +
                        "Hell litt grenadine i og la det synke ned til bunnen slik at det ser ut som en soloppgang.\n" +
                        "Bruk appelsinskive som garnityr på kanten glasset."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 4);
        spritvalues.put("spritType", "Vodka");
        spritvalues.put("spritMengde", "4 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 4);
        blandevannvalues.put("blandevannName", "Appelsinjuice");
        db.insert("tblBlandevann", null, blandevannvalues);

        blandevannvalues.put("drink_id", 4);
        blandevannvalues.put("blandevannName", "Grenadine");
        db.insert("tblBlandevann", null, blandevannvalues);

        ekstravalues.put("drink_id", 4);
        ekstravalues.put("ekstraName", "Appelsinskive");
        db.insert("tblEkstra", null, ekstravalues);

//        insert values drink 5
        drinkvalues.put("drinkName", "Tequila sunrise");
        drinkvalues.put("isType", Is.Isbiter.toString());
        drinkvalues.put("glassType", Glass.Highball.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Ha isbitene i glasset først.\n" +
                        "Hell spriten over isbitene.\n" +
                        "Fyll med juice til ønsket mengde.\n" +
                        "Hell litt grenadine i og la det synke ned til bunnen slik at det ser ut som en soloppgang.\n" +
                        "Bruk appelsinskive som garnityr på kanten glasset."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 5);
        spritvalues.put("spritType", "Sierra tequila");
        spritvalues.put("spritMengde", "4 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 5);
        blandevannvalues.put("blandevannName", "Appelsinjuice");
        db.insert("tblBlandevann", null, blandevannvalues);

        blandevannvalues.put("drink_id", 5);
        blandevannvalues.put("blandevannName", "Grenadine");
        db.insert("tblBlandevann", null, blandevannvalues);

        ekstravalues.put("drink_id", 5);
        ekstravalues.put("ekstraName", "Appelsinskive");
        db.insert("tblEkstra", null, ekstravalues);

//                insert values drink 6
        drinkvalues.put("drinkName", "Cuba libre");
        drinkvalues.put("isType", Is.Isbiter.toString());
        drinkvalues.put("glassType", Glass.Highball.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Ha isbitene i glasset først.\n" +
                        "Hell spriten over isbitene.\n" +
                        "Ha i litt limejuice og topp drinken med cola."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 6);
        spritvalues.put("spritType", "Bacardi rom");
        spritvalues.put("spritMengde", "4 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 6);
        blandevannvalues.put("blandevannName", "Cola");
        db.insert("tblBlandevann", null, blandevannvalues);

        blandevannvalues.put("drink_id", 6);
        blandevannvalues.put("blandevannName", "Limejuice");
        db.insert("tblBlandevann", null, blandevannvalues);

//                insert values drink 7
        drinkvalues.put("drinkName", "Shark attack");
        drinkvalues.put("isType", Is.Isbiter.toString());
        drinkvalues.put("glassType", Glass.Highball.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Ha isbitene i glasset først.\n" +
                        "Hell tequila og vodka over isbitene.\n" +
                        "Ha oppi blue curacao og litt grenadine.\n" +
                        "Fyll resten med sprite."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 7);
        spritvalues.put("spritType", "Tequila");
        spritvalues.put("spritMengde", "3 cl");
        db.insert("tblSprit", null, spritvalues);

        spritvalues.put("drink_id", 7);
        spritvalues.put("spritType", "Vodka");
        spritvalues.put("spritMengde", "1 cl");
        db.insert("tblSprit", null, spritvalues);

        spritvalues.put("drink_id", 7);
        spritvalues.put("spritType", "Blue curacao");
        spritvalues.put("spritMengde", "1 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 7);
        blandevannvalues.put("blandevannName", "Sprite");
        db.insert("tblBlandevann", null, blandevannvalues);

        blandevannvalues.put("drink_id", 7);
        blandevannvalues.put("blandevannName", "Grenadine");
        db.insert("tblBlandevann", null, blandevannvalues);

//                insert values drink 8
        drinkvalues.put("drinkName", "Aloe vera");
        drinkvalues.put("isType", Is.Isbiter.toString());
        drinkvalues.put("glassType", Glass.Highball.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Ha isbitene i glasset først.\n" +
                        "Hell spriten over isbitene.\n" +
                        "Ha oppi litt limejuice.\n" +
                        "Tilsett like mye sour mix som spriten til sammen.\n" +
                        "Fyll resten med 7up."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 8);
        spritvalues.put("spritType", "Vodka");
        spritvalues.put("spritMengde", "2 cl");
        db.insert("tblSprit", null, spritvalues);

        spritvalues.put("drink_id", 8);
        spritvalues.put("spritType", "Midori");
        spritvalues.put("spritMengde", "2 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 8);
        blandevannvalues.put("blandevannName", "7up");
        db.insert("tblBlandevann", null, blandevannvalues);

        blandevannvalues.put("drink_id", 8);
        blandevannvalues.put("blandevannName", "Limejuice");
        db.insert("tblBlandevann", null, blandevannvalues);

        blandevannvalues.put("drink_id", 8);
        blandevannvalues.put("blandevannName", "Sour mix");
        db.insert("tblBlandevann", null, blandevannvalues);

//                insert values drink 9
        drinkvalues.put("drinkName", "Summer breeze");
        drinkvalues.put("isType", Is.Isbiter.toString());
        drinkvalues.put("glassType", Glass.Highball.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Ha isbitene i glasset først.\n" +
                        "Hell spriten over isbitene.\n" +
                        "Ha oppi litt sour mix.\n" +
                        "Fyll like mengder fersken og tranebærjuice."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 9);
        spritvalues.put("spritType", "Vodka");
        spritvalues.put("spritMengde", "4 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 9);
        blandevannvalues.put("blandevannName", "Ferskenjuice");
        db.insert("tblBlandevann", null, blandevannvalues);

        blandevannvalues.put("drink_id", 9);
        blandevannvalues.put("blandevannName", "Tranebærjuice");
        db.insert("tblBlandevann", null, blandevannvalues);

        blandevannvalues.put("drink_id", 9);
        blandevannvalues.put("blandevannName", "Sour mix");
        db.insert("tblBlandevann", null, blandevannvalues);

//                insert values drink 10
        drinkvalues.put("drinkName", "Vik n' rum");
        drinkvalues.put("isType", Is.Ingen.toString());
        drinkvalues.put("glassType", Glass.Tilfeldig.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Ha oppi malibu rom.\n" +
                        "Fyll resterende med fersken iste."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 10);
        spritvalues.put("spritType", "Malibu");
        spritvalues.put("spritMengde", "8 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 10);
        blandevannvalues.put("blandevannName", "Fersken iste");
        db.insert("tblBlandevann", null, blandevannvalues);
        //        insert values drink 11
        drinkvalues.put("drinkName", "Long island iced tea");
        drinkvalues.put("isType", Is.Isbiter.toString());
        drinkvalues.put("glassType", Glass.Highball.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Ha isbitene i glasset først.\n" +
                        "Hell spriten over isbitene.\n" +
                        "Ha oppi sour mix/ sitronjuice etter smak.\n" +
                        "Toppes med cola."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 11);
        spritvalues.put("spritType", "Vodka");
        spritvalues.put("spritMengde", "1 cl");
        db.insert("tblSprit", null, spritvalues);

        spritvalues.put("drink_id", 11);
        spritvalues.put("spritType", "Rom");
        spritvalues.put("spritMengde", "1 cl");
        db.insert("tblSprit", null, spritvalues);

        spritvalues.put("drink_id", 11);
        spritvalues.put("spritType", "Tequila");
        spritvalues.put("spritMengde", "1 cl");
        db.insert("tblSprit", null, spritvalues);

        spritvalues.put("drink_id", 11);
        spritvalues.put("spritType", "Gin");
        spritvalues.put("spritMengde", "1 cl");
        db.insert("tblSprit", null, spritvalues);

        spritvalues.put("drink_id", 11);
        spritvalues.put("spritType", "Triple sec");
        spritvalues.put("spritMengde", "1 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 11);
        blandevannvalues.put("blandevannName", "Sour mix/ sitronjuice");
        db.insert("tblBlandevann", null, blandevannvalues);

        blandevannvalues.put("drink_id", 11);
        blandevannvalues.put("blandevannName", "Cola");
        db.insert("tblBlandevann", null, blandevannvalues);
        //        insert values drink 12
        drinkvalues.put("drinkName", "Green orange");
        drinkvalues.put("isType", Is.Isbiter.toString());
        drinkvalues.put("glassType", Glass.Highball.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Ha isbitene i glasset først.\n" +
                        "Hell pisang ambon over isbitene.\n" +
                        "Fyll resten med juice til ønsket mengde.\n" +
                        "Bruk appelsinskive som garnityr på kanten glasset."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 12);
        spritvalues.put("spritType", "Pisang ambon");
        spritvalues.put("spritMengde", "8 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 12);
        blandevannvalues.put("blandevannName", "Appelsinjuice");
        db.insert("tblBlandevann", null, blandevannvalues);

        ekstravalues.put("drink_id", 12);
        ekstravalues.put("ekstraName", "Appelsinskive");
        db.insert("tblEkstra", null, ekstravalues);
        //        insert values drink 13
        drinkvalues.put("drinkName", "Sure sivert");
        drinkvalues.put("isType", Is.Isbiter.toString());
        drinkvalues.put("glassType", Glass.Highball.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Ha isbitene i glasset først.\n" +
                        "Hell spriten over isbitene.\n" +
                        "Ha i litt sour mix og litt krem.\n" +
                        "Rist/ rør sammen til den er mikset.\n" +
                        "Fyll resten med 7up til ønsket mengde."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 13);
        spritvalues.put("spritType", "Vodka");
        spritvalues.put("spritMengde", "2 cl");
        db.insert("tblSprit", null, spritvalues);

        spritvalues.put("drink_id", 13);
        spritvalues.put("spritType", "Pisang ambon");
        spritvalues.put("spritMengde", "2 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 13);
        blandevannvalues.put("blandevannName", "Sour mix");
        db.insert("tblBlandevann", null, blandevannvalues);

        blandevannvalues.put("drink_id", 13);
        blandevannvalues.put("blandevannName", "Krem");
        db.insert("tblBlandevann", null, blandevannvalues);

        blandevannvalues.put("drink_id", 13);
        blandevannvalues.put("blandevannName", "7up");
        db.insert("tblBlandevann", null, blandevannvalues);
        //        insert values drink 14
        drinkvalues.put("drinkName", "Bloody mary");
        drinkvalues.put("isType", Is.Ingen.toString());
        drinkvalues.put("glassType", Glass.Tilfeldig.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Hell vodka i glasset.\n" +
                        "Ha i litt sitronjuice, litt worchestershire sauce og litt tabasco.\n" +
                        "Tilsett en klype sellerisalt og pepper.\n" +
                        "Toppes med tomatjuice."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 14);
        spritvalues.put("spritType", "Vodka");
        spritvalues.put("spritMengde", "4 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 14);
        blandevannvalues.put("blandevannName", "Tomatjuice");
        db.insert("tblBlandevann", null, blandevannvalues);

        blandevannvalues.put("drink_id", 14);
        blandevannvalues.put("blandevannName", "Sitronjuice");
        db.insert("tblBlandevann", null, blandevannvalues);

        ekstravalues.put("drink_id", 14);
        ekstravalues.put("ekstraName", "Worchestershire sauce");
        db.insert("tblEkstra", null, ekstravalues);

        ekstravalues.put("drink_id", 14);
        ekstravalues.put("ekstraName", "Tabasco");
        db.insert("tblEkstra", null, ekstravalues);

        ekstravalues.put("drink_id", 14);
        ekstravalues.put("ekstraName", "Sellerisalt salt");
        db.insert("tblEkstra", null, ekstravalues);

        ekstravalues.put("drink_id", 14);
        ekstravalues.put("ekstraName", "Pepper");
        db.insert("tblEkstra", null, ekstravalues);
        //        insert values drink 15
        drinkvalues.put("drinkName", "Kamikaze");
        drinkvalues.put("isType", Is.Ingen.toString());
        drinkvalues.put("glassType", Glass.Shotglass.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Hell spriten sammen i et glass.\n" +
                        "Tilsett samme mengde blandevann som det er sprit i glasset.\n" +
                        "Miks ingrediensene sammen og hell over i shotglass.\n" +
                        "Ca. 4 shots."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 15);
        spritvalues.put("spritType", "Vodka");
        spritvalues.put("spritMengde", "4 cl");
        db.insert("tblSprit", null, spritvalues);

        spritvalues.put("drink_id", 15);
        spritvalues.put("spritType", "Blue curacao/ triple sec");
        spritvalues.put("spritMengde", "4 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 15);
        blandevannvalues.put("blandevannName", "Sprite/ limejuice");
        db.insert("tblBlandevann", null, blandevannvalues);

        //        insert values drink 16
        drinkvalues.put("drinkName", "Pineapple upside down");
        drinkvalues.put("isType", Is.Ingen.toString());
        drinkvalues.put("glassType", Glass.Shotglass.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Miks sammen like mengder sprit og juice i et glass.\n" +
                        "Hell miksen over i shotglass.\n" +
                        "Ha i litt grenadine i hvert glass så den danner et rødt lag i bunn."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 16);
        spritvalues.put("spritType", "Vodka");
        spritvalues.put("spritMengde", "2 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 16);
        blandevannvalues.put("blandevannName", "Annanasjuice");
        db.insert("tblBlandevann", null, blandevannvalues);

        blandevannvalues.put("drink_id", 16);
        blandevannvalues.put("blandevannName", "Grenadine");
        db.insert("tblBlandevann", null, blandevannvalues);

        //        insert values drink 17
        drinkvalues.put("drinkName", "Peachy lemon drop");
        drinkvalues.put("isType", Is.Ingen.toString());
        drinkvalues.put("glassType", Glass.Shotglass.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Miks sammen sprit og litt sitronjuice i et glass.\n" +
                        "Hell miksen over i shotglass."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 17);
        spritvalues.put("spritType", "Vodka");
        spritvalues.put("spritMengde", "2 cl");
        db.insert("tblSprit", null, spritvalues);

        spritvalues.put("drink_id", 17);
        spritvalues.put("spritType", "Ferskenlikør");
        spritvalues.put("spritMengde", "1 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 17);
        blandevannvalues.put("blandevannName", "Sitronjuice");
        db.insert("tblBlandevann", null, blandevannvalues);

        //        insert values drink 18
        drinkvalues.put("drinkName", "Lemon drop");
        drinkvalues.put("isType", Is.Ingen.toString());
        drinkvalues.put("glassType", Glass.Shotglass.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Miks sammen sprit og litt sitronjuice i et glass.\n" +
                        "Hell miksen over i shotglass."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 18);
        spritvalues.put("spritType", "Vodka");
        spritvalues.put("spritMengde", "2 cl");
        db.insert("tblSprit", null, spritvalues);

        spritvalues.put("drink_id", 18);
        spritvalues.put("spritType", "Triple sec");
        spritvalues.put("spritMengde", "1 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 18);
        blandevannvalues.put("blandevannName", "Sitronjuice");
        db.insert("tblBlandevann", null, blandevannvalues);

        //        insert values drink 19
        drinkvalues.put("drinkName", "Tequila shot");
        drinkvalues.put("isType", Is.Ingen.toString());
        drinkvalues.put("glassType", Glass.Shotglass.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "1. Salt\n" +
                        "2. Shot\n" +
                        "3. Sitron"
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 19);
        spritvalues.put("spritType", "Tequila");
        spritvalues.put("spritMengde", "4 cl");
        db.insert("tblSprit", null, spritvalues);

        ekstravalues.put("drink_id", 19);
        ekstravalues.put("ekstraName", "Sitronskive");
        db.insert("tblEkstra", null, ekstravalues);

        ekstravalues.put("drink_id", 19);
        ekstravalues.put("ekstraName", "Salt");
        db.insert("tblEkstra", null, ekstravalues);

        //        insert values drink 20
        drinkvalues.put("drinkName", "Mimosa");
        drinkvalues.put("isType", Is.Ingen.toString());
        drinkvalues.put("glassType", Glass.Flute.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Fyll glasset med like mengder champagne og appelsinjuice."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 20);
        spritvalues.put("spritType", "Champagne");
        spritvalues.put("spritMengde", "8 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 20);
        blandevannvalues.put("blandevannName", "Appelsinjuice");
        db.insert("tblBlandevann", null, blandevannvalues);

        //        insert values drink 21
        drinkvalues.put("drinkName", "Luna 2");
        drinkvalues.put("isType", Is.Isbiter.toString());
        drinkvalues.put("glassType", Glass.Highball.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Ha isbitene i glasset først.\n" +
                        "Hell spriten over isbitene.\n" +
                        "Fyll resten med blandevann.\n" +
                        "1/3 fersken iste.\n" +
                        "2/3 cola."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 21);
        spritvalues.put("spritType", "Bacardi rom");
        spritvalues.put("spritMengde", "4 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 21);
        blandevannvalues.put("blandevannName", "Cola");
        db.insert("tblBlandevann", null, blandevannvalues);

        blandevannvalues.put("drink_id", 21);
        blandevannvalues.put("blandevannName", "Fersken iste");
        db.insert("tblBlandevann", null, blandevannvalues);

        //        insert values drink 22
        drinkvalues.put("drinkName", "St. Hans Hella");
        drinkvalues.put("isType", Is.Isbiter.toString());
        drinkvalues.put("glassType", Glass.Highball.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Ha isbitene i glasset først.\n" +
                        "Hell spriten over isbitene.\n" +
                        "Fyll resten med like mengder blandevann."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 22);
        spritvalues.put("spritType", "Vodka");
        spritvalues.put("spritMengde", "4 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 22);
        blandevannvalues.put("blandevannName", "Appelsinjuice");
        db.insert("tblBlandevann", null, blandevannvalues);

        blandevannvalues.put("drink_id", 22);
        blandevannvalues.put("blandevannName", "Fersken iste");
        db.insert("tblBlandevann", null, blandevannvalues);

        //        insert values drink 23
        drinkvalues.put("drinkName", "Fjellbekk");
        drinkvalues.put("isType", Is.Isbiter.toString());
        drinkvalues.put("glassType", Glass.Highball.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Ha isbitene i glasset først.\n" +
                        "Hell sprit over isbitene.\n" +
                        "Ha i litt limejuice.\n" +
                        "Fyll resten med sprite.\n" +
                        "Bruk limeskiver/båter som garnityr oppi glasset."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 23);
        spritvalues.put("spritType", "Vodka");
        spritvalues.put("spritMengde", "2 cl");
        db.insert("tblSprit", null, spritvalues);

        spritvalues.put("drink_id", 23);
        spritvalues.put("spritType", "Akevitt");
        spritvalues.put("spritMengde", "2 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 23);
        blandevannvalues.put("blandevannName", "Limejuice");
        db.insert("tblBlandevann", null, blandevannvalues);

        blandevannvalues.put("drink_id", 23);
        blandevannvalues.put("blandevannName", "Sprite");
        db.insert("tblBlandevann", null, blandevannvalues);

        ekstravalues.put("drink_id", 23);
        ekstravalues.put("ekstraName", "Limeskiver");
        db.insert("tblEkstra", null, ekstravalues);

        //        insert values drink 24
        drinkvalues.put("drinkName", "Mojito");
        drinkvalues.put("isType", Is.Knust.toString());
        drinkvalues.put("glassType", Glass.Highball.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Press limebåtene, mynteblader og sukker sammen i bunnen av et glass.\n" +
                        "Tilsett hvit rom i blandingen.\n" +
                        "Ha oppi knust is og miks sammen innhold.\n" +
                        "Fyll opp med club soda."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 24);
        spritvalues.put("spritType", "Hvit rom");
        spritvalues.put("spritMengde", "4 cl");
        db.insert("tblSprit", null, spritvalues);

        blandevannvalues.put("drink_id", 24);
        blandevannvalues.put("blandevannName", "Club soda");
        db.insert("tblBlandevann", null, blandevannvalues);

        ekstravalues.put("drink_id", 24);
        ekstravalues.put("ekstraName", "2-3 limebåter");
        db.insert("tblEkstra", null, ekstravalues);

        ekstravalues.put("drink_id", 24);
        ekstravalues.put("ekstraName", "2 ts. sukker");
        db.insert("tblEkstra", null, ekstravalues);

        ekstravalues.put("drink_id", 24);
        ekstravalues.put("ekstraName", "Mynteblader");
        db.insert("tblEkstra", null, ekstravalues);

        //        insert values drink 25
        drinkvalues.put("drinkName", "Manhatten");
        drinkvalues.put("isType", Is.Ingen.toString());
        drinkvalues.put("glassType", Glass.Cocktail.toString());
        drinkvalues.put("instruksjoner",
                "Instruksjoner:\n" +
                        "Tilsett all spriten i et avkjølt glass.\n" +
                        "Pynt glasset med cocktailbær."
        );
        drinkvalues.put("rating", "0");
        db.insert("tblDrink", null, drinkvalues);

        spritvalues.put("drink_id", 25);
        spritvalues.put("spritType", "Whiskey");
        spritvalues.put("spritMengde", "4 cl");
        db.insert("tblSprit", null, spritvalues);

        spritvalues.put("drink_id", 25);
        spritvalues.put("spritType", "Rød vermut");
        spritvalues.put("spritMengde", "2 cl");
        db.insert("tblSprit", null, spritvalues);

        spritvalues.put("drink_id", 25);
        spritvalues.put("spritType", "Angostura bitter");
        spritvalues.put("spritMengde", "5 dråper");
        db.insert("tblSprit", null, spritvalues);

        ekstravalues.put("drink_id", 25);
        ekstravalues.put("ekstraName", "Cocktailbær");
        db.insert("tblEkstra", null, ekstravalues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context, "onUpgrade", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context, "onDowngrade", Toast.LENGTH_SHORT).show();
    }
}