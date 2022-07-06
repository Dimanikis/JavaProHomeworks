package ua.kiev.prog;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddFilesServlet extends HttpServlet {

    private File fd = new File("D:\\ServerFileFolder");

    /*
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String filesName = req.getParameter("filesname");
        if (filesName != null) {
            File fl = new File(fd.getAbsolutePath() + "/" + filesName);
        try {
            fl.createNewFile();
            FileOutputStream fos = new FileOutputStream(fl);
            byte[] buf = new byte[20024];
            for (int len = req.getInputStream().read(buf); len > 0; len = req.getInputStream().read(buf)) {
                fos.write(buf, 0, len);
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String filesName = req.getParameter("filesname");
        if (filesName != null) {
            File fl = new File(fd.getAbsolutePath() + "/" + filesName);
            try (BufferedInputStream inputStream = new BufferedInputStream(req.getInputStream());
                 FileOutputStream fileOS = new FileOutputStream(fl)) {
                byte data[] = new byte[1024];
                int byteContent;
                while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                    fileOS.write(data, 0, byteContent);
                }
            } catch (IOException e) {
                // handles IO exceptions
            }
        }


    }



}