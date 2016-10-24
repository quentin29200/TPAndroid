package com.example.quentin.androidinit.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.quentin.androidinit.Object.Perso;
import com.example.quentin.androidinit.R;

public class MainActivity extends AppCompatActivity {
    private EditText nom;
    private EditText prenom;
    private EditText date_naissance;
    private EditText ville_naissance;
    private EditText phone;
    private RelativeLayout main_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.reset:
                this.resetParam();
                return true;
            case R.id.add_phone:
                this.addPhone();
                return true;
            case R.id.wiki:
                this.searchWiki();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void searchWiki() {
        this.ville_naissance = (EditText) findViewById(R.id.ville_naissance);
        Intent wiki = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fr.wikipedia.org/wiki/"+ville_naissance));
        startActivity(wiki);
    }

    private void resetParam() {
        this.nom = (EditText) findViewById(R.id.nom);
        this.prenom = (EditText) findViewById(R.id.prenom);
        this.date_naissance = (EditText) findViewById(R.id.date_naissance);
        this.ville_naissance = (EditText) findViewById(R.id.ville_naissance);

        this.nom.setText("");
        this.prenom.setText("");
        this.date_naissance.setText("");
        this.ville_naissance.setText("");
        if (phone != null)
            this.phone.setText("");
    }

    private void addPhone() {
        // Get main layout
        this.main_layout = (RelativeLayout) findViewById(R.id.main_layout);
        // Add rules to add below
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        param.addRule(RelativeLayout.BELOW, R.id.departements);

        // Generate the widget
        this.phone = new EditText(getApplicationContext());
        this.phone.setText("Phone");

        // Add to the layout
        this.main_layout.addView(this.phone, param);

    }

    public void validate(View v) {
        this.nom = (EditText) findViewById(R.id.nom);
        this.prenom = (EditText) findViewById(R.id.prenom);
        this.date_naissance = (EditText) findViewById(R.id.date_naissance);
        this.ville_naissance = (EditText) findViewById(R.id.ville_naissance);

        String resp = " " +
                this.nom.getText() + " " +
                this.prenom.getText() + " " +
                this.date_naissance.getText() + " " +
                this.ville_naissance.getText();

        Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_SHORT).show();

        // Send to the second activity
        Intent secondActivity = new Intent(MainActivity.this, ListActivity.class);

        // Add data
        Perso p = new Perso(this.nom.getText().toString(),
                this.prenom.getText().toString(),
                this.date_naissance.getText().toString(),
                this.ville_naissance.getText().toString(),
                null);

        secondActivity.putExtra("perso", p);

        // Start secondActivity
        startActivity(secondActivity);
    }
}
