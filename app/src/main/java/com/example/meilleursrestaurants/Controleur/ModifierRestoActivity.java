package com.example.meilleursrestaurants.Controleur;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meilleursrestaurants.Modele.Restaurant;
import com.example.meilleursrestaurants.Modele.RestaurantDAO;
import com.example.meilleursrestaurants.R;

public class ModifierRestoActivity extends AppCompatActivity {
    private EditText nomR, numAdR, voieAdrR, cpR, villeR, descR, horairesR;
    private int idRecu;
    private String nomRecu, numAdRecu, voieAdrRecu, cpRecu, villeRecue, descRecu, horairesRecues;
    private Button boutonModifier;
    private Restaurant nouveauResto, ancienResto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_resto);

        final RestaurantDAO restoDAO = new RestaurantDAO(this);
        boutonModifier = (Button) findViewById(R.id.buttonModifierR);

        // On identifie toutes les zones de textes à remplir
        nomR = (EditText) findViewById(R.id.editTextNomR);
        numAdR = (EditText) findViewById(R.id.editTextNumAdR);
        voieAdrR = (EditText) findViewById(R.id.editTextVoieAdrR);
        cpR = (EditText) findViewById(R.id.editTextCpR);
        villeR = (EditText) findViewById(R.id.editTextVilleR);
        descR = (EditText) findViewById(R.id.editTextDescR);
        horairesR = (EditText) findViewById(R.id.editTextHorairesR);

        // On récupère toutes les valeurs envoyées de la page FicheRestaurantActivity
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

        ancienResto = restoDAO.getRestaurant(idRecu);

        boutonModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // message de confirmation

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Voulez-vous vraiment modifier cette critique ?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                nouveauResto = new Restaurant(idRecu, String.valueOf(nomR.getText()), String.valueOf(numAdR.getText()), String.valueOf(voieAdrR.getText()), String.valueOf(cpR.getText()), String.valueOf(villeR.getText()), String.valueOf(descR.getText()), String.valueOf(horairesR.getText()));
                                restoDAO.updateResto(nouveauResto, ancienResto);
                                Toast. makeText(getApplicationContext(),"Restaurant modifié !", Toast. LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), GestionRestosActivity.class);
                                startActivity(intent);

                                dialog.cancel();
                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder.create().show();
            }
        });

    }
}
