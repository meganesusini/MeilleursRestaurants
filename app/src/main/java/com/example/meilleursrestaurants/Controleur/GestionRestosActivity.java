package com.example.meilleursrestaurants.Controleur;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.meilleursrestaurants.Modele.Restaurant;
import com.example.meilleursrestaurants.Modele.RestaurantDAO;
import com.example.meilleursrestaurants.R;

import java.util.ArrayList;

public class GestionRestosActivity extends AppCompatActivity {
    private ListView listeRestos;
    private Button boutonAjouter;
    private Button boutonSupprimer, boutonRetour;
    private ArrayList<Restaurant> lesRestaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_restos);

        listeRestos = (ListView) findViewById(R.id.listViewRestos);
        boutonAjouter = (Button) findViewById(R.id.buttonAjouter);
        boutonSupprimer = (Button) findViewById(R.id.buttonSupprimer);
        boutonRetour = (Button)findViewById(R.id.btnRetour);
        // On configure la listView des restaurants
        final RestaurantDAO restoDAO = new RestaurantDAO(this);
        lesRestaurants = restoDAO.getRestaurants();

        final ListView listViewRestos = (ListView)findViewById(R.id.listViewRestos);

        final ArrayAdapter<Restaurant> arrayAdapter  = new ArrayAdapter<Restaurant>(this, android.R.layout.simple_list_item_1 , lesRestaurants);

        listViewRestos.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        // Fin config des restos

        // On configure les boutons d'ajout et de suppression d'un restaurant
        boutonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), AjouterRestoActivity.class);
                arrayAdapter.notifyDataSetChanged(); // !!!
                startActivity(intent);
            }
        });

        boutonSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), SupprimerRestoActivity.class);
                arrayAdapter.notifyDataSetChanged(); // !!!
                startActivity(intent);
            }
        });

        boutonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), AccueilAdminActivity.class);
                arrayAdapter.notifyDataSetChanged(); // !!!
                startActivity(intent);
            }
        });
        // Fin config boutons ajout et sup

        listViewRestos.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurant restoItem = (Restaurant) listViewRestos.getAdapter().getItem(position);
                Intent i = new Intent(getApplicationContext(), FicheRestaurantActivity.class);
                i.putExtra("idR", restoItem.getIdR());
                i.putExtra("nomR", restoItem.getNomR());
                i.putExtra("numAdrR", restoItem.getNumAdR());
                i.putExtra("voieAdrR", restoItem.getVoieAdR());
                i.putExtra("cpR", restoItem.getCpR());
                i.putExtra("villeR", restoItem.getVilleR());
                i.putExtra("descR", restoItem.getDescR());
                i.putExtra("horairesR", restoItem.getHorairesR());
                i.putExtra("pos", position);
                arrayAdapter.notifyDataSetChanged(); // !!!

                startActivity(i);
            }
        }));
    }
}
