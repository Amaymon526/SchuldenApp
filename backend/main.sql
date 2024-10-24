-- Erstelle eine neue Datenbank
CREATE DATABASE debt_management;

-- Verwende die erstellte Datenbank
USE debt_management;

-- Erstelle die Tabelle für Schulden
CREATE TABLE debts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(255) NOT NULL,
    invoice_link VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    information TEXT NOT NULL,
    percentage_paid DECIMAL(5, 2) NOT NULL DEFAULT 0,
    date DATE NOT NULL,
    screenshot_path VARCHAR(255)
);
