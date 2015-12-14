package data;

import java.util.LinkedList;

/**
 * Created by Francis O'Brien - 12/13/2015 - 6:20 AM
 */

public class Messages {

    public static LinkedList<String> messages = new LinkedList<>();
    private static final int MAX_MESSAGES = 6;

    public static void newMessage(String m){
        String time = GameVars.year + ":" + GameVars.day + " ";
        if (m.length() > 68 - time.length()){
            m = m.substring(0, 68 - time.length());
            m += "...";
        }
        if (messages.size() == MAX_MESSAGES){
            messages.removeLast();
            messages.addFirst(time + m);
        } else {
            messages.addFirst(time + m);
        }
    }

}
