/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karhabti.services;

import java.sql.Connection;
import Karhabti.technique.DataSource;
import Karhabti.interfaces.Iservices;
import Karhabti.modeles.Annonces;
import com.oracle.jrockit.jfr.Producer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Karhabti.services.UserServices;
import java.sql.SQLData;

/**
 *
 * @author Mohamed
 */
public class AnnoncesServices implements Iservices<Annonces, Integer, String> {

    private Connection connection;

    public AnnoncesServices() {
        connection = DataSource.getInstance().getConnection();
    }

    public void ajouter(Annonces Annonce) {
        try {
            String req = "insert into annonces (TITRE,boite,region,NOMBRE_DE_CYLINDRES,ENERGIE,DESCRIPITION,PRIX,PUISSANCE_FISCALE,CYLINDREE,user,image,VALIDATION)values(?,?,?,?,?,?,?,?,?,?,?,?)  ";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, Annonce.getTITRE());
            ps.setString(2, Annonce.getBOITE());
            ps.setString(3, Annonce.getRegion());
            ps.setInt(4, Annonce.getNOMBRE_DE_CYLINDRES());
            ps.setString(5, Annonce.getENERGIE());
            ps.setString(6, Annonce.getDESCRIPITION());
            ps.setFloat(7, Annonce.getPRIX());
            ps.setFloat(8, Annonce.getPUISSANCE_FISCALE());
            ps.setFloat(9, Annonce.getCYLINDREE());
            ps.setInt(10, Annonce.getUser().getId());
            ps.setString(11, Annonce.getImage());
            ps.setInt(12, 0);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void modifier(Annonces Annonce) {
        try {
            String req = "update annonces set TITRE=? , BOITE=? , region=? ,NOMBRE_DE_CYLINDRES=?,ENERGIE=?,DESCRIPITION=?,PRIX=?,PUISSANCE_FISCALE=?,CYLINDREE=?  where id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, Annonce.getTITRE());
            ps.setString(2, Annonce.getBOITE());
            ps.setString(3, Annonce.getRegion());
            ps.setInt(4, Annonce.getNOMBRE_DE_CYLINDRES());
            ps.setString(5, Annonce.getENERGIE());
            ps.setString(6, Annonce.getDESCRIPITION());
            ps.setFloat(7, Annonce.getPRIX());
            ps.setFloat(8, Annonce.getPUISSANCE_FISCALE());
            ps.setFloat(9, Annonce.getCYLINDREE());
            ps.setInt(10, Annonce.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void Accepter(Integer id) {
        try {
            String req = "update annonces set VALIDATION=? where id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, 1);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void supprimer(Integer id) {
        try {
            String req = "delete from Annonces where id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Annonces> getAll() {
        List<Annonces> annonces = new ArrayList<>();
        try {
            String req = "select * from annonces";
            PreparedStatement ps = connection.prepareStatement(req);
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

    public List<Annonces> getAnnoncesValider() {
        List<Annonces> annonces = new ArrayList<>();
        try {
            String req = "select * from annonces where VALIDATION = 1";
            PreparedStatement ps = connection.prepareStatement(req);
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

    public List<Annonces> getAnnoncesNonValider() {
        List<Annonces> annonces = new ArrayList<>();
        try {
            String req = "select * from annonces where VALIDATION = 0";
            PreparedStatement ps = connection.prepareStatement(req);
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

    public List<Annonces> rechercher(String titre, String boite, String region, float prix_min, float prix_max) {
        List<Annonces> annonces = new ArrayList<>();

        try {
            String req = "select * from annonces where titre like ? and boite like ? or region like ?  or prix > ? or prix < ? and VALIDATION = 1 ";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, "%" + titre + "%");
            ps.setString(2, "%" + boite + "%");
            ps.setString(3, "%" + region + "%");
            ps.setFloat(4, prix_min);
            ps.setFloat(5, prix_max);

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

    public List<Annonces> rechercheprixmin(String titre, String boite, String region, float prix_min) {
        List<Annonces> annonces = new ArrayList<>();

        try {
            String req = "select * from annonces where titre like ? and boite like ? and region like ? or prix>=? and VALIDATION = 1";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, "%" + titre + "%");
            ps.setString(2, "%" + boite + "%");
            ps.setString(3, "%" + region + "%");
            ps.setFloat(4, prix_min);

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

    public List<Annonces> rechercherprixmax(String titre, String boite, String region, float prix_max) {
        List<Annonces> annonces = new ArrayList<>();

        try {
            String req = "select * from annonces where titre like ? and boite like ? and region like ?  or prix< ? and VALIDATION = 1 ";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, "%" + titre + "%");
            ps.setString(2, "%" + boite + "%");
            ps.setString(3, "%" + region + "%");

            ps.setFloat(4, prix_max);

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

    public List<Annonces> rechercherprix(String titre, String boite, String region) {
        List<Annonces> annonces = new ArrayList<>();

        try {
            String req = "select * from annonces where titre like ? and boite like ? and region like ? and VALIDATION = 1  ";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, "%" + titre + "%");
            ps.setString(2, "%" + boite + "%");
            ps.setString(3, "%" + region + "%");

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

    public Annonces findById(Integer id) {
        Annonces annonce = null;
        try {
            String req = "select * from annonces where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                annonce = new Annonces(rs.getInt(1), rs.getString(4), rs.getString(9), rs.getString(13), rs.getInt(5), rs.getString(6), rs.getString(10), rs.getFloat(12), rs.getFloat(7), rs.getFloat(8), new UserServices().findById(rs.getInt(2)), rs.getString(14),rs.getDate(3));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return annonce;
    }

}
