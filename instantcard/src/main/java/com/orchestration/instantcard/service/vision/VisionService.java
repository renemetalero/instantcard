package com.orchestration.instantcard.service.vision;

public interface VisionService<T, R> {
    T consumeVisionCustomerAdd(R data, Class<T> responseClass);
}
