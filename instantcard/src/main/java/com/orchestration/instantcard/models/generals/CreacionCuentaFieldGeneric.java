package com.orchestration.instantcard.models.generals;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Valid
@Getter
@Setter
public class CreacionCuentaFieldGeneric {

	@Max(value = 999, message = "El campo logo es de tipo numerico entre 1 a 999")
	private Long logo;

	@Size(max = 3, message = "El campo currencyCode debe ser de {max} caracteres")
	@NotNull(message = "0505")
	private String currencyCode;

	@Size(min = 19, max = 19, message = "El campo customerNumber debe tener {max} caracteres")
	@NotNull(message = "0505")
	private String customerNumber;

	private Long cycle;

	@Size(min = 3, max = 3, message = "El campo pct debe ser de {max} caracteres")
	private String pct;

	@Min(value = 1, message = "El campo owningBranch es de tipo numerico entre 1 a 999999999")
	@Max(value = 999999999, message = "El campo owningBranch es de tipo numerico entre 1 a 999999999")
	@NotNull(message = "0505")
	private Long owningBranch;

	@Max(value = 9, message = "El campo ddPaymentType es de tipo numerico entre 0 hasta {value}")
	private Long ddPaymentType;

	@Max(value = 99, message = "El campo customerSelectedDueDate es de tipo numerico entre 0 hasta {value}")
	@NotNull(message = "0505")
	private Long customerSelectedDueDate;

	@Digits(integer = 17, fraction = 0, message = "El campo temporaryCreditLimit es de tipo numerico de 17 digitos")
	private BigDecimal temporaryCreditLimit;

	@Digits(integer = 7, fraction = 0, message = "El campo temporaryCreditLimitExpDt es de tipo numerico de 7 digitos")
	private Long temporaryCreditLimitExpDt;

	@Max(value = 99999, message = "El campo accountCashPlanNumber es de tipo numerico entre 0 hasta {value}")
	private Long accountCashPlanNumber;

	@Max(value = 99999, message = "El campo accountRetailPlanNumber es de tipo numerico entre 0 hasta {value}")
	private Long accountRetailPlanNumber;

	@Max(value = 99999, message = "El campo accountPromoPlanNumber es de tipo numerico entre 0 hasta {value}")
	private Long accountPromoPlanNumber;

	@Size(min = 0, max = 19, message = "El campo directDebitAccount es de tipo numerico entre {min} hasta {max}")
	private String directDebitAccount;

	@Size(message = "El campo ddAccountType debe ser de {max} caracteres")
	private String ddAccountType;

	private String customerNameLine1;
	private String customerNameLine2;
	private String customerNameLine3;
	private String customerAddress1;
	private String customerAddress2;
	private String customerAddress3;
	private String customerAddress4;
	private String customerCity;
	private String customerState;
	private String postalCode;

	private String maritalStatus;
	private String nameMagneticStripe;
	private String lastNameMagneticStripe;
	private Integer genderCode;
	private String customerType;
	private Integer dateOfBirth ;
	private String homePhoneNumber;
	private String faxNumber;
	private String mobilePhoneNumber;
	private Integer flagIdentificationNumber;
	private String identificationNumber;
	private String customerEmployer;
	private String userDefined12;

}
