package Karhabti.services;

import Karhabti.interfaces.ISms;
import Karhabti.services.Sms;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author ELYES
 */
public class SmsService implements ISms{
 public static final String ACCOUNT_SID = "ACc4e2e8a97ac92126498d85a6eee69541";
 public static final String AUTH_TOKEN = "f0225e1c84f322ad461fba7326e0c2d7";
    
  

    @Override
    public void sendSms(Sms sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message msg = Message.creator(new PhoneNumber(sms.getNum()), new PhoneNumber("+14804282771"), sms.getMessagetel()).create();
            System.out.println(msg.getSid());
    }
    
    
    
}