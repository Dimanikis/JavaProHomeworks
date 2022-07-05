package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class Files {
    private File file;

    public Files(String filepath,String filename) {
        this.file = new File(filepath, filename);
    }

     /*public int send(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Cache-Control", "no-cache");
        conn.setRequestProperty("Content-Type", URLConnection.guessContentTypeFromName(file.getName()));
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream(); InputStream is = new FileInputStream(file)) {
            byte[] bytes = RespBody.responseBodyToArray(is);
            os.write(bytes);
            return conn.getResponseCode(); // 200?
        }
    }*/

    public int send(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Cache-Control", "no-cache");
        conn.setRequestProperty("Content-Type", URLConnection.guessContentTypeFromName(file.getName()));
        conn.setDoOutput(true);


        try (BufferedOutputStream os = new BufferedOutputStream(conn.getOutputStream()); InputStream fis = new FileInputStream(file)) {
            byte[] dataBuffer = RespBody.responseBodyToArray(fis);
            os.write(dataBuffer);
            return conn.getResponseCode(); // 200?
        }
    }

    /*public int sendFile(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        try (BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
             FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            return conn.getResponseCode(); // 200?
        }
    }*/

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}

