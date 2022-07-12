package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetUserList {
    private final Gson gson;

    public GetUserList(){
        gson = new GsonBuilder().create();
    }

    public void GetUList() throws IOException {
        URL url = new URL(Utils.getURL() + "/getUser");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();

        InputStream is = http.getInputStream();
        byte[] buf = RespBody.responseBodyToArray(is);
        String strBuf = new String(buf, StandardCharsets.UTF_8);

        JsonUser list = this.gson.fromJson(strBuf, JsonUser.class);
        if (list != null) {
            for (User u : list.getList()) {
                System.out.println(u);
            }
        }
    }

}
