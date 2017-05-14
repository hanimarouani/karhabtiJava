/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karhabti.services;

import Karhabti.interfaces.Iservices;
import Karhabti.modeles.Offre;
import Karhabti.interfaces.IOffreService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Karhabti.technique.DataSource;

/**
 *
 * @author ELYES
 */
public class OffreService implements IOffreService{
    
    private Connection connection;

   
    

    public OffreService() {
               connection = DataSource.getInstance().getConnection();

    }

    @Override
    public void add(Offre offre) {
        try {
//            System.out.println(offre.getNom_offre());
//            System.out.println(offre.getDescription());
//            System.out.println(offre.getPhoto());
//            System.out.println(offre.getPtvente().getId());
//            System.out.println(offre.getEtat());
//            System.out.println(offre.getProprietaire().getId());
//            System.out.println(offre.getPrixinit());
//            System.out.println(offre.getTaux_remise());
            String req = "insert into offre(nom_offre, description,photo,ptvente_id_id,etat,membre_id_id,prixinit,taux_remise) values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, offre.getNom_offre());
            ps.setString(2, offre.getDescription());
            ps.setString(3, offre.getPhoto());
            ps.setInt(4, offre.getPtvente().getId());
            ps.setInt(5,0);
            ps.setInt(6, offre.getProprietaire().getId());
            ps.setFloat(7, offre.getPrixinit());
            ps.setFloat(8, offre.getTaux_remise());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Offre offre) {
        try {
            String req = "update offre set nom_offre=?, description=?, photo=?,ptvente_id_id=?,membre_id_id=?,prixinit=?,taux_remise=? where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, offre.getNom_offre());
            ps.setString(2, offre.getDescription());
            ps.setString(3, offre.getPhoto());
            ps.setInt(4, offre.getPtvente().getId());
            ps.setInt(5, offre.getProprietaire().getId());
            ps.setFloat(6, offre.getPrixinit());
            ps.setFloat(7, offre.getTaux_remise());
            ps.setInt(8, offre.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateoffre(Integer id) {
        try {
            String req = "update offre set etat=? where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            
            ps.setInt(1, 1);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void delete(Integer id) {
        try {
            String req = "delete from offre where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Offre> getAll() {
        List<Offre> offres = new ArrayList<>();
        try {
            String req = "select * from offre";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Offre offre = new Offre(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),new PointDeVenteService().findById(rs.getInt(5)),rs.getInt(6),new UserServices().findById(rs.getInt(7)),rs.getFloat(8),rs.getFloat(9));
                offres.add(offre);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return offres;
    }


    @Override
    public Offre findById(Integer id) {
      Offre offre=null;
        try {
            String req = "select * from offre where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
               offre = new Offre(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),new PointDeVenteService().findById(rs.getInt(5)),rs.getInt(6),new UserServices().findById(rs.getInt(7)),rs.getFloat(8),rs.getFloat(9));
                offre.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return offre;
    }
    
    
    
    
    
    
}
