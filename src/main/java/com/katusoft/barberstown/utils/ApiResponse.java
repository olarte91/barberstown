package com.katusoft.barberstown.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
  private int status;
  private String message;
  private T data;

  public ApiResponse(int status, String message, T data) {
    this.status = status;
    this.message = message;
    this.data = data;
  }

  public static <T> ApiResponse<T> success(T data){
    return new ApiResponse<>(200, "Success", data);
  }

  public static <T> ApiResponse<T> success(String message, T data){
    return new ApiResponse<>(200, message, data);
  }

  public static <T> ApiResponse<T> error(int status, String message){
    return new ApiResponse<>(status, message, null);
  }
}
