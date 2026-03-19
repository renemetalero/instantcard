package com.orchestration.instantcard.utils.constants;

public class CommonConstants {

    private CommonConstants() {
        throw new IllegalStateException(ILLEGAL_STATE_EXCEPTION_MSG);
    }

    public static final int RETRY_ATTEMPT_COUNT = 5;
    public static final int RETRY_ATTEMPT_DELAY = 1;

    public static final String ILLEGAL_STATE_EXCEPTION_MSG="Utility class";

    public static final String FIELD_NOT_FOUND = "EL campo %s no fue encontrado - error: %s";

    public static final String VAL_ERROR_EN_LOS_DATOS = "error en los datos";

    public static final String EMPTY_STRING = "";
}
