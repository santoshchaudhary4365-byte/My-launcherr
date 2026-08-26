package com.mclauncher.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText etUsername;
    private Spinner spVersion;
    private SeekBar sbRam;
    private TextView tvRamLabel;
    private Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI Components Mapping
        etUsername = findViewById(R.id.etUsername);
        spVersion = findViewById(R.id.spVersion);
        sbRam = findViewById(R.id.sbRam);
        tvRamLabel = findViewById(R.id.tvRamLabel);
        btnPlay = findViewById(R.id.btnPlay);

        // Version List Setup
        String[] versions = {"1.20.1", "1.16.5", "1.12.2", "1.8.9"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, versions);
        spVersion.setAdapter(adapter);

        // RAM Slider Control
        sbRam.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int ramMb = Math.max(1024, progress); // Minimum 1GB RAM
                tvRamLabel.setText("RAM Allocation: " + ramMb + " MB");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Play Button Action
        btnPlay.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String selectedVersion = spVersion.getSelectedItem().toString();

            if (username.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter a username first!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Starting Minecraft " + selectedVersion + " for " + username, Toast.LENGTH_LONG).show();
                // Future integration: Trigger Java Runtime / LWJGL launch process
            }
        });
    }
}

