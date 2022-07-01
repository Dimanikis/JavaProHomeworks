package main;

import java.io.FileWriter;
import java.io.IOException;

@SaveTo(path = "test.txt")
public class TextConteiner {
    private String str;

    public TextConteiner(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Saver
    public void save (String path) throws IOException {
        FileWriter fw = new FileWriter(path);
        fw.write(str);
        fw.close();
    }
}
