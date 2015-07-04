package com.coffeebeans.services.api.error;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muhamadto on 12/07/2015.
 */
@Data
public class ErrorResponse {

    private String errorCode;
    private String consumerMessage;
    private String applicationMessage;
    private List<ValidationError> validationErrors = new ArrayList<>();

    public ErrorResponse(){}

    public ErrorResponse(String errorCode, String consumerMessage,  String applicationMessage){
        this(errorCode, consumerMessage, applicationMessage, null);
    }

    public ErrorResponse(String errorCode, String consumerMessage,  String applicationMessage, List<ValidationError> validationErrors){
        this.errorCode = errorCode;
        this.consumerMessage = consumerMessage;
        this.applicationMessage = applicationMessage;
        this.validationErrors = validationErrors;
    }
}
