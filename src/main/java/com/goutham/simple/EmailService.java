package com.goutham.simple;

public class EmailService {
    public void sendRegistrationEmail(String email, String username) {
    System.out.printf(String.format("Email for user with username %s sent to email : %s%n",email,username));
    }
}
