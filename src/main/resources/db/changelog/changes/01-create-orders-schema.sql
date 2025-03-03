--liquibase formatted sql
--changeset trae:1

CREATE SEQUENCE order_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE order_item_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE orders (
    id VARCHAR(255) PRIMARY KEY,
    order_number VARCHAR(50) UNIQUE NOT NULL,
    customer_id VARCHAR(255) NOT NULL,
    total_amount DECIMAL(19,2),
    status VARCHAR(50),
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE order_items (
    id VARCHAR(255) PRIMARY KEY,
    item_number VARCHAR(50) UNIQUE NOT NULL,
    order_id VARCHAR(255) NOT NULL,
    product_id VARCHAR(255) NOT NULL,
    quantity INT,
    unit_price DECIMAL(19,2),
    subtotal DECIMAL(19,2),
    CONSTRAINT fk_order_items_orders FOREIGN KEY (order_id) REFERENCES orders(id)
);