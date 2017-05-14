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
public interface IService <T,R>{
    void add(T t);

    void update(T t);

    void delete(R id);

    List<T> getAll();

    T findById(R id);
    void addAdmin (T t);
}
