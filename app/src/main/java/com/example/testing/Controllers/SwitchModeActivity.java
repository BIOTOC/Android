package com.example.testing.Controllers;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.testing.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SwitchModeActivity extends AppCompatActivity {

    TextView textView;
    SwitchMaterial switchMaterial;

    private void bindingView(){
        textView = findViewById(R.id.textView);
        switchMaterial = findViewById(R.id.switchMode);
    }

    private void bindingAction(){
        int currentNightMode = AppCompatDelegate.getDefaultNightMode();
        if (currentNightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            textView.setText("DarkMode");
            switchMaterial.setChecked(true);
        } else {
            textView.setText("LightMode");
            switchMaterial.setChecked(false);
        }

        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textView.setText("DarkMode");
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    textView.setText("LightMode");
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_switch_mode);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bindingView();
        bindingAction();
    }
}