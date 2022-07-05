package ua.kiev.prog;

import java.io.IOException;

public class Commands {

    public static Message command(String login,String text) throws IOException {
        String[] st = text.split(" ", 3);
        if(st[0].equals("@whisper") || st[0].equals("@w")){
            try {
                return new Message(login, st[2], st[1]);
            } catch (Exception e) {
                System.out.println("use next template for private message(@w username message)");
                return null;
            }
        }
        if(st[0].equals("@user") || st[0].equals("@u")){
            GetUserList gul = new GetUserList();
            gul.GetUList();
            return null;
        }
        if(st[0].equals("@file") || st[0].equals("@f")){
            try {
                Files file = new Files(st[1], st[2]);
                st[2] = st[2].replace(" ", "+");
                int res = file.send(Utils.getURL() + "/addFiles?filesname=" + st[2]);
                if (res != 200) { // 200 OK
                    System.out.println("HTTP error occured: " + res);
                    return null;
                }
            }catch (Exception e){
                System.out.println("use next template for file send(@f filepath filename)");
                return null;
            }
            return null;
        }
        if(st[0].equals("@help") || st[0].equals("@h")){
            System.out.println("available command:" + "\n" +
                    "@user(@u) - prints the user's list" + "\n" +
                    "@whisper(@w) - for private messages. Use next template (@w username message)" + "\n" +
                    "@file(@f) - for file send. Use next template (@f filename)" + "\n" +
                    "@help(@h) - prints command list" + "\n");
        } else
            System.out.println("Print @help or @h for command list");
        return null;
    }

}

