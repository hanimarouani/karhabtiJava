/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karhabti.interfaces;
import Karhabti.modeles.User;
/**
 *
 * @author Mohamed
 */
public interface Iuser extends Iservices<User, Integer, String> {
   Boolean authentication(String username , String password);
}
