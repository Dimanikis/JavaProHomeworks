package ua.kiev.prog.Servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import jakarta.servlet.http.*;
import ua.kiev.prog.MessageList;
import ua.kiev.prog.shared.User;
import ua.kiev.prog.UserList;

public class GetListServlet extends HttpServlet {
	
	private MessageList msgList = MessageList.getInstance();
	private UserList usrList = UserList.getInstance();

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String fromStr = req.getParameter("from");
		String username = req.getParameter("username");
		int from = 0;
		try {
			from = Integer.parseInt(fromStr);
			if (from < 0)
				from = 0;
		} catch (Exception ex) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		if (username == null){
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
	}
		addUser(username);
		resp.setContentType("application/json");
		
		String json = msgList.toJSON(from, username);
		if (json != null) {
			OutputStream os = resp.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
			os.write(buf);

		}
	}
	private void addUser(String username){

		User usr = new User(username);
		if (usrList.contain(usr)) {
			usrList.online(usr);
		} else
			usrList.add(usr);
	}
}
