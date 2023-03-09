package com.example.meilleursrestaurants.Controleur;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meilleursrestaurants.Modele.Restaurant;
import com.example.meilleursrestaurants.Modele.RestaurantDAO;
import com.example.meilleursrestaurants.R;

public class AjouterRestoActivity extends AppCompatActivity {
    private EditText nomR, numAdR, voieAdrR, cpR, villeR, descR, horairesR;
    private Button boutonAjouter;
    private Restaurant nouveauRestoAAjouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_resto);

        // On identifie toutes les zones de textes à sélectionner
        nomR = (EditText) findViewById(R.id.editTextNomR);
        numAdR = (EditText) findViewById(R.id.editTextNumAdR);
        voieAdrR = (EditText) findViewById(R.id.editTextVoieAdrR);
        cpR = (EditText) findViewById(R.id.editTextCpR);
        villeR = (EditText) findViewById(R.id.editTextVilleR);
        descR = (EditText) findViewById(R.id.editTextDescR);
        horairesR = (EditText) findViewById(R.id.editTextHorairesR);

        boutonAjouter = (Button) findViewById(R.id.buttonAjouterR);

        final RestaurantDAO restoDAO = new RestaurantDAO(this);

        // On
        boutonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                // On vérifie si tous les EditText sont remplis
                // Si ils sont tous remplis, on instancie le resto à ajouter
                if (!String.valueOf(nomR.getText()).isEmpty() && !String.valueOf(numAdR.getText()).isEmpty() && !String.valueOf(voieAdrR.getText()).isEmpty() && !String.valueOf(cpR.getText()).isEmpty() && !String.valueOf(villeR.getText()).isEmpty() && !String.valueOf(descR.getText()).isEmpty() && !String.valueOf(horairesR.getText()).isEmpty()) {
                    nouveauRestoAAjouter = new Restaurant(String.valueOf(nomR.getText()), String.valueOf(numAdR.getText()), String.valueOf(voieAdrR.getText()), String.valueOf(cpR.getText()), String.valueOf(villeR.getText()), String.valueOf(descR.getText()), String.valueOf(horairesR.getText()));
                    restoDAO.addRestaurant(nouveauRestoAAjouter);
                    Toast. makeText(getApplicationContext(),"Restaurant ajouté !", Toast. LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(), GestionRestosActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast. makeText(getApplicationContext(),"Un ou plusieurs champs sont vides.", Toast. LENGTH_LONG).show();
                    Log.d("*****","*****Un ou plusieurs champs sont vides.");
                }
            }
        });
    }
}
