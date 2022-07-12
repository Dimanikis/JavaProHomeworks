package ua.kiev.prog;

import java.util.ArrayList;
import java.util.List;

public class JsonUser {
    private final List<User> list = new ArrayList<>();

    public JsonUser(List<User> sourceList) {
        for (int i = 0; i < sourceList.size(); i++)
            list.add(sourceList.get(i));
    }
}
