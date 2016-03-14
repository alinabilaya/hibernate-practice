package ua.skillsup.javacourse.paintinggallery.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Set;

/**
 * Created by Shine on 12.03.2016.
 */


@Entity
@Table(name = "Artist")
public class Artist {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Basic(fetch = FetchType.EAGER, optional = false)
  @Column(name = "name", nullable = false)
  private String name;

  @Basic(fetch = FetchType.EAGER, optional = false)
  @Column(name = "country", nullable = false)
  private String country;

  @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Painting> paintings;

  @Version
  private Integer version;

  //-------------------------------------------------------------------------------------------//

  public Long getId() { return id; }

  public String getCountry() { return country; }

  public void setCountry(String country) { this.country = country; }

  public String getName() { return name; }

  public void setName(String name) { this.name = name; }

  public Set<Painting> getPaintings() { return paintings; }

  public void setPaintings(Set<Painting> paintings) { this.paintings = paintings; }

  public Integer getVersion() { return version; }

  public void setVersion(Integer version) { this.version = version; }

  @Override
  public String toString() {
    return "Artist{" +
        "id=" + id +
        ", name=" + name +
        ", country=" + country +
        "}";
  }
}
