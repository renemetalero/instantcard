package com.orchestration.instantcard.validate;

import com.orchestration.instantcard.components.cmc.CatalogsComponent;
import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.models.request.InstantCardRequestBody;
import com.orchestration.instantcard.utils.*;
import com.orchestration.instantcard.utils.constants.FieldInstantCardConstants;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ManualValidationProcessImpl implements ManualValidationProcess{

    private CatalogsComponent catalogsComponents;

    public ManualValidationProcessImpl(CatalogsComponent catalogsComponents) {
        this.catalogsComponents = catalogsComponents;
    }
    private void validateFieldSizeLimit(InstantCardRequestBody body) {
        RangeValidation[] paramRangeValidation = {
                new RangeValidation(FieldInstantCardConstants.TEMPORARY_CREDIT_LIMIT.getField(),body.getTemporaryCreditLimit(), FieldInstantCardConstants.TEMPORARY_CREDIT_LIMIT.getLimit()),
                new RangeValidation(FieldInstantCardConstants.CREDIT_LIMIT.getField(), body.getCreditLimit(), FieldInstantCardConstants.CREDIT_LIMIT.getLimit()),
                new RangeValidation(FieldInstantCardConstants.RELATION_SHIP_NUMBER.getField(), body.getRelationshipNumber(), FieldInstantCardConstants.RELATION_SHIP_NUMBER.getLimit()),
                new RangeValidation(FieldInstantCardConstants.ATM_CASH_AMOUNT.getField(), body.getAtmCashAmount(), FieldInstantCardConstants.ATM_CASH_AMOUNT.getLimit()),
                new RangeValidation(FieldInstantCardConstants.ATM_CASH_SINGLE_TRANSACTION_LIMIT.getField(), body.getAtmCashSingleTransactionLimit(), FieldInstantCardConstants.ATM_CASH_SINGLE_TRANSACTION_LIMIT.getLimit()),
                new RangeValidation(FieldInstantCardConstants.OVER_COUNTER_CASH_AMOUNT.getField(), body.getOverTheCounterCashAmount(), FieldInstantCardConstants.OVER_COUNTER_CASH_AMOUNT.getLimit()),
                new RangeValidation(FieldInstantCardConstants.OVER_COUNTER_CASH_SINGLE_TRANSACTION_LIMIT.getField(), body.getOverTheCounterCashSingleTransactionLimit(), FieldInstantCardConstants.OVER_COUNTER_CASH_SINGLE_TRANSACTION_LIMIT.getLimit()),
                new RangeValidation(FieldInstantCardConstants.RETAIL_PURCHASE_AMT.getField(), body.getRetailPurchaseAmt(), FieldInstantCardConstants.RETAIL_PURCHASE_AMT.getLimit()),
                new RangeValidation(FieldInstantCardConstants.RETAIL_PURCHASE_SINGLE_TRANSACTION_LIMIT.getField(), body.getRetailPurchaseSingleTransactionLimit(), FieldInstantCardConstants.RETAIL_PURCHASE_SINGLE_TRANSACTION_LIMIT.getLimit()),
                new RangeValidation(FieldInstantCardConstants.INTERNET_PURCHASE_AMOUNT.getField(), body.getInternetPurchaseAmount(), FieldInstantCardConstants.INTERNET_PURCHASE_AMOUNT.getLimit()),
                new RangeValidation(FieldInstantCardConstants.INTERNET_PURCHASE_SINGLE_TRANSACTION_LIMIT.getField(), body.getInternetPurchaseSingleTransactionLimit(), FieldInstantCardConstants.INTERNET_PURCHASE_SINGLE_TRANSACTION_LIMIT.getLimit())
        };
        Arrays.stream(paramRangeValidation).forEach(this::rangeValidationBigNumber);
    }

    private void validateInList(String field, Long value, List<Long> list){
        if(!list.contains(value)) {
            Utility.senderError(InstantCardEnumError.VALIDATE_FIELD_NOT_IN_LIST,field,list.stream().map(String::valueOf).collect(Collectors.joining(",")));
        }
    }

    private boolean validateRangeError(String value, RangeValidation rangeValidation) {
        return !(value.length() >= rangeValidation.getLimit()[0] && value.length() <= rangeValidation.getLimit()[1]);
    }

    private String determineRangeValidationValue(RangeValidation rangeValidation) {
        if (Objects.nonNull(rangeValidation.getValueDecimal())) {
            return String.valueOf(rangeValidation.getValueDecimal());
        }
        return rangeValidation.getValue() != null ? String.valueOf(rangeValidation.getValue()) : "";
    }

    private void rangeValidationBigNumber(RangeValidation rangeValidation){
        String rangeValidationValue = determineRangeValidationValue(rangeValidation);
        if(this.validateRangeError(rangeValidationValue, rangeValidation))
            Utility.senderError(
                    InstantCardEnumError.VALIDATE_FIELD_SIZE,
                    rangeValidation.getField(),
                    String.valueOf(rangeValidation.getLimit()[1])
            );
    }

    private void validateEmblem(String emblemId) {
        if(Objects.isNull(emblemId) || emblemId.length() != FieldInstantCardConstants.EMBLEM_ID.getLimit()[0])
            Utility.senderError(
                    InstantCardEnumError.VALIDATE_FIELD_SIZE,
                    FieldInstantCardConstants.EMBLEM_ID.getField(),
                    String.valueOf(FieldInstantCardConstants.EMBLEM_ID.getLimit()[0])
            );
    }

    private void validateTcd(InstantCardRequestBody body) {

        if (Objects.isNull(body.getLogo()) || body.getLogo().isBlank())
            Utility.senderError(InstantCardEnumError.VALIDATE_FIELD_NOT_NULL, FieldInstantCardConstants.LOGO.getField());

        if (Objects.isNull(body.getPct()) || body.getPct().isBlank())
            Utility.senderError(InstantCardEnumError.VALIDATE_FIELD_NOT_NULL, FieldInstantCardConstants.PCT.getField());

        if(Objects.isNull(body.getCreditLimit()) || body.getCreditLimit().compareTo(BigDecimal.ZERO) < 0) {
            Utility.senderError(InstantCardEnumError.VALIDATE_FIELD_NOT_NULL, FieldInstantCardConstants.CREDIT_LIMIT.getField());
        }
    }

    private void validateProductTypeExists(InstantCardRequestBody body){
        if (body.getProductType() != null && !body.getProductType().isBlank()) {
            if (!InstantCardConstants.LIST_PRODUCT_TYPE.contains(body.getProductType().toUpperCase()))
                Utility.senderError(InstantCardEnumError.VALIDATE_FIELD_NOT_IN_LIST, FieldInstantCardConstants.PRODUCT_TYPE.getField(), InstantCardConstants.LIST_PRODUCT_TYPE.toString());

            this.validateTcd(body);
        }
    }

    private void validateCycle(Long cycle){
        if(Objects.isNull(cycle))
            Utility.senderError(InstantCardEnumError.VALIDATE_FIELD_NOT_NULL, FieldInstantCardConstants.CYCLE.getField());

        if (cycle < InstantCardConstants.ONE || cycle > InstantCardConstants.THIRTY)
            Utility.senderError(InstantCardEnumError.VALIDATE_FIELD_MATCH_ERROR,"cycle es de tipo numerico", "entre 1 a 30");

        if(!this.catalogsComponents.getValidateCatalogCycle(cycle))
            Utility.senderError(InstantCardEnumError.VALIDATE_FIELD_DATA_NOT_VALID, FieldInstantCardConstants.CYCLE.getField());
    }

    public void validateProcessManual(InstantCardRequestBody body){
        this.validateFieldSizeLimit(body);
        this.validateInList(FieldInstantCardConstants.ORGANIZATION.getField(), body.getOrganization(), InstantCardConstants.LIST_ORGANIZATION);
        this.validateEmblem(body.getEmblemId());
        this.validateProductTypeExists(body);
        this.validateCycle(body.getCycle());
    }
}
