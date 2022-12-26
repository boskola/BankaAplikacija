INSERT INTO banka (id, naziv, sredstva_banke) VALUES (1, 'UniCredit', 300000);
INSERT INTO banka (id, naziv, sredstva_banke) VALUES (2, 'Erste', 200000);
INSERT INTO banka (id, naziv, sredstva_banke) VALUES (3, 'Intesa', 100000);

INSERT INTO tip_racuna (id, naziv, provizija, banka_id) VALUES (1, 'Standard', 0.2, 1);
INSERT INTO tip_racuna (id, naziv, provizija, banka_id) VALUES (2, 'Premium', 0.1, 2);
INSERT INTO tip_racuna (id, naziv, provizija, banka_id) VALUES (3, 'Student', 0.0, 3);

INSERT INTO racun (id, ime_prezime, jmbg, broj_racuna, stanje_racuna, tip_racuna_id, banka_id) VALUES (1, 'Petar Petrovic', '109329320312', 100200300, 23000, 1, 1);
INSERT INTO racun (id, ime_prezime, jmbg, broj_racuna, stanje_racuna, tip_racuna_id, banka_id) VALUES (2, 'Marko Markovic', '123329320312', 200300400, 200000, 2, 2);
INSERT INTO racun (id, ime_prezime, jmbg, broj_racuna, stanje_racuna, tip_racuna_id, banka_id) VALUES (3, 'Laza Lazic', '323334455610', 300400500, 4000000, 3, 3);
INSERT INTO racun (id, ime_prezime, jmbg, broj_racuna, stanje_racuna, tip_racuna_id, banka_id) VALUES (4, 'Jovan Jovanovic', '312984328943', 500600700, 5000, 1, 3);
INSERT INTO racun (id, ime_prezime, jmbg, broj_racuna, stanje_racuna, tip_racuna_id, banka_id) VALUES (5, 'Ivana Ivanovic', '423943290234', 700800900, 32032, 2, 2);
INSERT INTO racun (id, ime_prezime, jmbg, broj_racuna, stanje_racuna, tip_racuna_id, banka_id) VALUES (6, 'Gorana Goranovic', '901328943289', 111200300, 32143, 2, 1);
INSERT INTO racun (id, ime_prezime, jmbg, broj_racuna, stanje_racuna, tip_racuna_id, banka_id) VALUES (7, 'Milos Milosevic', '133129132894', 100222300, 8989843, 3, 1);
INSERT INTO racun (id, ime_prezime, jmbg, broj_racuna, stanje_racuna, tip_racuna_id, banka_id) VALUES (8, 'Sveta Svetic', '123904329043', 333400500, 321323, 3, 3);
INSERT INTO racun (id, ime_prezime, jmbg, broj_racuna, stanje_racuna, tip_racuna_id, banka_id) VALUES (9, 'Jovana Jovanovic', '871328913283', 600999200, 3000, 2, 2);
INSERT INTO racun (id, ime_prezime, jmbg, broj_racuna, stanje_racuna, tip_racuna_id, banka_id) VALUES (10, 'Marko Ilic', '312984328924', 111234000, 132123, 1, 2);
INSERT INTO racun (id, ime_prezime, jmbg, broj_racuna, stanje_racuna, tip_racuna_id, banka_id) VALUES (11, 'Vera Matovic', '234842389984', 12313243, 20023, 1, 1);


INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');