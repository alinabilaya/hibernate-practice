package ua.skillsup.javacourse.paintinggallery.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Created by Shine on 12.03.2016.
 */

@Entity
public class PrivateGallery extends PaintingGallery{

  @OneToMany(mappedBy = "paintingGallery", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Painting> paintings;

  public Set<Painting> getPaintings() { return paintings; }

  public void setPaintings(Set<Painting> paintings) { this.paintings = paintings; }
}
