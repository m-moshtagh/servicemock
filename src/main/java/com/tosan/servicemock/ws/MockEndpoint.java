package com.tosan.servicemock.ws;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tosan.mocks.soap.GetMockRequest;
import com.tosan.mocks.soap.GetMockResponse;
import com.tosan.servicemock.service.DelayService;

@Endpoint
public class MockEndpoint {

    private static final String NAMESPACE_URI = "http://tosan.com/mocks/soap";
    private final DelayService delayService;
    
    public MockEndpoint(DelayService delayService) {
        this.delayService = delayService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMockRequest")
	@ResponsePayload
    public GetMockResponse getResponse(@RequestPayload GetMockRequest request) {
        delayService.wait(request.getDelay());
        GetMockResponse response = new GetMockResponse();
        response.setResponse(LocalTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toString());
        return response;
    }
}
