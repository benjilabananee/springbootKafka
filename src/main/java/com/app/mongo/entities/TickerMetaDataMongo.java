package com.app.mongo.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TickerMetaDataMongo {

    @Id
    private String symbol;
    private String name;
    private String lastsale;
    private String netChange;
    private String pctchange;
    private String marketCap;

    public String getSymbol() {return symbol;}
    public void setSymbol(String symbol) {this.symbol = symbol;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getLastsale() {return lastsale;}
    public void setLastsale(String lastsale) {this.lastsale = lastsale;}

    public String getNetChange() {return netChange;}
    public void setNetChange(String netChange) {this.netChange = netChange;}

    public String getPctchange() {return pctchange;}
    public void setPctchange(String pctchange) {this.pctchange = pctchange;}

    public String getMarketCap() {return marketCap;}
    public void setMarketCap(String marketCap) {this.marketCap = marketCap;}
}
