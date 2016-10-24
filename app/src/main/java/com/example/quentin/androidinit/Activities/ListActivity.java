package com.example.quentin.androidinit.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.quentin.androidinit.Adapter.PersoCursorAdapter;
import com.example.quentin.androidinit.Model.PersoModel;
import com.example.quentin.androidinit.Provider.PersonContentProvider;
import com.example.quentin.androidinit.Object.Perso;
import com.example.quentin.androidinit.R;

public class ListActivity extends AppCompatActivity {

    private ListView listClients;
    //private ArrayList<Perso> persos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        //Récupération de la listview créée dans le fichier main.xml
        this.listClients = (ListView) findViewById(R.id.listClients);

        /*
        this.persos = new ArrayList<>();

        Perso p1 = new Perso("Lagadec","Quentin","15/02/1994","Brest","");
        Perso p2 = new Perso("Dupont","Martin","15/02/1994","Quimper","");
        Perso p3 = new Perso("Bernard","Frank","15/02/1994","Douarnenez","");
        Perso p4 = new Perso("Albert","Marc","15/02/1994","Brest","");

        this.persos.add(p1);
        this.persos.add(p2);
        this.persos.add(p3);
        this.persos.add(p4);
        */
        Intent i = getIntent();
        if (i != null) {
            Perso p = i.getParcelableExtra("perso");
            if (p != null) {
                this.insertPerso(p);
            }
        }
        this.displayContentProvider();
    }

    public void addPerson(View v) {
        Intent addActivity = new Intent(ListActivity.this, MainActivity.class);
        startActivity(addActivity);
    }

    private void displayContentProvider() {
        Uri persons = PersonContentProvider.CONTENT_URI;
        Cursor c = getContentResolver().query(persons, null, null, null, null);
        PersoCursorAdapter persocadapter = new PersoCursorAdapter(this, c, 0);
        this.listClients.setAdapter(persocadapter);
    }

    private void insertPerso(Perso p) {
        ContentValues persons = new ContentValues();
        persons.put(PersoModel.NOM, p.getNom());
        persons.put(PersoModel.PRENOM, p.getPrenom());
        persons.put(PersoModel.DATE_NAISSANCE, p.getDate_naissance());
        persons.put(PersoModel.VILLE_NAISSANCE, p.getVille_naissance());
        getContentResolver().insert(PersonContentProvider.CONTENT_URI, persons);

        persons.clear();
    }
}
