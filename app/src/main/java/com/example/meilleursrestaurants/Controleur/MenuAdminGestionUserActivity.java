package com.example.meilleursrestaurants.Controleur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.meilleursrestaurants.R;

public class MenuAdminGestionUserActivity extends Activity {

    private Button suppression, consultation, retour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin_gestion_user);

        consultation = (Button) findViewById(R.id.buttonConsult);
        suppression = (Button) findViewById(R.id.buttonSupress);
        retour = (Button) findViewById(R.id.buttonAccueil);

        consultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), ConsultationUserActivity.class);
                startActivity(intent);
            }
        });

        suppression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), SuppressionUserActivity.class);
                startActivity(intent);
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), AccueilAdminActivity.class);
                startActivity(intent);
            }
        });

    }
}
