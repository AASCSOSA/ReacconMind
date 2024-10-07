-- Crear base de datos
CREATE DATABASE reacconMind;
USE reacconMind;

CREATE TABLE User (
    idUser INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(25) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255), -- Contraseña hasheada, puede ser NULL si el usuario se autentica con Google
    imageProfile VARCHAR(2083), -- URL para la imagen de perfil
    imageFacade VARCHAR(2083), -- URL para la imagen de fachada
    biography VARCHAR(50),
    username VARCHAR(50) NOT NULL UNIQUE,
    typeAuth ENUM('Email', 'Google') NOT NULL DEFAULT 'Email', -- Tipo de autenticación
    dateCreationProfile TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status ENUM ('Active','Inactive') DEFAULT 'Active', 
    theme ENUM ('Dark','Light') NOT NULL DEFAULT 'Light', -- 0: Tema claro, 1: Tema oscuro
    INDEX (email),
    INDEX (username)
);

CREATE TABLE Publication (
    idPublication INT PRIMARY KEY AUTO_INCREMENT,
    idUser INT,
    content VARCHAR(250),
    publicationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idUser) REFERENCES User(idUser) ON DELETE CASCADE
);

CREATE TABLE Image (
    idImage INT PRIMARY KEY AUTO_INCREMENT,
    url VARCHAR(2083) NOT NULL,
    thumbnail VARCHAR(2083),
    uploadDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    idPublication INT,
    FOREIGN KEY (idPublication) REFERENCES Publication(idPublication) ON DELETE CASCADE
);

CREATE TABLE Multimedia (
    id_multimedia INT PRIMARY KEY AUTO_INCREMENT,
    url VARCHAR(2083) NOT NULL,
    type ENUM('Image', 'Video', 'Audio') NOT NULL, -- Tipo de multimedia
    uploadDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Hashtag (
    idHashtag INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE PublicationHashtag (
    idPublication INT NOT NULL,
    idHashtag INT NOT NULL,
    FOREIGN KEY (idPublication) REFERENCES Publication(idPublication) ON DELETE CASCADE,
    FOREIGN KEY (idHashtag) REFERENCES Hashtag(idHashtag) ON DELETE CASCADE,
    PRIMARY KEY (idPublication, idHashtag) -- Llave primaria compuesta
);

CREATE TABLE Follower (
    idUserFollowing INT NOT NULL,
    idUserFollower INT NOT NULL,
    dateFollowing TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idUserFollowing) REFERENCES User(idUser) ON DELETE CASCADE,
    FOREIGN KEY (idUserFollower) REFERENCES User(idUser) ON DELETE CASCADE,
    PRIMARY KEY (idUserFollower, idUserFollowing), -- Llave primaria compuesta
    CONSTRAINT checkFollowerId
    CHECK (idUserFollower <> idUserFollowing)
);

CREATE TABLE Tendency (
    idTendency INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    dateBegin TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    dateEnd TIMESTAMP,
    CHECK (dateEnd IS NULL OR dateEnd >= dateBegin)
);

CREATE TABLE TendencyHashtag (
    idTendency INT NOT NULL,
    idHashtag INT NOT NULL,
    FOREIGN KEY (idTendency) REFERENCES Tendency(idTendency) ON DELETE CASCADE,
    FOREIGN KEY (idHashtag) REFERENCES Hashtag(idHashtag) ON DELETE CASCADE,
    PRIMARY KEY (idTendency, idHashtag) -- Llave primaria compuesta
);

CREATE TABLE ModerationType (
    idModerationType INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Moderation (
    idUser INT NOT NULL,
    idPublication INT NOT NULL,
    idModerationType INT NOT NULL, -- Referencia a la tabla de tipos de moderación
    moderationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idUser) REFERENCES User(idUser) ON DELETE CASCADE,
    FOREIGN KEY (idPublication) REFERENCES Publication(idPublication) ON DELETE CASCADE,
    FOREIGN KEY (idModerationType) REFERENCES ModerationType(idModerationType) ON DELETE CASCADE,
    UNIQUE (idUser, idPublication, idModerationType), -- Llave única para evitar duplicados
    PRIMARY KEY (idUser, idPublication, idModerationType) -- Llave primaria compuesta
);

CREATE TABLE Bot (
    idBot INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    theme ENUM('Sports', 'Technology', 'News', 'Music', 'Movies') NOT NULL,
    idMultimedia INT,
    shippingDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idMultimedia) REFERENCES Multimedia(id_multimedia) ON DELETE SET NULL
);

