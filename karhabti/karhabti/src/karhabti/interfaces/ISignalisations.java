/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karhabti.interfaces;

import Karhabti.modeles.Annonces;
import Karhabti.modeles.Signalisations;
import java.util.List;

/**
 *
 * @author Mohamed
 */
public interface ISignalisations {
        void ajouter (Signalisations signaliation);
        void supprimer(int id,int id_user);
        List<Signalisations> getAll();
}
