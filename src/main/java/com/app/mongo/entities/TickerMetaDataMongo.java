package com.app.mongo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TickerMetaDataMongo {

    @Id
    private String symbol;
    private String name;
    private Double lastsale;
    private String currency;
    private Double netChange;
    private Double pctchange;
    private String marketCap;

    public String getSymbol() {return symbol;}
    public void setSymbol(String symbol) {this.symbol = symbol;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public Double getLastsale() {return lastsale;}
    public void setLastsale(Double lastsale) {this.lastsale = lastsale;}

    public String getCurrency() {return currency;}
    public void setCurrency(String currency) {this.currency = currency;}

    public Double getNetChange() {return netChange;}
    public void setNetChange(Double netChange) {this.netChange = netChange;}

    public Double getPctchange() {return pctchange;}
    public void setPctchange(Double pctchange) {this.pctchange = pctchange;}

    public String getMarketCap() {return marketCap;}
    public void setMarketCap(String marketCap) {this.marketCap = marketCap;}
}
