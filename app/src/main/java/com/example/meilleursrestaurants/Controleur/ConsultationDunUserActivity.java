package com.example.meilleursrestaurants.Controleur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.meilleursrestaurants.R;

/**
 * Created by lili.pamphile on 28/03/2022.
 */

public class ConsultationDunUserActivity extends Activity {

    private Button retour;
    private TextView pseudo, mail;
    private String recupMail, recupPseudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultater_dun_user);

        pseudo = (TextView) findViewById(R.id.textViewPseudo);
        mail = (TextView) findViewById(R.id.textViewMail);

        recupPseudo = getIntent().getStringExtra("pseudo");
        recupMail = getIntent().getStringExtra("mail");

        pseudo.setText(recupPseudo);
        mail.setText(recupMail);

        retour = (Button) findViewById(R.id.buttonAccueil);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), ConsultationUserActivity.class);
                startActivity(intent);
            }
        });
    }
}