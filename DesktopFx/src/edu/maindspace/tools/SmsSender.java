/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.maindspace.tools;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author mohamedbouslah
 */
public class SmsSender {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "ACebffadd6502785d88d8238a17d1bf7c2";
    public static final String AUTH_TOKEN =
            "97b814e520ca6bcfb0337dbcecd2c473";


    public void send(String s,String x){
         Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      String y="+216"+x;
        Message message = Message 
                .creator(new PhoneNumber(y), // to
                        new PhoneNumber("+12014742109"), // from
                       ""+s)
                .create();
  System.out.println("aaslema");
        System.out.println(message.getSid());
    }
}
