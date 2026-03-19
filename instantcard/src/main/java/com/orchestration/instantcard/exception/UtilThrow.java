package com.orchestration.instantcard.exception;

import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.models.generals.ResponseModel;
import com.orchestration.instantcard.utils.constants.CommonConstants;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import com.orchestration.instantcard.utils.Utility;

import java.util.Objects;

public class UtilThrow {
    private UtilThrow() {
        throw new IllegalStateException(CommonConstants.ILLEGAL_STATE_EXCEPTION_MSG);
    }

    public static <T extends ResponseModel> void throwExc(InstantCardEnumError enums, T responseService) {
        if(Objects.isNull(responseService)) {
            throwExcDetail(enums.getCode(), enums, String.format(InstantCardConstants.CONNECTION_FAILED,enums.getTitle()));
        }

        if(Objects.isNull((responseService.getData()))){
            throw new BusinessException(enums.getCode(), responseService.getErrors().subList(0,1));
        }
    }

    public static void throwExcDetail(String code, InstantCardEnumError enums, String details) {
        throw new BusinessException(code, Utility.buildCustomErrors(enums, details));
    }

}
