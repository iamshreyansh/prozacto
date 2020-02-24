DROP TABLE IF EXISTS prozacto_user;
  
CREATE TABLE prozacto_user (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  username VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL,
  contactNumber VARCHAR(10),
  specialization VARCHAR(100),
  subSpecialization VARCHAR(100)
);

CREATE TABLE location(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    latitude DOUBLE,
    longitude DOUBLE,
    landmark VARCHAR(255),
    address VARCHAR(255)
)