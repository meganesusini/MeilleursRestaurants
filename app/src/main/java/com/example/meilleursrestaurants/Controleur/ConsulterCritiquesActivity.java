package com.example.meilleursrestaurants.Controleur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.IntegerRes;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.meilleursrestaurants.Modele.Critiquer;
import com.example.meilleursrestaurants.Modele.CritiquerDAO;
import com.example.meilleursrestaurants.Modele.RestaurantDAO;
import com.example.meilleursrestaurants.R;

import java.util.ArrayList;

/**
 * Created by lili.pamphile on 28/03/2022.
 */

public class ConsulterCritiquesActivity extends Activity {

    private Button accueil, recherche;
    private ListView liste;
    private ArrayList<Critiquer> lesCrit = new ArrayList<Critiquer>();
    //private ArrayList<String> lesCrit = new ArrayList<String>();
    private CritiquerDAO critDAO = new CritiquerDAO(this);
    private Critiquer uneCrit ;
    private RestaurantDAO restoDAO = new RestaurantDAO(this);
    private String nomR;
    private int idU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulter_critiques);

        accueil = (Button) findViewById(R.id.buttonAccueil);
        recherche = (Button) findViewById(R.id.buttonRech);

        liste = (ListView) findViewById(R.id.listView2);

        idU = getIntent().getIntExtra("idU", 0);
        Log.d("*****", "idutilisateur : " + idU);
        lesCrit=critDAO.getLesCritiquesDunUser(idU);

        for (Critiquer crit : lesCrit){
            Log.d("**********", "crit : "+crit);
        }

        ArrayAdapter<Critiquer> monAdapter = new ArrayAdapter<Critiquer>(this, android.R.layout.simple_list_item_1, lesCrit);
        liste.setAdapter(monAdapter);


        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                uneCrit = (Critiquer) liste.getAdapter().getItem(position);

                Log.d("*****", "onItemClick: " + uneCrit);
                String nomR = uneCrit.getResto().getNomR();


                // Envoie A modifCritique le nomR, la note et le commentaire
                Intent i = new Intent(getApplicationContext(),ModifierCritiquesActivity.class);
                i.putExtra("nomR", (String) nomR);
                i.putExtra("note", (Integer) uneCrit.getNote());
                i.putExtra("commentaire", (String) uneCrit.getCommentaire());
                i.putExtra("idR", (Integer) uneCrit.getResto().getIdR());
                i.putExtra("idU", (Integer) idU);
                startActivity(i);
            }
        });

        accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(),AccueilUtilisateurActivity.class);
                intent.putExtra("idU", idU);
                startActivity(intent);
            }
        });

        recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), RechercheUtilisateurActivity.class);
                intent.putExtra("idU", idU);
                startActivity(intent);
            }
        });

    }
}