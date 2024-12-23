package com.app.DTO;

import com.app.mongo.entities.TickerMetaDataMongo;
import com.app.mongo.entities.TickersLastOppMongo;

import java.util.List;

public class TickerLastOppAggregatedDTO extends TickersLastOppMongo {

    private List<TickerMetaDataMongo> tickerInfo;

    public List<TickerMetaDataMongo> getTickerInfo() {return tickerInfo;}
    public void setTickerInfo(List<TickerMetaDataMongo> tickerInfo) {this.tickerInfo = tickerInfo;}
}
