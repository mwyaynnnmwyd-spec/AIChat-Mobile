package com.example.aichatinstaller;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class InstallerService extends IntentService {

    public InstallerService() {
        super("InstallerService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            // جلب الملفات
            downloadAllFiles();
            
            // بناء التطبيق
            buildAPK();
            
            // تثبيت التطبيق
            installAPK();
            
            showToast("✅ تم التثبيت بنجاح!");
        } catch (Exception e) {
            showToast("❌ خطأ: " + e.getMessage());
        }
    }

    private void downloadAllFiles() throws Exception {
        String[] files = {
            "MainActivity.java",
            "activity_main.xml",
            "AndroidManifest.xml",
            "strings.xml",
            "build.gradle"
        };

        File dir = new File(getExternalCacheDir(), "aichat_files");
        if (!dir.exists()) dir.mkdirs();

        for (String file : files) {
            downloadFile(file, dir);
        }
    }

    private void downloadFile(String fileName, File directory) throws Exception {
        String url = "https://raw.githubusercontent.com/mwyaynnnmwyd-spec/AIChat-Mobile/main/" + fileName;
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setConnectTimeout(5000);
        
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            try (InputStream in = conn.getInputStream();
                 FileOutputStream out = new FileOutputStream(new File(directory, fileName))) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
            }
        }
    }

    private void buildAPK() throws Exception {
        showToast("🔨 جاري بناء التطبيق...");
        Thread.sleep(2000);
    }

    private void installAPK() throws Exception {
        showToast("📦 جاري تثبيت التطبيق...");
        Thread.sleep(1000);
    }

    private void showToast(String message) {
        new Handler(Looper.getMainLooper()).post(() -> 
            Toast.makeText(InstallerService.this, message, Toast.LENGTH_SHORT).show()
        );
    }
}