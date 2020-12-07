package com.ss.covidupdate.controller;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**+
 * Twilio whatsapp message controller
 *
 */
public class SmsController {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "AC1fe5bb96d3874db1e84021e29c6378bc";
    public static final String AUTH_TOKEN =
            "5a85f5c899a708951470b4178be8193e";

    public static void sendMessage(String response){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        try {
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("whatsapp:+919620100305"),
                    new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                    response)
                    .create();

            System.out.println(message.getSid());
        }catch (ApiException e){
            System.out.println("Exception " + e.getMessage());
        }

    }
}
