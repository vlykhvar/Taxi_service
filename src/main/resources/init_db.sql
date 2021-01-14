CREATE TABLE manufacture (
                          id INT NOT NULL AUTO_INCREMENT,
                          name VARCHAR(255),
                          country VARCHAR(100),
                          is_exist bit DEFAULT true,
                          PRIMARY KEY (id)
);
