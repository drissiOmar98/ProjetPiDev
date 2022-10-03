package Badis.entityBa;

public class Hotel {
        private  int id;
        private  String nom;
        private int etoile;
        private  String lieu;
        private String hebergement;
        private  String Path_image ;
        private  String Path_video ;
        private String chambre ;
        private int ch_sing ;
     //   private int ch_doub ;
      //  private int ch_trip ;


        public Hotel(int id, String nom, int etoile, String lieu, String hebergement, String path_image, String path_video, String chambre, int ch_sing/*, int ch_doub, int ch_trip*/) {
            this.id = id;
            this.nom = nom;
            this.etoile = etoile;
            this.lieu = lieu;
            this.hebergement = hebergement;
            Path_image = path_image;
            Path_video = path_video;
            this.chambre = chambre;
            this.ch_sing = ch_sing;
           // this.ch_doub = ch_doub;
           // this.ch_trip = ch_trip;
        }

        public String getPath_image() {
            return Path_image;
        }

        public String getPath_video() {
            return Path_video;
        }

        public int getId() {
            return id;
        }

        public String getNom() {
            return nom;
        }

        public String getChambre() {
            return chambre;
        }

        public int getEtoile() {
            return etoile;
        }

        public String getLieu() {
            return lieu;
        }

        public String getHebergement() {
            return hebergement;
        }

        public int getCh_sing() {
            return ch_sing;
        }

      /*  public int getCh_doub() {
            return ch_doub;
        }

        public int getCh_trip() {
            return ch_trip;
        }*/









    }

