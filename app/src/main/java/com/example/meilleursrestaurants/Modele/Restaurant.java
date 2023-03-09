package com.example.meilleursrestaurants.Modele;

/**
 * Created by mega.susini on 21/03/2022.
 */

public class Restaurant {
    private int idR;
    private String nomR;
    private String numAdR;
    private String voieAdR;
    private String cpR;
    private String villeR;
    private String descR;
    private String horairesR;

    public Restaurant(int idR, String nomR, String numAdR, String voieAdR, String cpR, String villeR, String descR, String horairesR) {
        this.idR = idR;
        this.nomR = nomR;
        this.numAdR = numAdR;
        this.voieAdR = voieAdR;
        this.cpR = cpR;
        this.villeR = villeR;
        this.descR = descR;
        this.horairesR = horairesR;
    }

    public Restaurant(String nomR, String numAdR, String voieAdR, String cpR, String villeR, String descR, String horairesR) {
        this.nomR = nomR;
        this.numAdR = numAdR;
        this.voieAdR = voieAdR;
        this.cpR = cpR;
        this.villeR = villeR;
        this.descR = descR;
        this.horairesR = horairesR;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public String getNomR() {
        return this.nomR;
    }

    public void setNomR(String nomR) {
        this.nomR = nomR;
    }

    public String getNumAdR() {
        return numAdR;
    }

    public void setNumAdR(String numAdR) {
        this.numAdR = numAdR;
    }

    public String getVoieAdR() {
        return voieAdR;
    }

    public void setVoieAdR(String voieAdR) {
        this.voieAdR = voieAdR;
    }

    public String getCpR() {
        return cpR;
    }

    public void setCpR(String cpR) {
        this.cpR = cpR;
    }

    public String getVilleR() {
        return villeR;
    }

    public void setVilleR(String villeR) {
        this.villeR = villeR;
    }

    public String getDescR() {
        return descR;
    }

    public void setDescR(String descR) {
        this.descR = descR;
    }

    public String getHorairesR() {
        return horairesR;
    }

    public void setHorairesR(String horairesR) {
        this.horairesR = horairesR;
    }

    @Override
    public String toString() {
        return nomR + "\n" + numAdR + " " + voieAdR + "\n" + cpR + " " + villeR  ;
    }
}
