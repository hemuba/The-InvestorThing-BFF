package com.theinvestorthing.bff.wallet.service;

import com.theinvestorthing.bff.commons.exceptions.NotFoundException;
import com.theinvestorthing.bff.commons.response.ApiResponse;
import com.theinvestorthing.bff.commons.response.ErrorResponse;
import com.theinvestorthing.bff.commons.serverconfig.ServerProperties;
import com.theinvestorthing.bff.wallet.dto.WalletDTOResp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class WalletService {

    private final WebClient webClient;
    private final ServerProperties serverProperties;


    public WalletService(WebClient.Builder builder, ServerProperties serverProperties){
        this.serverProperties = serverProperties;
        String baseUrl = UriComponentsBuilder.newInstance()
                .scheme(serverProperties.getProtocol())
                .host(serverProperties.getHost())
                .port(serverProperties.getPort())
                .pathSegment(serverProperties.getAppName())
                .build()
                .toUriString();
        this.webClient = builder
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<ApiResponse<List<WalletDTOResp>>> getWalletFromBE(String traceId){
        return webClient.get()
                .uri("/my-stocks")
                .header("x-trace-id", traceId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<WalletDTOResp>>>() {});
    }

    public Mono<ApiResponse<WalletDTOResp>> getTickerFromBE(String traceId, String ticker){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/my-stocks/by-ticker")
                        .queryParam("ticker", ticker)
                        .build())
                .header("x-trace-id", traceId)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        response -> response.bodyToMono(ErrorResponse.class)
                                .map(error -> new NotFoundException("Client error: " + error.getErrors()))
                                .flatMap(Mono::error)
                )
                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        response -> response.bodyToMono(ErrorResponse.class)
                                .map(error -> new RuntimeException("Server error: " + error.getErrors()))
                                .flatMap(Mono::error)
                )
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<WalletDTOResp>>() {});
    }

    public Mono<ApiResponse<List<WalletDTOResp>>> getStocksHavingCurrentReturnGreaterThan(String traceId, Double value){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/my-stocks/by-current-return-greater-than")
                        .queryParam("value", value)
                        .build())
                .header("x-trace-id", traceId)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        resp -> resp.bodyToMono(ErrorResponse.class)
                                .map(error -> new NotFoundException("Client error: " + error.getErrors()))
                                .flatMap(Mono::error)
                )
                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        resp -> resp.bodyToMono(ErrorResponse.class)
                                .map(error -> new RuntimeException("Server error: " + error.getErrors()))
                                .flatMap(Mono::error)
                )
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<WalletDTOResp>>>() {});
    }

}
