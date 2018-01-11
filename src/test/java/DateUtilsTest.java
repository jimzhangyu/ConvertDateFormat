import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Lenovo on 2017/6/11.
 */
public class DateUtilsTest {
    @Test
    public void convert() throws Exception {
        List<String> strings = new ArrayList<>();
        strings.add("2008/07/30 05:06:30");
        strings.add("20080730 05:07:30");
        strings.add("2008年7月30日 05:07:30");
        strings.add("2008年7月 05:06:30");
        strings.add("7月30日 05:06:30");
        strings.add("Jun 3,2008 05:07:30");
        strings.add("Jun 30 05:06");
        strings.add("05:07:30");
        for (String str : strings) {
            System.out.println(DateUtils.convert(str));
        }

    }

}