set names utf8mb4;

-- usersテーブル(CREATE / INSERT)
CREATE TABLE users (
id INT AUTO_INCREMENT PRIMARY KEY,
email VARCHAR(255) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL,
created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (email, password) VALUES
('testuser1@email.com', '$2a$10$uVPSuuB08SWuWISYUwXOFu.1GG5yG24JAuDRc6UDkwX8yONDN.tzu'),
('testuser2@email.com', '$2a$10$xd.7oxAMSommk3geOBi7te5W3jpxCFqkB7cN6v.V7lmnX5tx6Ds9.');


-- categoriesテーブル(CREATE / INSERT)
CREATE TABLE categories (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  category_name	VARCHAR(30) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO categories (user_id, category_name) VALUES
  (1, '食費'),
  (1, '趣味・娯楽'),
  (1, 'その他'),
  (2, '食費'),
  (2, '趣味・娯楽'),
  (2, 'その他');


-- monthlyテーブル(CREATE / INSERT)
CREATE TABLE monthly (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  financial_category BOOLEAN NOT NULL DEFAULT TRUE,
  fixed_name VARCHAR(255) NOT NULL,
  monthly_amount INT(255) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO monthly (user_id, financial_category, fixed_name, monthly_amount) VALUES
  (1, TRUE, '収入', 200000),
  (1, FALSE, '家賃', 50000),
  (1, FALSE, '光熱費', 12000),
  (1, FALSE, 'スマホ・通信費', 10000),
  (1, FALSE, 'サブスクリプション', 3000),
  (2, TRUE, '収入', 300000),
  (2, FALSE, '家賃', 70000),
  (2, FALSE, '光熱費', 15000),
  (2, FALSE, 'スマホ・通信費', 10000),
  (2, FALSE, '保険料', 20000);


-- dailyテーブル(CREATE / INSERT)
CREATE TABLE daily (
 id INT AUTO_INCREMENT PRIMARY KEY,
 user_id INT NOT NULL,
 date DATE NOT NULL DEFAULT (CURRENT_DATE),
 category_name VARCHAR(30)	NOT NULL,
 in_out BOOLEAN NOT NULL DEFAULT TRUE,
 daily_amount INT(255) NOT NULL,
 FOREIGN KEY (user_id) REFERENCES users (id)
);

INSERT INTO daily (user_id, date, category_name, in_out, daily_amount) VALUES 
(1, '2026-05-10', '趣味・娯楽', TRUE, 5000),
(1, '2026-06-04', 'その他', FALSE, 10000),
(1, '2026-06-10', '食費', TRUE, 10000),
(2, '2026-05-10', '趣味・娯楽', TRUE, 7000),
(2, '2026-06-04', 'その他', FALSE, 15000),
(2, '2026-06-10', '食費', TRUE, 20000);


