package com.example.meilleursrestaurants.Controleur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.meilleursrestaurants.R;


/**
 * Created by lali.nguyen on 29/03/2022.
 */

public class AccueilAdminActivity extends Activity {

    private Button gestionRestos, gestionUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin_global);

        // On configure les boutons de gestion des restaurants et des utilisateurs
        gestionRestos = (Button) findViewById(R.id.buttonGestionRestos);
        gestionUsers = (Button) findViewById(R.id.buttonGestionUsers);

        gestionUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), MenuAdminGestionUserActivity.class);
                startActivity(intent);
            }
        });

        gestionRestos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), GestionRestosActivity.class);
                startActivity(intent);
            }
        });
        // Fin config gestion restos et users

    }
}
