package ua.skillsup.javacourse.paintinggallery;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.skillsup.javacourse.paintinggallery.model.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shine on 12.03.2016.
 */
public class PaintingGalleryHibernate {

  private static final Logger log = LoggerFactory.getLogger(PaintingGalleryHibernate.class);

  static SessionFactory sessionFactory;

  public static void main(String[] args) {
    sessionFactory = new Configuration()

        //        For h2 DB
//        .setProperty("hibernate.connection.driver_class", "org.h2.Driver")
//        .setProperty("hibernate.connection.url", "jdbc:h2:mem:books_db;DB_CLOSE_DELAY=-1")
//        .setProperty("hibernate.show_sql", "true")
//        .setProperty("hibernate.hbm2ddl.auto", "update")
//        .setProperty("hibernate.connection.isolation", "4")
//        .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")

        .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
        .setProperty("hibernate.connection.url", "jdbc:mysql://localhost/hibernate")
        .setProperty("hibernate.connection.username", "root")
        .setProperty("hibernate.connection.password", "221155")
        .setProperty("hibernate.show_sql", "true")
        .setProperty("hibernate.connection.isolation", "4")
        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
        .addAnnotatedClass(Address.class)
        .addAnnotatedClass(Artist.class)
        .addAnnotatedClass(Painting.class)
        .addAnnotatedClass(PaintingGallery.class)
        .addAnnotatedClass(PrivateGallery.class)
        .addAnnotatedClass(PublicGallery.class)
        .buildSessionFactory();

//    createArtistAndPainting("Michelangelo", "Italy", "The Musicians", 1595, null);
//    createArtistAndPainting("Pablo Picasso", "Spain", "Guernica", 1937, null);
//    createPrivateGallery();
//    findPainting();
    findAllArtistPainting();

    sessionFactory.close();
  }

  static void createArtistAndPainting(String artistName, String artistCountry, String paintingTitle,
                                      int paintingDateMade, String paintingSummary) {
    log.info("Create Artist and Painting");
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    final Artist artist = new Artist();
    artist.setName(artistName);
    artist.setCountry(artistCountry);

    final Painting painting = new Painting();
    painting.setTitle(paintingTitle);
    painting.setDateMade(paintingDateMade);
    painting.setSummary(paintingSummary);
    painting.setArtist(artist);

    artist.setPaintings(new HashSet<>());
    artist.getPaintings().add(painting);

    session.persist(artist);

    log.info("Created new artist {} with id {}", artist.getName(), artist.getId());

    session.getTransaction().commit();
    session.close();
  }

  static void createPrivateGallery() {
    log.info("Create private Painting gallery");
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    final PrivateGallery privateGallery = new PrivateGallery();

    Query query = session.createQuery("SELECT * from Painting p where p.title=:t").setParameter("t", "The elephants");
    final Painting elephants = (Painting) query.uniqueResult();

    elephants.setPaintingGallery(privateGallery);

    session.persist(privateGallery);
    log.info("Created new artist {} with id {}", privateGallery.getId());

    session.getTransaction().commit();
    session.close();
  }

  static void findPainting() {
    log.info("Find painting");
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    Query query = session.createQuery("from Painting p where p.title=:t").setParameter("t", "The elephants");
    Painting painting = (Painting)query.uniqueResult();

    System.out.println("\n" + "query result is:" + "\n" + painting);

    session.getTransaction().commit();
    session.close();
  }

  static void findAllArtistPainting() {
    log.info("Find all painting by artist");
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    final Artist vanGogh = (Artist)session
        .createQuery("from Artist a where a.name=:n")
        .setParameter("n", "Vincent van Gogh")
        .uniqueResult();

    Set<Painting> paintings = vanGogh.getPaintings();
    for(Painting painting : paintings)
      System.out.println("query result is:" + "\n" + painting);

    session.getTransaction().commit();
    session.close();
  }
}
