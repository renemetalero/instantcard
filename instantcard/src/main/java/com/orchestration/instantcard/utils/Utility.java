package com.orchestration.instantcard.utils;

import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.exception.models.ValidationsException;
import com.orchestration.instantcard.utils.constants.CommonConstants;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Utility {

    private Utility() {
        throw new IllegalStateException(CommonConstants.ILLEGAL_STATE_EXCEPTION_MSG);
    }

    public static boolean isNumeric(String value) {
        return (value!= null && value.matches("\\d+"));
    }
    public static List<ErrorModel> buildCustomErrors(InstantCardEnumError enume, String... details) {
        return buildCustomErrors(buildCustomError(enume,details));
    }
    public static List<ErrorModel> buildCustomErrors(Object obj){

        List<ErrorModel> listEm = new ArrayList<>();

        if(obj instanceof ErrorModel object)
            listEm.add(object);

        else listEm.add(new ErrorModel(
                InstantCardEnumError.CUSTOM_ERROR_API.getCode(),
                InstantCardEnumError.CUSTOM_ERROR_API.getTitle(),
                String.format(obj.toString(),InstantCardEnumError.CUSTOM_ERROR_API.getMessage())));

        return listEm;
    }

    public static ErrorModel buildCustomError(InstantCardEnumError enume, String... details){

        return new ErrorModel(enume, String.format(enume.getMessage(), (Object[]) details));
    }

    public static void senderError(InstantCardEnumError enume, String... detail) {
        throw new ValidationsException(
                buildCustomErrors(
                        buildCustomError(
                                enume, detail)
                ));
    }

    public static String validateString(String validate, String defailt){
        return (!Objects.isNull(validate) && validate.length()>0)
                ? validate
                : defailt;
    }

    public static String getLocalDateNowISO(){
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        return today.format(formatter);
    }

    public static String formatCreditLimit(BigDecimal creditLimit, Integer zeros){
        String pattern = "0." + "0".repeat(Math.max(0, zeros)) + "################";
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(creditLimit);
    }

}
