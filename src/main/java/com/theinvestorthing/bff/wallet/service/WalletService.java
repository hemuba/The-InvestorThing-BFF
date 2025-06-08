package com.theinvestorthing.bff.wallet.service;

import com.theinvestorthing.bff.wallet.commons.exceptions.NotFoundException;
import com.theinvestorthing.bff.wallet.commons.response.ApiResponse;
import com.theinvestorthing.bff.wallet.commons.response.ErrorResponse;
import com.theinvestorthing.bff.wallet.dto.WalletDTOResp;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class WalletService {

    private final WebClient webClient;

    public WalletService(WebClient.Builder builder){
        this.webClient = builder
                .baseUrl("http://localhost:8080/the-investorthing")
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
                        status -> status.value() >= 400 && status.value() < 500,
                        response -> response.bodyToMono(ErrorResponse.class)
                                .map(error -> new NotFoundException("Client error: " + error.getErrors()))
                                .flatMap(Mono::error)
                )
                .onStatus(
                        status -> status.value() >= 500,
                        response -> response.bodyToMono(ErrorResponse.class)
                                .map(error -> new RuntimeException("Server error: " + error.getErrors()))
                                .flatMap(Mono::error)
                )
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<WalletDTOResp>>() {});
    }

}
