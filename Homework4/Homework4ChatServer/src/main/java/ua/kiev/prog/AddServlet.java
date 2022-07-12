package ua.kiev.prog;

import jakarta.servlet.http.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AddServlet extends HttpServlet {

	private MessageList msgList = MessageList.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		byte[] buf = RequestBody.requestBodyToArray(req.getInputStream());
        String bufStr = new String(buf, StandardCharsets.UTF_8);

		Message msg = Message.fromJSON(bufStr);
		if (msg != null)
			msgList.add(msg);
		else
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}

}
