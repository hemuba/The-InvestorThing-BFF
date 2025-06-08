package com.theinvestorthing.bff.wallet.service;

import com.theinvestorthing.bff.wallet.commons.response.ApiResponse;
import com.theinvestorthing.bff.wallet.dto.WalletDTOResp;
import org.springframework.core.ParameterizedTypeReference;
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
}
