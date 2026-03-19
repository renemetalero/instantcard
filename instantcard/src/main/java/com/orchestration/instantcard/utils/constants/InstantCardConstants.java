package com.orchestration.instantcard.utils.constants;

import java.math.BigDecimal;
import java.util.List;

public class InstantCardConstants {

    private InstantCardConstants() {
        throw new IllegalStateException(CommonConstants.ILLEGAL_STATE_EXCEPTION_MSG);
    }

    public static final String FISERV_TOKEN_CACHE_NAME = "authTokenFiserv";

    //enums string start
    public static final String TITLE_ERROR = "Orquestador";
    public static final String TITLE_ERROR_FIELDS = "Orquestador - Field error";
    public static final String MESSAGE_CUSTOM_ERROR = "Error: %s";
    //enums string end

    //fields error response start
    public static final String DATA_VALIDATE = "Validacion de campos";
    //fields error response end

    //fields metadata start
    public static final String TAG_RESPONSE = "Response";
    public static final String API_ID = "InstantCard";
    //fields metadata end

    //fields header start
    public static final String ZEROTO255 = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    public static final String IPV4_REGEXP = "^" + ZEROTO255 + "\\." + ZEROTO255 + "\\." + ZEROTO255 + "\\." + ZEROTO255 + "$";
    //fields header end

    public static final String BUSINESS_EXC_SEND_MESSAGE = "Exception with senderToQueue";

    public static final String PRODUCT_TYPE_TCD = "TCD";
    public static final Long VALID_ORGANIZATION_ORG_BA = 540l;

    public static final String X_CLIENTID = "00540";

    public static final List<String> LIST_PRODUCT_TYPE = List.of(PRODUCT_TYPE_TCD);

    public static final List<Long> LIST_ORGANIZATION = List.of(VALID_ORGANIZATION_ORG_BA);

    public static final String CONNECTION_FAILED = "Connection failed with %s";

    public static final List<String> LIST_NOT_ERROR_CMC = List.of("134", "135", "EL LOGO NO ES VALIDO.", "0601");


    public static final Integer ZERO = 0;
    public static final Integer ONE = 1;
    public static final Integer TWO = 2;

    public static final Integer THREE = 3;
    public static final Integer THIRTY = 30;

    public static final String BUSINESS_EXC_RECEIEVE_MESSAGE = "Exception with receiverFormQueue";

    public static final String SUCCESS = "Transacción exitosa";

    public static final Integer ORGANIZATION = 540;

    public static final String SLV = "SLV";

    public static final String Y = "Y";
    public static final String N = "N";

    public static final String EXIST_CLIENT_VP = "VPL5SNA24S";

    // InstantCard Fiserv Request
    public static final Integer CARD_ACTION_FLAG = 1;
    public static final Integer DIGITAL_CARD_FLAG = 1;
    public static final String FIRST_PURCHASE_FLAG = "N";
    public static final Integer POS_SERVICE_CODE = 201;
    public static final Integer ORGANIZATION_NUMBER = 540;
    public static final Integer SAME_DAY_PROCESSING_TYPE = 1;
    public static final Integer CARD_TECHNOLOGY = 3;
    public static final Long AUTHORIZATION_LIMIT_OVERRIDES =  null;

    public static final Boolean INSTANT_CARD_RESPONSE_OK = true;
    public static final Boolean INSTANT_CARD_RESPONSE_ERROR = false;

    public static final String INSTANT_CARD_ERROR_CMC = "430";
    public static final String INSTANT_CARD_ERROR = "400";
    public static final String INSTANT_CARD_ERROR_FISERV = "460";
    public static final String CREATE_INSTANT_CARD_ERROR_MESSAGE = "Error al crear tarjeta digital";

    public static final String INSERT_INSTANT_CARD_ERROR_MESSAGE = "Error insertando tarjeta en la CMC";
    public static final String INSERT_INSTANT_CARD_ERROR_SERVICE_MESSAGE = "Error con el servicio para insertar tarjeta en la CMC";
    public static final String INSTANT_CARD_SERVICE_ERROR_MESSAGE = "Error al obtener información de request";

    public static final String TOKEN_FISERV_ERROR_MESSAGE = "Error al obtener el token a Fiserv";

    public static final String IBS_DATA_NOT_FOUND = "No se encontró data en Ibs";

    public static final String CMC_RESPONSE_CODE_OK = "Ok";

    public static final String DATE_FORMAT_ISO_8601= "yyyyMMdd";

    public static final BigDecimal CARD_SEQUENCE = new BigDecimal("1");

    public static final String CARD_HOLDER_TYPE = "1";

    public static final String USER_CREATE_CMC_INSTANT_CARD = "instant_card";

    public static final Long CMC_INSTANT_CARD_CHANNEL_ID = 55555l;

    public static final String DEFAULT_CODE = "0";
    public static final boolean FIELD_ACCESIBLE = true;
    public static final boolean FIELD_NOT_ACCESIBLE = true;

    public static final String EMPTY_STRING = "";
    public static final String CARD_NOT_FOUND = "Not found card";

    public static final String FISERV_ERROR_500_MSG = "500 Internal Server Error:";

}
