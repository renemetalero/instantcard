package com.orchestration.instantcard.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestsConstants {
    public static final String CUSTOMER_NUMBER_TEST = "Test message";

    public static final String SERVICE_ID_IBS = "9000635";
    public static final String SERVICE_ID_IBS_2 = "0000000";

    public static final String RECEIVER_IBS = "babroker.to.test.test.rsp.lq";
    public static final String RECEIVER_VISION = "alternative.to.test.test.rsp.lq";


    public static final String INTERNAL_SERVER_ERROR = "500";
    public static final String BUSSINESS_ERROR_CODE_ZERO = "0";
    public static final String QUEUE_SENDER_ERROR_MESSAGE = "Exception with senderToQueue";
    public static final String QUEUE_RECEIVER_ERROR_MESSAGE = "Exception with receiverFormQueue";

    public static final String CREATE_FISERV_ERROR = "Error creating fiserv";

    public static final String EMBOSSER_UPDATE_URL_TEST = "http://test.com/embosser-update";

}
