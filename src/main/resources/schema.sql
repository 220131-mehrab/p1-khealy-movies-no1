CREATE TABLE "movie" ( 
    "movieID" INT PRIMARY KEY NOT NULL,
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

INSERT INTO "movie" VALUES (1, 'No Highway', 'plane crashed', "1-1-1950", 2);
INSERT INTO "movie" VALUES (2, 'Highway No', 'crashed plane', "1-1-1950", 3);
