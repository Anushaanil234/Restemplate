package Vendingmachine.logginginterceptor;


import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException {
        System.out.println("===========================request begin================================================");
        System.out.println("URI         : " + request.getURI());
        System.out.println("Method      : " + request.getMethod());
        System.out.println("Headers     : " + request.getHeaders());
        System.out.println("Request body: " + new String(body, StandardCharsets.UTF_8));
        System.out.println("==========================request end================================================");
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        System.out.println("============================response begin==========================================");
        System.out.println("Status code  : " + response.getStatusCode());
        System.out.println("Status text  : " + response.getStatusText());
        System.out.println("Headers      : " + response.getHeaders());
        System.out.println("Response body: " + getResponseBody(response));
        System.out.println("=======================response end=================================================");
    }
    private String getResponseBody(ClientHttpResponse response) throws IOException {
        StringBuilder inputStringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                inputStringBuilder.append(line);
                inputStringBuilder.append('\n');
            }
        }
        return inputStringBuilder.toString();
    }
}



