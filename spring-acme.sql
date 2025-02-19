create table Users(
	id Serial NOT NULL PRIMARY KEY,
	name Varchar(100) NOT NULL,
	username Varchar(50) NOT NULL UNIQUE,
    celphone varchar(20) NOT NULL UNIQUE,
	email Varchar(255) NOT NULL UNIQUE,
    date_birth DATE NOT NULL,
	password Varchar(255) NOT NULL UNIQUE,
	bibliography TEXT DEFAULT '',
	profile_picture TEXT DEFAULT 'https://images.unsplash.com/photo-1511367461989-f85a21fda167?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cHJvZmlsZXxlbnwwfHwwfHx8MA%3D%3D',
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


create table Posts(
	id Serial NOT NULL PRIMARY KEY,
	user_id int NOT NULL,
	image_url TEXT,
	description TEXT CHECK(LENGTH(description) BETWEEN 5 AND 500),
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	FOREIGN KEY(user_id) REFERENCES Users(id) ON DELETE CASCADE
);

create table Tags(
	id Serial NOT NULL PRIMARY KEY,
    tag Varchar(255) NOT NULL UNIQUE
);

create table Post_Tag (
    post_id INT NOT NULL,
    tag_id INT NOT NULL,
    PRIMARY KEY (post_id, tag_id),
    FOREIGN KEY (post_id) REFERENCES Posts(id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES Tags(id) ON DELETE CASCADE
);


create table Likes(
	id Serial NOT NULL PRIMARY KEY,
	user_id int NOT NULL,
	post_id int NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY(user_id) REFERENCES Users(id) ON DELETE CASCADE,
	FOREIGN KEY(post_id) REFERENCES Posts(id) ON DELETE CASCADE
);

create table Follows(
	id Serial NOT NULL PRIMARY KEY,
	user_id int NOT NULL,
	followed_id int NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY(user_id) REFERENCES Users(id) ON DELETE CASCADE,
	FOREIGN KEY(followed_id) REFERENCES Users(id) ON DELETE CASCADE
);

create table Notifications(
	id Serial NOT NULL PRIMARY KEY,
	sender_id int NOT NULL,
	receiver_id int NOT NULL,
	subject TEXT NOT NULL,
	description TEXT NOT NULL,
	is_read Boolean NOT NULL DEFAULT FALSE,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY(sender_id) REFERENCES Users(id) ON DELETE CASCADE,
	FOREIGN KEY(receiver_id) REFERENCES Users(id) ON DELETE CASCADE
);

CREATE TABLE Comments (
    id SERIAL PRIMARY KEY,
    comment_id INT,
    post_id INT NOT NULL,
    user_id INT NOT NULL, 
    content TEXT NOT NULL CHECK (LENGTH(content) BETWEEN 1 AND 200),
    role TEXT DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (post_id) REFERENCES Posts(id) ON DELETE CASCADE,
    FOREIGN KEY (comment_id) REFERENCES Comments(id) ON DELETE CASCADE
);

CREATE TABLE Comment_Mentions (
    comment_id INT NOT NULL,
    mentioned_user_id INT NOT NULL,
    PRIMARY KEY (comment_id, mentioned_user_id),
    FOREIGN KEY (comment_id) REFERENCES Comments(id) ON DELETE CASCADE,
    FOREIGN KEY (mentioned_user_id) REFERENCES Users(id) ON DELETE CASCADE
);


