CREATE TABLE Artist (
  id       int       AUTO_INCREMENT,
  name     VARCHAR(255) NOT NULL,
  country  VARCHAR(255) NOT NULL,
  version  INTEGER DEFAULT 0,
  PRIMARY KEY (id)
);

CREATE TABLE Painting (
  id          int          AUTO_INCREMENT,
  title       VARCHAR(255)  NOT NULL,
  summary     VARCHAR(255),
  dateMade    int           NOT NULL,
  artist_id   VARCHAR(255)  NOT NULL,
  gallery_id  VARCHAR(255),
  PRIMARY KEY (id),
  FOREIGN KEY (artist_id)   REFERENCES Artist,
  FOREIGN KEY (gallery_id)  REFERENCES PaintingGallery
);

CREATE TABLE PaintingGallery (
  id          int          AUTO_INCREMENT,
  type        VARCHAR(255) NOT NULL,
  owner       VARCHAR(255),
  country     VARCHAR(255),
  city        VARCHAR(255),
  street      VARCHAR(255),
  PRIMARY KEY (id)
);


INSERT INTO Artist (id, name, country) VALUES
  (1, 'Leonardo da Vinci', 'Italy' ),
  (2, 'Vincent van Gogh', 'Holland'),
  (3, 'Salvador Dali', 'Spain'),
  (4, 'Ilya Repin', 'Russia');

INSERT INTO Painting (title, summary, dateMade, artist_id) VALUES
  ('Mona Lisa or La Gioconda', null, 1499, 1),
  ('Bacchus', null, 1515, 1),
  ('Starry Night', null, 1889, 2),
  ('Madonna Litta', null, 1491, 1),
  ('The Persistence Of Memory', null, 1931, 3),
  ('Ivan the Terrible and His Son Ivan', null, 1885, 4),
  ('Starry Night', null, 1889, 2),
  ('The elephants', null, 1948, 3),
  ('Madonna Benois', null, 1480, 1),
  ('Barge Haulers on the Volga', null, 1873, 4),
  ('Self-Portrait Without Beard', null, 1875, 2),
  ('Sunflowers', null, 1888, 2);




--Museums:
--
--1. The Hermitage Museum, St. Petersburg, Дворцовая набережная, 34 Russia
--2. Tretyakov Gallery, Moscow, Russia
--3. Louvre Museum, Paris - France
--4. Metropolitan Museum of Art, 1000 Fifth Avenue at 82nd Street, New York -USA
--5. Museum of Modern Art, 11 West 53 Street, New York - USA
--6. Nation Gallery, Trafalgar Square, London - UK
--7. Museo Reina Sofía, Madrid, Spain
--______________________________________________________________________________________
--
--Artist:
--Leonardo da Vinci, Italy
-- Mona Lisa or La Gioconda -  Louvre Museum, Paris, France
-- Bacchus (1515) -  Louvre Museum, Paris, France
-- Madonna Litta, (1490-91) - The Hermitage Museum, St. Petersburg, Russia
-- Madonna Benois (1478-1480) - The Hermitage Museum, St. Petersburg, Russia
--
--Vincent van Gogh, Holland
-- Starry Night – 1889, Museum of Modern Art in New York, USA
-- Self-Portrait Without Beard - private collection
-- Starry Night - 1889, Museum of Modern Art in New York, USA
-- Sunflowers -  1888, Nation Gallery, Trafalgar Square, London
--
--Michelangelo, Italy
-- The Musicians - 1595, Metropolitan Museum of Art, New York
--
--Pablo Picasso, Spain
-- Guernica  - 1937,  Museo Reina Sofía, Madrid, Spain
--
--Salvador Dali, Spain
-- The Persistence Of Memory, 1931, Museum of Modern Art in New York
-- The elephants - 1948, private collection
--
--Ilya Repin (1844-1930), Russian
-- Barge Haulers on the Volga, (1870–73), The Hermitage Museum, St. Petersburg, Russia
-- Ivan the Terrible and His Son Ivan (1885), Tretyakov Gallery, Moscow, Russia