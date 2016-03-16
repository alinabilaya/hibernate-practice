package ua.skillsup.javacourse.paintinggallery.model;

import javax.persistence.Embeddable;

/**
 * Created by Shine on 12.03.2016.
 */

@Embeddable
public class Address {

  private String country;

  private String city;

  private String street;

  //-----------------------------------------------------------------------------------------//

  public String getCountry() { return country; }

  public void setCountry(String country) { this.country = country; }

  public String getCity() { return city; }

  public void setCity(String city) { this.city = city; }

  public String getStreet() { return street; }

  public void setStreet(String street) { this.street = street; }

  @Override
  public String toString() {
    return " " + country +
            ", " + city +
            ", " + street +
            " ";
  }
}

