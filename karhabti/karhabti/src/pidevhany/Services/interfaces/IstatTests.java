/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhany.Services.interfaces;

import pidevhany.Models.resultat;

/**
 *
 * @author HANY
 */
public interface IstatTests {
       resultat nombreTestsSucces(int niv);
       void updateSucce(int score,int id);
       void updateEchec(int score,int id);
}
