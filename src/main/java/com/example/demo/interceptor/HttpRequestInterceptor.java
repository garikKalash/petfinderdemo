package com.example.demo.interceptor;

import com.example.demo.service.TokenRefresher;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class HttpRequestInterceptor implements ClientHttpRequestInterceptor {
    private static Logger log = Logger.getLogger("HttpRequestInterceptor");
    private final TokenRefresher tokenRefresher;

    public HttpRequestInterceptor(TokenRefresher tokenRefresher) {
        this.tokenRefresher = tokenRefresher;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        request.getHeaders().setBearerAuth(tokenRefresher.getToken());
        return execution.execute(request, body);
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException {
        log.info("===========================request begin================================================");
        log.info("URI         :"+ request.getURI());
        log.info("Method      : "+ request.getMethod());
        log.info("Headers     : "+ request.getHeaders());
        log.info("Request body: "+ new String(body, StandardCharsets.UTF_8));
        log.info("==========================request end================================================");
    }
}
