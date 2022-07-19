package ua.kiev.prog.Servlet;

import jakarta.servlet.http.*;
import ua.kiev.prog.ConnectDAO;
import ua.kiev.prog.shared.ConnectionFactory;
import ua.kiev.prog.shared.Message;
import ua.kiev.prog.MessageList;
import ua.kiev.prog.RequestBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;

public class AddServlet extends HttpServlet {

	private MessageList msgList = MessageList.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		byte[] buf = RequestBody.requestBodyToArray(req.getInputStream());
        String bufStr = new String(buf, StandardCharsets.UTF_8);

		Message msg = Message.fromJSON(bufStr);
		Connection conn;
		try {
			conn = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		ConnectDAO c = new ConnectDAO();
		try {
			c.ConnectTest(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		if (msg != null)
			msgList.add(msg);
		else
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}

}
