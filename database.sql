CREATE DATABASE IF NOT EXISTS TETRIS;

USE TETRIS;

CREATE TABLE USUARIO(
    nombreUsuario VARCHAR(20) NOT NULL,
    contrasena VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    PRIMARY KEY(nombreUsuario)
);

CREATE TABLE PARTIDA(
    id INT NOT NULL,
    puntos INT NOT NULL,
    estadoTablero VARCHAR(8000) NOT NULL,
    nombreUsuario VARCHAR(20) NOT NULL,
    dificultad VARCHAR(20) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(nombreUsuario)
    REFERENCES USUARIO(nombreUsuario),
    FOREIGN KEY(dificultad)
    REFERENCES DIFICULTAD(nivelDificultad)
);

CREATE TABLE PREMIO(
    nombre VARCHAR(20) NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    progresoMax INT NOT NULL,
    PRIMARY KEY(nombre)
);

CREATE TABLE DIFICULTAD(
    nivelDificultad VARCHAR(20) NOT NULL,
    velocidadLadrillos DOUBLE(4,2) NOT NULL, --Ejemplo: 23.47
    tamanoTablero VARCHAR(5) NOT NULL, --Ejemplo: 05x20
    PRIMARY KEY(nivelDificultad)
);

CREATE TABLE PREMIOOBTENIDO(
    nombrePremio VARCHAR(20) NOT NULL,
    nombreUsuario VARCHAR(20) NOT NULL,
    progreso INT NOT NULL,
    PRIMARY KEY(nombrePremio,nombreUsuario)
    FOREIGN KEY(nombrePremio)
    REFERENCES PREMIO(nombre),
    FOREIGN KEY(nombreUsuario)
    REFERENCES USUARIO(nombreUsuario)
);

CREATE TABLE RANKING(
    nombreUsuario VARCHAR(20) NOT NULL,
    nivelDificultad VARCHAR(20) NOT NULL,
    fechaHora DATETIME NOT NULL,
    puntos INT NOT NULL,
    PRIMARY KEY(nombreUsuario,nivelDificultad),
    FOREIGN KEY(nombreUsuario)
    REFERENCES USUARIO(nombreUsuario),
    FOREIGN KEY(nivelDificultad)
    REFERENCES DIFICULTAD(nivelDificultad)
);

CREATE TABLE PREMIOSENPARTIDA(
    idPartida INT NOT NULL, 
    nombrePremio VARCHAR(20) NOT NULL,
    progreso INT NOT NULL,
    PRIMARY KEY(idPartida,nombrePremio),
    FOREIGN KEY(idPartida)
    REFERENCES PARTIDA(id)
    FOREIGN KEY(nombrePremio)
    REFERENCES PREMIO(nombre)
);

CREATE TABLE PERSONALIZACION(
    nombreUsuario VARCHAR(20) NOT NULL,
    codColorFondo INT NOT NULL,
    codColorLadrillo INT NOT NULL,
    codNombreMusica INT NOT NULL,
    PRIMARY KEY(nombreUsuario),
    FOREIGN KEY(nombreUsuario)
    REFERENCES USUARIO(nombreUsuario),
    FOREIGN KEY(codColorFondo)
    REFERENCES COLORFONDO(codColorFondo),
    FOREIGN KEY(codColorLadrillo)
    REFERENCES COLORLADRILLO(codColorLadrillo),
    FOREIGN KEY(codNombreMusica)
    REFERENCES MUSICA(codNombreMusica)
);

CREATE TABLE COLORFONDO(
    codColorFondo INT NOT NULL,
    nombreColor VARCHAR(20) NOT NULL,
    PRIMARY KEY(codColorFondo)
);

CREATE TABLE COLORLADRILLO(
    codColorLadrillo INT NOT NULL,
    nombreColor VARCHAR(20) NOT NULL,
    PRIMARY KEY(codColorLadrillo)
);

CREATE TABLE MUSICA(
    codNombreMusica INT NOT NULL,
    nombreMusica VARCHAR(20) NOT NULL,
    PRIMARY KEY(codNombreMusica)
);

--INSERT INTO DIFICULTAD VALUES("",,);
--INSERT INTO PREMIO VALUES(...);

--Añadir en el diagrama ER la dificultad de partida
