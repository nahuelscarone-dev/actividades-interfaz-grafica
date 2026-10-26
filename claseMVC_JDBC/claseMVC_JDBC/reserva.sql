-- ╔══════════════════════════════════════════════════════════════╗
-- ║  reserva.sql — crea la base de datos y la tabla de animales  ║
-- ║  Se ejecuta UNA vez (phpMyAdmin → pestaña SQL → pegar → Continuar) ║
-- ╚══════════════════════════════════════════════════════════════╝

-- 1) La base de datos (como una carpeta que contiene las tablas)
CREATE DATABASE IF NOT EXISTS reserva CHARACTER SET utf8mb4;
USE reserva;

-- 2) La tabla: una columna por cada atributo que queremos guardar
--    (es el equivalente a una línea de animales.txt, pero con columnas)
CREATE TABLE IF NOT EXISTS animales (
    id       INT AUTO_INCREMENT PRIMARY KEY,   -- número único, lo asigna MySQL solo
    especie  VARCHAR(30) NOT NULL,             -- Tigre, Lobo, Loro...
    nombre   VARCHAR(50) NOT NULL,
    edad     INT         NOT NULL,
    energia  INT         NOT NULL,
    estado   VARCHAR(20) NOT NULL              -- SALUDABLE, HERIDO... (el enum como texto)
);

-- 3) Los mismos animales que tenía animales.txt
INSERT INTO animales (especie, nombre, edad, energia, estado) VALUES
    ('Tigre', 'Shere Khan', 6, 100, 'SALUDABLE'),
    ('Zorro', 'Robin',      2, 100, 'SALUDABLE'),
    ('Aguila','Apolo',      5,  75, 'SALUDABLE'),
    ('Loro',  'Pancho',     8, 100, 'SALUDABLE'),
    ('Lobo',  'Fenrir',     3, 100, 'SALUDABLE');

-- 4) Para comprobar que quedó bien
SELECT * FROM animales;
