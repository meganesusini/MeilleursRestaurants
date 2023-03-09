package com.example.meilleursrestaurants.Modele;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

/**
 * Created by lali.nguyen on 21/03/2022.
 */

public class ConnexionDAO {
    private static String base = "Bd";
    private static int version = 1;
    BdSQLiteOpenHelper accesBD;

    public ConnexionDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    //A utiliser pour connexion, si true alors peut se connecter, sinon envoit vers inscription ou message erreur
    public boolean connexionUser(String pseudoU, String mdpU){
        boolean res= false;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from Utilisateur where pseudoU= '"+pseudoU+ "' and mdpU= '"+mdpU+"' and idRole = 2;",null);
        if (curseur.getCount() > 0) {
            res = true ;
        }
        return res;
    }

    //A utiliser pour connexion admin, si true alors interface admin
    public boolean connexionAdmin(String pseudoU, String mdpU){
        boolean res= false;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from Utilisateur where pseudoU= '"+pseudoU+ "' and mdpU= '"+mdpU+"' and idRole = 1;",null);
        if (curseur.getCount() > 0) {
            res = true ;
        }
        return res;
    }

    public Utilisateur getUnUserCo(String pseudoU, String mdpU){
        Utilisateur user = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from Utilisateur where pseudoU= '"+pseudoU+ "' and mdpU= '"+mdpU+"' ;",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            user = new Utilisateur(curseur.getInt(0),curseur.getString(1), curseur.getString(2), curseur.getString(3), curseur.getInt(4));
        }
        return user;
    }


}
