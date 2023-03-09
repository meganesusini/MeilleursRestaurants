package com.example.meilleursrestaurants.Modele;

/**
 * Created by lali.nguyen on 17/03/2022.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BdSQLiteOpenHelper extends SQLiteOpenHelper {
    private String creaTableUtilisateur="create table Utilisateur ( "
            + " 	idU INTEGER PRIMARY KEY AUTOINCREMENT, "
            + " 	mailU Text NOT NULL , "
            + " 	mdpU Text NOT NULL , "
            + " 	pseudoU Text NOT NULL, "
            + " 	idRole integer NOT NULL, "
            + " 	foreign key(idRole) references Role(idRole) "
            + " ); ";

    private String creaTableTypeCuisine = " create table TypeCuisine ( "
            + " 	idTC integer NOT NULL , "
            + " 	libelleTC Text NOT NULL , "
            + " 	primary key (idTC) "
            + " ); ";

    private String creaTableRestaurant = " create table Restaurant ( "
            + " 	idR integer NOT NULL , "
            + " 	nomR Text NOT NULL , "
            + " 	numAdR Text NOT NULL , "
            + " 	voieAdrR Text NOT NULL , "
            + " 	cpR Text NOT NULL , "
            + " 	villeR Text NOT NULL , "
            + " 	descR Text NOT NULL , "
            + " 	horairesR Text NOT NULL , "
            + " 	primary key (idR) "
            + " ); ";

    private String creaTableRole = " create table Role ( "
            + " 	idRole integer, "
            + " 	libelleR text, "
            + " 	primary key (idRole) "
            + " )";

    private String creaTablePreferer = " create table Preferer ( "
            + " 	idU  integer NOT NULL , "
            + " 	idTC integer NOT NULL , "
            + " 	primary key (idU, idTC), "
            + " 	foreign key(idU) references Utilisateur(idU), "
            + " 	foreign key(idTC) references TypeCuisine(idTC) "
            + " ); ";

    private String creaTableAimer = " create table Aimer ( "
            + " 	idR integer NOT NULL , "
            + " 	idU integer NOT NULL ,"
            + " 	primary key (idR, idU), "
            + " 	foreign key(idR) references restaurant(idR), "
            + " 	foreign key(idU) references Utilisateur(idU) "
            + " ); ";

    private String creaTableCritiquer = " create table Critiquer ( "
            + " 	idR integer NOT NULL , "
            + " 	idU integer NOT NULL , "
            + " 	note integer , "
            + " 	commentaire Text , "
            + " 	primary key (idR, idU), "
            + " 	foreign key(idR) references restaurant(idR), "
            + " 	foreign key(idU) references Utilisateur(idU) "
            + " ); ";

    private String creaTableProposer = " create table Proposer ( "
            + " 	idR integer NOT NULL , "
            + " 	idTC integer NOT NULL , "
            + " 	primary key (idR, idTC), "
            + " 	foreign key(idR) references restaurant(idR), "
            + " 	foreign key(idTC) references TypeCuisine(idTC) "
            + " ); ";


    // insert
    private String insertTableUtilisateur = "INSERT INTO Utilisateur "
            +" (idU, mailU, mdpU, pseudoU, idRole) VALUES "
            + " (1,'lili.pam@gm.com', 'lili', 'lili', 1), "
            + " (2,'lalie.ngy@gm.com', 'lalie', 'lalie', 1), "
            + " (3,'meg.ssi@gm.com', 'megane', 'megane', 1), "
            + " (4,'quentin.lab@gm.com', 'quentin', 'quentin', 1), "
            + " (5,'admin@sio.fr', 'Adm!n', 'adminSIO', 2), "
            + " (6,'alex.garat@gmail.com', 'toto', '@lex', 2), "
            + " (7,'jj.soueix@gmail.com', '$1$zvN5hYMI$SDFGSDFGJqJSDJF.', 'drskott', 2), "
            + " (8,'mathieu.capliez@gmail.com', 'seSzpoUAQgIl.', 'pich', 2), "
            + " (9,'michel.garay@gmail.com', '$1$zvN5hYMI$VSatLQ6SDFGdsfgznHF5osT.', 'Mitch', 2), "
            + " (10,'nicolas.harispe@gmail.com', '$1$zvNDSFQSdfqsDfQsdfsT.', 'Nico40', 2), "
            + " (11,'yann@lechambon.fr', 'sej6dETYl/ea.', 'yann', 2)"
            + " ; ";

    private String insertTableTypeCuisine="INSERT INTO TypeCuisine"
            + " (idTC, libelleTC) VALUES "
            + "(1, 'sud ouest'), "
            + " (2, 'japonaise'), "
            + " (3, 'orientale'), "
            + " (4, 'fastfood'), "
            + " (5, 'vegetarienne'), "
            + " (6, 'vegan'), "
            + " (7, 'crepe'), "
            + " (8, 'sandwich'), "
            + " (9, 'tartes'), "
            + " (10, 'viande'), "
            + " (11, 'grillade') "
            + " ; ";

    private String insertTableRestaurant="INSERT INTO Restaurant"
            + " (idR, nomR, numAdR, voieAdrR, cpR, villeR, descR, horairesR) VALUES "
            + " (1, 'Lentrepote', '2', 'rue Maurice Ravel', '33000', 'Bordeaux','description', 'Midi : 12h - 15h Soir : 18h - 22h'), "
            + " (2, 'Le bar du charcutier', '30', 'rue Sainte-Catherine', '33000', 'Bordeaux', 'description', 'Midi : 12h - 15h Soir : 18h - 22h'), "
            + " (3, 'Sapporo', '33', 'rue Saint Rémi', '33000', 'Bordeaux', 'plats japonais.', 'Midi : 12h - 15h Soir : 18h - 22h'), "
            + " (4, 'Cidrerie du fronton', '55', 'Place du Fronton', '64210', 'Arbonne', 'description', 'Midi : 12h - 15h Soir : 18h - 22h'), "
            + " (5, 'Agadir', '3', 'Rue Sainte-Catherine', '64100', 'Bayonne', 'description', 'Midi : 12h - 15h Soir : 18h - 22h'), "
            + " (6, 'Le Bistrot Sainte Cluque', '9', 'Rue Hugues', '64100', 'Bayonne', 'description', 'Midi : 12h - 15h Soir : 18h - 22h'), "
            + " (7, 'La petite auberge', '15', 'rue des cordeliers', '64100', 'Bayonne', 'description', 'Midi : 12h - 15h Soir : 18h - 22h'), "
            + " (8, 'La table de POTTOKA', '21', 'Quai Amiral Dubourdieu', '64100', 'Bayonne', 'description', 'Midi : 12h - 15h Soir : 18h - 22h'), "
            + " (9, 'La Rotisserie du Roy Léon', '8', 'rue de coursic', '64100', 'Bayonne', 'description', 'Midi : 12h - 15h Soir : 18h - 22h'), "
            + " (10, 'Bar du Marché', '39', 'Rue des Basques', '64100', 'Bayonne', 'description', 'Midi : 12h - 15h Soir : 18h - 22h'), "
            + " (11, 'Trinquet Moderne', '60', 'Avenue Dubrocq', '64100', 'Bayonne', 'description', 'Midi : 12h - 15h Soir : 18h - 22h'), "
            + " (12, 'Otacos', '2', 'rue Foch', '64000', 'Pau', 'Véritable Tacos', '11h - 22h') "
            + " ; ";

    private String insertTableProposer="INSERT INTO Proposer"
            + " (idR, idTC) VALUES "
            + " (1, 1), "
            + " (1, 3), "
            + " (1, 7), "
            + " (2, 1), "
            + " (3, 3), "
            + " (4, 1), "
            + " (4, 8), "
            + " (4, 11), "
            + " (5, 3), "
            + " (5, 7), "
            + " (6, 10), "
            + " (7, 6), "
            + " (8, 11), "
            + " (9, 10), "
            + " (10, 1), "
            + " (11, 1), "
            + " (11, 10), "
            + " (12, 5), "
            + " (12, 8), "
            + " (12, 4) "
            + " ; ";

    private String insertTablePreferer="INSERT INTO Preferer"
            + " (idU, idTC) VALUES "
            + " (1, 1),  "
            + " (1, 5), "
            + " (2, 9), "
            + " (2, 10), "
            + " (3, 11), "
            + " (3, 1), "
            + " (4, 2), "
            + " (4, 3), "
            + " (5, 10), "
            + " (5, 1), "
            + " (6, 2), "
            + " (6, 11), "
            + " (7, 1), "
            + " (7, 2), "
            + " (8, 3), "
            + " (8, 10), "
            + " (1, 11), "
            + " (2, 1), "
            + " (3, 7), "
            + " (4, 9), "
            + " (7, 10), "
            + " (2, 11) "
            + " ; ";

    private String insertTableCritiquer="INSERT INTO Critiquer"
            + " (idR, idU, note, commentaire) VALUES "
            + " (1, 1, 3, 'moyen'),"
            + " (1, 2, 1, 'accueil désagréable.'),"
            + " (1, 3, 3, 'Très bonne entrecote, les frites sont maisons et delicieuses.'),"
            + " (1, 4, 4, 'Très bon accueil.'),"
            + " (1, 5, 4, '5/5 parce que jaime les entrecotes'),"
            + " (1, 6, 5, NULL),"
            + " (2, 7, 2, 'bof.'),"
            + " (2, 8, 1, 'À éviter...'),"
            + " (2, 1, 1, 'Cuisine tres moyenne.'),"
            + " (2, 2, 5, NULL),"
            + " (3, 3, 4, 'très bon'),"
            + " (4, 4, 5, 'Rapide.'),"
            + " (5, 5, 1, 'pas bon'),"
            + " (5, 6, 3, 'Cuisine correcte.'),"
            + " (6, 7, 4, 'Cuisine de qualité.'),"
            + " (7, 8, 4, 'Bon accueil.'),"
            + " (7, 1, 5, 'Excellent.'),"
            + " (8, 2, 1, NULL),"
            + " (8, 3, 4, NULL),"
            + " (9, 4, 4, 'bon'),"
            + " (9, 5, 4, 'Très bon accueil') "
            + " ; ";

    private String insertTableRole="INSERT INTO Role"
            + " (idRole, libelleR) VALUES "
            + " (1, 'admin'),"
            + " (2, 'utilisateur') "
            + " ; ";

    private String insertTableAimer="INSERT INTO Aimer"
            + " (idR, idU) VALUES "
            + " (1, 2), "
            + " (1, 3), "
            + " (1, 4), "
            + " (2, 1), "
            + " (3, 1), "
            + " (3, 6), "
            + " (4, 7), "
            + " (4, 8), "
            + " (5, 4), "
            + " (6, 1), "
            + " (7, 2), "
            + " (7, 5), "
            + " (8, 3), "
            + " (8, 6), "
            + " (8, 7), "
            + " (9, 2), "
            + " (10, 3), "
            + " (11, 5) "
            + " ; ";

    public BdSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        //creation table
        db.execSQL(creaTableRole);
        db.execSQL(creaTableUtilisateur);
        db.execSQL(creaTableTypeCuisine);
        db.execSQL(creaTableRestaurant);
        db.execSQL(creaTablePreferer);
        db.execSQL(creaTableAimer);
        db.execSQL(creaTableCritiquer);
        db.execSQL(creaTableProposer);

        //insertion
        db.execSQL(insertTableRole);
        db.execSQL(insertTableUtilisateur);
        db.execSQL(insertTableRestaurant);
        db.execSQL(insertTableTypeCuisine);
        db.execSQL(insertTableProposer);
        db.execSQL(insertTablePreferer);
        db.execSQL(insertTableCritiquer);
        db.execSQL(insertTableAimer);

        Log.d("log","base de test cree");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }
}
