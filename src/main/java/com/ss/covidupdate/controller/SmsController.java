package com.ss.covidupdate.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsController {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "AC1fe5bb96d3874db1e84021e29c6378bc";
    public static final String AUTH_TOKEN =
            "fff6d077e307a53bfe95f677ae2c72b9";

    public static void sendMessage(String response){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+919620100305"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                response)
                .create();

        System.out.println(message.getSid());

    }
}
