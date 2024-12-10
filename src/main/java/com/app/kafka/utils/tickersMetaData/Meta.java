
package com.app.kafka.utils.tickersMetaData;

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
    "searchTerm",
    "processedTime"
})
@Generated("jsonschema2pojo")
public class Meta {

    @JsonProperty("searchTerm")
    private String searchTerm;
    @JsonProperty("processedTime")
    private String processedTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("searchTerm")
    public String getSearchTerm() {
        return searchTerm;
    }

    @JsonProperty("searchTerm")
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @JsonProperty("processedTime")
    public String getProcessedTime() {
        return processedTime;
    }

    @JsonProperty("processedTime")
    public void setProcessedTime(String processedTime) {
        this.processedTime = processedTime;
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
