/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhany.Services.interfaces;

import java.util.List;

/**
 *
 * @author HANY
 */
public interface Itests<T,R> {
      void ajouterTest(T t);
    void modifierTest(T t,R id);
    void supprimerTest(R id);
    List<T> getAllTest();
    List<T> getAllTestNiveau(R niveau);
    void getField(String table,String culumn,int niveau);
    List<String> getAllResponses(int niv);
}
