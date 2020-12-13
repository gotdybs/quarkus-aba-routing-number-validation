package io.gotdybs.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gotdybs.model.routing.number.ABARoutingNumberResponseDto;
import io.gotdybs.service.ABARoutingNumberValidator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.Objects;

@Named("aba-routing-number-validator")
public class ABARoutingNumberRequestHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final ABARoutingNumberValidator abaRoutingNumberValidator;
    private final ObjectMapper objectMapper;

    @Inject
    public ABARoutingNumberRequestHandler(ABARoutingNumberValidator abaRoutingNumberValidator) {
        this.abaRoutingNumberValidator = Objects.requireNonNull(abaRoutingNumberValidator);
        this.objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, Context context) {
        String rtn = apiGatewayProxyRequestEvent.getPathParameters().get("rtn");
        try {
            boolean isValid = this.abaRoutingNumberValidator.isValid(rtn);
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200)
                    .withBody(serialize(
                            new ABARoutingNumberResponseDto()
                                    .isValid(isValid)
                    ));
        } catch (Exception e) {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(500)
                    .withBody(serialize(Collections.singletonMap("message", "Oops something went wrong...")));
        }
    }

    private String serialize(Object object) {
        try {
            return this.objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to serialize!", e);
        }
    }

}