package com.orchestration.instantcard.utils.validations;

import static com.orchestration.instantcard.utils.Utility.*;

import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.exception.models.ValidationsException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ValidationsModels {

    @Value("${constraint-validator-payload}")
    private String constraintValidatorPayload;

    public void validateModel(Object object) {
        List<ErrorModel> errors = new ArrayList<>();

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
