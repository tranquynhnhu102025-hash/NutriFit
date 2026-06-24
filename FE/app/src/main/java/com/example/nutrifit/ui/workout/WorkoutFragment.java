package com.example.nutrifit.ui.workout;

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

import org.json.JSONArray;
import org.json.JSONObject;

public class WorkoutFragment extends Fragment {

    private static final String BASE_URL = "http://10.0.2.2:5104";
    private TextView tvWorkoutList;
    private RequestQueue queue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout, container, false);

        tvWorkoutList = view.findViewById(R.id.tvWorkoutList);
        queue = Volley.newRequestQueue(requireContext());

        loadWorkoutPlan();

        return view;
    }

    private void loadWorkoutPlan() {
        StringRequest request = new StringRequest(Request.Method.GET, BASE_URL + "/workout_plan",
                response -> {
                    try {
                        JSONArray arr = new JSONArray(response);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject obj = arr.getJSONObject(i);
                            sb.append("• ").append(obj.getString("exercise_name"))
                                    .append(" - ").append(obj.getInt("duration")).append(" phút")
                                    .append(" (").append(obj.getString("plan_date"), 0, 10).append(")\n");
                        }
                        tvWorkoutList.setText(sb.toString());
                    } catch (Exception e) {
                        Log.e("WorkoutFragment", "Lỗi parse workout: " + e.getMessage());
                    }
                },
                error -> tvWorkoutList.setText("Không tải được kế hoạch tập luyện"));
        queue.add(request);
    }
}
