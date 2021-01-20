package com.jdck.data.common;

import java.util.UUID;

public class CreatCode {

    public static String uuidZero="00000000-0000-0000-0000-000000000000";
    /**
     * 生成六位随机数
     * @return
     */
    public static String getCode(){
        int v =(int)((Math.random() * 9 + 1) * 100000);
        String code = String.valueOf(v);
        return code;
    }

    /**
     * 生成唯一id,字母小写
     * @return
     */
    public static String getUuid(){
        return UUID.randomUUID().toString().toLowerCase();
    }
}
