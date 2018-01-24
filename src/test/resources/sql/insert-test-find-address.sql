SET REFERENTIAL_INTEGRITY FALSE;

TRUNCATE TABLE USER;
TRUNCATE TABLE ADDRESS;

INSERT INTO address(city, number, postcode, street) VALUES('Saint-Pierre-des-Corps', '2', '37700', 'Place de la Gare');
