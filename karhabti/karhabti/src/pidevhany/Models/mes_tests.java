/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhany.Models;

import java.util.Objects;

/**
 *
 * @author HANY
 */
public class mes_tests {
     private int id;
    private int niveau;
    private int reponse;
    private String question;
    private String image;
    private String choix1;
    private String choix2;
    private String choix3;
    private String choix4;
    private String choix5;
    private String responsej;
    private static int scorce;

    public mes_tests() {
    }

    
    public mes_tests(int id, int niveau, int reponse, String question, String image, String choix1, String choix2, String choix3, String choix4, String choix5) {
        this.id = id;
        this.niveau = niveau;
        this.reponse = reponse;
        this.question = question;
        this.image = image;
        this.choix1 = choix1;
        this.choix2 = choix2;
        this.choix3 = choix3;
        this.choix4 = choix4;
        this.choix5 = choix5;
    }

    public mes_tests(int niveau, int reponse, String question, String image, String choix1, String choix2, String choix3, String choix4, String choix5,String resjava) {
        this.niveau = niveau;
        this.reponse = reponse;
        this.question = question;
        this.image = image;
        this.choix1 = choix1;
        this.choix2 = choix2;
        this.choix3 = choix3;
        this.choix4 = choix4;
        this.choix5 = choix5;
        this.responsej=resjava;
    }

    public mes_tests(int niveau,String image,String question,int reponse,String choix1,String choix2,String choix3) {
        this.niveau = niveau;
        this.reponse = reponse;
        this.question = question;
        this.image = image;
        this.choix1 = choix1;
        this.choix2 = choix2;
        this.choix3 = choix3;
    }
    public mes_tests(int id,int niveau,String image,String question,int reponse,String choix1,String choix2,String choix3) {
        this.id = id;
        this.niveau = niveau;
        this.reponse = reponse;
        this.question = question;
        this.image = image;
        this.choix1 = choix1;
        this.choix2 = choix2;
        this.choix3 = choix3;
    }
    public String getResponsej() {
        return responsej;
    }

    public void setResponsej(String responsej) {
        this.responsej = responsej;
    }

    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getReponse() {
        return reponse;
    }

    public void setReponse(int reponse) {
        this.reponse = reponse;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getChoix1() {
        return choix1;
    }

    public void setChoix1(String choix1) {
        this.choix1 = choix1;
    }

    public String getChoix2() {
        return choix2;
    }

    public void setChoix2(String choix2) {
        this.choix2 = choix2;
    }

    public String getChoix3() {
        return choix3;
    }

    public void setChoix3(String choix3) {
        this.choix3 = choix3;
    }

    public String getChoix4() {
        return choix4;
    }

    public void setChoix4(String choix4) {
        this.choix4 = choix4;
    }

    public String getChoix5() {
        return choix5;
    }

    public void setChoix5(String choix5) {
        this.choix5 = choix5;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + this.niveau;
        hash = 37 * hash + this.reponse;
        hash = 37 * hash + Objects.hashCode(this.question);
        hash = 37 * hash + Objects.hashCode(this.image);
        hash = 37 * hash + Objects.hashCode(this.choix1);
        hash = 37 * hash + Objects.hashCode(this.choix2);
        hash = 37 * hash + Objects.hashCode(this.choix3);
        hash = 37 * hash + Objects.hashCode(this.choix4);
        hash = 37 * hash + Objects.hashCode(this.choix5);
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
        final mes_tests other = (mes_tests) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.niveau != other.niveau) {
            return false;
        }
        if (this.reponse != other.reponse) {
            return false;
        }
        if (!Objects.equals(this.question, other.question)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.choix1, other.choix1)) {
            return false;
        }
        if (!Objects.equals(this.choix2, other.choix2)) {
            return false;
        }
        if (!Objects.equals(this.choix3, other.choix3)) {
            return false;
        }
        if (!Objects.equals(this.choix4, other.choix4)) {
            return false;
        }
        if (!Objects.equals(this.choix5, other.choix5)) {
            return false;
        }
        return true;
    }

    public static int getScorce() {
        return scorce;
    }

    public static void setScorce(int scorce) {
        mes_tests.scorce = scorce;
    }

    @Override
    public String toString() {
        return "mes_tests{" + "id=" + id + ", niveau=" + niveau + ", reponse=" + reponse + ", question=" + question + ", image=" + image + ", choix1=" + choix1 + ", choix2=" + choix2 + ", choix3=" + choix3 + ", choix4=" + choix4 + ", choix5=" + choix5 + ", responsej=" + responsej + '}';
    }
    
    
}
