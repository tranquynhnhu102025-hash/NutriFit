package edu.nttu.test;

import android.os.Bundle;
import android.util.Log;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;

    // ĐỊNH NGHĨA CÁC ĐƯỜNG DẪN API ĐỂ KIỂM TRA
    private final String URL_USER = "http://10.0.2.2:3000/user";
    private final String URL_FOOD = "http://10.0.2.2:3000/food_dictionary";
    private final String URL_NUTRITION = "http://10.0.2.2:3000/nutrition_log";
    private final String URL_WORKOUT = "http://10.0.2.2:3000/workout_plan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Khởi tạo hàng đợi xử lý request mạng của Volley
        queue = Volley.newRequestQueue(this);

        // KÍCH HOẠT QUY TRÌNH KIỂM TRA BACKEND TỰ ĐỘNG
        runBackendUnitTests();
    }

    /**
     * Hàm tổng hợp dùng để kích hoạt chạy tất cả các ca kiểm thử API
     */
    private void runBackendUnitTests() {
        Log.i("Backend_Test", "=== BẮT ĐẦU KIỂM TRA HỆ THỐNG API ===");
        testFetchUserAPI();
        testFetchFoodAPI();
        testFetchNutritionAPI();
        testFetchWorkoutAPI();
    }

    /**
     * Ca kiểm thử 1: Kiểm tra tính đúng đắn của API lấy danh sách User
     */
    private void testFetchUserAPI() {
        StringRequest userRequest = new StringRequest(
                Request.Method.GET, URL_USER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Backend_Test_Success", "[GET /user] Hợp lệ! Dữ liệu trả về: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handleApiError("GET /user", error);
                    }
                }
        );
        queue.add(userRequest);
    }

    /**
     * Ca kiểm thử 2: Kiểm tra tính đúng đắn của API lấy danh sách Thức ăn
     */
    private void testFetchFoodAPI() {
        StringRequest foodRequest = new StringRequest(
                Request.Method.GET, URL_FOOD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Backend_Test_Success", "[GET /food] Hợp lệ! Dữ liệu trả về: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handleApiError("GET /food", error);
                    }
                }
        );
        queue.add(foodRequest);
    }

    /**
     * Hàm dùng chung để phân tích và in lỗi từ Server nếu việc gọi API thất bại
     */
    private void testFetchNutritionAPI() {
        StringRequest nutritionRequest = new StringRequest(
                Request.Method.GET, URL_NUTRITION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Backend_Test_Success", "[GET /nutrition_log] Hợp lệ! Dữ liệu trả về: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handleApiError("GET /nutrition_log", error);
                    }
                }
        );
        queue.add(nutritionRequest);
    }

    private void testFetchWorkoutAPI() {
        StringRequest workoutRequest = new StringRequest(
                Request.Method.GET, URL_WORKOUT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Backend_Test_Success", "[GET /workout_plan] Hợp lệ! Dữ liệu trả về: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handleApiError("GET /workout_plan", error);
                    }
                }
        );
        queue.add(workoutRequest);
    }

    private void handleApiError(String apiEndpoint, VolleyError error) {
        if (error.networkResponse != null) {
            int statusCode = error.networkResponse.statusCode;
            Log.e("Backend_Test_Error", "[" + apiEndpoint + "] Thất bại! Mã lỗi HTTP từ Server: " + statusCode);

            if (statusCode == 404) {
                Log.e("Backend_Test_Error", "-> Gợi ý: Sai đường dẫn Endpoint trên NodeJS hoặc Server chưa khởi động lại.");
            } else if (statusCode == 500) {
                Log.e("Backend_Test_Error", "-> Gợi ý: Lỗi cú pháp SQL bên trong NodeJS (Kiểm tra lại Terminal VS Code).");
            }
        } else {
            Log.e("Backend_Test_Error", "[" + apiEndpoint + "] Thất bại! Không thể kết nối (Lỗi mạng mạng hoặc sai IP máy chủ).");
        }
    }
}
