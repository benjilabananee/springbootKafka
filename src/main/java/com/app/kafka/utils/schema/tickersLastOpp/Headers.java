
package com.app.kafka.utils.schema.tickersLastOpp;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "symbol",
    "name",
    "lastsale",
    "netchange",
    "pctchange",
    "marketCap"
})
@Generated("jsonschema2pojo")
public class Headers implements Serializable
{

    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("name")
    private String name;
    @JsonProperty("lastsale")
    private String lastsale;
    @JsonProperty("netchange")
    private String netchange;
    @JsonProperty("pctchange")
    private String pctchange;
    @JsonProperty("marketCap")
    private String marketCap;
    private final static long serialVersionUID = 1232306929300408090L;

    @JsonProperty("symbol")
    public String getSymbol() {
        return symbol;
    }

    @JsonProperty("symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("lastsale")
    public String getLastsale() {
        return lastsale;
    }

    @JsonProperty("lastsale")
    public void setLastsale(String lastsale) {
        this.lastsale = lastsale;
    }

    @JsonProperty("netchange")
    public String getNetchange() {
        return netchange;
    }

    @JsonProperty("netchange")
    public void setNetchange(String netchange) {
        this.netchange = netchange;
    }

    @JsonProperty("pctchange")
    public String getPctchange() {
        return pctchange;
    }

    @JsonProperty("pctchange")
    public void setPctchange(String pctchange) {
        this.pctchange = pctchange;
    }

    @JsonProperty("marketCap")
    public String getMarketCap() {
        return marketCap;
    }

    @JsonProperty("marketCap")
    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

}
