package com.example.quentin.androidinit.BDD;

import android.provider.BaseColumns;

/**
 * Created by quentin on 23/10/16.
 */

public class BDDStructure {

    public BDDStructure() {
    }

    public static final class Person implements BaseColumns {

        private Person() {}
        public static final String _ID = "_id";
        public static final String NOM ="nom";
        public static final String PRENOM = "prenom";
        public static final String DATE_NAISSANCE = "date_naissance";
        public static final String VILLE_NAISSANCE = "ville_naissance";
    }
}