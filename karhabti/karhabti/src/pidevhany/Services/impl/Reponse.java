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
import pidevhany.Models.mes_tests;
import pidevhany.Services.interfaces.IstatTests;
import pidevhany.Techniques.DataSource;
import pidevhany.Models.resultat;

/**
 *
 * @author HANY
 */
public class Reponse implements IstatTests{
    private Connection connection;
    public Reponse()
    {
        connection = DataSource.getInstane().getConnection();
    }

    @Override
 public resultat nombreTestsSucces(int id) {
     resultat res = null;
        try {
            String query = "select * from resultat where id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {  
                res=new resultat(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3)) ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }   

    @Override
    public void updateSucce(int score , int id) {
    try {
            String req = "update resultat set reponse_succes=? where id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(2, id);
            ps.setInt(1,score);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEchec(int score,int id) {
    try {
            String req = "update resultat set reponse_echec=? where id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(2, id);
            ps.setInt(1,score);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