CREATE TABLE UsuarioBot (
    idUsuarioBot INT PRIMARY KEY AUTO_INCREMENT,
    idUser INT NOT NULL,
    idBot INT NOT NULL,
    followingDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idUser) REFERENCES User(idUser) ON DELETE CASCADE,
    FOREIGN KEY (idBot) REFERENCES Bot(idBot) ON DELETE CASCADE,
    UNIQUE (idUser, idBot)
);

CREATE TABLE Notification (
    idNotification INT PRIMARY KEY AUTO_INCREMENT,
    idUser INT NOT NULL,
    typeNotification ENUM('Message', 'Like', 'Follow', 'Comment') NOT NULL,
    content VARCHAR(50),
    estate ENUM('Read', 'Unread') NOT NULL,
    dateNotification TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idUser) REFERENCES User(idUser) ON DELETE CASCADE
);

CREATE TABLE Comment (
    idComment INT PRIMARY KEY AUTO_INCREMENT,
    idUser INT NOT NULL,
    idPublication INT NOT NULL,
    contentComment VARCHAR(200) NOT NULL,
    commentDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idUser) REFERENCES User(idUser) ON DELETE CASCADE,
    FOREIGN KEY (idPublication) REFERENCES Publication(idPublication) ON DELETE CASCADE
);

CREATE TABLE Reply (
    idReply INT PRIMARY KEY AUTO_INCREMENT,
    idUser INT NOT NULL,
    idComment INT NOT NULL,
    contentReply VARCHAR(255) NOT NULL,
    replyDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idUser) REFERENCES User(idUser) ON DELETE CASCADE,
    FOREIGN KEY (idComment) REFERENCES Comment(idComment) ON DELETE CASCADE
);

CREATE TABLE Reaction (
    idUser INT NOT NULL,
    idPublication INT,
    idComment INT,
    liked BOOLEAN NOT NULL, -- true: le gusta, false: no le gusta
    reactionDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idUser) REFERENCES User(idUser) ON DELETE CASCADE,
    FOREIGN KEY (idPublication) REFERENCES Publication(idPublication) ON DELETE CASCADE,
    FOREIGN KEY (idComment) REFERENCES Comment(idComment) ON DELETE CASCADE,
    UNIQUE (idUser, idPublication, idComment), -- Evita múltiples reacciones
    PRIMARY KEY (idUser, idPublication, idComment) -- Llave primaria compuesta
);

CREATE TABLE MentionedUser (
    idPublication INT NOT NULL,
    idMentionedUser INT NOT NULL,
    FOREIGN KEY (idPublication) REFERENCES Publication(idPublication) ON DELETE CASCADE,
    FOREIGN KEY (idMentionedUser) REFERENCES User(idUser) ON DELETE CASCADE,
    UNIQUE (idPublication, idMentionedUser), -- Evita menciones duplicadas
    PRIMARY KEY (idPublication, idMentionedUser) -- Llave primaria compuesta
);

CREATE TABLE Message (
    idMessage INT PRIMARY KEY AUTO_INCREMENT,
    idSender INT NOT NULL,
    idAddressee INT NOT NULL,
    content TEXT NOT NULL,
    multimedia VARCHAR(255),
    shippingDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idSender) REFERENCES User(idUser) ON DELETE CASCADE,
    FOREIGN KEY (idAddressee) REFERENCES User(idUser) ON DELETE CASCADE,
    CHECK (idSender <> idAddressee) -- Asegura que no se envíen mensajes a sí mismos
);


CREATE TABLE PasswordResetToken (
    idResetToken INT PRIMARY KEY AUTO_INCREMENT,
    idUser INT NOT NULL,
    token VARCHAR(255) NOT NULL UNIQUE,
    expirationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    used BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (idUser) REFERENCES User(idUser) ON DELETE CASCADE
);

CREATE TABLE GoogleAuth (
    idGoogleAuth INT PRIMARY KEY AUTO_INCREMENT,
    idUser INT NOT NULL,
    googleId VARCHAR(255) NOT NULL UNIQUE,
    FOREIGN KEY (idUser) REFERENCES User(idUser) ON DELETE CASCADE
);
