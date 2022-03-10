CREATE TABLE "movie" ( 
    "movieID" INT PRIMARY KEY NOT NULL,
    "title" VARCHAR NOT NULL,
    "description" VARCHAR,
    "cost" INT NOT NULL
);

CREATE TABLE "user" (
    "userID" INT PRIMARY KEY NOT NULL,
    "userName" VARCHAR NOT NULL,
    "userEmail" VARCHAR NOT NULL,
    "userPassword" VARCHAR,
    "creditCard" VARCHAR,
    "userChoice" INT NOT NULL,
    CONSTRAINT "FK_userChoice" FOREIGN KEY ("userChoice") REFERENCES "movie" ("movieID")
    );

CREATE INDEX "IFK_userChoice" ON "user" ("movieID");


