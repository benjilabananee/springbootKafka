package com.app.mongo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashMap;
import java.util.Map;

@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TickerMetaDataMongo {

    private String symbol;
    private String name;
    private String exch;
    private String type;
    private String exchDisp;
    private String typeDisp;

    @Id
    public String getSymbol() {return symbol;}
    public void setSymbol(String symbol) {this.symbol = symbol;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getExch() {return exch;}
    public void setExch(String exch) {this.exch = exch;}

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    public String getExchDisp() {return exchDisp;}
    public void setExchDisp(String exchDisp) {this.exchDisp = exchDisp;}

    public String getTypeDisp() {return typeDisp;}
    public void setTypeDisp(String typeDisp) {this.typeDisp = typeDisp;}
}
