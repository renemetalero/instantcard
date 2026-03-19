package com.orchestration.instantcard.validate;

import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.exception.models.ValidationsException;

import com.orchestration.instantcard.models.request.InstantCardRequest;
import com.orchestration.instantcard.models.request.InstantCardRequestBody;
import com.orchestration.instantcard.utils.InjectionValidator;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import java.util.ArrayList;
import java.util.Set;
import static com.orchestration.instantcard.utils.Utility.isNumeric;

@Component
@RequestScope
public class ValidateModels {

    @Value("${constraint-validator-payload}")
    private String constraintValidatorPayload;

    private ManualValidationProcess manualValidationProcess;

    public ValidateModels(ManualValidationProcess manualValidationProcess) {
        this.manualValidationProcess = manualValidationProcess;
    }

    public void validateModel(Object object) {
        var errors = new ArrayList<ErrorModel>();

        Validator validator = createValidator(this.constraintValidatorPayload);

        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        for (ConstraintViolation<?> violation : violations) {
            ErrorModel error = new ErrorModel();
            error.setCode(InstantCardEnumError.HTTP_STATUS_BAD_REQUEST_QUERY.getCode());
            error.setTitle(InstantCardEnumError.HTTP_STATUS_BAD_REQUEST_QUERY.getTitle());

            if(isNumeric(violation.getMessage())){

                String field ="";
                for(Path.Node node : violation.getPropertyPath()){
                    field = node.getName();
                }
                error.setDetail(String.format(InstantCardEnumError.findMessageByCode(violation.getMessage()).getMessage(), field));
            }else
                error.setDetail(violation.getMessage());

            errors.add(error);
        }

        if(!errors.isEmpty())
            throw new ValidationsException(errors.get(0));

        InstantCardRequestBody requestBody = ((InstantCardRequest) object).getData().getBody();

        InjectionValidator.validateStringsForInjection(requestBody, InstantCardConstants.FIELD_ACCESIBLE);

        this.manualValidationProcess.validateProcessManual(requestBody);
    }

    private static Validator createValidator(String constraint) {

        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .constraintValidatorPayload(constraint)
                .buildValidatorFactory();

        Validator validator = validatorFactory.getValidator();
        validatorFactory.close();

        return validator;
    }
}
