package com.example.meilleursrestaurants.Modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by lali.nguyen on 21/03/2022.
 */

public class RoleDAO {
    private static String base = "Bd";
    private static int version = 1;
    BdSQLiteOpenHelper accesBD;

    public RoleDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }


}
