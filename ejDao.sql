create database Ej;
use Ej;


CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255),
    password VARCHAR(255),
    is_admin BOOLEAN
);


