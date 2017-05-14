/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhany.Services.interfaces;

import pidevhany.Models.pi_user;

/**
 *
 * @author HANY
 */
public interface IUserService extends IService<pi_user, Integer>{
    Boolean authentication(String username , String password);
    public boolean RechLogin(String text);
    public pi_user getUser(int id);
}
