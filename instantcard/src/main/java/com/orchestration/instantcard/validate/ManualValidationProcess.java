package com.orchestration.instantcard.validate;

import com.orchestration.instantcard.models.request.InstantCardRequestBody;

public interface ManualValidationProcess {
    void validateProcessManual(InstantCardRequestBody body);
}
