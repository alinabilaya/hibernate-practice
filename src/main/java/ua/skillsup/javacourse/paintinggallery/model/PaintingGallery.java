package ua.skillsup.javacourse.paintinggallery.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by Shine on 12.03.2016.
 */

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class  PaintingGallery {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "owner")
  private String owner;


  //--------------------------------------------------------------------------------------------------------//

  public int getId() { return id; }

  public String getOwner() { return owner; }

  public void setOwner(String owner) { this.owner = owner; }

}
