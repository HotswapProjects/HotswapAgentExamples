package org.hotswap.agent.example.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Person extends PersonModelBase {
	
	@JsonProperty
	public String getP2() {
		return "P2!!";
	}
	public String getFullName() {
		
		return this.getFirstName()+ " - " + this.getLastName();
	}

}
