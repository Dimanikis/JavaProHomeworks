package ua.kiev.prog;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Enter your login: ");
			String login = scanner.nextLine();
	
			Thread th = new Thread(new GetThread(login));
			th.setDaemon(true);
			th.start();

			Thread oh = new Thread(new OnlineThread(login));
			oh.setDaemon(true);
			oh.start();

            System.out.println("Enter your message: ");
			while (true) {
				Message m;
				String to = null;
				String text = scanner.nextLine();
				if (text.isEmpty())
					continue;

				if(text.charAt(0) == '@'){
					m = Commands.command(login,text);
				} else
					m = new Message(login, text);

				try {
					int res = m.send(Utils.getURL() + "/add?t");
					if (res != 200) { // 200 OK
						System.out.println("HTTP error occured: " + res);
						return;
					}
				} catch (NullPointerException ignored) {
				}

			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			scanner.close();
		}
	}
}
