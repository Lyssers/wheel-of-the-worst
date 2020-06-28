/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wheelofmisfortune;

/**
 *
 * @author Alyssa
 */
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"Source",
"Value"
})
public class Rating {

@JsonProperty("Source")
private String source;
@JsonProperty("Value")
private String value;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
*
*/
public Rating() {
}

/**
*
* @param source
* @param value
*/
public Rating(String source, String value) {
super();
this.source = source;
this.value = value;
}

@JsonProperty("Source")
public String getSource() {
return source;
}

@JsonProperty("Source")
public void setSource(String source) {
this.source = source;
}

@JsonProperty("Value")
public String getValue() {
return value;
}

@JsonProperty("Value")
public void setValue(String value) {
this.value = value;
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
