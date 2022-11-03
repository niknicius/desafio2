package tech.dock.paymentapi.core.dto;

import java.util.List;

public class ErrorDto {
    private String message;
    private Integer status;
    private Long timestamp;
    private List<String> errors;

    private ErrorDto() {

    }

    public static ErrorDto newBuilder(){
        return new ErrorDto();
    }

    public ErrorDto message(String message) {
        this.message = message;
        return this;
    }

    public ErrorDto status(Integer status){
        this.status = status;
        return this;
    }

    public ErrorDto timestamp(Long timestamp){
        this.timestamp = timestamp;
        return this;
    }

    public ErrorDto errors(List<String> errors){
        this.errors = errors;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public List<String> getErrors() {
        return errors;
    }
}
