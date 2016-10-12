package com.example.quentin.androidinit;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;
import android.widget.RelativeLayout;

/**
 * Created by quentin on 06/10/16.
 */

public class Perso implements Parcelable {
    private String nom;
    private String prenom;
    private String date_naissance;
    private String ville_naissance;
    private String phone;

    public Perso(String nom, String prenom, String date_naissance, String ville_naissance, String phone) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.ville_naissance = ville_naissance;
        this.phone = phone;
    }

    protected Perso(Parcel in) {
        nom = in.readString();
        prenom = in.readString();
        date_naissance = in.readString();
        ville_naissance = in.readString();
        phone = in.readString();
    }

    public static final Creator<Perso> CREATOR = new Creator<Perso>() {
        @Override
        public Perso createFromParcel(Parcel in) {
            return new Perso(in);
        }

        @Override
        public Perso[] newArray(int size) {
            return new Perso[size];
        }
    };

    @Override
    public int describeContents() {
        //No FileDescriptor
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nom);
        dest.writeString(this.prenom);
        dest.writeString(this.date_naissance);
        dest.writeString(this.ville_naissance);
        dest.writeString(this.phone);
    }

    @Override
    public String toString() {
        return "Perso{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", date_naissance='" + date_naissance + '\'' +
                ", ville_naissance='" + ville_naissance + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
