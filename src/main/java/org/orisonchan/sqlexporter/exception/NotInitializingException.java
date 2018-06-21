package org.orisonchan.sqlexporter.exception;

public class NotInitializingException extends Exception {

  private String msg;

  public NotInitializingException(String msg) {
    this.msg = msg;
  }

}
