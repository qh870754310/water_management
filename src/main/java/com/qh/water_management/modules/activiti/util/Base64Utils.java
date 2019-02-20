package com.qh.water_management.modules.activiti.util;


import org.apache.log4j.Logger;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: qh
 * @Date: 2019/1/9 10:05
 * @Description:
 */
public class Base64Utils {

    private static Logger logger = Logger.getLogger(Base64Utils.class);

    private static BASE64Encoder encoder = new BASE64Encoder();

    public static String ioToBase64(InputStream in) throws IOException {
        String strBase64 = null;
        try {
            byte[] bytes = new byte[in.available()];
            // 将文件中的内容读入到数组中
            in.read(bytes);
            strBase64 = encoder.encode(bytes);  //将字节流数组转换为字符串
            in.close();
        } catch (IOException e) {
            logger.error("图片转64编码异常", e);
        }
        return strBase64;
    }
}
