/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karhabti.interfaces;

import java.util.List;
import Karhabti.modeles.Annonces;
/**
 *
 * @author Mohamed
 */
public interface Iannonces extends Iservices<Annonces, Integer, String> {
    void ajouter (Annonces annonce);
    void modifier (Annonces annonce);
    List<Annonces> rechercher (String titre);
    void supprimer(int id);
    List<Annonces> getAll();
}
