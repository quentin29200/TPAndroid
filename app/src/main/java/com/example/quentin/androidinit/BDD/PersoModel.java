package com.example.quentin.androidinit.BDD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.quentin.androidinit.Perso;

public class PersoModel extends SQLiteOpenHelper {

    public static final String TABLE_PERSOS = "persos";
    public static final String COLUMN_ID = "_id";
    public static final String NOM ="nom";
    public static final String PRENOM = "prenom";
    public static final String DATE_NAISSANCE = "date_naissance";
    public static final String VILLE_NAISSANCE = "ville_naissance";

    private static final String DATABASE_NAME = "personnes.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_PERSOS + "( " + COLUMN_ID
            + " integer primary key autoincrement, "
            + NOM + " VARCHAR(255),"
            + PRENOM + " VARCHAR(255),"
            + DATE_NAISSANCE + " VARCHAR(10),"
            + VILLE_NAISSANCE + " VARCHAR(255)" + ");";

    public PersoModel(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private Perso cursorToPerso(Cursor cursor) {
        Perso p = new Perso();
        p.setId(cursor.getInt(0));
        p.setNom(cursor.getString(1));
        p.setPrenom(cursor.getString(2));
        p.setDate_naissance(cursor.getString(3));
        p.setVille_naissance(cursor.getString(4));

        return p;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(PersoModel.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSOS);
        onCreate(db);
    }

}