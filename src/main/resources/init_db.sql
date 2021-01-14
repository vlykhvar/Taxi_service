CREATE TABLE manufacture (
                          id INT NOT NULL AUTO_INCREMENT,
                          name VARCHAR(255),
                          country VARCHAR(100),
                          isexist bit DEFAULT true,
                          PRIMARY KEY (id)
);
