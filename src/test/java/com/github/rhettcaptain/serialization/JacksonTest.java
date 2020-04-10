package com.github.rhettcaptain.serialization;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Slf4j
public class JacksonTest {
    @Test
    public void helloJackson() throws IOException {
        MyClass myClass = new MyClass();
        MySubClass mySubClass = new MySubClass();
        mySubClass.setId("111");
        myClass.setName("rhett");
        myClass.setAge(18);
        myClass.setGood(true);
        myClass.setHasMoney(true);
        myClass.setSubClass(mySubClass);
        ObjectMapper objectMapper = new ObjectMapper();
        String str = "["+objectMapper.writeValueAsString(myClass)+","+objectMapper.writeValueAsString(myClass)+"]";
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.readValue(objectMapper.writeValueAsString(myClass),MySubClass.class);
        List<MyClass> myClassList = objectMapper.readValue(str,List.class);
        log.info("{}",myClassList);
        String douStr = "["+str+","+str+"]";
        List<List<MyClass>> doubleList = objectMapper.readValue(douStr,List.class);
        log.info("{}",doubleList);
        String complexStr = objectMapper.writeValueAsString(Arrays.asList(new HashMap(){{put(1,myClass);}}));
        List<Map<Integer,List<MyClass>>> complexList = new ArrayList<>();
        complexList = objectMapper.readValue(complexStr,complexList.getClass());
        log.info("{}",complexList);
        complexList = objectMapper.readValue(complexStr,new TypeReference<List<Map<Integer,MyClass>>>(){});
        log.info("{}",complexList);
    }

    @Test
    public void myModule() throws IOException {
        MyClass myClass = new MyClass();
        myClass.setZonedDateTime(ZonedDateTime.of(2000,1,1,1,1,1,1, ZoneId.systemDefault()));
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(new MySerilizer(ZonedDateTime.class));
        module.addDeserializer(ZonedDateTime.class, new MyDeserilizer());
        mapper.registerModule(module);
        String str = mapper.writeValueAsString(myClass);
        log.info("{}",str);
        log.info("{}",mapper.readValue(str,MyClass.class));
    }

    @Test
    public void nonConsTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        NoConClass noConClass = new NoConClass(ZonedDateTime.now());
        String str = mapper.writeValueAsString(noConClass);
        log.info("{}", str);
        NoConClass jsonClass = mapper.readValue(str, NoConClass.class);
        log.info("{}",jsonClass);
    }
}

class MySerilizer extends StdSerializer<ZonedDateTime>{

    protected MySerilizer(Class<ZonedDateTime> t) {
        super(t);
    }

    @Override public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        gen.writeString("2020-01-01");
    }
}

class MyDeserilizer extends StdDeserializer<ZonedDateTime> {
    public MyDeserilizer() {
        super(ZonedDateTime.class);
    }
    protected MyDeserilizer(Class<?> vc) {
        super(vc);
    }

    @Override public ZonedDateTime deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        return ZonedDateTime.now();
    }
}

