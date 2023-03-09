package com.example.meilleursrestaurants.Controleur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.meilleursrestaurants.Modele.Utilisateur;
import com.example.meilleursrestaurants.Modele.UtilisateurDAO;
import com.example.meilleursrestaurants.R;

import java.util.ArrayList;

/**
 * Created by lili.pamphile on 22/03/2022.
 */

public class ConsultationUserActivity extends Activity {

    private Button retour;
    private ArrayList<Utilisateur> lesUser = new ArrayList<Utilisateur>();
    private ListView liste;
    private UtilisateurDAO unUserDAO = new UtilisateurDAO(this);
    private Utilisateur user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulter_user);

        liste = (ListView) findViewById(R.id.listView);

        lesUser = unUserDAO.getQueLesUtilisateurs();
        ArrayAdapter monAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lesUser);
        liste.setAdapter(monAdapter);

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                user = (Utilisateur) liste.getAdapter().getItem(position);
                Intent i = new Intent(getApplicationContext(),ConsultationDunUserActivity.class);
                i.putExtra("pseudo", (String) user.getPseudoU());
                i.putExtra("mail", (String) user.getMailU());
                startActivity(i);
            }
        });

        retour = (Button) findViewById(R.id.buttonAccueil);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), MenuAdminGestionUserActivity.class);
                startActivity(intent);
            }
        });
    }
}
