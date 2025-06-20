package com.goutham.simple;

import java.util.Objects;

public class PasswordValidator {

  public static final int MIN_PASSWORD_LENGTH = 8;

  public static boolean isValidPassword(String password) {
    if (Objects.isNull(password ) || password.length() < MIN_PASSWORD_LENGTH) {
      return false;
    }
    boolean hasUpper = false;
    boolean hasLower = false;
    boolean hasDigit = false;
    boolean hasSpecialCharacter = false;
    boolean hasWhiteSpace = false;

    for (Character c : password.toCharArray()) {
      if (Character.isDigit(c)) hasDigit = true;
      else if (Character.isUpperCase(c)) hasUpper = true;
      else if (Character.isLowerCase(c)) hasLower = true;
      else if (Character.isWhitespace(c)) hasWhiteSpace = true;
      else if ("@#$%^&+=".indexOf(c) >= 0) hasSpecialCharacter = true;
    }
    return hasUpper && hasLower && hasDigit && hasSpecialCharacter && !hasWhiteSpace;
  }
}
