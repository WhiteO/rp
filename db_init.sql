DROP TABLE packets;

CREATE TABLE packets (
    id          INTEGER        PRIMARY KEY AUTOINCREMENT
                               NOT NULL,
    bindId      BLOB (16),
    clientVerId BLOB (16),
    objectId    BLOB (16),
    firstId     BLOB (16),
    parentId    BLOB (16),
    name        VARCHAR (255),
    comment     VARCHAR (1024),
    isAccepted  BOOLEAN        NOT NULL
                               DEFAULT (false),
    isSent       BOOLEAN       NOT NULL
                               DEFAULT (false) 
);