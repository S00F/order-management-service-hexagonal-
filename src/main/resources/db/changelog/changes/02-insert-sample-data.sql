--liquibase formatted sql
--changeset trae:2

INSERT INTO orders (id, order_number, customer_id, total_amount, status, created_at, updated_at)
VALUES ('1', 'ORD-2023-001', 'CUST001', 299.98, 'CREATED', '2023-11-14 10:00:00', '2023-11-14 10:00:00');

INSERT INTO order_items (id, item_number, order_id, product_id, quantity, unit_price, subtotal)
VALUES ('1', 'ITEM-001-001', '1', 'PROD001', 2, 149.99, 299.98);

INSERT INTO orders (id, order_number, customer_id, total_amount, status, created_at, updated_at)
VALUES ('2', 'ORD-2023-002', 'CUST002', 499.95, 'CONFIRMED', '2023-11-14 11:00:00', '2023-11-14 11:30:00');

INSERT INTO order_items (id, item_number, order_id, product_id, quantity, unit_price, subtotal)
VALUES ('2', 'ITEM-002-001', '2', 'PROD002', 3, 166.65, 499.95);