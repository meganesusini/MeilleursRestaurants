package com.example.meilleursrestaurants.Controleur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.meilleursrestaurants.Modele.ConnexionDAO;
import com.example.meilleursrestaurants.Modele.Utilisateur;
import com.example.meilleursrestaurants.Modele.UtilisateurDAO;
import com.example.meilleursrestaurants.R;

/**
 * Created by lali.nguyen on 24/03/2022.
 */

public class ConnexionActivity extends Activity {

    private EditText editPseudo, editMdp;
    private Button btnValider;
    private ConnexionDAO coDao = new ConnexionDAO(this);
    private UtilisateurDAO uDAO = new UtilisateurDAO(this);
    private TextView msgErreur;
    private String pseudo, mdp;
    private EditText essai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        editPseudo = (EditText)findViewById(R.id.editTextPseudo);
        editMdp = (EditText)findViewById(R.id.editTextMdp);
        btnValider = (Button) findViewById(R.id.buttonValider);

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pseudo = editPseudo.getText().toString();
                mdp = editMdp.getText().toString();
                //Log.d("**** jpp", "pseudo : "+pseudo + " mdp : "+mdp);
                boolean connexionUser =  coDao.connexionUser(pseudo, mdp);
                boolean connexionAdmin = coDao.connexionAdmin(pseudo, mdp);


                if(connexionUser == true && connexionAdmin == false ){
                    Utilisateur userCO = uDAO.getUnUserPseudo(pseudo);
                    int idUCo = userCO.getIdU();
                    Intent intent = new Intent(v.getContext(), AccueilUtilisateurActivity.class);
                    intent.putExtra("idU", idUCo);
                    intent.putExtra("pseudo", pseudo);
                    intent.putExtra("mdp", mdp);
                    startActivity(intent);
                    Log.d("***log CoUSER", "OK");
                } else
                    if (connexionAdmin == true && connexionUser == false ){
                    Intent intent = new Intent(v.getContext(), AccueilAdminActivity.class);
                    startActivity(intent);
                    Log.d("***log CoADMIN", "OK");
                    }
                    else
                        if (connexionAdmin == false && connexionUser == false){
                            msgErreur = findViewById(R.id.txtViewMsgErreur);
                            msgErreur.setText("Erreur d'identifiants");
                            editPseudo.setText(" ");
                            editMdp.setText(" ");
                           Log.d("***log Co1", "NOK pseudo "+pseudo.toString()+" mdp "+mdp.toString());
                        }

            }
        });


    }
}
