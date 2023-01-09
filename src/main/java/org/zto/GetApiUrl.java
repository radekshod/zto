package org.zto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class GetApiUrl {

    public static int getAge(String name) {
        try {
            URL url = new URL("https://api.agify.io/?name=" + name);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();

            JSONObject obj = new JSONObject(result.toString());
            int age = obj.getInt("age");

            return age;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
