/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karhabti.services;

import Karhabti.modeles.PointDeVente;
import Karhabti.interfaces.IPointVenteService;
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
public class PointDeVenteService implements IPointVenteService{

    private Connection connection;

   
    

    public PointDeVenteService() {
               connection = DataSource.getInstance().getConnection();

    }


    @Override
    public void add(PointDeVente a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(PointDeVente a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PointDeVente> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PointDeVente findById(Integer id) {

    
        PointDeVente pt=null;
      try {
            String req = "select * from pointdevente where  id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
            pt = new PointDeVente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
               
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      return pt;
    }

    @Override
    public List<PointDeVente> getAllpointVente() {
        List<PointDeVente> ptvente = new ArrayList<PointDeVente>();
      try {
            String req = "select * from pointdevente";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
            PointDeVente pt = new PointDeVente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
                ptvente.add(pt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      return ptvente;
    }

    @Override
    public PointDeVente getptVenteByName(String nom) {
           PointDeVente pt=null;
      try {
            String req = "select * from pointdevente where  nom=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1,nom);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
            pt = new PointDeVente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
               
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      return pt;
    }

    @Override
    public void updateoffre(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
