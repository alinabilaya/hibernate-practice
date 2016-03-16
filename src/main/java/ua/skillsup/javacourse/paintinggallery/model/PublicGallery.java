package ua.skillsup.javacourse.paintinggallery.model;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Created by Shine on 12.03.2016.
 */

@Entity
public class PublicGallery extends PaintingGallery {

  @Embedded
  private Address address;

  @OneToMany(mappedBy = "paintingGallery", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Painting> paintings;

  //---------------------------------------------------------------------------------------------//

  public Address getAddress() { return address; }

  public void setAddress(Address address) { this.address = address; }

  public Set<Painting> getPaintings() { return paintings; }

  public void setPaintings(Set<Painting> paintings) { this.paintings = paintings; }

  @Override
  public String toString() {
    return "Public gallery {" +
            "id= " + getId() +
            ", owner= " + getOwner() +
            ", address= " + address +
            "}";
  }
}
