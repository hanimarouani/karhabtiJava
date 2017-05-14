
package Karhabti.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class Sms {
    
   
    private String num;
    private String messagetel;

    public Sms(String num, String messagetel) {
        this.num = num;
        this.messagetel = messagetel;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getMessagetel() {
        return messagetel;
    }

    public void setMessagetel(String messagetel) {
        this.messagetel = messagetel;
    }
           

    public Sms() { 

    }
    
    
  
    
}