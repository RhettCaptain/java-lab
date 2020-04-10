package com.github.rhettcaptain.serialization;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.time.ZonedDateTime;

@Slf4j
public class FastJsonTest {
    @Test
    public void zonetimetest(){
        MyClass myClass = new MyClass();
        myClass.setZonedDateTime(ZonedDateTime.now());
        String str = JSONObject.toJSONString(myClass);
        log.info("{}",str);
        log.info("{}", JSONObject.parseObject(str,MyClass.class));

        NoConClass noConClass = new NoConClass(ZonedDateTime.now());
        str = JSONObject.toJSONString(noConClass);
        log.info("{}",str);
        NoConClass jsonClass = JSONObject.parseObject(str,NoConClass.class);
        log.info("{}", jsonClass);


        Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class<?>[] { NoConClass.class },
                JSON.parseObject(str));
        log.info("{}",proxy);
    }
}
