/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karhabti.interfaces;

import java.util.List;

/**
 *
 * @author Mohamed
 */
public interface Iservices <T,R,D> {
    
    void ajouter(T t);
    void modifier(T t);
    void supprimer(R id);
    List<T> getAll();
     T findById(R id);
    
}
