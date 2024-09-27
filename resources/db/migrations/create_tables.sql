# user table for authentication
CREATE TABLE IF NOT EXISTS users(
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    username VARCHAR(50) NOT NULL,
                                    password VARCHAR(50) NOT NULL UNIQUE,
                                    role ENUM('admin','manager','accountant') NOT NULL,
                                    status INT DEFAULT 1,
                                    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS contacts(
                                    id INT AUTO_INCREMENT PRIMARY KEY ,
                                    email VARCHAR(100) UNIQUE,
                                    phone VARCHAR(100) UNIQUE ,
                                    address VARCHAR(255)
);

# people
CREATE TABLE  IF NOT EXISTS people(
                                         id INT AUTO_INCREMENT PRIMARY KEY ,
                                         name VARCHAR(100),
                                         type ENUM('Customer','Supplier'),
                                         contact_id INT,
                                         status INT DEFAULT 1,
                                         created_by INT,
                                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                         FOREIGN KEY (contact_id) REFERENCES contacts(id) ON UPDATE CASCADE ON DELETE CASCADE ,
                                         FOREIGN KEY (created_by) REFERENCES users(id)
);

#categories
CREATE TABLE IF NOT EXISTS categories(
                                        id INT AUTO_INCREMENT PRIMARY KEY ,
                                        name VARCHAR(50) NOT NULL UNIQUE ,
                                        status INT DEFAULT 1,
                                        description  TEXT
);

# items
CREATE TABLE IF NOT EXISTS items(
                                    id INT AUTO_INCREMENT PRIMARY KEY ,
                                    code VARCHAR(100) NOT NULL UNIQUE ,
                                    name VARCHAR(100) NOT NULL,
                                    category_id INT NOT NULL,
                                    qty INT NOT NULL,
                                    cost DECIMAL(10,2) NOT NULL ,
                                    price DECIMAL(10,2) NOT NULL,
                                    status INT DEFAULT 1,
                                    created_by INT,
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                    FOREIGN KEY (category_id) REFERENCES categories(id) ON UPDATE CASCADE ON DELETE CASCADE ,
                                    FOREIGN KEY (created_by) REFERENCES users(id)
);

#warehouse
CREATE TABLE IF NOT EXISTS warehouses(
                                    id INT AUTO_INCREMENT PRIMARY KEY ,
                                    name VARCHAR(50) NOT NULL ,
                                    type varchar(50) ,
                                    status INT DEFAULT 1,
                                    location VARCHAR(255)
);




# processes
CREATE TABLE IF NOT EXISTS processes(
                                        id INT AUTO_INCREMENT PRIMARY KEY ,
                                        people_id INT NOT NULL ,
                                        item_id INT NOT NULL ,
                                        qty INT NOT NULL,
                                        price DECIMAL(10,2) NOT NULL,
                                        warehouse_id INT,
                                        type ENUM('sale','purchase'),
                                        status INT DEFAULT 1,
                                        created_by INT ,
                                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        FOREIGN KEY (people_id) REFERENCES people(id),
                                        FOREIGN KEY (item_id) REFERENCES items(id),
                                        FOREIGN KEY (created_by) REFERENCES users(id)
);

#payments
CREATE TABLE IF NOT EXISTS payments(
                                        id INT AUTO_INCREMENT PRIMARY KEY ,
                                        type VARCHAR(50) NOT NULL UNIQUE,
                                        status INT DEFAULT 1
);

#invoices
CREATE TABLE  IF NOT EXISTS invoices
(
        id            INT AUTO_INCREMENT PRIMARY KEY,
        invoiceNumber VARCHAR(100) NOT NULL UNIQUE,
        invoiceDate DATE,
        total decimal(10,2) NOT NULL,
        status INT DEFAULT 1,
        created_by INT ,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        FOREIGN KEY (created_by) REFERENCES users(id)
);

#process_invoices
CREATE TABLE IF NOT EXISTS process_invoices
(
    id INT AUTO_INCREMENT PRIMARY KEY ,
    invoice_id  INT NOT NULL ,
    process_id INT NOT NULL,
    processDate DATE,
    payment_id INT NOT NULL,
    FOREIGN KEY(payment_id) REFERENCES payments(id)
);

#Monthly
CREATE TABLE IF NOT EXISTS monthly(
    id INT primary key auto_increment,
    name VARCHAR(50) NOT NULL,
    startDate DATE,
    endDate DATE
);

#monthly_inventories
CREATE TABLE IF NOT EXISTS stockDetails(
    id INT AUTO_INCREMENT PRIMARY KEY ,
    warehouse_id INT NOT NULL,
    monthly_id INT,
    item_id INT,
    openingQty INT,
    closingQty INT,
    transferIn INT,
    transferOut INT,
    status INT DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by INT,
    FOREIGN KEY (warehouse_id) REFERENCES warehouses(id),
    FOREIGN KEY (monthly_id) REFERENCES monthly(id),
    FOREIGN KEY (item_id) REFERENCES items(id),
    FOREIGN KEY (created_by) REFERENCES users(id)
);

