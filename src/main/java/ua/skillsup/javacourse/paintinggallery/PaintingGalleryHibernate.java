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

//                For h2 DB
        .setProperty("hibernate.connection.driver_class", "org.h2.Driver")
        .setProperty("hibernate.connection.url", "jdbc:h2:mem:books_db;DB_CLOSE_DELAY=-1")
        .setProperty("hibernate.show_sql", "true")
        .setProperty("hibernate.hbm2ddl.auto", "update")
        .setProperty("hibernate.connection.isolation", "4")
        .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")

//        .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
//        .setProperty("hibernate.connection.url", "jdbc:mysql://localhost/hibernate")
//        .setProperty("hibernate.connection.username", "root")
//        .setProperty("hibernate.connection.password", "")
//        .setProperty("hibernate.show_sql", "true")
//        .setProperty("hibernate.connection.isolation", "4")
//        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
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
//    findPainting();//

      createArtist("Michelangelo", "Italy");
      createPaintingAndSetArtist("TestPainting", 1585, "some summary", "Michelangelo");
      createPublicGalleryAndSetPainting("Some museum", "UK", "London", "some address", "TestPainting");
      findAllArtistPaintings("Michelangelo");
      findGalleryByPainting("TestPainting");

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

    log.info("Created new artist {} with id {} and new painting {} with id {}.", artist.getName(),
            artist.getId(), painting.getTitle(), painting.getId() + "\n");

    session.getTransaction().commit();
    session.close();
  }

  static void createArtist(String artistName, String artistCountry) {

    log.info("Create new Artist");
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    final Artist artist = new Artist();
    artist.setName(artistName);
    artist.setCountry(artistCountry);

    session.persist(artist);

    log.info("Created new artist {} with id {}", artist.getName(), artist.getId() +"\n");

    session.getTransaction().commit();
    session.close();
  }

  static void createPaintingAndSetArtist (String paintingTitle, int paintingDateMade,
                                          String paintingSummary, String artistName) {
    log.info("Create new Painting and set its artist");
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    Query query = session.createQuery("from Artist a where a.name=:n").setParameter("n", artistName);
    final Artist artist = (Artist) query.uniqueResult();

    final Painting painting = new Painting();
    painting.setTitle(paintingTitle);
    painting.setDateMade(paintingDateMade);
    painting.setSummary(paintingSummary);
    painting.setArtist(artist);

    session.persist(painting);

    log.info("Created new painting {} with id {} and set its artist: {}", painting.getTitle(), painting.getId(),
            artist.getName() + "\n");

    session.getTransaction().commit();
    session.close();
  }

  static void createPrivateGalleryAndSetPainting(String paintingTitle) {
    log.info("Create private Painting gallery");
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    final PrivateGallery privateGallery = new PrivateGallery();

    Query query = session.createQuery("from Painting p where p.title=:t").setParameter("t", paintingTitle);
    final Painting painting = (Painting) query.uniqueResult();

    painting.setPaintingGallery(privateGallery);

    session.persist(privateGallery);

    log.info("Created new private gallery {} with id {} and set painting {}", privateGallery.getClass().getSimpleName(),
            privateGallery.getId(), painting.getTitle() + "\n");

    session.getTransaction().commit();
    session.close();
  }

  static void createPublicGalleryAndSetPainting(String galleryOwner, String country, String city,
                                                String street, String paintingTitle) {
    log.info("Create public Painting gallery");
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    final PublicGallery publicGallery = new PublicGallery();
    final Address address = new Address();
    address.setCountry(country);
    address.setCity(city);
    address.setStreet(street);
    publicGallery.setOwner(galleryOwner);
    publicGallery.setAddress(address);

    Query query = session.createQuery("from Painting p where p.title=:t").setParameter("t", paintingTitle);
    final Painting painting = (Painting) query.uniqueResult();

    painting.setPaintingGallery(publicGallery);

    session.persist(publicGallery);

    log.info("Created new public gallery {} with id {} and set painting {}", publicGallery.getClass().getSimpleName(),
            publicGallery.getId(), painting.getTitle() + "\n");

    session.getTransaction().commit();
    session.close();
  }

  static void findPainting(String paintingTitle) {
    log.info("Find painting");
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    Query query = session.createQuery("from Painting p where p.title=:t").setParameter("t", paintingTitle);
    Painting painting = (Painting)query.uniqueResult();

    System.out.println("\n" + "query result is:" + "\n" + painting +"\n");

    session.getTransaction().commit();
    session.close();
  }

  static void findGalleryByPainting (String paintingTitle) {
    log.info("Find gallery by painting");
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    Query query = session.createQuery("from Painting p where p.title=:t").setParameter("t", paintingTitle);
    Painting painting = (Painting)query.uniqueResult();

    PaintingGallery gallery = painting.getPaintingGallery();

    System.out.println("\n" + "query result is:" + "\n" + gallery);

    session.getTransaction().commit();
    session.close();
  }

  static void findAllArtistPaintings(String artistName) {
    log.info("Find all painting by artist");
    final Session session = sessionFactory.openSession();
    session.getTransaction().begin();

    final Artist artist = (Artist)session
        .createQuery("from Artist a where a.name=:n")
        .setParameter("n", artistName)
        .uniqueResult();

    Set<Painting> paintings = artist.getPaintings();
    for(Painting painting : paintings)
      System.out.println("query result is:" + "\n" + painting + "\n");

    session.getTransaction().commit();
    session.close();
  }
}
