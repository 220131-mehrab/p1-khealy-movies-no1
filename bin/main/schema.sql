CREATE TABLE "movie" ( 
    "movieID" INT PRIMARY KEY NOT NULL,
    "imdb_id" VARCHAR NOT NULL,
    "title" VARCHAR NOT NULL,
    "overview" VARCHAR,
    "releaseDate" VARCHAR,
    "cost" INT NOT NULL
);

CREATE TABLE "user" (
    "userID" INT NOT NULL,
    "userName" VARCHAR NOT NULL,
    "userEmail" VARCHAR NOT NULL,
    "userPassword" VARCHAR,
    "creditCard" VARCHAR,
    "userChoice" INT NOT NULL,
    CONSTRAINT "PK_user" PRIMARY KEY ("userID"),
    CONSTRAINT "FK_userChoice" FOREIGN KEY ("userID") REFERENCES "movie" ("movieID")
    );

--CREATE INDEX "IFK_userChoice" ON "user" ("userID");

INSERT INTO "movie" VALUES(1, 'XXXXSKY1', 'No Highway1', 'plane crashed1', '1-1-1950', 1);
INSERT INTO "movie" VALUES(2, 'XXXXSKY2', 'Highway No2', 'crashed plane2', '1-2-1950', 2);
