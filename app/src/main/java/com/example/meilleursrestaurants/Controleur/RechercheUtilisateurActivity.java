package com.example.meilleursrestaurants.Controleur;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.meilleursrestaurants.Modele.Restaurant;
import com.example.meilleursrestaurants.Modele.RestaurantDAO;
import com.example.meilleursrestaurants.R;

import java.util.ArrayList;

/**
 * Created by lali.nguyen on 01/04/2022.
 */

public class RechercheUtilisateurActivity extends AppCompatActivity {
    private ListView listeRestos;
    private ArrayList<Restaurant> lesRestaurants;
    private int idURecu;
    private Button btnAcc, btnCritique;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_utilisateur);

        btnAcc = (Button)findViewById(R.id.buttonAccueil) ;
        listeRestos = (ListView) findViewById(R.id.listViewRestos);

        idURecu = getIntent().getIntExtra("idU", 0);

        // On configure la listView des restaurants
        final RestaurantDAO restoDAO = new RestaurantDAO(this);
        lesRestaurants = restoDAO.getRestaurants();

        final ListView listViewRestos = (ListView) findViewById(R.id.listViewRestos);

        final ArrayAdapter<Restaurant> arrayAdapter = new ArrayAdapter<Restaurant>(this, android.R.layout.simple_list_item_1, lesRestaurants);

        listViewRestos.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        // Fin config des restos

        listViewRestos.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurant restoItem = (Restaurant) listViewRestos.getAdapter().getItem(position);
                Intent i = new Intent(getApplicationContext(), AjouterCritiqueActivity.class);
                i.putExtra("idR", restoItem.getIdR());
                i.putExtra("nomR", restoItem.getNomR());
                i.putExtra("numAdrR", restoItem.getNumAdR());
                i.putExtra("voieAdrR", restoItem.getVoieAdR());
                i.putExtra("cpR", restoItem.getCpR());
                i.putExtra("villeR", restoItem.getVilleR());
                i.putExtra("idU", idURecu);

                startActivity(i);
            }
        }));

        btnAcc.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i = new Intent(getApplicationContext(), AccueilUtilisateurActivity.class);
              i.putExtra("idU", idURecu);
              startActivity(i);
          }
        }
        );

        btnCritique = findViewById(R.id.btnMesCritiques);
        btnCritique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ConsulterCritiquesActivity.class);
                i.putExtra("idU", idURecu);
                startActivity(i);
            }}
        );

    }
}
