/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karhabti.interfaces;

import Karhabti.modeles.Pi_User;

/**
 *
 * @author ELYES
 */
public interface Ipi_userService extends IService<Pi_User, Integer> {
    
    Boolean authentication (String username,String password);
    Boolean RechLogin(String text);
    
}
