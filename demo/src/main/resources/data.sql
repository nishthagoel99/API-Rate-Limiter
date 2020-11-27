

create table rate_database( userID VARCHAR(100) NOT NULL ,api  VARCHAR(100) NOT NULL,max_rate INTEGER DEFAULT 0,PRIMARY KEY(UserID, api));
INSERT INTO rate_database (userID, api  , max_rate) VALUES('manoj', 'documents', 3);
 INSERT INTO rate_database (userID, api  , max_rate) VALUES ('ashwini', 'documents', 6 );
 INSERT INTO rate_database (userID, api  , max_rate) VALUES ('ashwini', 'login', 3 );
  INSERT INTO rate_database (userID, api  , max_rate) VALUES('nishtha', 'login', 4);
    INSERT INTO rate_database (userID, api) VALUES('manoj2', 'login');

commit;