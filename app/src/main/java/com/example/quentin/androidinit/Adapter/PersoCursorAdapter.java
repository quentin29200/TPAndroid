package com.example.quentin.androidinit.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.quentin.androidinit.Model.PersoModel;
import com.example.quentin.androidinit.R;


public class PersoCursorAdapter extends CursorAdapter {
    private LayoutInflater mInflater;

    public PersoCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        Log.i("Adapter", "HELLO");
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return mInflater.inflate(R.layout.item, parent, false);
    }

    @Override
    public void bindView(View v, Context context, Cursor cursor) {
        TextView nom = (TextView) v.findViewById(R.id.nom);
        TextView prenom = (TextView) v.findViewById(R.id.prenom);
        TextView date_naiss = (TextView) v.findViewById(R.id.date_naiss);
        TextView ville = (TextView) v.findViewById(R.id.ville);

        // Populate the data into the template view using the data object
        nom.setText(cursor.getString(cursor.getColumnIndex(PersoModel.NOM)));
        prenom.setText(cursor.getString(cursor.getColumnIndex(PersoModel.PRENOM)));
        date_naiss.setText(cursor.getString(cursor.getColumnIndex(PersoModel.DATE_NAISSANCE)));
        ville.setText(cursor.getString(cursor.getColumnIndex(PersoModel.VILLE_NAISSANCE)));

    }

}