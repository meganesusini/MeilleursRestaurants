package com.example.meilleursrestaurants.Modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by lali.nguyen on 17/03/2022.
 */

public class UtilisateurDAO {
    private static String base = "Bd";
    private static int version = 1;
    BdSQLiteOpenHelper accesBD;

    public UtilisateurDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    //Ajoute un utilisateur dans la base OK
    public long addUser(String mailU, String mdpU, String pseudoU){
        long req, ret;
        // Contrôle du pseudo et du mail
        ret = userControlBoth(mailU, pseudoU);
        // Si ret > 0 : erreur --> Pas d'insertion
        if(ret>0){
            String message = specifyErrorMessage(ret);
            Log.d("***", "addUser: error "+ret+" : "+message);
            req = 0;
        }
        // Sinon on ajoute l'utilisateur
        else {
            SQLiteDatabase bd = accesBD.getWritableDatabase();
            ContentValues value = new ContentValues();
            value.put("mailU", mailU);
            value.put("mdpU",mdpU);
            value.put("pseudoU",pseudoU);
            value.put("idRole",2);
            req = bd.insert("Utilisateur", null, value);
        }
        return req;
    }

    // modifie un utilisateur OK
    public long updateUser(Utilisateur anUser, Utilisateur nvUser){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("idU", anUser.getIdU());
        value.put("mailU", nvUser.getMailU());
        value.put("mdpU",nvUser.getMdpU());
        value.put("pseudoU",nvUser.getPseudoU());
        value.put("idRole",anUser.getIdRole());

        String condition = "idU ="+anUser.getIdU()+";";
        ret = bd.update("Utilisateur", value, condition,null);
        return ret;
    }

    //supprime un utilisateur OK
    public long deleteUser(int idU) {
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        String condition = "idU ="+idU+";";
        ret = bd.delete("Utilisateur", condition,null);
        return ret;
    }

    //affiche un utilisateur OK
    public Utilisateur getUnUser(int idU){
        Utilisateur user = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from Utilisateur where idU="+idU+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            user = new Utilisateur(idU,curseur.getString(1), curseur.getString(2), curseur.getString(3), curseur.getInt(4));
        }
        return user;
    }

    public Utilisateur getUnUserPseudo(String pseudoU){
        Utilisateur user = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from Utilisateur where pseudoU= '"+pseudoU+"' ;",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            user = new Utilisateur(curseur.getInt(0),curseur.getString(1), curseur.getString(2), pseudoU, curseur.getInt(4));
        }
        return user;
    }


    //affiche l'ensemble des utilisateurs (sous forme arraylist)
    public ArrayList<Utilisateur> getLesUtilisateurs(){
        Cursor curseur;
        String req = "select * from Utilisateur";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToUtilisateurArrayList(curseur);
    }

    //converti curseur en arrayList
    private ArrayList<Utilisateur> cursorToUtilisateurArrayList(Cursor curseur){
        ArrayList<Utilisateur> listeUser = new ArrayList<Utilisateur>();
        int idU, idRole;
        String mailU, mdpU, pseudoU;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            idU = curseur.getInt(0);
            mailU = curseur.getString(1);
            mdpU = curseur.getString(2);
            pseudoU = curseur.getString(3);
            idRole = curseur.getInt(4);
            listeUser.add(new Utilisateur(idU,mailU,mdpU,pseudoU,idRole));
            curseur.moveToNext();
        }

        return listeUser;
    }

    // Contrôle l'ajout ou la modification en cas d'email déjà existant
    public boolean userControlEmail(String emailU) {
        boolean res = false;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from Utilisateur where mailU= '"+emailU+ "';",null);
        if (curseur.getCount() > 0) {
            res = true ;
        }
        return res;
    }

    // Contrôle l'ajoute ou la modification en cas de pseudo déjà existant
    public boolean userControlPseudo(String pseudoU) {
        boolean res = false;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from Utilisateur where pseudoU= '"+pseudoU+ "';",null);
        if (curseur.getCount() > 0) {
            res = true ;
        }
        return res;
    }

    // Contrôle le pseudo et l'email si déjà existant et renvoie un long --> REGARDER LA DESCRIPTION DU RET
    public long userControlBoth(String emailU, String pseudoU) {
        long ret=0;
        boolean res = userControlEmail(emailU);
        if (res==true){
            ret = 50; // 50 = email déjà existant
        }
        boolean res2 = userControlPseudo(pseudoU);
        if (res2==true){
            ret = 100; // 100 = pseudo déjà existant
        }
        if(res&&res2 == true){
            ret = 150; // 150 = mail ET pseudo déjà existant
        }
        return ret;
    }

    private String specifyErrorMessage(long errorId){
        String message="";
        if(errorId==50){
            message = "Email déjà existant";
        }
        else if(errorId==100) {
            message = "Pseudo déjà existant";
        }
        else if(errorId==150) {
            message = "Email et pseudo déjà existant";
        }
        return message;
    }

    public ArrayList<Utilisateur> getQueLesUtilisateurs(){
        Cursor curseur;
        String req = "select * from Utilisateur where idRole=2";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToUtilisateurArrayList(curseur);
    }

}
