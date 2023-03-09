package com.example.meilleursrestaurants.Modele;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by mega.susini on 21/03/2022.
 */

public class RestaurantDAO {
    private static String base = "Bd";
    private static int version = 1;
    BdSQLiteOpenHelper accesBD;

    public RestaurantDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    // Ajouter un restaurant
    public long addRestaurant(Restaurant unResto){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("idR", unResto.getIdR());
        value.put("nomR", unResto.getNomR());
        value.put("numAdR",unResto.getNumAdR());
        value.put("voieAdrR",unResto.getVoieAdR());
        value.put("cpR",unResto.getCpR());
        value.put("villeR",unResto.getVilleR());
        value.put("descR",unResto.getDescR());
        value.put("horairesR",unResto.getHorairesR());
        ret = bd.insert("Restaurant", null, value);

        return ret;
    }

    // Consulter les restaurants
    public ArrayList<Restaurant> getRestaurants(){
        Cursor curseur;
        String req = "select * from Restaurant order by nomR";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToRestaurantArrayList(curseur);
    }

    private ArrayList<Restaurant> cursorToRestaurantArrayList(Cursor curseur){
        ArrayList<Restaurant> listeRestaurants = new ArrayList<Restaurant>();
        int idR;
        String nomR;
        String numAdR;
        String voieAdR;
        String cpR;
        String villeR;
        String descR;
        String horairesR;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            idR = curseur.getInt(0);
            nomR = curseur.getString(1);
            numAdR = curseur.getString(2);
            voieAdR = curseur.getString(3);
            cpR = curseur.getString(4);
            villeR = curseur.getString(5);
            descR = curseur.getString(6);
            horairesR = curseur.getString(7);

            listeRestaurants.add(new Restaurant(idR,nomR,numAdR,voieAdR,cpR,villeR,descR,horairesR));
            curseur.moveToNext();
        }

        return listeRestaurants;
    }

    // Consulter un restaurant
    public Restaurant getRestaurant(int idR){
        Restaurant leResto = null;
        Cursor curseur;
        Log.d("test***", "idResto : "+idR);
        String req = "select * from Restaurant where idR="+idR+";";
        Log.d("****","requete : "+req);
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            leResto = new Restaurant(idR, curseur.getString(1),curseur.getString(2), curseur.getString(3), curseur.getString(4), curseur.getString(5), curseur.getString(6), curseur.getString(7));
        }

        return leResto;
    }

    // Supprimer un restaurant
    public int deleteResto(int idR) {
        int ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        String condition = "idR ="+idR+";";
        ret = bd.delete("Restaurant", condition,null);
        return ret;
    }

    // Modifier un restaurant
    public long updateResto(Restaurant nvResto, Restaurant anResto){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("idR", anResto.getIdR());
        value.put("nomR", nvResto.getNomR());
        value.put("numAdR",nvResto.getNumAdR());
        value.put("voieAdrR",nvResto.getVoieAdR());
        value.put("cpR",nvResto.getCpR());
        value.put("villeR",nvResto.getVilleR());
        value.put("descR",nvResto.getDescR());
        value.put("horairesR",nvResto.getHorairesR());

        String condition = "idR = "+anResto.getIdR()+";";
        ret = bd.update("Restaurant", value, condition,null);
        return ret;
    }

}
