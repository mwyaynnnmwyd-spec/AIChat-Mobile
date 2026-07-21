import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ScrollView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.json.JSONArray;
import android.os.AsyncTask;

public class MainActivity extends AppCompatActivity {

    private EditText inputMessage;
    private Button sendButton;
    private TextView chatDisplay;
    private ScrollView scrollView;
    private StringBuilder chatHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ربط العناصر من الواجهة
        inputMessage = findViewById(R.id.input_message);
        sendButton = findViewById(R.id.send_button);
        chatDisplay = findViewById(R.id.chat_display);
        scrollView = findViewById(R.id.scroll_view);

        chatHistory = new StringBuilder();

        // حدث الزر
        sendButton.setOnClickListener(v -> {
            String userMsg = inputMessage.getText().toString().trim();
            if (!userMsg.isEmpty()) {
                addMessageToChat("أنت: " + userMsg);
                inputMessage.setText("");
                new ChatTask().execute(userMsg);
            }
        });
    }

    // إضافة الرسالة للدردشة
    private void addMessageToChat(String message) {
        chatHistory.append(message).append("\n\n");
        chatDisplay.setText(chatHistory.toString());
        scrollView.post(() -> scrollView.fullScroll(ScrollView.FOCUS_DOWN));
    }

    // مهمة الاتصال بالخادم (بدون تجميد الواجهة)
    private class ChatTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            return getAIResponse(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            addMessageToChat("الذكاء الاصطناعي: " + result);
        }
    }

    // الاتصال بخادم Hugging Face
    private String getAIResponse(String userMessage) {
        try {
            String apiUrl = "https://api-inference.huggingface.co/models/gpt2";
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);

            // إرسال الرسالة
            JSONObject requestBody = new JSONObject();
            requestBody.put("inputs", userMessage);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // استقبال الرد
            int responseCode = connection.getResponseCode();
            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
                
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // معالجة JSON
                JSONArray jsonArray = new JSONArray(response.toString());
                String aiResponse = jsonArray.getJSONObject(0).getString("generated_text");
                
                return aiResponse;
            } else {
                return "عذراً، حدث خطأ في الاتصال. حاول مرة أخرى.";
            }

        } catch (Exception e) {
            return "خطأ: " + e.getMessage();
        }
    }
}
