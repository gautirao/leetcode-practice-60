package com.goutham.simple;

public class RegistrationService {
    private final RegistrationDAO registrationDAO;
    private final EmailService emailService;

    public RegistrationService(final RegistrationDAO registrationDAO,final EmailService emailService) {
        this.registrationDAO = registrationDAO;
        this.emailService = emailService;
    }

    public boolean registerUser(String username,String password, String email){
        if(registrationDAO.isUserExists(username)){
            return false;
        }
        if(!PasswordValidator.isValidPassword(password))
            throw new PasswordFormatException(String.format("Password: %s is not of correct format", password));
        User user = new User(username,password,email);
        registrationDAO.addUser(user);
        emailService.sendRegistrationEmail(email, username);
        return true;
    }
}
