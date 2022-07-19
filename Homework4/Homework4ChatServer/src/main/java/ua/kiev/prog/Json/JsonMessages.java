package ua.kiev.prog.Json;

import ua.kiev.prog.shared.Message;

import java.util.ArrayList;
import java.util.List;

public class JsonMessages {
    private final List<Message> list = new ArrayList<>();

    public JsonMessages(List<Message> sourceList, int fromIndex, String username) {
        for (int i = fromIndex; i < sourceList.size(); i++)
            if(sourceList.get(i).getTo() == null || username.equals(sourceList.get(i).getTo()) || username.equals(sourceList.get(i).getFrom())) {
                list.add(sourceList.get(i));
            }else {
                list.add(null);
            }
    }
}
