package com.orchestration.instantcard.controller;

import com.orchestration.instantcard.models.request.InstantCardRequest;
import com.orchestration.instantcard.models.response.InstantCardResponse;
import com.orchestration.instantcard.service.InstantCardService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Creacion de tarjeta instantanea", description = "Servicio de orquestacion creacion de de tarjeta instantanea")
@RestController
public class InstantCardController {

    private InstantCardService instantCardService;

    public InstantCardController(InstantCardService instantCardService){
        this.instantCardService = instantCardService;
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = InstantCardResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = InstantCardResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Orquestador - Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = InstantCardResponse.class)) }) })
    @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InstantCardResponse> processInstantCardController(@RequestBody InstantCardRequest request) {
        return instantCardService.processInstantCard(request);
    }
}
