/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karhabti.interfaces;


import Karhabti.services.Sms;
import Karhabti.services.SmsService;
/**
 *
 * @author ELYES
 */
public interface ISms {
      void sendSms(Sms sms);
}
