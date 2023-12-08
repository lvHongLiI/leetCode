import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 吕宏力
 * @Date: 2021/07/30/15:23
 * @Description:
 */
public abstract class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
       String str="^((?!0000)[0-9]{4}年((0[1-9]|1[0-2])月(0[1-9]|1[0-9]|2[0-8])日|(0[13-9]|1[0-2])月(29日|30日)|(0[13578]|1[02])月31日)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)年02月29日)|((?!0000)[0-9]{4}-((0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29)$";
        Pattern pattern = Pattern.compile(str);
        String max="2025年02月01日";
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date(0));
        Date time = instance.getTime();
        String formatStr=null;
        do {
            formatStr = format.format(time);
            instance.add(Calendar.DAY_OF_YEAR,1);
            time = instance.getTime();
            System.out.println("通过："+formatStr);
        }while (pattern.matcher(formatStr).matches()&&max.compareTo(formatStr)>-1);

        System.out.println(formatStr);
    }
}



