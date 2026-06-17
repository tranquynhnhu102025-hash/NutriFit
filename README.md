# NutriFit - Ứng dụng quản lý dinh dưỡng và tập luyện

## 📝 Giới thiệu dự án
NutriFit là ứng dụng hỗ trợ người dùng theo dõi chế độ dinh dưỡng, quản lý calo và lên kế hoạch tập luyện cá nhân hóa, giúp duy trì lối sống lành mạnh.

## 🚀 Công nghệ sử dụng
* **Backend:** ASP.NET Core (C#), Entity Framework Core
* **Database:** MySQL
* **Frontend:** Android Studio (Java/Kotlin)
* **Tools:** Visual Studio Code, Git/GitHub

## 🛠 Chức năng chính
* Đăng ký/Đăng nhập người dùng.
* Theo dõi chỉ số dinh dưỡng hằng ngày.
* Tương tác dữ liệu từ App Android thông qua API.

## 👥 Thành viên nhóm
| STT | Họ và tên | MSSV | Vai trò |
| :--- | :--- | :--- | :--- |
| 1 | Trần Quỳnh Như | ... | Backend & Android Developer |

## 📦 Hướng dẫn cài đặt
### Backend:
1. Mở thư mục `BE` trong Visual Studio Code.
2. Cấu hình chuỗi kết nối Database trong `appsettings.json`.
3. Chạy lệnh: `dotnet ef database update` để tạo bảng.
4. Chạy lệnh: `dotnet run` để khởi động API server.

### Android App:
1. Mở dự án trong Android Studio.
2. Đảm bảo Backend đang chạy (cổng `localhost:5104`).
3. Thay đổi URL trong code Android trỏ về IP của máy tính (thay `localhost` bằng IP mạng LAN).
4. Nhấn **Run** để khởi chạy ứng dụng trên máy ảo hoặc điện thoại.
