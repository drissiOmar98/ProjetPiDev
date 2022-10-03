package entity;

public class User {
    public int id ;
    public String Nom ;
    public String Prenom ;
    public String email ;
    public String roles ;
    public int Cin ;
    public String password ;
    public int is_Verified ;
    public String image_name ;

    public User(int id , String Nom , String Prenom , String email , String roles , int Cin , String password , int is_Verified , String image_name){
        this.id=id;
        this.Nom=Nom ;
        this.Prenom=Prenom;
        this.email=email ;
        this.roles=roles;
        this.Cin=Cin ;
        this.password=password;
        this.is_Verified=is_Verified;
        this.image_name=image_name;
    }
    public User(){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom(){ return  Nom ; }
    public void setNom(String Nom){this.Nom=Nom ;}
    public String getPrenom(){ return  Prenom ; }
    public void setPrenom(String Prenom){this.Prenom=Prenom ;}
    public String getEmail(){ return  email ; }
    public void setEmail(String email){this.email=email ;}
    public String getRoles(){ return  roles ; }
    public void setRoles(String roles){this.roles=roles ;}
    public int getCin(){ return  Cin ; }
    public void setCin(int Cin){this.Cin=Cin ;}
    public String getPassword(){ return  password ; }
    public void setPassword(String password){this.password=password ;}
    public int getIs_Verified(){ return  is_Verified ; }
    public void setIs_Verified(int is_Verified){this.is_Verified=is_Verified ;}
    public String getImage_name(){ return  image_name ; }
    public void setImage_name(String image_name){this.image_name=image_name ;}



}
