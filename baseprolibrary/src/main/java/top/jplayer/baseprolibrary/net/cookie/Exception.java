package top.jplayer.baseprolibrary.net.cookie;

/**
 * Created by Obl on 2018/2/9.
 * top.jplayer.baseprolibrary.net.cookie
 */

public class Exception {
    public static void illegalArgument(String msg, Object... params) {
        throw new IllegalArgumentException(String.format(msg, params));
    }


}
