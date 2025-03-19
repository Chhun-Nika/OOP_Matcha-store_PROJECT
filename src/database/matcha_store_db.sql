
CREATE DATABASE IF NOT EXISTS Matcha_store;

USE Matcha_store;

CREATE TABLE IF NOT EXISTS Customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR (255),
    phone_number VARCHAR(20) NOT NULL,
    email VARCHAR (255),
    password VARCHAR(255),
    dob DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS Product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    price DECIMAL(10,2),
    category ENUM('DRINK', 'DESSERT', 'RETAIL') NOT NULL,
    product_size VARCHAR(50) DEFAULT NULL,
    weight DECIMAL(10,2) DEFAULT NULL,
    expiry_date DATE DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS Cart (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    product_id INT NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2),
    total_price DECIMAL(10,2),
    FOREIGN KEY (customer_id) REFERENCES Customer(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES Product(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Receipt (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_method VARCHAR(50) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customer(id)
);

SELECT * FROM Customer;

CREATE TABLE Seller (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO Seller (name, email, password)
VALUES ('Admin Seller', 'seller@gmail.com', 'seller123');
