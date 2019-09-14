package org.hotswap.agent.example.cxf.jaxrs;

import javax.xml.bind.annotation.XmlElement;

public class AcceptPerson {
   @XmlElement
   String accepted;

  public String getAccepted() {
      return accepted;
  }

  public void setAccepted(String accepted) {
      this.accepted = accepted;
  }
}
