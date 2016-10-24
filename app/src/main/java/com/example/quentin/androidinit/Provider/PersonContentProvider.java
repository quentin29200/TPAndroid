package com.example.quentin.androidinit.Provider;


import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.quentin.androidinit.Model.PersoModel;

public class PersonContentProvider extends ContentProvider {
    private PersoModel pm;

    public static final String PROVIDER_NAME = "personcontentprovider";
    static final String AUTHORITY = "personcontentprovider";

    public static final Uri CONTENT_URI = Uri.parse("content://"+ PROVIDER_NAME);

    // Le Mime de notre content provider, la première partie est toujours identique
    public static final String CONTENT_PROVIDER_MIME = ContentResolver.CURSOR_DIR_BASE_TYPE + '/' + "com.persos";

    @Override
    public boolean onCreate() {
        // initialiser des données ici (on simule l'existence d'une BD)
        this.pm = new PersoModel(getContext());

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
        SQLiteDatabase db = this.pm.getWritableDatabase();
        if (id < 0) {
            return  db.query(PersoModel.TABLE_PERSOS,
                    projection, selection, selectionArgs, null, null,
                    sortOrder);
        } else {
            return      db.query(PersoModel.TABLE_PERSOS,
                    projection, PersoModel.COLUMN_ID + "=" + id, null, null, null,
                    null);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        this.pm.getWritableDatabase().insert(PersoModel.TABLE_PERSOS, null,
                values);
        this.pm.close();
        return uri;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        long id = getId(uri);
        SQLiteDatabase db = this.pm.getWritableDatabase();

        try {
            if (id < 0)
                return db.update(PersoModel.TABLE_PERSOS,values, selection, selectionArgs);
            else
                return db.update(PersoModel.TABLE_PERSOS,
                        values, PersoModel.COLUMN_ID + "=" + id, null);
        } finally {
            db.close();
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        long id = getId(uri);
        SQLiteDatabase db = this.pm.getWritableDatabase();
        try {
            if (id < 0)
                return db.delete(
                        PersoModel.TABLE_PERSOS,
                        selection, selectionArgs);
            else
                return db.delete(
                        PersoModel.TABLE_PERSOS,
                        PersoModel.COLUMN_ID + "=" + id, selectionArgs);
        } finally {
            db.close();
        }
    }
}