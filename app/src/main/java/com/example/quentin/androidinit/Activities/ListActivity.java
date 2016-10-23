package com.example.quentin.androidinit.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.quentin.androidinit.Adapter.PersoAdapter;
import com.example.quentin.androidinit.Adapter.PersoCursorAdapter;
import com.example.quentin.androidinit.BDD.BDDStructure.Person;
import com.example.quentin.androidinit.BDD.PersonContentProvider;
import com.example.quentin.androidinit.Perso;
import com.example.quentin.androidinit.R;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ListView listClients;
    private PersoAdapter persoAdapter;
    private ArrayList<Perso> persos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        //Récupération de la listview créée dans le fichier main.xml
        this.listClients = (ListView) findViewById(R.id.listClients);

        this.insertRecords();
        this.displayContentProvider();

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

        Intent i = getIntent();
        if (i != null) {
            Perso p = i.getParcelableExtra("perso");
            if (p != null) {
                this.persos.add(p);
            }
        }

        this.persoAdapter = new PersoAdapter(getApplicationContext(), this.persos);

        this.listClients.setAdapter(this.persoAdapter);*/
    }

    public void addPerson(View v) {
        Intent addActivity = new Intent(ListActivity.this, MainActivity.class);
        startActivity(addActivity);
    }

    private void displayContentProvider() {
        Uri persons = PersonContentProvider.CONTENT_URI;
        Cursor c = getContentResolver().query(persons, null, null, null, null);
        PersoCursorAdapter persocadapter = new PersoCursorAdapter(this,c, 0);
        this.listClients.setAdapter(persocadapter);
    }

    private void insertRecords() {
        ContentValues persons = new ContentValues();
        persons.put(Person.NOM, "Lagadec");
        persons.put(Person.PRENOM, "Quentin");
        persons.put(Person.DATE_NAISSANCE, "15/02/1994");
        persons.put(Person.VILLE_NAISSANCE, "BREST");
        getContentResolver().insert(PersonContentProvider.CONTENT_URI, persons);

        persons.clear();
        persons.put(Person.NOM, "Albert");
        persons.put(Person.PRENOM, "Frank");
        persons.put(Person.DATE_NAISSANCE, "15/12/1987");
        persons.put(Person.VILLE_NAISSANCE, "QUIMPER");
        getContentResolver().insert(PersonContentProvider.CONTENT_URI, persons);

        persons.clear();
        persons.put(Person.NOM, "Dupont");
        persons.put(Person.PRENOM, "Martin");
        persons.put(Person.DATE_NAISSANCE, "17/01/1982");
        persons.put(Person.VILLE_NAISSANCE, "MORLAIX");
        getContentResolver().insert(PersonContentProvider.CONTENT_URI, persons);
    }
}
