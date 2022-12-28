package com.example.listeproduits;

public class Produit
{
    private int id;
    private String libelle;
    private String codeBarre;
    private String prix;
    private boolean disponible;
    private boolean checkTask;
    private String image;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Boolean getCheckTask() {
        return checkTask;
    }

    public void setCheckTask(Boolean checkTask) {
        this.checkTask = checkTask;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Produit( String libelle, String codeBarre, String prix,Boolean disponible,  Boolean checkTask, String image) {

        this.libelle = libelle;
        this.codeBarre = codeBarre;
        this.prix = prix;
        this.disponible = disponible;
        this.checkTask = checkTask;
        this.image = image;
    }

    public Produit() {

    }

    public Produit(int id, String libelle, String codeBarre, String prix, String image, Boolean checkTask) {
        this.id = id;
        this.libelle = libelle;
        this.codeBarre = codeBarre;
        this.prix = prix;
        this.checkTask = checkTask;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", codeBarre='" + codeBarre + '\'' +
                ", prix='" + prix + '\'' +
                ", disponible=" + disponible +
                ", checkTask=" + checkTask +
                ", image='" + image + '\'' +
                '}';
    }



    public Produit(int id, String libelle, String codeBarre , String prix, Boolean checkTask, String image) {
        this.id = id;
        this.libelle = libelle;
        this.codeBarre = codeBarre;
        this.prix = prix;
        this.checkTask = checkTask;
        this.image = image;
    }
}

