package com.theinvestorthing.bff.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class WalletDTOReq {
    @NotBlank(message = "Ticker cannot be blank")
    private String ticker;

    @NotNull(message = "Number of shares cannot be null")
    private BigDecimal noOfShares;

    @NotNull(message = "Puchase price cannot be blank")
    private BigDecimal purchasePrice;

    @NotNull(message = "Current price cannot be blank")
    private BigDecimal currentPrice;

    public WalletDTOReq() {
    }

    public WalletDTOReq(String ticker, BigDecimal noOfShares, BigDecimal purchasePrice, BigDecimal currentPrice) {
        this.ticker = ticker;
        this.noOfShares = noOfShares;
        this.purchasePrice = purchasePrice;
        this.currentPrice = currentPrice;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public BigDecimal getNoOfShares() {
        return noOfShares;
    }

    public void setNoOfShares(BigDecimal noOfShares) {
        this.noOfShares = noOfShares;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
}
