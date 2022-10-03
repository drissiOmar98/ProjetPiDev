package GestionOffre.models;


import java.util.Date;

public class Offre {
    private int idoffre;
    private int id_reservation;
    private Date date_validite;
    private String taux_de_remise;
    private String description;
    private String Path_image;
    private String Path_video;
    private String Titre;
    private int prix;

    public Offre() {

    }

    public Offre(int idoffre, int id_reservation, Date date_validite, String taux_de_remise, String description, String path_image, String path_video, String titre,int prix) {
        this.idoffre = idoffre;
        this.id_reservation = id_reservation;
        this.date_validite = date_validite;
        this.taux_de_remise = taux_de_remise;
        this.description = description;
        Path_image = path_image;
        Path_video = path_video;
        Titre = titre;
         this.prix=prix;
    }

    public int getPrix() {
        return prix;
    }

    public int getIdoffre() {
        return idoffre;
    }

    public void setIdoffre(int idoffre) {
        this.idoffre = idoffre;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public Date getDate_validite() {
        return date_validite;
    }

    public void setDate_validite(Date date_validite) {
        this.date_validite = date_validite;
    }

    public String getTaux_de_remise() {
        return taux_de_remise;
    }

    public void setTaux_de_remise(String taux_de_remise) {
        this.taux_de_remise = taux_de_remise;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath_image() {
        return Path_image;
    }

    public void setPath_image(String path_image) {
        Path_image = path_image;
    }

    public String getPath_video() {
        return Path_video;
    }

    public void setPath_video(String path_video) {
        Path_video = path_video;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    @Override
    public String toString() {
        return "Offre{" +
                "idoffre='" + idoffre + '\'' +
                ", id_reservation='" + id_reservation + '\'' +
                ", date_validite=" + date_validite +
                ", taux_de_remise=" + taux_de_remise +
                ", description='" + description + '\'' +
                ", Path_image='" + Path_image + '\'' +
                ", Path_video='" + Path_video + '\'' +
                ", Titre='" + Titre + '\'' +
                '}';
    }
}
