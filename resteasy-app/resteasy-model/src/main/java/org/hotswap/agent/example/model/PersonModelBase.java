package org.hotswap.agent.example.model;

import javax.annotation.Generated;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * WARNING: Do not change the generated code. Instead use the class
 * that extends this class and put custom code there, overriding any methods
 * that you need to override
 */
@Generated(
        value = {"com.netu.codeGen.XMLModelGenerator, Version 3"},
        comments = "Model Object mapped to table person ",
        date = "Fri Nov 27 12:28:41 EET 2015"
    )

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonModelBase {

	private Long personId;
	private String lastName;
	private String firstName;
	private java.util.Date birthDate;
	private Long communityId;
	private String cyprusIdNumber;

    private static long nextId = -1;
   /**
    * PersonModelBase Constructor
    */
	public PersonModelBase() {
        super();
		this.personId = --nextId ;

	}

	@org.codehaus.jackson.annotate.JsonProperty
	public void setPersonId(final Long _PersonId) {
		this.personId = _PersonId;
	}


	@org.codehaus.jackson.annotate.JsonProperty
	public Long getPersonId() {
		return this.personId;
	}

	@org.codehaus.jackson.annotate.JsonProperty
	public void setLastName(final String _LastName) {
		if (_LastName!=null && _LastName.length()>100){
			throw new IllegalArgumentException("Too Large Value for field: LastName.  max length:100");
		}

		this.lastName = _LastName;

	}

	@org.codehaus.jackson.annotate.JsonProperty
	public String getLastName() {
		return this.lastName;
	}



	@org.codehaus.jackson.annotate.JsonProperty
	public void setFirstName(final String _FirstName) {
		if (_FirstName!=null && _FirstName.length()>100){
			throw new IllegalArgumentException("Too Large Value for field: FirstName.  max length:100");
		}

		this.firstName = _FirstName;

	}

	@org.codehaus.jackson.annotate.JsonProperty
	public String getFirstName() {
		return this.firstName;
	}

	@org.codehaus.jackson.annotate.JsonProperty
	public void setBirthDate(final java.util.Date _BirthDate) {
		this.birthDate = _BirthDate;

	}

	@org.codehaus.jackson.annotate.JsonProperty
	public java.util.Date getBirthDate() {
		return this.birthDate;
	}


	@org.codehaus.jackson.annotate.JsonProperty
	public void setCommunityId(final Long _CommunityId) {
		this.communityId = _CommunityId;

	}


	@org.codehaus.jackson.annotate.JsonProperty
	public Long getCommunityId() {
		return this.communityId;
	}

	@org.codehaus.jackson.annotate.JsonProperty
	public void setCyprusIdNumber(final String _CyprusIdNumber) {
		if (_CyprusIdNumber!=null && _CyprusIdNumber.length()>100){
			throw new IllegalArgumentException("Too Large Value for field: CyprusIdNumber.  max length:100");
		}

			this.cyprusIdNumber = _CyprusIdNumber;

	}

	@org.codehaus.jackson.annotate.JsonProperty
	public String getCyprusIdNumber() {
		return this.cyprusIdNumber;
	}


}
