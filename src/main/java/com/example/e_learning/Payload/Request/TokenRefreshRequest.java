package com.example.e_learning.Payload.Request;

public class TokenRefreshRequest {
    private String refrechToken;

    public String getRefrechToken() {
        return refrechToken;
    }

    public void setRefrechToken(String refrechToken) {
        this.refrechToken = refrechToken;
    }

    public TokenRefreshRequest() {
    }

    public TokenRefreshRequest(String refrechToken) {
        this.refrechToken = refrechToken;
    }
}
