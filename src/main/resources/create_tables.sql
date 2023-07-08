CREATE TABLE customer (
  id SERIAL PRIMARY KEY,
  names VARCHAR(255) NOT NULL,
  surname VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phone VARCHAR(255) NOT NULL
);

CREATE TABLE orders (
  id SERIAL PRIMARY KEY,
  order_date DATE NOT NULL,
  order_status VARCHAR(20) NOT NULL,
  payment_method VARCHAR(20) NOT NULL,
  customer_id INT REFERENCES customer(id)
);

CREATE TABLE product (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
  description TEXT,
  in_stock BOOLEAN NOT NULL
);

CREATE TABLE orderDetail (
  id SERIAL PRIMARY KEY,
  order_id INT REFERENCES orders(id),
  product_id INT REFERENCES product(id),
  price DECIMAL(10, 2) NOT NULL,
  quantity INT NOT NULL
);