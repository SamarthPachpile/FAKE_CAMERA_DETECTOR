package com.example.fakecameradetector;

import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView numberOfCameras = findViewById(R.id.detected_cameras);

        int result_ = getNumberOfDetectedCameras();
        if (result_ == 404) {
            numberOfCameras.setTextSize(17);
            numberOfCameras.setText("Could Not Detect Any Camera in this device");
        } else {
            numberOfCameras.setText(String.valueOf(result_));
        }
    }

    private int getNumberOfDetectedCameras() {
        try {
            Context context = getBaseContext();
            CameraManager manager = (CameraManager) context.getSystemService(CAMERA_SERVICE);
            String[] cameraIds = manager.getCameraIdList();
            return cameraIds.length;  // Return the number of detected cameras
        } catch (Exception e) {
            return 404;  // Return 404 if an exception occurs
        }
    }
}
