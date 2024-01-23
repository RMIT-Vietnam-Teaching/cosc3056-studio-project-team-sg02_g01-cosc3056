PRAGMA foreign_keys = OFF;
DROP TABLE IF EXISTS "teamMembers";
DROP TABLE IF EXISTS "personas";
DROP TABLE IF EXISTS "countries";
DROP TABLE IF EXISTS "cities";
DROP TABLE IF EXISTS "states";
DROP TABLE IF EXISTS "cityTemp";
DROP TABLE IF EXISTS "countryTemp";
DROP TABLE IF EXISTS "stateTemp";
DROP TABLE IF EXISTS "worldTemp";
DROP TABLE IF EXISTS "population";
PRAGMA foreign_keys = ON;

CREATE TABLE "teamMembers"(
    "id"      TEXT    PRIMARY KEY,
    "name"    TEXT,
    "img_path"      TEXT
);

CREATE TABLE "personas"(
    "name"          TEXT    PRIMARY KEY,
    "age"           INTEGER,
    "location"      TEXT,
    "background"    TEXT,
    "quotes"        TEXT,
    "needs"         TEXT,
    "goals"         TEXT,
    "skills"        TEXT,
    "img_path"      TEXT
);

CREATE TABLE "countries"(
    "name"  TEXT    PRIMARY KEY,
    "code"  CHAR(3) UNIQUE
);

CREATE TABLE "cities"(
    "id"            INTEGER     UNIQUE    NOT NULL,
    "countryCode"   CHAR(3),
    "name"          TEXT,
    "latitude"      TEXT,
    "longitude"     TEXT,
    PRIMARY KEY ("name", "countryCode")
    FOREIGN KEY ("countryCode") REFERENCES countries("code")
);

CREATE TABLE "states"(
    "id"            INTEGER     UNIQUE    NOT NULL,
    "countryCode"   CHAR(3),
    "name"          TEXT,
    PRIMARY KEY ("name", "countryCode")
    FOREIGN KEY ("countryCode") REFERENCES countries("code")
);

CREATE TABLE "cityTemp"(
    "year"          INTEGER,
    "cityID"        INTEGER,
    "landAvgTemp"   REAL,
    "landMinTemp"   REAL,
    "landMaxTemp"   REAL,
    PRIMARY KEY ("year", "cityID"),
    FOREIGN KEY ("cityID") REFERENCES cities("id")
);

CREATE TABLE "countryTemp"(
    "year"          INTEGER,
    "countryCode"   CHAR(3),
    "landAvgTemp"   REAL,
    "landMinTemp"   REAL,
    "landMaxTemp"   REAL,
    PRIMARY KEY ("year", "countryCode"),
    FOREIGN KEY ("countryCode") REFERENCES countries("code")
);

CREATE TABLE "stateTemp"(
    "year"          INTEGER,
    "stateID"       INTEGER,
    "landAvgTemp"   REAL,
    "landMinTemp"   REAL,
    "landMaxTemp"   REAL,
    PRIMARY KEY ("year", "stateID"),
    FOREIGN KEY ("stateID") REFERENCES states("id")
);

CREATE TABLE "worldTemp"(
    "year"  INTEGER PRIMARY KEY,
    "landOceanAvgTemp"   REAL,
    "landOceanMinTemp"   REAL,
    "landOceanMaxTemp"   REAL,
    "landAvgTemp"        REAL,
    "landMinTemp"        REAL,
    "landMaxTemp"        REAL
);

CREATE TABLE "population"(
    "year"          INTEGER,
    "countryCode"   CHAR(3),
    "populationNum" INTEGER,
    PRIMARY KEY ("year", "countryCode"),
    FOREIGN KEY ("countryCode") REFERENCES countries("code")
);