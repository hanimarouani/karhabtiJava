/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karhabti.interfaces;

import Karhabti.modeles.PointDeVente;
import java.util.List;


/**
 *
 * @author ELYES
 */
public interface IPointVenteService extends IService<PointDeVente, Integer>{
     public List<PointDeVente> getAllpointVente();
     public PointDeVente getptVenteByName(String nom);
}
