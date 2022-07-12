package ua.kiev.prog;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;

public class FileServlet extends HttpServlet {

    private final File fd = new File("D:\\ServerFileFolder");

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {

        String filesName = req.getParameter("fileName");
        if (filesName != null) {
            File fl = new File(fd.getAbsolutePath() + "/" + filesName);
            try (BufferedInputStream inputStream = new BufferedInputStream(req.getInputStream());
                 FileOutputStream fileOS = new FileOutputStream(fl)) {
                byte[] data = new byte[1024];
                int byteContent;
                while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                    fileOS.write(data, 0, byteContent);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String list = req.getParameter("list");
        if(list != null){
            if(list.equals("true")){
                StringBuilder sb = new StringBuilder();
                for(File item : Objects.requireNonNull(fd.listFiles())){
                    sb.append(item.getName());
                    sb.append("\n");
                }
                String fileList = sb.toString();
                OutputStream os = resp.getOutputStream();
                byte[] buf = fileList.getBytes(StandardCharsets.UTF_8);
                os.write(buf);
            }
        } else {
            String fileName = req.getParameter("fileName");
            if(fileName != null) {
                File file = new File(fd.getAbsolutePath(), fileName);
                try (OutputStream os = resp.getOutputStream(); InputStream is = new FileInputStream(file)){
                    byte[] buf = RequestBody.requestBodyToArray(is);
                    os.write(buf);
                }
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }

    }



}
