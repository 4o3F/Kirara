package moe.exusiai.kirara;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Data {
    public static Map<String, String> Emojis = new HashMap<>();
    public static Pattern pattern = Pattern.compile("(?=(:\\w+:))");
}
