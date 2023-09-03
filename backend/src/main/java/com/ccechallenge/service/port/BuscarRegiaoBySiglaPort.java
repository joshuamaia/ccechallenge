package com.ccechallenge.service.port;

import java.util.List;

public interface BuscarRegiaoBySiglaPort<IN, OUT> {

    public List<OUT> execute(IN in);
}
