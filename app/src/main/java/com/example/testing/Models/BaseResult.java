package com.example.testing.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class BaseResult<T> {

    @SerializedName("Data")
    @Expose
    private T Data;

    @SerializedName("statusCode")
    @Expose
    private String StatusCode;

    @SerializedName("message")
    @Expose
    private String Message;

    public BaseResult() {
    }

    public BaseResult(T data, String statusCode, String message) {
        Data = data;
        StatusCode = statusCode;
        Message = message;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String statusCode) {
        StatusCode = statusCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }


    @Override
    public String toString() {
        return "BaseResult{" +
                "Data=" + Data +
                ", StatusCode='" + StatusCode + '\'' +
                ", Message='" + Message + '\'' +
                '}';
    }
}
