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
public class pi_user {
    
public static int connectedUser;
private int id;
private String username;
private String username_canonical;
private String image;
private String email;
private String password;
private String roles;
private String position;
private static Integer idUser=0;

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public pi_user(String username, String username_canonical, String image, String email, String password, String roles) {
        this.username = username;
        this.username_canonical = username_canonical;
        this.image = image;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }


    public pi_user() {
    }

    public pi_user(int id) {
        this.id = id;
    }

    public pi_user(int id, String username, String image, String email, String password, String roles) {
        this.id = id;
        this.username = username;
        this.image = image;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    

    public pi_user(int id, String username) {
        this.id = id;
        this.username = username;
        
    }
    
    
    public pi_user(String username,String email, String image){
    this.username=username;
    this.email=email;
    this.image=image;
    }

   
    

    public String getUsername() {
        return username;
    }

    public pi_user(String password) {
        this.password = password;
    }
    

    public void setUsername(String username) {
        this.username = username;
    }
    

    public pi_user(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
    

    public pi_user(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    } 
    

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public static int getConnectedUser() {
        return connectedUser;
    }

    public static void setConnectedUser(int connectedUser) {
        pi_user.connectedUser = connectedUser;
    }

   
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id;
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
        final pi_user other = (pi_user) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public static Integer getIdUser() {
        return idUser;
    }

    public static void setIdUser(Integer idUser) {
        pi_user.idUser = idUser;
    }

    @Override
    public String toString() {
        return  username ;
    }

}
