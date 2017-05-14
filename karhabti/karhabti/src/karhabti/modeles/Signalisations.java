/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karhabti.modeles;

import java.util.Objects;

/**
 *
 * @author Mohamed
 */
public class Signalisations {
    private int id;
    private Annonces id_annonce;
    private int id_user;
    private User id_usersig;
    private String nom_annonce;
    private int count;

    public Signalisations() {
    }

    public Signalisations(Annonces id_annonce, int id_user, User id_usersig, String nom_annonce) {
        this.id_annonce = id_annonce;
        this.id_user = id_user;
        this.id_usersig = id_usersig;
        this.nom_annonce = nom_annonce;
    }

    public Signalisations(int id, Annonces id_annonce, int id_user, User id_usersig, String nom_annonce) {
        this.id = id;
        this.id_annonce = id_annonce;
        this.id_user = id_user;
        this.id_usersig = id_usersig;
        this.nom_annonce = nom_annonce;
    }


    

    public Signalisations(int id) {
        this.id = id;
    }

    public Signalisations(int id, Annonces id_annonce, int id_user, User id_usersig, String nom_annonce, int count) {
        this.id = id;
        this.id_annonce = id_annonce;
        this.id_user = id_user;
        this.id_usersig = id_usersig;
        this.nom_annonce = nom_annonce;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Annonces getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(Annonces id_annonce) {
        this.id_annonce = id_annonce;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public User getId_usersig() {
        return id_usersig;
    }

    public void setId_usersig(User id_usersig) {
        this.id_usersig = id_usersig;
    }

    public String getNom_annonce() {
        return nom_annonce;
    }

    public void setNom_annonce(String nom_annonce) {
        this.nom_annonce = nom_annonce;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.id_annonce);
        hash = 59 * hash + this.id_user;
        hash = 59 * hash + Objects.hashCode(this.id_usersig);
        hash = 59 * hash + Objects.hashCode(this.nom_annonce);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Signalisations other = (Signalisations) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (!Objects.equals(this.nom_annonce, other.nom_annonce)) {
            return false;
        }
        if (!Objects.equals(this.id_annonce, other.id_annonce)) {
            return false;
        }
        if (!Objects.equals(this.id_usersig, other.id_usersig)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Signalisations{" + "id=" + id + ", id_annonce=" + id_annonce + ", id_user=" + id_user + ", id_usersig=" + id_usersig + ", nom_annonce=" + nom_annonce +"count"+count+ '}';
    }

    
    
    



    
            
}
