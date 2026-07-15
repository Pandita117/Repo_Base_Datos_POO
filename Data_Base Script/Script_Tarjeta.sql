USE master
GO
CREATE DATABASE TARJETAS_BD
GO
USE TARJETAS_BD
GO

CREATE TABLE usuarios_emiel(
    id_user INT IDENTITY (1,1),
    nombre NVARCHAR(50) NOT NULL,
    apellido NVARCHAR(50) NOT NULL,
    edad INT NOT NULL,
    email NVARCHAR(50) UNIQUE NOT NULL,
    password NVARCHAR(20) NOT NULL,
    activo BIT DEFAULT 1,
    PRIMARY KEY (id_user)
);
GO

CREATE TABLE tarjetas_emiel(
    id_tarjeta INT IDENTITY (1,1),
    clabe CHAR(18) UNIQUE NOT NULL,
    n_tar CHAR(16) UNIQUE NOT NULL,
    f_exp DATE NOT NULL,
    saldo DECIMAL(18,2) NOT NULL,
    tipo CHAR(1) NOT NULL,
    credito DECIMAL(18,2) DEFAULT 0,
    activo BIT DEFAULT 1,
    id_user INT NOT NULL,
    PRIMARY KEY (id_tarjeta),
    FOREIGN KEY (id_user) REFERENCES usuarios_emiel(id_user)
);
GO
