package com.goutham.simple;

import java.util.Objects;
import java.util.Set;

public class PasswordValidator {

  public static final int MIN_PASSWORD_LENGTH = 8;

  public static boolean isValidPassword(String password) {
    if (Objects.isNull(password ) || password.length() < MIN_PASSWORD_LENGTH) {
      return false;
    }
    Set<Character> specialChars = Set.of('@', '#', '$', '%', '^', '&', '+', '=');

    if (!password.chars().anyMatch(Character::isDigit)) return false;
    if (!password.chars().anyMatch(Character::isUpperCase)) return false;
    if (!password.chars().anyMatch(Character::isLowerCase)) return false;
    if (password.chars().anyMatch(Character::isWhitespace)) return false;
    if (!password.chars().anyMatch(specialChars::contains)) return false;

    return true;
  }
}
