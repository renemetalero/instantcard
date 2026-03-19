package com.orchestration.instantcard.service.cmc;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.orchestration.instantcard.context.ApiContext;
import com.orchestration.instantcard.mapper.CmcInstantCardMapper;
import com.orchestration.instantcard.models.request.InstantCardRequest;
import com.orchestration.instantcard.models.request.messages.cmc.catalog.CatalogBody;
import com.orchestration.instantcard.models.request.messages.cmc.catalog.CatalogData;
import com.orchestration.instantcard.models.request.messages.cmc.catalog.CatalogReq;
import com.orchestration.instantcard.models.request.messages.cmc.createcard.CmcInstantCardRequest;
import com.orchestration.instantcard.models.request.messages.cmc.createcard.CmcInstantCardRequestBody;
import com.orchestration.instantcard.models.request.messages.cmc.createcard.CmcInstantCardRequestDTO;
import com.orchestration.instantcard.models.request.messages.cmc.createcard.CmcInstantCardRequestData;
import com.orchestration.instantcard.models.request.messages.cmc.customer.CreacionClienteRequest;
import com.orchestration.instantcard.models.response.messages.cmc.catalog.CatalogRes;
import com.orchestration.instantcard.models.response.messages.cmc.createcard.CmcInstantCardResponse;
import com.orchestration.instantcard.models.response.messages.cmc.customer.CreacionClienteResponse;
import com.orchestration.instantcard.models.response.messages.cmc.validations.ValidacionTarjetaResponse;
import com.orchestration.instantcard.models.response.messages.vision.card.fiserv.CreacionTarjetaFiservResponseData;
import com.orchestration.instantcard.utils.LoggerObjectUtil;

@Service
public class CmcServiceImpl implements CmcService {
	private CmcClientService<ValidacionTarjetaResponse, InstantCardRequest> cmcValidationTarjeta;
	private CmcClientService<CreacionClienteResponse, CreacionClienteRequest> cmcCreacionCliente;
	private CmcClientService<CatalogRes, CatalogReq> cmcCatalogoService;
	private CmcClientService<CmcInstantCardResponse, CmcInstantCardRequest> cmcInstantCardService;
	private ApiContext apiContext;

	@Value("${cmc-validacion-tarjeta}")
	private String validacionTarjetaCmc;

	@Value("${cmc-creacion-cliente}")
	private String creacionClienteCmc;

	@Value("${cmc-bitacora-tarjeta}")
	private String cmcUrlCreacionBitacora;

	@Value("${catalogo-validaciones-cmc}")
	private String catalogoCmc;

	@Value("${instant-card-cmc}")
	private String instantCardInformationCmcUrl;

	@Value("${instant-card-cmc}")
	private String customerofferUpdate;

	@Value("${instantcard.fountain.value}")
	private String fountain;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public CmcServiceImpl(CmcClientService<ValidacionTarjetaResponse, InstantCardRequest> cmcValidationTarjeta,
			CmcClientService<CreacionClienteResponse, CreacionClienteRequest> cmcCreacionCliente,
			CmcClientService<CatalogRes, CatalogReq> cmcCatalogoService,
			CmcClientService<CmcInstantCardResponse, CmcInstantCardRequest> cmcInstantCardService,
			ApiContext apiContext) {
		this.cmcValidationTarjeta = cmcValidationTarjeta;
		this.cmcCreacionCliente = cmcCreacionCliente;
		this.cmcCatalogoService = cmcCatalogoService;
		this.cmcInstantCardService = cmcInstantCardService;
		this.apiContext = apiContext;
	}

	public ValidacionTarjetaResponse validateDataCmc(InstantCardRequest request) {
		/* Consumo hacia CMC */
		logger.info("Enviando mensaje a CMC - Consultar Cliente");

		cmcValidationTarjeta.setResourceUrlCmc(validacionTarjetaCmc);
		return cmcValidationTarjeta.comsumeCmcService(request, HttpMethod.POST, ValidacionTarjetaResponse.class, true);
	}

	public CreacionClienteResponse crearClienteCmc(CreacionClienteRequest request, boolean sendError) {
		/* Consumo hacia CMC */
		logger.info("Enviando mensaje a CMC - Crear Clientes");
		cmcCreacionCliente.setResourceUrlCmc(creacionClienteCmc);
		return cmcCreacionCliente.comsumeCmcService(request, HttpMethod.POST, CreacionClienteResponse.class, sendError);
	}

	public CatalogRes catalogSearch(String nemonicoTable) {
		cmcCatalogoService.setResourceUrlCmc(catalogoCmc);
		return cmcCatalogoService.comsumeCmcService(this.createInstance(nemonicoTable), HttpMethod.POST,
				CatalogRes.class, true);
	}

	public CmcInstantCardResponse insertInstantCardInformation(InstantCardRequest instantCardRequest,
			CreacionTarjetaFiservResponseData creacionTarjetaFiservResponseData) {
		CmcInstantCardRequestDTO cmcInstantCardRequestDTO = CmcInstantCardMapper.INSTANCE
				.mapModelToCmcInstantCardRequestDTO(instantCardRequest.getData().getBody(),
						creacionTarjetaFiservResponseData);
		CmcInstantCardRequestBody cmcInstantCardRequestBody = CmcInstantCardMapper.INSTANCE
				.mapModelToCmcInstantCardRequestBody(cmcInstantCardRequestDTO);
		CmcInstantCardRequest cmcInstantCardRequest = new CmcInstantCardRequest();
		cmcInstantCardRequest.setMetadata(instantCardRequest.getMetadata());
		CmcInstantCardRequestData cmcInstantCardRequestData = new CmcInstantCardRequestData();
		cmcInstantCardRequestData.setBody(cmcInstantCardRequestBody);
		cmcInstantCardRequest.setData(cmcInstantCardRequestData);
		cmcInstantCardRequest.getData().getBody().setBmUsername(instantCardRequest.getData().getBody().getUserBM());
		if (instantCardRequest.getData().getBody().getEmail() != null) {
			cmcInstantCardRequest.getData().getBody().setEmailSource(fountain);
		}
		if (instantCardRequest.getData().getBody().getPhoneNumber() != null) {
			cmcInstantCardRequest.getData().getBody().setPhoneSource(fountain);
		}
		cmcInstantCardService.setResourceUrlCmc(instantCardInformationCmcUrl);
		LoggerObjectUtil.print("cmcInstantCardRequest", cmcInstantCardRequest);
		return cmcInstantCardService.comsumeCmcService(cmcInstantCardRequest, HttpMethod.POST,
				CmcInstantCardResponse.class, true);
	}

	private CatalogReq createInstance(String nemonicoTable) {
		CatalogReq catalogReq = new CatalogReq();
		catalogReq.setMetadata(apiContext.getMetadata());
		catalogReq.setData(new CatalogData());
		catalogReq.getData().setBody(new CatalogBody());
		Set<String> listDeta = new HashSet<>();
		listDeta.add(nemonicoTable);
		catalogReq.getData().getBody().setCatList(listDeta);
		return catalogReq;
	}
}