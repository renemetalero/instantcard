package com.orchestration.instantcard.service.ibs;

public interface IbsService<T,R> {
    T validateDataIbs(R data, Class<T> responseClass);
}
