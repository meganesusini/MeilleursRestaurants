package com.example.meilleursrestaurants.Controleur;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meilleursrestaurants.Modele.Aimer;
import com.example.meilleursrestaurants.Modele.AimerDAO;
import com.example.meilleursrestaurants.Modele.Critiquer;
import com.example.meilleursrestaurants.Modele.CritiquerDAO;
import com.example.meilleursrestaurants.Modele.Proposer;
import com.example.meilleursrestaurants.Modele.ProposerDAO;
import com.example.meilleursrestaurants.Modele.Restaurant;
import com.example.meilleursrestaurants.Modele.RestaurantDAO;
import com.example.meilleursrestaurants.Modele.TypeCuisineDAO;
import com.example.meilleursrestaurants.R;

import java.util.ArrayList;
/**
 * Created by lali.nguyen on 01/04/2022.
 */

public class AjouterCritiqueActivity extends AppCompatActivity {
    private int idRRecu, idTc, noteSelectionnee, idURecu;
    private String nomRecu, numAdRecu, voieAdrRecu, cpRecu, villeRecue, commentaire;
    private TextView nomR, tcR, adrR;
    private Spinner spinnerNote, spinnerAimer;
    private ArrayList<Integer> listeNotes;
    private ArrayList<Proposer> lesTcDunResto;
    private ArrayList<String> listeRepAimer;
    private ArrayList<Aimer> lesAimersParUser;
    private ArrayList<Restaurant> lesRestosAimesParUnUser;
    private Button boutonAjouter, buttonRtr;
    private EditText zonecommentaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_critique);

        // On identifie toutes les zones de textes à modifier
        nomR = (TextView) findViewById(R.id.textViewNomR);
        adrR = (TextView) findViewById(R.id.textViewAdresse);
        tcR = (TextView) findViewById(R.id.textViewTc);


        // On récupère toutes les valeurs envoyées de la page RechercheUtilisateur
        idURecu = getIntent().getIntExtra("idU", 0);
        idRRecu = getIntent().getIntExtra("idR", 0);
        nomRecu = getIntent().getStringExtra("nomR");
        numAdRecu = getIntent().getStringExtra("numAdrR");
        voieAdrRecu = getIntent().getStringExtra("voieAdrR");
        cpRecu = getIntent().getStringExtra("cpR");
        villeRecue = getIntent().getStringExtra("villeR");

        // On affiche les valeurs envoyées dans les zones de texte
        nomR.setText(nomRecu);
        adrR.setText(numAdRecu + " " + voieAdrRecu + " " + cpRecu + " " + villeRecue);
        // Configuration affichage d'un ou plusieurs types de cuisine
        ProposerDAO propoDAO = new ProposerDAO(this);
        TypeCuisineDAO tcDAO = new TypeCuisineDAO(this);
        lesTcDunResto = propoDAO.getLesProposerDunResto(idRRecu);
        if (lesTcDunResto.size() == 0) {
            tcR.setText(" ");
        } else if (lesTcDunResto.size() == 1) {
            idTc = lesTcDunResto.get(0).getIdTC();
            tcR.setText(tcDAO.getTypeCuisine(idTc).getLibelleTC());
        } else {
            String lesTc = "";
            for (int i = 0; i < lesTcDunResto.size(); i++) {
                idTc = lesTcDunResto.get(i).getIdTC();
                if (i == 0) {
                    lesTc = lesTc + String.valueOf(tcDAO.getTypeCuisine(idTc).getLibelleTC());
                } else {
                    lesTc = lesTc + ", " + String.valueOf(tcDAO.getTypeCuisine(idTc).getLibelleTC());
                }
            }
            tcR.setText(lesTc);
        }
        // fin config tc
        // Fin affichage valeurs dans zones de texte


        // On configure la liste déroulante des notes
        spinnerNote = (Spinner) findViewById(R.id.spinnerNote);

        listeNotes = new ArrayList<Integer>();
        listeNotes.add(0);
        listeNotes.add(1);
        listeNotes.add(2);
        listeNotes.add(3);
        listeNotes.add(4);
        listeNotes.add(5);

        ArrayAdapter<Integer> spinNotesAdapter = new ArrayAdapter<Integer>(this.getBaseContext(), android.R.layout.simple_spinner_item);

        for (int i = 0; i < listeNotes.size(); i++) {
            spinNotesAdapter.add(listeNotes.get(i));
        }

        spinnerNote.setAdapter(spinNotesAdapter);
        // fin config notes

        // Configuration du bouton OUI/NON
        // Besoin de la classe Aimer et AimerDAO
        // Besoin de la classe Utilisateur et UtilisateurDAO
        spinnerAimer = (Spinner) findViewById(R.id.spinnerAimer);
        listeRepAimer = new ArrayList<String>();
        listeRepAimer.add("OUI");
        listeRepAimer.add("NON");

        ArrayAdapter<String> spinAimerAdapter = new ArrayAdapter<String>(this.getBaseContext(), android.R.layout.simple_spinner_item);

        for (int i = 0; i < listeRepAimer.size(); i++) {
            spinAimerAdapter.add(listeRepAimer.get(i));
        }

        spinnerAimer.setAdapter(spinAimerAdapter);
        // fin config bouton oui/non

        // Configuration du bouton ajouter
        // Besoin de : idR, idU, note, commentaire || Besoin de : enregistrer un resto aimé ou pas
        final AimerDAO aimerDAO = new AimerDAO(this);
        final RestaurantDAO restoDAO = new RestaurantDAO(this);
        final CritiquerDAO critiquerDAO = new CritiquerDAO(this);

        boutonAjouter = (Button) findViewById(R.id.buttonAjouter);

        boutonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // Configuration des notes
                noteSelectionnee = spinnerNote.getSelectedItemPosition();
                // fin config notes
                //Configuration du commentaire
                zonecommentaire = (EditText) findViewById(R.id.editTextCommentaire);
                commentaire = String.valueOf(zonecommentaire.getText());
                // fin config commentaire

                // Configuration de la liste déroulante Oui/Non
                if (spinnerAimer.getSelectedItemPosition() == 0) { // Si l'utilisateur aime le resto

                    // On vérifie si l'utilisateur avait déjà aimé ce restaurant
                    lesAimersParUser = aimerDAO.getLesAimerDunUser(idURecu);
                    int idR;
                    boolean restoDejaAime = false;

                    // On ajoute tous ses restaurants aimés dans une liste
                    lesRestosAimesParUnUser = new ArrayList<Restaurant>();
                    for (Aimer unAimer : lesAimersParUser) {
                        idR = unAimer.getIdR();
                        lesRestosAimesParUnUser.add(restoDAO.getRestaurant(idR));
                    }

                    // On vérifie si parmis tous ses restaurants aimés si l'id d'un de ces restaurants correspond à l'id du restaurant actuel
                    for (Restaurant unRestoAime : lesRestosAimesParUnUser) {
                        if (unRestoAime.getIdR() == idRRecu) {
                            restoDejaAime = true;
                        }
                    }

                    // Si le restaurant n'a jamais été aimé, on l'ajoute dans la table Aimer
                    if (restoDejaAime == false) {
                        aimerDAO.addAimer(new Aimer(idRRecu, idURecu));
                        Toast.makeText(getApplicationContext(), "Restaurant aimé !", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Restaurant déjà aimé !", Toast.LENGTH_LONG).show();
                    }

                }
                // fin config Oui/Non

                // Ajout de la nouvelle critique
                critiquerDAO.addCritiquer(new Critiquer(idRRecu, idURecu, noteSelectionnee, commentaire));
                Toast.makeText(getApplicationContext(), "Critique envoyée !", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), RechercheUtilisateurActivity.class);
                intent.putExtra("idU", idURecu);
                startActivity(intent);
                // fin ajout critique

            }
        });

        // fin config bouton ajouter

        //config du bouton retour
        buttonRtr = findViewById(R.id.buttonRtr);
        buttonRtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AccueilUtilisateurActivity.class);
                i.putExtra("idU", idURecu);
                startActivity(i);
            }

        });
    }
}