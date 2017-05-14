/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karhabti.interfaces;

import java.util.List;

/**
 *
 * @author ELYES
 * @param <A>
 * @param <B>
 */
public interface IService<A , B> {
    
    void add(A a);

    void update(A a);
    
    void updateoffre(B id);

    void delete(B id);

    List<A> getAll();

    A findById(B id);
    
}
