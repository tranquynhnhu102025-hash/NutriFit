const express = require('express');
const mysql = require('mysql2');
const app = express();
const PORT = 3000; 

// 1. Cấu hình kết nối tới MySQL
const db = mysql.createConnection({
    host: '127.0.0.1',
    port: 3306,         
    user: 'root',       
    password: '123456',       
    database: 'nutrifit' 
});

db.connect((err) => {
    if (err) {
        console.error('Lỗi kết nối MySQL: ' + err.message);
        return;
    }
    console.log('Đã kết nối thành công tới database nutrifit!');
});

// 2. Định nghĩa các API cho Android Studio

// API lấy danh sách user
app.get('/user', (req, res) => {
    const sqlQuery = "SELECT * FROM user"; 
    
    db.query(sqlQuery, (err, results) => {
        if (err) {
            console.error("LỖI TRUY VẤN SQL:", err.message); 
            return res.status(500).json({ error: err.message });
        }
        
        if (results.length === 0) {
            console.warn("CẢNH BÁO: Bảng user đang trống!");
        }

        console.log("Đã gửi dữ liệu user cho Android!");
        res.json(results); 
    });
});

// API lấy danh sách thức ăn (Đã xóa bỏ đoạn trùng lặp)
app.get('/food_dictionary', (req, res) => {
    const sqlQuery = "SELECT * FROM food_dictionary";
    
    db.query(sqlQuery, (err, results) => {
        if (err) {
            console.error("LỖI TRUY VẤN FOOD:", err.message);
            return res.status(500).json({ error: err.message });
        }
        console.log("Đã gửi dữ liệu food cho Android!");
        res.json(results); 
    });
});

// API lấy nhật ký dinh dưỡng
app.get('/nutrition_log', (req, res) => {
    const sqlQuery = "SELECT * FROM nutrition_log";

    db.query(sqlQuery, (err, results) => {
        if (err) {
            console.error("LỖI TRUY VẤN NUTRITION_LOG:", err.message);
            return res.status(500).json({ error: err.message });
        }
        console.log("Đã gửi dữ liệu nutrition_log cho Android!");
        res.json(results);
    });
});

// API lấy danh sách kế hoạch tập luyện
app.get('/workout_plan', (req, res) => {
    const sqlQuery = "SELECT * FROM workout_plan";

    db.query(sqlQuery, (err, results) => {
        if (err) {
            console.error("LỖI TRUY VẤN WORKOUT_PLAN:", err.message);
            return res.status(500).json({ error: err.message });
        }
        console.log("Đã gửi dữ liệu workout_plan cho Android!");
        res.json(results);
    });
});

// 3. Khởi động Web Server (Chỉ gọi 1 lần duy nhất ở cuối file)
app.listen(PORT, () => {
    console.log(`Server API đang chạy tại: http://localhost:${PORT}`);
});
