CREATE TABLE "movie" ( 
    "movieID" INT PRIMARY KEY NOT NULL,
    "imdb_id" VARCHAR NOT NULL,
    "title" VARCHAR NOT NULL,
    "overview" VARCHAR,
    "releaseDate" VARCHAR,
    "cost" FLOAT NOT NULL
);

CREATE TABLE "user1" (
    "userID" INT NOT NULL,
    "userName" VARCHAR NOT NULL,
    "email" VARCHAR NOT NULL,
    "password" VARCHAR,
    "imdb_id" VARCHAR,
    --CONSTRAINT "PK_user" PRIMARY KEY ("userID"),
    --CONSTRAINT "FK_userChoice" FOREIGN KEY ("userID") REFERENCES "movie" ("movieID") ON DELETE NO ACTION ON UPDATE NO ACTION
    );

--CREATE INDEX "IFK_userChoice" ON "user1" ("userID");

INSERT INTO "movie" VALUES(1, 'XXXXSKY1', 'No Highway1', 'plane crashed1', '1-1-1950', 1);
INSERT INTO "movie" VALUES(2, 'XXXXSKY2', 'Highway No2', 'crashed plane2', '1-2-1950', 2);
