/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karhabti.interfaces;

import Karhabti.modeles.Rate;
import java.util.List;

/**
 *
 * @author Mohamed
 */
public interface Irate extends Iservices<Rate, Integer, String>{
    void ajouter (Rate rate);
    void modifier (Rate rate);
    List<Rate> rechercher (String titre);
    void supprimer(int id);
    List<Rate> getAll();
    
}
