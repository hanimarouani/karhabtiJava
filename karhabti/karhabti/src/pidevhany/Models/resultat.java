/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhany.Models;

/**
 *
 * @author HANY
 */
public class resultat {
     private int id;
    private int reponseSucces;
    private int reponseEchec;

    public resultat(int id, int reponseSucces, int reponseEchec) {
        this.id = id;
        this.reponseSucces = reponseSucces;
        this.reponseEchec = reponseEchec;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReponseSucces() {
        return reponseSucces;
    }

    public void setReponseSucces(int reponseSucces) {
        this.reponseSucces = reponseSucces;
    }

    public int getReponseEchec() {
        return reponseEchec;
    }

    public void setReponseEchec(int reponseEchec) {
        this.reponseEchec = reponseEchec;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + this.reponseSucces;
        hash = 79 * hash + this.reponseEchec;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final resultat other = (resultat) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.reponseSucces != other.reponseSucces) {
            return false;
        }
        if (this.reponseEchec != other.reponseEchec) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "resultat{" + "id=" + id + ", reponseSucces=" + reponseSucces + ", reponseEchec=" + reponseEchec + '}';
    }
    

}
