/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

/**
 *
 * @author OrbitG
 */
public class sms {
    
     public static final String ACCOUNT_SID = "ACb6b533b08eb122b9ba8e2e15a87a7d0c"; 
    public static final String AUTH_TOKEN = "096d74e72bb49721c330e8dde6449d9e"; 
 
      public static void sendsms(){
          
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message = Message.creator(
      new com.twilio.type.PhoneNumber("whatsapp:+21652320001"),
      new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
      "Bonjour Mr, vous vous trouvez dans notre site E-FIT comme transporteur.")

    .create();

    System.out.println(message.getSid());
  }
    
}
