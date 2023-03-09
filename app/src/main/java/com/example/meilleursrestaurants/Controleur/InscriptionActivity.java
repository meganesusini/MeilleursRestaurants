package com.example.meilleursrestaurants.Controleur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meilleursrestaurants.Modele.Utilisateur;
import com.example.meilleursrestaurants.Modele.UtilisateurDAO;
import com.example.meilleursrestaurants.R;

/**
 * Created by lali.nguyen on 28/03/2022.
 */

public class InscriptionActivity extends Activity {

    private EditText lblMail, lblPseudo, lblMdp;
    private Button btnValider;
    private UtilisateurDAO uDao = new UtilisateurDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);


        lblMail = findViewById(R.id.libelleMail);
        lblPseudo = findViewById(R.id.libellePseudo);
        lblMdp = findViewById(R.id.libelleMdp);
        btnValider = findViewById(R.id.buttonValider);

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailU = lblMail.getText().toString();
                String pseudoU = lblPseudo.getText().toString();
                String mdpU = lblMdp.getText().toString();
                Utilisateur u1 = new Utilisateur(mailU, mdpU, pseudoU);
                long add = uDao.addUser(u1.getMailU(), u1.getMdpU(), u1.getPseudoU());
                if (add > 0) {
                    Intent intent = new Intent(v.getContext(), ConnexionActivity.class);
                    startActivity(intent);
                    Log.d("***log", "OK");
                } else {
                    Toast.makeText(InscriptionActivity.this, "Erreur d'inscription", Toast.LENGTH_LONG).show();
                    Log.d("***log", "NON ok");
                }

            }
        });

    }
}
