package com.goutham.simple;

import java.util.HashMap;
import java.util.Map;
 class RegistrationDAO {

  private final Map<String, User> userDatabase = new HashMap<>();

  public User addUser(User user){
      return userDatabase.put(user.username(),user);
  }

  public boolean isUserExists(String username){
      return userDatabase.containsKey(username);
  }

  public User findByUserEmail(String username){
      if(!isUserExists(username)){
          throw new UserNotFoundException(String.format("User with username %s is not found ",username));
      }
      return userDatabase.get(username);
  }
}
