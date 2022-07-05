package ua.kiev.prog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonUser {
    private final List<User> list = new ArrayList<>();

    public JsonUser(List<User> sourceList, int fromIndex) {
        for (int i = fromIndex; i < sourceList.size(); i++)
            list.add(sourceList.get(i));
    }

    public List<User> getList() {
        return Collections.unmodifiableList(list);
    }
}
