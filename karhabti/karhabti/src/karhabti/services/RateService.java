/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karhabti.services;

import Karhabti.interfaces.Irate;
import Karhabti.modeles.Annonces;
import Karhabti.modeles.Rate;
import Karhabti.modeles.User;
import Karhabti.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mohamed
 */
public class RateService implements Irate{
    private Connection connection;
    private PreparedStatement ps;

    public RateService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void ajouter(Rate t) {
        String req = "insert into rate (nbrRate,idUser,idAnnonce) values (?,?,?)";
        try {
            ps = connection.prepareStatement(req);
            ps.setDouble(1, t.getNbrVote());
            ps.setInt(2, t.getIdUser().getId());
            ps.setInt(3, t.getIdAnnonce().getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifier(Rate rate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rate> rechercher(String titre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rate> getAll() {
        String req = "select * from rate";
        List<Rate> listRate = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Rate rate= new Rate( resultSet.getInt(1),resultSet.getDouble(2), new User(resultSet.getInt(4)),new Annonces(resultSet.getInt(3)));
                listRate.add(rate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listRate; 
    }

    @Override
    public void supprimer(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rate findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Double Moyenne(){
        String req="SELECT AVG(nbrRate) FROM rate";
        Rate rate = null;
        try{
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
               // rate= new Rate( resultSet.getInt(1),resultSet.getDouble(2), new User(resultSet.getInt(4)),new Annonce(resultSet.getInt(3)));
            return resultSet.getDouble(1);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    
}
