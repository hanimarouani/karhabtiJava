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
import pidevhany.Services.interfaces.Itests;
import pidevhany.Techniques.DataSource;

/**
 *
 * @author HANY
 */
public class Tests implements Itests<mes_tests, Integer> {

    private Connection connection;

    public Tests() {
        connection = DataSource.getInstane().getConnection();
    }

    public void ajouterTest(mes_tests test) {
        try {
            String req = "insert into mes_tests(niveau,image,question,reponse,choix1,choix2,choix3,choix4,choix5)"
                    + "values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, test.getNiveau());
            ps.setString(2, test.getImage());
            ps.setString(3, test.getQuestion());
            ps.setInt(4, test.getReponse());
            ps.setString(5, test.getChoix1());
            ps.setString(6, test.getChoix2());
            ps.setString(7, test.getChoix3());
            ps.setString(8, test.getChoix4());
            ps.setString(9, test.getChoix5());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierTest(mes_tests test, Integer id) {
        try {
            String req = "update mes_tests set niveau=? ,image=?,question=?,reponse=?,choix1=?,choix2=?,choix3=?,choix4=?,choix5=?"
                    + " where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, test.getNiveau());
            ps.setString(2, test.getImage());
            ps.setString(3, test.getQuestion());
            ps.setInt(4, test.getReponse());
            ps.setString(5, test.getChoix1());
            ps.setString(6, test.getChoix2());
            ps.setString(7, test.getChoix3());
            ps.setString(8, test.getChoix4());
            ps.setString(9, test.getChoix5());
            ps.setInt(10, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerTest(Integer id) {
        try {

            String req = "delete from mes_tests where id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<mes_tests> getAllTest() {
        List<mes_tests> tests = new ArrayList<>();
        try {
            String req = "select * from mes_tests ";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                mes_tests test = new mes_tests(resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getString(4), resultSet.getString(3),
                        resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8),
                        resultSet.getString(9), resultSet.getString(11));
                tests.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    public List<mes_tests> getAllTestNiveau(Integer niveau) {
        List<mes_tests> tests = new ArrayList<>();
        try {
            String req = "select * from mes_tests where niveau =" + niveau + "";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                mes_tests test = new mes_tests(resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getString(4), resultSet.getString(3),
                        resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8),
                        resultSet.getString(9), resultSet.getString(11));
                tests.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    public void getField(String table, String culumn, int niveau) {
        try {
            String query = "select " + culumn + " from " + table + " where niveau="+ niveau +"";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery(query);
            while (resultSet.next()) {
                String var = resultSet.getString(culumn);
                System.out.println(var);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public List<String> getAllResponses(int niv) {
        List<String> liste = new ArrayList<>();
        try {
            String query = "select responseforja from mes_tests where niveau=" + niv + "";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                liste.add(resultSet.getString("responseforja"));

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return liste;
    }

}
