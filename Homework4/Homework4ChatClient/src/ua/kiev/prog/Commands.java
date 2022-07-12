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
        if(st[0].equals("@fileSend") || st[0].equals("@fs")){
            try {
                Files file = new Files(st[1], st[2]);
                st[2] = st[2].replace(" ", "+");
                int res = file.send(Utils.getURL() + "/File?fileName=" + st[2]);
                if (res != 200) { // 200 OK
                    System.out.println("HTTP error occurred: " + res);
                    return null;
                }
                System.out.println("Complete");
            } catch (Exception e){
                System.out.println("use next template for file send(@fs filepath filename)");
                return null;
            }
            return null;
        }
        if(st[0].equals("@fileList") || st[0].equals("@fl")){
            Files file = new Files();
            System.out.println(file.GetList());
            return null;
        }
        if(st[0].equals("@fileDownload") || st[0].equals("@fd")){
            try{
            Files file = new Files(st[1], st[2]);
            int res = file.download(Utils.getURL() + "/File?fileName=" + st[2]);
            if (res != 200) { // 200 OK
                System.out.println("HTTP error occurred: " + res);
                return null;
            }
            System.out.println("Complete");
            } catch (Exception e){
                System.out.println("use next template for file send(@fd Download_Path FileName)");
                return null;
            }
            return null;
        }
        if(st[0].equals("@help") || st[0].equals("@h")){
            System.out.println("""
                    available command:
                    @user(@u) - prints the user's list
                    @whisper(@w) - for private messages. Use next template (@w username message)
                    @fileSend(@fs) - for file send. Use next template (@fs filepath filename)
                    @fileList(@fl) - prints a list of files on the server
                    @help(@h) - prints command list
                    """);
        } else
            System.out.println("Print @help or @h for command list");
        return null;
    }

}

