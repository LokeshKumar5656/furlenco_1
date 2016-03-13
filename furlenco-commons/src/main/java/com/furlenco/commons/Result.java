package com.furlenco.commons;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Result {

private List<AddressComponent> addressComponents = new ArrayList<AddressComponent>();
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* 
* @return
* The addressComponents
*/
public List<AddressComponent> getAddressComponents() {
return addressComponents;
}

/**
* 
* @param addressComponents
* The address_components
*/
public void setAddressComponents(List<AddressComponent> addressComponents) {
this.addressComponents = addressComponents;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}