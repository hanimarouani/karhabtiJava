/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhany.Services.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.mindrot.jbcrypt.BCrypt;
import pidevhany.Models.pi_user;
import pidevhany.Services.interfaces.IUserService;
import pidevhany.Techniques.DataSource;

/**
 *
 * @author HANY
 */
public class UserService implements IUserService{
    private Connection connection;
    public UserService()
    {
        connection = DataSource.getInstane().getConnection();
    }

    @Override
    public Boolean authentication(String username, String password) {
        Boolean exist = false;
        try {
            String query = "select * from pi_user  where username = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if (i == 1) {
                    if (BCrypt.checkpw(password, rs.getString("password")) == true) {
                        pi_user.setConnectedUser(rs.getInt(1));
                        exist = true;

                    } else {
                        exist = false;
                    }
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(IUserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist;
    }

    @Override
    public boolean RechLogin(String text) {
         boolean b = false;
        try {
            String query = "SELECT * FROM  pi_user WHERE pi_user.username ='" + text + "'";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                b = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }

    @Override
    public pi_user getUser(int id) {
     pi_user u = new pi_user();
        try {
            String req = "select * from fos_user where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));

                u.setImage(rs.getString(4));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return u;
    }

    @Override
    public void add(pi_user user) {
      try {
            String req = "insert into pi_user(username,username_canonical,image,email,password,roles) values (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, user.getUsername());
            ps.setString(2, "hanyy2");
            ps.setString(3, user.getImage());
            ps.setString(4, user.getEmail());
            String pw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(13));
            ps.setString(5, pw);
            ps.setString(6, user.getRoles()
                    
          
                    
            );

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(pi_user t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<pi_user> getAll() {
        List<pi_user> users = new ArrayList<>();
        try {
            String req = "select * from pi_user";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               
                pi_user user = new pi_user(rs.getInt(1),rs.getString(2),rs.getString(16),rs.getString(4),rs.getString(8),rs.getString(12));
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public pi_user findById(Integer id) {
    pi_user user = null;
        try {
            String req = "select * from pi_user where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new pi_user(rs.getInt(1),rs.getString(2),rs.getString(16),rs.getString(4),rs.getString(8),rs.getString(12));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public pi_user findByUsername(String s) {
        pi_user user = null;
        try {
            String req = "select * from pi_user where username =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, s);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new pi_user(rs.getInt(1),rs.getString(2),rs.getString(16),rs.getString(4),rs.getString(8),rs.getString(12));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    @Override
    public void addAdmin(pi_user t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
