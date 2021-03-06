package com.furlenco.commons;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MapResponse {

private List<Result> results = new ArrayList<Result>();
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* 
* @return
* The results
*/
public List<Result> getResults() {
return results;
}

/**
* 
* @param results
* The results
*/
public void setResults(List<Result> results) {
this.results = results;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}