package ua.kiev.prog;

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

    public Files(){
    }

     public int send(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection http = (HttpURLConnection) obj.openConnection();

        http.setRequestMethod("PUT");
        http.setRequestProperty("Connection", "Keep-Alive");
        http.setRequestProperty("Cache-Control", "no-cache");
        http.setRequestProperty("Content-Type", URLConnection.guessContentTypeFromName(file.getName()));
        http.setDoOutput(true);

        try (OutputStream os = http.getOutputStream(); InputStream is = new FileInputStream(file)) {
            byte[] bytes = RespBody.responseBodyToArray(is);
            os.write(bytes);
            return http.getResponseCode(); // 200?
        }
    }
    public int download(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection http = (HttpURLConnection) obj.openConnection();

        http.setRequestProperty("Connection", "Keep-Alive");
        http.setRequestProperty("Cache-Control", "no-cache");
        http.setRequestProperty("Content-Type", URLConnection.guessContentTypeFromName(file.getName()));
        System.out.println(this.file.getAbsolutePath());

        try (BufferedInputStream inputStream = new BufferedInputStream(http.getInputStream());
             FileOutputStream fileOS = new FileOutputStream(this.file)) {
            byte[] data = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                fileOS.write(data, 0, byteContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return http.getResponseCode();
    }
    public String GetList() throws IOException {
        URL obj = new URL(Utils.getURL() + "/File?list=true");
        HttpURLConnection http = (HttpURLConnection) obj.openConnection();

        InputStream is = http.getInputStream();
        byte[] buf = RespBody.responseBodyToArray(is);
        return new String(buf, StandardCharsets.UTF_8);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}

