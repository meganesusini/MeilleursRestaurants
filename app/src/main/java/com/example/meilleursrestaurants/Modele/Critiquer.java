package com.example.meilleursrestaurants.Modele;

/**
 * Created by lili.pamphile on 17/03/2022.
 */

public class Critiquer {
    private int idR , idU, note;
    private String commentaire;

    private Restaurant resto;
    private Utilisateur user;

    public Critiquer(int idR, int idU, int note, String commentaire) {
        this.idR = idR;
        this.idU = idU;
        this.note = note;
        this.commentaire = commentaire;
    }

    public Critiquer(int note, String commentaire, Restaurant resto, Utilisateur user) {
        this.note = note;
        this.commentaire = commentaire;
        this.resto = resto;
        this.user = user;
    }

    public Critiquer(int idR, int note, String commentaire, Restaurant resto) {
        this.idR = idR;
        this.note = note;
        this.commentaire = commentaire;
        this.resto = resto;
    }


    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Restaurant getResto (){
        return resto;
    }

    @Override
    public String toString() {
        return "Nom Restaurant : "+ resto.getNomR() +"\n Note : " + this.note +"\n Commentaire : " + this.commentaire;
    }
}
