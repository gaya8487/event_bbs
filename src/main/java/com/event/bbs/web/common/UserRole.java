package com.event.bbs.web.common;


public enum UserRole {

  ADMIN("ROLE_ADMIN"),
  USER("ROLE_USER");

  private final String value;

  UserRole(String roleUser) {

    this.value = roleUser;
  }

  public String getValue(){
    return value;
  }
}
