
package com.app.kafka.utils.schema.tickersMetaData;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "symbol",
    "name",
    "exch",
    "type",
    "exchDisp",
    "typeDisp"
})
@Generated("jsonschema2pojo")
public class Body {

    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("name")
    private String name;
    @JsonProperty("exch")
    private String exch;
    @JsonProperty("type")
    private String type;
    @JsonProperty("exchDisp")
    private String exchDisp;
    @JsonProperty("typeDisp")
    private String typeDisp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

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

    @JsonProperty("exch")
    public String getExch() {
        return exch;
    }

    @JsonProperty("exch")
    public void setExch(String exch) {
        this.exch = exch;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("exchDisp")
    public String getExchDisp() {
        return exchDisp;
    }

    @JsonProperty("exchDisp")
    public void setExchDisp(String exchDisp) {
        this.exchDisp = exchDisp;
    }

    @JsonProperty("typeDisp")
    public String getTypeDisp() {
        return typeDisp;
    }

    @JsonProperty("typeDisp")
    public void setTypeDisp(String typeDisp) {
        this.typeDisp = typeDisp;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
