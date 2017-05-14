/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karhabti.services;
import java.sql.Connection;
import Karhabti.technique.DataSource;
import Karhabti.interfaces.Iuser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Karhabti.modeles.BCrypt;
import Karhabti.modeles.User;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Mohamed
 */
public class UserServices implements Iuser{
         private Connection connection;

    public UserServices() {
        connection = DataSource.getInstance().getConnection();
    }
   
    
   
    public void ajouter(User user) {
        try {
            String req = "insert into pi_user(email,username,password,image) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
          
         
            String pw = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(13));
            ps.setString(3, pw);
            ps.setString(4, user.getImage());
            


            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

   
    public void modifier(User user) {
        try {
            String req = "update pi_user set username=?,nom=? ,email=?,password=? where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
                        ps.setInt(4, user.getId());



            ps.setInt(4, user.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

  
    public void supprimer(Integer id) {
        try {
            String req = "delete from pi_user where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            String req = "select * from pi_user";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt(1));
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

   
    public User findById(Integer id) {
        User user = null;
        try {
            String req = "select * from pi_user where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt(1),rs.getString(2),rs.getString(16),rs.getString(4),rs.getString(12));
            }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    
     public Boolean RechLogin(String text) {
         boolean b=false;
        try {
            String query = "SELECT * FROM  pi_user WHERE PI_user.username ='"+text+"'" ;
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs=  ps.executeQuery(query);

            while (rs.next()) {
                b=true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    return b;
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
              User.setConnectedUser(rs.getInt(1));
              exist=true;
              
          }else{
              exist=false;
          }
      }
      
           }
         
     } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    return exist;
    }
}
