package cn.co.allttss.api.common.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class MvcJsonConverter extends MappingJackson2HttpMessageConverter {

    public class NullBeanJsonSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
            if(value == null) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeEndObject();
            }
        }
    }


    /**
     * <p>处理数组类型的null值
     */
    public class NullArrayJsonSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
            if (value == null) {
                jsonGenerator.writeStartArray();
                jsonGenerator.writeEndArray();
            }
        }
    }

    /**
     * <p>处理日期类型的null为""
     * */
    public class NullDateJsonSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
            if(value == null) {
                jsonGenerator.writeString("");
            }
        }

    }

    /**
     * <p>处理字符串类型的null值
     */
    public class NullStringJsonSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            jsonGenerator.writeString("");
        }
    }

    /**
     * <p>处理数字类型的null值
     */
    public class NullNumberJsonSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            jsonGenerator.writeNumber(0);
        }
    }

    /**
     * <p>处理布尔类型的null值
     */
    public class NullBooleanJsonSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            jsonGenerator.writeBoolean(false);
        }
    }

    public class MyBeanSerializerModifier extends BeanSerializerModifier {


        @Override
        public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
            //循环所有的beanPropertyWriter
            for (Object beanProperty : beanProperties) {
                BeanPropertyWriter writer = (BeanPropertyWriter) beanProperty;
                //判断字段的类型，如果是array，list，set则注册nullSerializer
                if (isArrayType(writer)) {
                    //给writer注册一个自己的nullSerializer
                    writer.assignNullSerializer(new NullArrayJsonSerializer());
                } else if (isNumberType(writer)) {
                    writer.assignNullSerializer(new NullNumberJsonSerializer());
                } else if (isBooleanType(writer)) {
                    writer.assignNullSerializer(new NullBooleanJsonSerializer());
                } else if (isStringType(writer)) {
                    writer.assignNullSerializer(new NullStringJsonSerializer());
                }else if(isDateType(writer)) {
                    writer.assignNullSerializer(new NullDateJsonSerializer());
                }else {
                    writer.assignNullSerializer(new NullBeanJsonSerializer());
                }
            }
            return beanProperties;
        }

        /**
         * <p>是否是数组
         */
        private boolean isArrayType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return clazz.isArray() || Collection.class.isAssignableFrom(clazz);
        }

        /**
         * <p>是否是string
         */
        private boolean isStringType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return CharSequence.class.isAssignableFrom(clazz) || Character.class.isAssignableFrom(clazz);
        }

        /**
         * <p>是否是int
         */
        private boolean isNumberType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return Number.class.isAssignableFrom(clazz);
        }

        /**
         * <p>是否是boolean
         */
        private boolean isBooleanType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return clazz.equals(Boolean.class);
        }

        /**
         * <p>是否是Date
         * */
        private boolean isDateType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return clazz.equals(Date.class);
        }
    }

    /**
     * <p>构造函数
     * */
    MvcJsonConverter() {
        getObjectMapper().setSerializerFactory(getObjectMapper().getSerializerFactory().withSerializerModifier(new MyBeanSerializerModifier()));
        // 日期统一格式化(优先级低于在视图对象使用注解格式化或在代码中格式化)
        ObjectMapper objectMapper = super.getObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        setObjectMapper(objectMapper);
    }
}