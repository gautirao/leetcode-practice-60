package com.goutham.simple;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegistrationServiceTest {

  private RegistrationDAO registrationDAO;
  private RegistrationService service;
  private EmailService emailService;

  @BeforeEach
  void init() {
    registrationDAO = mock(RegistrationDAO.class);
    emailService = mock(EmailService.class);
    service = new RegistrationService(registrationDAO, emailService);
  }

  @Test
  void shouldRegisterNewUserAndSendEmail() {
    String username = "test", password = "Password@1", email = "hello@test.com";

    when(registrationDAO.isUserExists(username)).thenReturn(false);
    boolean result = service.registerUser(username, password, email);
    assertTrue(result);
    verify(registrationDAO).addUser(any(User.class));
    verify(emailService, times(1)).sendRegistrationEmail(email, username);
  }

  @Test
  void shouldThrowExceptionWhenIncorrectPasswordUsed() {
    String username = "test", password = "test", email = "hello@test.com";

    when(registrationDAO.isUserExists(username)).thenReturn(false);
    assertThrows(
        PasswordFormatException.class, () -> service.registerUser(username, password, email));
  }

  @Test
  void shouldNotRegisterUserIfUserAlreadyExists() {
    String username = "test", password = "Password@1", email = "hello@test.com";

    when(registrationDAO.isUserExists(username)).thenReturn(true);
    boolean result = service.registerUser(username, password, email);
    assertFalse(result);
    verify(registrationDAO, never()).addUser(any(User.class));
    verify(emailService, never()).sendRegistrationEmail(anyString(), anyString());
  }
}
