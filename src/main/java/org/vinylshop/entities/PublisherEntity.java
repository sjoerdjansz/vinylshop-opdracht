package org.vinylshop.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "publisher")
public class PublisherEntity extends BaseEntity{

  private String name;
  private String address;
  private String contactDetails;

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getContactDetails() {
    return contactDetails;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setContactDetails(String contactDetails) {
    this.contactDetails = contactDetails;
  }
}
