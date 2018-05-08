package top.jplayer.baseprolibrary;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        String a = "222(2332)";
        System.out.println(a.substring(a.indexOf("(")+1,a.lastIndexOf(")")));
    }
}