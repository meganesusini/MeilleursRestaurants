package com.example.meilleursrestaurants.Controleur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.meilleursrestaurants.Modele.Aimer;
import com.example.meilleursrestaurants.Modele.AimerDAO;
import com.example.meilleursrestaurants.Modele.Restaurant;
import com.example.meilleursrestaurants.Modele.RestaurantDAO;
import com.example.meilleursrestaurants.R;

import java.util.ArrayList;

/**
 * Created by lali.nguyen on 29/03/2022.
 */

public class AccueilUtilisateurActivity extends Activity {
    private ListView listResto ;
    private ArrayList<Aimer> lesAimerFav;
    private ArrayList<Restaurant> lesRestosFav = new ArrayList<Restaurant>();
    private AimerDAO aDAO = new AimerDAO(this);
    private RestaurantDAO rDAO = new RestaurantDAO(this);
    private Restaurant restoFav;
    private int idUserCo;
    private Button btnModif, btnRecherche, buttonCritiques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        btnModif = findViewById(R.id.buttonInfo);
        btnRecherche = findViewById(R.id.buttonRecherche);
        listResto = findViewById(R.id.listeRestoFav);

        idUserCo = getIntent().getIntExtra("idU", 0);
        lesAimerFav = aDAO.getLesAimerDunUser(idUserCo);
        //transforme obj aime en obj resto
        for (Aimer unResto : lesAimerFav) {
            restoFav = rDAO.getRestaurant(unResto.getIdR());
            Log.d("*** resto", restoFav.toString());
            lesRestosFav.add(restoFav);
        }

        ArrayAdapter monAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lesRestosFav);
        listResto.setAdapter(monAdapter);

        listResto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurant restaurant = (Restaurant) listResto.getAdapter().getItem(position);
                Intent i = new Intent(getApplicationContext(), SupprimerRestoAimeActivity.class);
                i.putExtra("idR", restaurant.getIdR());
                i.putExtra("nomR", restaurant.getNomR());
                i.putExtra("numR", restaurant.getNumAdR());
                i.putExtra("rueR", restaurant.getVoieAdR());
                i.putExtra("villeR", restaurant.getVilleR());
                i.putExtra("cpR", restaurant.getCpR());
                i.putExtra("idU", idUserCo);
                Log.d("*** nomR envoyé", restaurant.getNomR());
                startActivity(i);
            }
        });

        btnModif.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), UpdateProfileActivity.class);
                i.putExtra("idU", idUserCo);
                startActivity(i);
            }
        }
        );

        btnRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RechercheUtilisateurActivity.class);
                i.putExtra("idU", idUserCo);
                startActivity(i);
            }
        }
        );

        buttonCritiques = findViewById(R.id.buttonCritiques);
        buttonCritiques.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(getApplicationContext(), ConsulterCritiquesActivity.class);
               i.putExtra("idU", idUserCo);
               startActivity(i);
        }}
        );
    }
}
