package com.example.meilleursrestaurants.Controleur;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meilleursrestaurants.Modele.Utilisateur;
import com.example.meilleursrestaurants.Modele.UtilisateurDAO;
import com.example.meilleursrestaurants.R;

public class UpdateProfileActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPseudo;
    private EditText editTextPassword;
    private Button buttonApply;
    private int userId;
    private String emailLayoutString, pseudoLayoutString, passwordLayoutString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        // On identifie toutes les zones de textes à modifier
        editTextEmail =(EditText) findViewById(R.id.editTextEmail) ;
        editTextPseudo = (EditText) findViewById(R.id.editTextPseudo);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        // On identifie le bouton
        buttonApply = (Button) findViewById(R.id.buttonApply);

        // On récupère toutes les valeurs envoyées de la page Accueil
        // Version finale
        userId = getIntent().getIntExtra("idU", 0);


        // On crée un objet Utilisateur afin de récupérer l'utilisateur connecté
        final UtilisateurDAO utilisateurDAO = new UtilisateurDAO(this);
        final Utilisateur utilisateur = utilisateurDAO.getUnUser(userId);

        // On affiche les valeurs envoyées dans les zones de texte
        editTextEmail.setText(utilisateur.getMailU());
        editTextPseudo.setText(utilisateur.getPseudoU());
        editTextPassword.setText(utilisateur.getMdpU());

        // Action suite à l'appuie sur le bouton
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // Retour à une page après l'envoie des données
                Intent i = new Intent(v.getContext(), AccueilUtilisateurActivity.class);


                // On transforme les valeurs des EditText en String
                emailLayoutString = String.valueOf(editTextEmail.getText());
                pseudoLayoutString = String.valueOf(editTextPseudo.getText());
                passwordLayoutString = String.valueOf(editTextPassword.getText());
                // On crée un utilisateur afin d'y stocker les nouvelles infos
                Utilisateur newUser = new Utilisateur(emailLayoutString, passwordLayoutString, pseudoLayoutString);

                // SI le mail et le pseudo n'ont pas changé --> error
                if(utilisateur.getMailU().equals(emailLayoutString) && utilisateur.getPseudoU().equals(pseudoLayoutString)){
                    Log.d("", "onClick: aucune modification effectué");
                    Toast.makeText(getApplicationContext(),"L'email et le pseudo n'ont pas été modifiés", Toast. LENGTH_LONG).show();
                }
                // SINON SI l'email et le pseudo ont été changé --> création de deux booleen
                else if(!(emailLayoutString.equals(utilisateur.getMailU())) && !(pseudoLayoutString.equals(utilisateur.getPseudoU()))){
                    boolean email, pseudo;
                    email = false; pseudo = false;
                    // SI email existe déjà --> error
                    if(utilisateurDAO.userControlEmail(emailLayoutString)){
                        Log.d("*********", "onClick: mail existe déjà");
                        Toast.makeText(getApplicationContext(),"L'email est déjà pris", Toast. LENGTH_LONG).show();
                    }
                    // SINON la variable email = true
                    else {
                        email = true;
                    }
                    // SI le pseudo existe déjà --> error
                    if(utilisateurDAO.userControlPseudo(pseudoLayoutString)){
                        Log.d("*********", "onClick: email et pseudo existe déjà");
                        Toast.makeText(getApplicationContext(),"L'email et le pseudo sont déjà pris", Toast. LENGTH_LONG).show();
                    }
                    // SINON la variable pseudo = true
                    else {
                        pseudo = true;
                    }
                    // SI les deux variables pseudos/mail = true --> on réalise l'UPDATE
                    if(email == true && pseudo == true){
                        utilisateurDAO.updateUser(utilisateur, newUser);
                        Log.d("*** update", newUser.toString());
                        Toast.makeText(getApplicationContext(),"Vos informations ont été mise à jour", Toast. LENGTH_LONG).show();
                    }
                }
                // SINON SI l'email OU le pseudo sont différents --> Création d'une variable booleen
                else if(!(emailLayoutString.equals(utilisateur.getMailU())) || !(pseudoLayoutString.equals(utilisateur.getPseudoU()))){
                    boolean pseudoEtEmail = false;
                    // SI l'email a été changé
                    if(!(emailLayoutString.equals(utilisateur.getMailU()))) {
                        // SI l'email existe déjà --> error
                        if(utilisateurDAO.userControlEmail(emailLayoutString)) {
                            Log.d("*********", "onClick: email déjà pris");
                            Toast.makeText(getApplicationContext(), "L'email est déjà pris", Toast.LENGTH_LONG).show();
                        }
                        // SINON la variable pseudoEmail= true
                        else {
                            pseudoEtEmail = true;
                        }
                    }
                    // SI le pseudo a été changé
                    if(!(pseudoLayoutString.equals(utilisateur.getPseudoU()))){
                        // SI le pseudo existe déjà --> error
                        if(utilisateurDAO.userControlPseudo(pseudoLayoutString)){
                            Log.d("*********", "onClick: pseudo déjà pris");
                            Toast.makeText(getApplicationContext(),"Le pseudo est déjà pris", Toast. LENGTH_LONG).show();
                        }
                        // Sinon la variable pseudoEmail = true
                        else {
                            pseudoEtEmail = true;
                        }
                    }
                    // SI la variable pseudoEmail = true --> on réalise l'UPDATE
                    if(pseudoEtEmail == true){
                        utilisateurDAO.updateUser(utilisateur, newUser);
                        Log.d("*** update2", newUser.toString());
                        Toast.makeText(getApplicationContext(),"Vos informations ont été mise à jour", Toast. LENGTH_LONG).show();
                    }
                }
                i.putExtra("idU", userId);
                startActivity(i);
            }
        });
    }
}
