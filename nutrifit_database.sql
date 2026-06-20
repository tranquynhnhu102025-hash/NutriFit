-- =============================================
-- DATABASE NUTRIFIT - Ứng dụng quản lý dinh dưỡng và tập luyện
-- =============================================

CREATE DATABASE IF NOT EXISTS nutrifit;
USE nutrifit;

-- =============================================
-- BẢNG 1: USER - Thông tin người dùng
-- =============================================
CREATE TABLE IF NOT EXISTS user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100),
    age INT,
    gender VARCHAR(10),
    height FLOAT,
    weight FLOAT,
    goal VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO user (username, email, password, full_name, age, gender, height, weight, goal) VALUES
('hoangduy', 'hoangduy@gmail.com', '123456', 'Nguyễn Hoàng Duy', 20, 'Nam', 170, 65, 'Giảm cân'),
('minhtu', 'minhtu@gmail.com', '123456', 'Trần Minh Tú', 22, 'Nữ', 160, 52, 'Tăng cơ'),
('vananh', 'vananh@gmail.com', '123456', 'Lê Vân Anh', 21, 'Nữ', 165, 55, 'Duy trì'),
('quochuy', 'quochuy@gmail.com', '123456', 'Phạm Quốc Huy', 23, 'Nam', 175, 72, 'Giảm cân');

-- =============================================
-- BẢNG 2: FOOD_DICTIONARY - Từ điển thực phẩm
-- =============================================
CREATE TABLE IF NOT EXISTS food_dictionary (
    id INT AUTO_INCREMENT PRIMARY KEY,
    food_name VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    calories FLOAT DEFAULT 0,
    protein FLOAT DEFAULT 0,
    carbs FLOAT DEFAULT 0,
    fat FLOAT DEFAULT 0,
    fiber FLOAT DEFAULT 0,
    serving_size VARCHAR(50),
    unit VARCHAR(20)
);

INSERT INTO food_dictionary (food_name, category, calories, protein, carbs, fat, fiber, serving_size, unit) VALUES
('Phở bò', 'Món nước', 450, 25, 60, 10, 1, '1 tô', 'tô'),
('Cơm gà xối mỡ', 'Cơm', 650, 35, 80, 18, 2, '1 đĩa', 'đĩa'),
('Bánh mì trứng', 'Bánh mì', 350, 15, 40, 12, 1, '1 ổ', 'ổ'),
('Bún chả', 'Món nước', 550, 28, 65, 15, 2, '1 suất', 'suất'),
('Salad cá hồi', 'Salad', 380, 30, 15, 20, 3, '1 đĩa', 'đĩa'),
('Sữa chua và trái cây', 'Tráng miệng', 200, 8, 30, 5, 2, '1 phần', 'phần'),
('Cơm tấm sườn', 'Cơm', 700, 32, 85, 22, 1, '1 đĩa', 'đĩa'),
('Gỏi cuốn', 'Món cuốn', 180, 10, 25, 3, 2, '2 cuốn', 'cuốn'),
('Trứng luộc', 'Trứng', 155, 13, 1, 11, 0, '2 quả', 'quả'),
('Ức gà nướng', 'Thịt', 280, 45, 0, 8, 0, '200g', 'gram');

-- =============================================
-- BẢNG 3: NUTRITION_LOG - Nhật ký dinh dưỡng
-- =============================================
CREATE TABLE IF NOT EXISTS nutrition_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    log_date DATE NOT NULL,
    meal_type VARCHAR(20) NOT NULL,
    food_name VARCHAR(100) NOT NULL,
    calories FLOAT DEFAULT 0,
    protein FLOAT DEFAULT 0,
    carbs FLOAT DEFAULT 0,
    fat FLOAT DEFAULT 0,
    note TEXT
);

INSERT INTO nutrition_log (user_id, log_date, meal_type, food_name, calories, protein, carbs, fat, note) VALUES
(1, '2026-06-21', 'Bữa sáng', 'Phở bò', 450, 25, 60, 10, 'Ăn tại quán quen'),
(1, '2026-06-21', 'Bữa trưa', 'Cơm gà xối mỡ', 650, 35, 80, 18, NULL),
(1, '2026-06-21', 'Bữa tối', 'Salad cá hồi', 380, 30, 15, 20, 'Ăn nhẹ buổi tối'),
(2, '2026-06-21', 'Bữa sáng', 'Bánh mì trứng', 350, 15, 40, 12, NULL),
(2, '2026-06-21', 'Bữa trưa', 'Bún chả', 550, 28, 65, 15, 'Bún chả Hà Nội'),
(2, '2026-06-21', 'Bữa tối', 'Sữa chua và trái cây', 200, 8, 30, 5, 'Ăn kiêng');

-- =============================================
-- BẢNG 4: WORKOUT_PLAN - Kế hoạch tập luyện
-- =============================================
CREATE TABLE IF NOT EXISTS workout_plan (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    plan_name VARCHAR(100) NOT NULL,
    day_of_week VARCHAR(20) NOT NULL,
    exercise_name VARCHAR(100) NOT NULL,
    sets INT DEFAULT 0,
    reps INT DEFAULT 0,
    duration_minutes INT DEFAULT 0,
    calories_burned FLOAT DEFAULT 0,
    note TEXT
);

INSERT INTO workout_plan (user_id, plan_name, day_of_week, exercise_name, sets, reps, duration_minutes, calories_burned, note) VALUES
(1, 'Kế hoạch giảm cân', 'Thứ 2', 'Chạy bộ', 1, 1, 30, 300, 'Chạy nhẹ nhàng'),
(1, 'Kế hoạch giảm cân', 'Thứ 2', 'Plank', 3, 1, 5, 50, 'Giữ 60 giây mỗi set'),
(1, 'Kế hoạch giảm cân', 'Thứ 4', 'Đạp xe', 1, 1, 45, 400, NULL),
(1, 'Kế hoạch giảm cân', 'Thứ 6', 'Bơi lội', 1, 1, 40, 350, 'Bơi tự do'),
(2, 'Tăng cơ', 'Thứ 3', 'Bench Press', 4, 10, 20, 150, 'Tăng tạ dần'),
(2, 'Tăng cơ', 'Thứ 3', 'Squat', 4, 12, 15, 180, NULL),
(2, 'Tăng cơ', 'Thứ 5', 'Deadlift', 3, 8, 20, 200, 'Chú ý giữ lưng thẳng'),
(2, 'Tăng cơ', 'Thứ 7', 'Pull-up', 3, 10, 10, 120, NULL);
