INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');
              
INSERT INTO category (id, name) VALUES (1, 'Sokovi');
INSERT INTO category (id, name) VALUES (2, 'Cokolade');
INSERT INTO category (id, name) VALUES (3, 'Voce');
              
INSERT INTO product (id, name, price, quantity, category_id ) VALUES (1, 'Coca-Cola', 190.0, 18, 1);
INSERT INTO product (id, name, price, quantity, category_id ) VALUES (2, 'Fanta', 185.0, 18, 1);
INSERT INTO product (id, name, price, quantity, category_id ) VALUES (3, 'Pepsi', 178.0, 18, 1);
INSERT INTO product (id, name, price, quantity, category_id ) VALUES (4, 'Milka', 99.0, 18, 2);
INSERT INTO product (id, name, price, quantity, category_id ) VALUES (5, 'Najlepse zelje', 85.0, 18, 2);
INSERT INTO product (id, name, price, quantity, category_id ) VALUES (6, 'Kinder bueno', 85.0, 18, 2);
INSERT INTO product (id, name, price, quantity, category_id ) VALUES (7, 'Banana', 115.0, 18, 3);
INSERT INTO product (id, name, price, quantity, category_id ) VALUES (8, 'Jabuka', 76.0, 18, 3);
INSERT INTO product (id, name, price, quantity, category_id ) VALUES (9, 'Pomorandza', 187.0, 18, 3);
INSERT INTO product (id, name, price, quantity, category_id ) VALUES (10, 'Limun', 112.0, 18, 3);

INSERT INTO order_class (id, quantity, product_id) VALUES (1, 6, 1);