package ua.kiev.prog;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class AddUserServlet extends HttpServlet {

    private UserList usrList = UserList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = RequestBody.requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        User usr = User.fromJSON(bufStr);
        if (usr != null) {
            if (usrList.contain(usr)) {
                usrList.online(usr);
            } else
                usrList.add(usr);
        } else
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }


}
