package com.example.nutrifit.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nutrifit.R;
import com.example.nutrifit.data.local.SharedPrefs;
import com.example.nutrifit.ui.auth.LoginActivity;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView tvName = view.findViewById(R.id.tvProfileName);
        TextView tvEmail = view.findViewById(R.id.tvProfileEmail);
        Button btnLogout = view.findViewById(R.id.btnLogout);

        SharedPrefs prefs = new SharedPrefs(requireContext());
        tvName.setText(prefs.getUserName());
        tvEmail.setText(prefs.getUserEmail());

        btnLogout.setOnClickListener(v -> {
            prefs.logout();
            startActivity(new Intent(requireContext(), LoginActivity.class));
            requireActivity().finish();
        });

        return view;
    }
}
