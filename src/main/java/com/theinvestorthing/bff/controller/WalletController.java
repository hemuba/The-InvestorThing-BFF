package com.theinvestorthing.bff.controller;


import com.theinvestorthing.bff.wallet.commons.response.ApiResponse;
import com.theinvestorthing.bff.wallet.dto.WalletDTOResp;
import com.theinvestorthing.bff.wallet.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bff-wallet")
@Validated
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public Mono<ResponseEntity<ApiResponse<List<WalletDTOResp>>>> getWalletFromBE(){
        String traceId = UUID.randomUUID().toString();
        return walletService.getWalletFromBE(traceId)
                .map(ResponseEntity::ok);
    }
}
