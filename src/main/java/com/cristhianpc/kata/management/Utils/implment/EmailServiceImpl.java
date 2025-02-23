package com.cristhianpc.kata.management.Utils.implment;

import com.cristhianpc.kata.management.Utils.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Component
public class EmailServiceImpl implements IEmailService {

    private final static String MY_EMAIL = "m11mario11m@gmail.com";

    @Autowired
    private SesClient sesClient;

    @Override
    public String sendEmailTicket(String to_email,Integer seat) {
        try {
            SendEmailRequest request = SendEmailRequest.builder()
                    .destination(Destination.builder().toAddresses(to_email).build())
                    .message(Message.builder()
                            .subject(Content.builder().data("Asignacion de puesto para la funcion").build())
                            .body(Body.builder()
                                    .html(Content.builder().data("ticket generado ==> asiento asignado : "+seat).build())
                                    .build())
                            .build())
                    .source(MY_EMAIL)
                    .build();

            sesClient.sendEmail(request);
            return "The seat was assignment correctly.";
        } catch (SesException e) {
            return "Error try to send email: " + e.awsErrorDetails().errorMessage();
        }

    }
}
