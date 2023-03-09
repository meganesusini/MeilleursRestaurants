package com.example.meilleursrestaurants.Modele;

/**
 * Created by lali.nguyen on 17/03/2022.
 */

public class Utilisateur {
     private int idU, idRole;
     private String mailU, mdpU, pseudoU;

    public Utilisateur(int idU, String mailU, String mdpU, String pseudoU,int idRole) {
        this.idU = idU;
        this.mailU = mailU;
        this.mdpU = mdpU;
        this.pseudoU = pseudoU;
        this.idRole = idRole;
    }

    public Utilisateur(String mailU, String mdpU, String pseudoU) {
        this.mailU = mailU;
        this.mdpU = mdpU;
        this.pseudoU = pseudoU;
    }

    public Utilisateur(String pseudoU, String mdpU){
        this.pseudoU = pseudoU;
        this.mdpU = mdpU;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getMailU() {
        return mailU;
    }

    public void setMailU(String mailU) {
        this.mailU = mailU;
    }

    public String getMdpU() {
        return mdpU;
    }

    public void setMdpU(String mdpU) {
        this.mdpU = mdpU;
    }

    public String getPseudoU() {
        return pseudoU;
    }

    public void setPseudoU(String pseudoU) {
        this.pseudoU = pseudoU;
    }

    @Override
    public String toString() {
        return mailU + "\n" +pseudoU ;
    }
}
