package ua.skillsup.javacourse.paintinggallery.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by Shine on 12.03.2016.
 */

@Embeddable
public class Address {

    @Column
    private String country;
    private String city;
    private String street;

}
