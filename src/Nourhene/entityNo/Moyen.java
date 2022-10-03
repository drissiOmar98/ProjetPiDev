package Nourhene.entityNo;

public class Moyen {
    private int id ;
    private String type;
    private String disponibilite;
    private String chauffeur;
    private String prix1;


    public Moyen(int id, String type, String disponibilite, String chauffeur , String prix1) {
        this.id = id;
        this.type = type;
        this.disponibilite = disponibilite;
        this.chauffeur = chauffeur;
        this.prix1=prix1;
    }

    public String getPrix1() {
        return prix1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(String chauffeur) {
        this.chauffeur = chauffeur;
    }

    @Override
    public String toString() {
        return "Moyen{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", disponibilite='" + disponibilite + '\'' +
                ", chauffeur='" + chauffeur + '\'' +
                '}';
    }
}
