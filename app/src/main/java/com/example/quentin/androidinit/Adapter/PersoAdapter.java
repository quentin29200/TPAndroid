package com.example.quentin.androidinit.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.quentin.androidinit.Object.Perso;
import com.example.quentin.androidinit.R;

import java.util.ArrayList;

public class PersoAdapter extends ArrayAdapter<Perso> {
    public PersoAdapter(Context context, ArrayList<Perso> persos) {
        super(context, 0, persos);
    }

    @NonNull
    @Override
    public View getView(int position, View v, ViewGroup parent) {
        Perso p = getItem(position);
        if (p != null) {
            if (v == null) {
                v = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
            }

            // Lookup view for data population
            TextView nom = (TextView) v.findViewById(R.id.nom);
            TextView prenom = (TextView) v.findViewById(R.id.prenom);
            TextView date_naiss = (TextView) v.findViewById(R.id.date_naiss);
            TextView ville = (TextView) v.findViewById(R.id.ville);

            // Populate the data into the template view using the data object
            nom.setText(p.getNom());
            prenom.setText(p.getPrenom());
            date_naiss.setText(p.getDate_naissance());
            ville.setText(p.getVille_naissance());
        }
        return v;
    }
}