
CREATE DATABASE IF NOT EXISTS Matcha_store;

USE Matcha_store;

CREATE TABLE IF NOT EXISTS Customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    dob DATE NOT NULL,
    password VARCHAR(255),
    phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS Product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    price DECIMAL(10,2),
    category ENUM('DRINK', 'DESSERT', 'RETAIL') NOT NULL,
    product_size VARCHAR(50) NULL,
    weight DECIMAL(10,2) NULL,
    expiry_date DATE NULL,
    quantity INT NULL
);

SELECT id, name, description, price, category, product_size, weight, expiry_date, quantity FROM Product;

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


SELECT * FROM Product;



