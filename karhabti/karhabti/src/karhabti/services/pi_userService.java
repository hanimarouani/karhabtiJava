/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karhabti.services;

import Karhabti.modeles.Pi_User;
import Karhabti.interfaces.Ipi_userService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Karhabti.modeles.BCrypt;
import Karhabti.technique.DataSource;

/**
 *
 * @author ELYES
 */
public class pi_userService implements Ipi_userService{
    private Connection connection;

    public pi_userService() {
        connection = DataSource.getInstance().getConnection();
    }
    @Override
    public Boolean authentication(String username, String password) {
       Boolean exist=false;
        try {
            String query = "select * from pi_user  where username = ?";
            PreparedStatement ps = connection.prepareStatement(query);
           ps.setString(1,username);
            ResultSet rs =  ps.executeQuery();
            int i=0;
            while (rs.next()) {
      i++;
      if(i==1){
          if(BCrypt.checkpw(password,rs.getString(8))==true){
              Pi_User.setConnectedUser(rs.getInt(1));
              exist=true;
              
          }else{
              exist=false;
          }
      }
      
           }
         
     } catch (SQLException ex) {
            Logger.getLogger(Ipi_userService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return exist;
    }
    
   

    @Override
    public void add(Pi_User user) {
        try {
            String req = "insert into pi_user(username, email, image, password,num_tel) values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getImage());
            String pw = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(13));
            ps.setString(4, pw);
            ps.setInt(5, user.getNumTel());
            

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Pi_User user) {
        try {
            String req = "update pi_user set username=?, email=?, image=?, password=? where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getImage());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            String req = "delete from pi_user where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Pi_User> getAll() {
        List<Pi_User> users = new ArrayList<>();
        try {
            String req = "select * from pi_user";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pi_User user = new Pi_User(rs.getString(1),rs.getInt(13), rs.getString(2), rs.getString(3),rs.getString(4));
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public Pi_User findById(Integer id) {
        Pi_User user = null;
        try {
            String req = "select * from pi_user where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new Pi_User(rs.getInt(1),rs.getString(2),rs.getInt(13), rs.getString(16), rs.getString(4),rs.getString(5));
             
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    @Override
     public Boolean RechLogin(String text) {
         boolean b=false;
        try {
            String query = "SELECT * FROM  pi_user WHERE pi_user.username ='"+text+"'" ;
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs=  ps.executeQuery(query);

            while (rs.next()) {
                b=true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(pi_userService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return b;
    }

    @Override
    public void updateoffre(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

     
    
}
