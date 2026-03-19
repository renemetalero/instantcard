package com.orchestration.instantcard.exception.enums;

import java.util.stream.Stream;

import static com.orchestration.instantcard.utils.constants.InstantCardConstants.*;

public enum InstantCardEnumError {
    HTTP_STATUS_BAD_REQUEST_INYECTION("400", "Bad request", "Error, el campo '%s' incluye caracteres o una combinación no permitida"),
    HTTP_STATUS_BAD_REQUEST_QUERY("400", "Error en validacion", "Error en el Request"),
    VALIDATE_FIELD_CODE_NOT_FOUND("0500", TITLE_ERROR+"- Codigo error", "Codigo de error no encontrado"),
    VALIDATE_FIELD_METADATA("0501", TITLE_ERROR+" -Metadata error", "Metadata no puede se nulo"),
    VALIDATE_FIELD_DATA("0502", TITLE_ERROR+" -Data error", "Data no puede se nulo"),
    VALIDATE_FIELD_HEADER("0503", TITLE_ERROR+" -Header error", "Header no puede se nulo"),
    VALIDATE_FIELD_BODY("0504", TITLE_ERROR+" - Body error", "Body no puede se nulo"),
    VALIDATE_FIELD_NOT_NULL("0505", TITLE_ERROR+"", "El campo %s es requerido"),
    VALIDATE_FIELD_NOT_BLANK("0506", TITLE_ERROR_FIELDS, "El campo %s no puede ir vacio"),
    VALIDATE_FIELD_SIZE("0507", DATA_VALIDATE, "El campo %s debe ser de %s caracteres"),
    VALIDATE_FIELD_NOT_IN_LIST("0508", TITLE_ERROR_FIELDS, "El campo %s debe ser de la siguiente lista %s"),
    VALIDATE_EMBLEM_IN_LIST("0508", TITLE_ERROR_FIELDS, "El campo emblemId debe ser %s"),
    VALIDATE_FIELD_MATCH_ERROR("0509", TITLE_ERROR_FIELDS, "El valor del campo %s debe de ser %s"),
    VALIDATE_FIELD_DATA_NOT_VALID("0510", TITLE_ERROR_FIELDS, "El valor del campo %s no es valido"),
    DATA_NOT_FOUND("0600", TITLE_ERROR_FIELDS, "No se encontraron datos, con los parametros solicitados"),
    BUSINESS_EXCEPTION("0650", TITLE_ERROR+" - Reglas de Negocio", "Error en el Procesamiento de las reglas de Negocio"),
    CUSTOM_ERROR_API("0700", TITLE_ERROR+" - Error de validacion", MESSAGE_CUSTOM_ERROR),

    EXIST_CLIENT_VP("VPL5SNA24S", "Vision Plus- Customer Exist", "Customer Exist"),

    BUSINESS_CMC("430", "CMC - Service", MESSAGE_CUSTOM_ERROR),
    BUSINESS_VISION("460", "VISION PLUS - Service", MESSAGE_CUSTOM_ERROR),

    BUSINESS_FISERV_TOKEN("460", "FISERV TOKEN - Service", MESSAGE_CUSTOM_ERROR),

    BUSINESS_INSTANT_CARD("400", "INSTANT CARD - Service", MESSAGE_CUSTOM_ERROR),

    BUSINESS_VISION_FISERV("460", "VISION PLUS FISERV - Service", MESSAGE_CUSTOM_ERROR),
    BUSINESS_IBS("440", "IBS - Service", MESSAGE_CUSTOM_ERROR),
    BUSINESS_EMBOSSER_UPDATE("440", "EMBOSSER UPDATE - Service", MESSAGE_CUSTOM_ERROR);


    private final String code;
    private final String title;
    private final String message;

    InstantCardEnumError(String code, String title, String message) {
        this.code = code;
        this.title = title;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public static InstantCardEnumError findMessageByCode(String code) {
        return stream().filter(
                        obj -> obj.getCode().equals(code))
                .findAny().orElse(VALIDATE_FIELD_CODE_NOT_FOUND);
    }

    public static Stream<InstantCardEnumError> stream() {
        return Stream.of(InstantCardEnumError.values());
    }
}
