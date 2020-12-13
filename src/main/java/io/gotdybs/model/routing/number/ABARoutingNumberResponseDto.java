package io.gotdybs.model.routing.number;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ABARoutingNumberResponseDto {

    @JsonProperty("isValid")
    private boolean isValid;

    public ABARoutingNumberResponseDto() {

    }

    public boolean getIsValid() {
        return isValid;
    }

    public ABARoutingNumberResponseDto isValid(boolean valid) {
        isValid = valid;
        return this;
    }

}