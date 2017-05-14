/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karhabti.services;

import Karhabti.interfaces.ISignalisations;
import Karhabti.modeles.Annonces;
import Karhabti.modeles.Signalisations;
import Karhabti.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mohamed
 */
public class SignalisationsServices implements ISignalisations {

    private Connection connection;

    public SignalisationsServices() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void ajouter(Signalisations signalisation) {
        try {
            String req = "insert into signalisations (id_annonce,id_user,id_usersig,nom_annonce)values(?,?,?,?)  ";
            PreparedStatement ps = connection.prepareStatement(req);

            ps.setInt(1, signalisation.getId_annonce().getId());
            ps.setInt(2, signalisation.getId_usersig().getId());
            ps.setInt(3, signalisation.getId_annonce().getUser().getId());
            ps.setString(4, signalisation.getId_annonce().getTITRE());

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id,int id_user) {
        try {
            String req = "delete from signalisations where id_annonce=? and id_user = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.setInt(2, id_user);

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
            
    @Override
    public List<Signalisations> getAll() {
                List<Signalisations> sig = new ArrayList<>();
        try {
            String req = "select count(id_annonce) , id , id_annonce,id_user,id_usersig,nom_annonce from signalisations group by id_annonce";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Signalisations sigl= new Signalisations(rs.getInt(2),new AnnoncesServices().findById(rs.getInt(3)), rs.getInt(4),new UserServices().findById(rs.getInt(5)),rs.getString(6),rs.getInt(1));
                    sig.add(sigl);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return sig ;
    }
       public List<Annonces> tonlist(int id_usersig){
           List<Annonces> annonces = new ArrayList<>();
           try{
               String req ="select a.* from annonces a where VALIDATION = 1 and not exists(select * from signalisations s where a.id = s.id_annonce and s.id_user = ? )";
               PreparedStatement ps = connection.prepareStatement(req);
                         ps.setInt(1, id_usersig);
                ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                    Annonces annonce = new Annonces(rs.getInt(1), rs.getString(4), rs.getString(9), rs.getString(13), rs.getInt(5), rs.getString(6), rs.getString(10), rs.getFloat(12), rs.getFloat(7), rs.getFloat(8), new UserServices().findById(rs.getInt(2)), rs.getString(14),rs.getDate(3));
                    annonces.add(annonce);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return annonces;
            }

       public List<Annonces> tonsignaler(int user_sign){
           List<Annonces> annonces = new ArrayList<>();
           try{
               String req ="select a.* from annonces a where exists(select * from signalisations s where a.id = s.id_annonce and s.id_user = ? )  ";
               PreparedStatement ps = connection.prepareStatement(req);
                        ps.setInt(1, user_sign);
                        
                ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                    Annonces annonce = new Annonces(rs.getInt(1), rs.getString(4), rs.getString(9), rs.getString(13), rs.getInt(5), rs.getString(6), rs.getString(10), rs.getFloat(12), rs.getFloat(7), rs.getFloat(8), new UserServices().findById(rs.getInt(2)), rs.getString(14),rs.getDate(3));
                    annonces.add(annonce);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return annonces;
            }



}

           
           
       

