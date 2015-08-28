CREATE TABLE post (
    id INTEGER PRIMARY KEY, 
    user_id INTEGER, 
    content VARCHAR(140), 
    post_date DATE
);

CREATE TABLE tweetUser (
    id INTEGER PRIMARY KEY, 
    name VARCHAR(50), 
    password VARCHAR(20), 
    motto VARCHAR(50), 
    join_date DATE
);

CREATE SEQUENCE POST_ID_GENERATOR START WITH 2;
CREATE SEQUENCE TWEETUSER_ID_GENERATOR START WITH 2;