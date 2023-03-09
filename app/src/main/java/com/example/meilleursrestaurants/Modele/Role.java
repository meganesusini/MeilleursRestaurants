package com.example.meilleursrestaurants.Modele;

/**
 * Created by lali.nguyen on 17/03/2022.
 */

public class Role {

    private int idRole;
    private String libelleR;

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getLibelleR() {
        return libelleR;
    }

    public void setLibelleR(String libelleR) {
        this.libelleR = libelleR;
    }

    @Override
    public String toString() {
        return "Role{" +
                "idRole=" + idRole +
                ", libelleR='" + libelleR + '\'' +
                '}';
    }
}
