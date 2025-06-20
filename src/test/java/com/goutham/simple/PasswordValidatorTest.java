package com.goutham.simple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PasswordValidatorTest {
  /*
  Minimum 8 characters

  At least one uppercase letter

  At least one lowercase letter

  At least one digit

  At least one special character (e.g., @#$%^&+=)

  No whitespace
  * */
  @ParameterizedTest(name = "Password: {0} should be valid {1}")
  @CsvSource({
    "Pass,false",
    "Password, false",
    "Password1@, true",
    "password1@, false",
    "Password@,false",
    "PASSWORD@1,false",
    "'',false",
    "'        ',false"
  })
  void testValidPassword(String password, boolean expected) {
    Assertions.assertEquals(expected, PasswordValidator.isValidPassword(password));
  }
}
