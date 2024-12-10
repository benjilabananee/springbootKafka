
package com.app.kafka.utils.tickersLastOpp;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "version",
    "status",
    "copywrite",
    "totalrecords",
    "headers"
})
@Generated("jsonschema2pojo")
public class Meta implements Serializable
{

    @JsonProperty("version")
    private String version;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("copywrite")
    private String copywrite;
    @JsonProperty("totalrecords")
    private Integer totalrecords;
    @JsonProperty("headers")
    private Headers headers;
    private final static long serialVersionUID = 7316640830356124353L;

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonProperty("copywrite")
    public String getCopywrite() {
        return copywrite;
    }

    @JsonProperty("copywrite")
    public void setCopywrite(String copywrite) {
        this.copywrite = copywrite;
    }

    @JsonProperty("totalrecords")
    public Integer getTotalrecords() {
        return totalrecords;
    }

    @JsonProperty("totalrecords")
    public void setTotalrecords(Integer totalrecords) {
        this.totalrecords = totalrecords;
    }

    @JsonProperty("headers")
    public Headers getHeaders() {
        return headers;
    }

    @JsonProperty("headers")
    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

}
