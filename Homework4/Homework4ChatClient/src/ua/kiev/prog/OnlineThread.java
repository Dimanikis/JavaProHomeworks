package ua.kiev.prog;

public class OnlineThread implements Runnable {
    private final User user;

    public OnlineThread(String login) {
        this.user = new User(login);
    }


    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                int res = user.send(Utils.getURL() + "/addUser");
                if (res != 200) { // 200 OK
                    System.out.println("HTTP error occured: " + res);
                    return;
                }
                Thread.sleep(10000);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}