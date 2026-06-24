package com.example.nutrifit.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nutrifit.R;
import com.example.nutrifit.data.local.SharedPrefs;

import org.json.JSONArray;
import org.json.JSONObject;

public class HomeFragment extends Fragment {

    private static final String BASE_URL = "http://10.0.2.2:5104";
    private TextView tvWelcome, tvFoodList, tvNutritionLog;
    private RequestQueue queue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tvWelcome = view.findViewById(R.id.tvWelcome);
        tvFoodList = view.findViewById(R.id.tvFoodList);
        tvNutritionLog = view.findViewById(R.id.tvNutritionLog);

        queue = Volley.newRequestQueue(requireContext());

        SharedPrefs prefs = new SharedPrefs(requireContext());
        tvWelcome.setText("Xin chào, " + prefs.getUserName() + "!");

        loadFood();
        loadNutritionLog();

        return view;
    }

    private void loadFood() {
        StringRequest request = new StringRequest(Request.Method.GET, BASE_URL + "/food_dictionary",
                response -> {
                    try {
                        JSONArray arr = new JSONArray(response);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject obj = arr.getJSONObject(i);
                            sb.append("• ").append(obj.getString("food_name"))
                                    .append(" - ").append(obj.getInt("calories")).append(" cal\n");
                        }
                        tvFoodList.setText(sb.toString());
                    } catch (Exception e) {
                        Log.e("HomeFragment", "Lỗi parse food: " + e.getMessage());
                    }
                },
                error -> tvFoodList.setText("Không tải được dữ liệu thức ăn"));
        queue.add(request);
    }

    private void loadNutritionLog() {
        StringRequest request = new StringRequest(Request.Method.GET, BASE_URL + "/nutrition_log",
                response -> {
                    try {
                        JSONArray arr = new JSONArray(response);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject obj = arr.getJSONObject(i);
                            sb.append("• ").append(obj.getString("food_name"))
                                    .append(" - ").append(obj.getInt("calories")).append(" cal")
                                    .append(" (").append(obj.getString("log_date"), 0, 10).append(")\n");
                        }
                        tvNutritionLog.setText(sb.toString());
                    } catch (Exception e) {
                        Log.e("HomeFragment", "Lỗi parse nutrition: " + e.getMessage());
                    }
                },
                error -> tvNutritionLog.setText("Không tải được nhật ký dinh dưỡng"));
        queue.add(request);
    }
}
