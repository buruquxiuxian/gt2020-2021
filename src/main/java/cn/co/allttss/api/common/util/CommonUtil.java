package cn.co.allttss.api.common.util;

import java.text.SimpleDateFormat;

public class CommonUtil {
    /**
     *
     * @return
     */
    public static String getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
        return simpleDateFormat.format(System.currentTimeMillis());
    }
}
