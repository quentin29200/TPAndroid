package com.example.quentin.androidinit.BDD;


import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.example.quentin.androidinit.BDD.BDDStructure.Person;

public class PersonContentProvider extends ContentProvider {
    private DatabaseHelper dbHelper;

    public static final String PROVIDER_NAME = "personcontentprovider";

    public static final Uri CONTENT_URI = Uri.parse("content://com.example.quentin.androidinit.BDD."+ PROVIDER_NAME);

    // Nom de notre base de données
    public static final String CONTENT_PROVIDER_DB_NAME = "person.db";
    // Version de notre base de données
    public static final int CONTENT_PROVIDER_DB_VERSION = 1;
    // Nom de la table de notre base
    public static final String CONTENT_PROVIDER_TABLE_NAME = "person";
    // Le Mime de notre content provider, la première partie est toujours identique
    public static final String CONTENT_PROVIDER_MIME = ContentResolver.CURSOR_DIR_BASE_TYPE + '/' + "com.example.quentin.androidinit.BDD.persos";


    // Notre DatabaseHelper
    private static class DatabaseHelper extends SQLiteOpenHelper {

        // Création à partir du Context, du Nom de la table et du numéro de version
        DatabaseHelper(Context context) {
            super(context,PersonContentProvider.CONTENT_PROVIDER_DB_NAME, null, PersonContentProvider.CONTENT_PROVIDER_DB_VERSION);
        }

        // Création des tables
        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("SQL CREATE : ", "CREATE TABLE " + PersonContentProvider.CONTENT_PROVIDER_TABLE_NAME + " ( " + Person._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Person.NOM + " VARCHAR(255)," + Person.PRENOM + " VARCHAR(255),"+ Person.DATE_NAISSANCE + " VARCHAR(10),"+ Person.VILLE_NAISSANCE + " VARCHAR(255)" + ");");
            db.execSQL("CREATE TABLE " + PersonContentProvider.CONTENT_PROVIDER_TABLE_NAME + " ( " + Person._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Person.NOM + " VARCHAR(255)," + Person.PRENOM + " VARCHAR(255),"+ Person.DATE_NAISSANCE + " VARCHAR(10),"+ Person.VILLE_NAISSANCE + " VARCHAR(255)" + ");");
        }

        // Cette méthode sert à gérer la montée de version de notre base
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + PersonContentProvider.CONTENT_PROVIDER_TABLE_NAME);
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        // initialiser des données ici (on simule l'existence d'une BD)
        dbHelper = new DatabaseHelper(getContext());
        return true;
        /*
        data = new Perso[3];

        data[0] = new Perso("Lagadec","Quentin","15/02/1994","Brest","");
        data[1] = new Perso("Dupont","Martin","15/02/1994","Quimper","");
        data[2] = new Perso("Bernard","Frank","15/02/1994","Douarnenez","");*/
    }

    @Override
    public String getType(Uri uri) {
        return CONTENT_PROVIDER_MIME;
    }

    private long getId(Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        if (lastPathSegment != null) {
            try {
                return Long.parseLong(lastPathSegment);
            } catch (NumberFormatException e) {
                Log.e("PersonContentProvider", "Number Format Exception : " + e);
            }
        }
        return -1;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        long id = getId(uri);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if (id < 0) {
            return  db.query(PersonContentProvider.CONTENT_PROVIDER_TABLE_NAME,
                    projection, selection, selectionArgs, null, null,
                    sortOrder);
        } else {
            return      db.query(PersonContentProvider.CONTENT_PROVIDER_TABLE_NAME,
                    projection, Person._ID + "=" + id, null, null, null,
                    null);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        long id = getId(uri);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            if (id < 0)
                return db.update(PersonContentProvider.CONTENT_PROVIDER_TABLE_NAME,values, selection, selectionArgs);
            else
                return db.update(PersonContentProvider.CONTENT_PROVIDER_TABLE_NAME,
                        values, Person._ID + "=" + id, null);
        } finally {
            db.close();
        }
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            long id = db.insertOrThrow(PersonContentProvider.CONTENT_PROVIDER_TABLE_NAME, null, values);

            if (id == -1) {
                throw new RuntimeException(String.format(
                        "%s : Failed to insert [%s] for unknown reasons.","PersonContentProvider", values, uri));
            } else {
                return ContentUris.withAppendedId(uri, id);
            }

        } finally {
            db.close();
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        long id = getId(uri);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            if (id < 0)
                return db.delete(
                        PersonContentProvider.CONTENT_PROVIDER_TABLE_NAME,
                        selection, selectionArgs);
            else
                return db.delete(
                        PersonContentProvider.CONTENT_PROVIDER_TABLE_NAME,
                        Person._ID + "=" + id, selectionArgs);
        } finally {
            db.close();
        }
    }
}