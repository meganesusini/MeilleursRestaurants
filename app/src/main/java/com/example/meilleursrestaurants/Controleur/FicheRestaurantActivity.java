package com.example.meilleursrestaurants.Controleur;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meilleursrestaurants.Modele.RestaurantDAO;
import com.example.meilleursrestaurants.R;

public class FicheRestaurantActivity extends AppCompatActivity {
    private int idRecu;
    private String nomRecu, numAdRecu, voieAdrRecu, cpRecu, villeRecue, descRecu, horairesRecues;
    private TextView nomR, numAdR, voieAdrR, cpR, villeR, descR, horairesR;
    private Button boutonModifier, boutonSupprimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_restaurant);

        boutonModifier = (Button) findViewById(R.id.buttonModifierR);
        boutonSupprimer = (Button) findViewById(R.id.buttonSupprimerR);

        // On identifie toutes les zones de textes à modifier
        nomR = (TextView) findViewById(R.id.textViewNomR);
        numAdR = (TextView) findViewById(R.id.textViewNumAdR);
        voieAdrR = (TextView) findViewById(R.id.textViewVoieAdrR);
        cpR = (TextView) findViewById(R.id.textViewCpR);
        villeR = (TextView) findViewById(R.id.textViewVilleR);
        descR = (TextView) findViewById(R.id.textViewDescR);
        horairesR = (TextView) findViewById(R.id.textViewHorairesR);

        // On récupère toutes les valeurs envoyées de la page GestionResto
        idRecu = getIntent().getIntExtra("idR", 0);
        nomRecu = getIntent().getStringExtra("nomR");
        numAdRecu = getIntent().getStringExtra("numAdrR");
        voieAdrRecu = getIntent().getStringExtra("voieAdrR");
        cpRecu = getIntent().getStringExtra("cpR");
        villeRecue = getIntent().getStringExtra("villeR");
        descRecu = getIntent().getStringExtra("descR");
        horairesRecues = getIntent().getStringExtra("horairesR");

        // On affiche les valeurs envoyées dans les zones de texte
        nomR.setText(nomRecu);
        numAdR.setText(numAdRecu);
        voieAdrR.setText(voieAdrRecu);
        cpR.setText(cpRecu);
        villeR.setText(villeRecue);
        descR.setText(descRecu);
        horairesR.setText(horairesRecues);

        final RestaurantDAO restoDAO = new RestaurantDAO(this);

        boutonModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(v.getContext(), ModifierRestoActivity.class);

                i.putExtra("idR", idRecu);
                i.putExtra("nomR", nomRecu);
                i.putExtra("numAdrR", numAdRecu);
                i.putExtra("voieAdrR", voieAdrRecu);
                i.putExtra("cpR", cpRecu);
                i.putExtra("villeR", villeRecue);
                i.putExtra("descR", descRecu);
                i.putExtra("horairesR", horairesRecues);

                startActivity(i);
            }
        });

        boutonSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                restoDAO.deleteResto(idRecu);
                Toast. makeText(getApplicationContext(),"Restaurant supprimé !", Toast. LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), GestionRestosActivity.class);
                startActivity(intent);
            }
        });

    }
}
