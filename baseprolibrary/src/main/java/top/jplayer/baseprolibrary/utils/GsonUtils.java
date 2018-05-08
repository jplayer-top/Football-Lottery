package top.jplayer.baseprolibrary.utils;

import android.support.annotation.NonNull;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by Obl on 2018/1/12.
 * top.jplayer.baseprolibrary.utils
 */

public class GsonUtils {
    @NonNull
    public static Gson setGsonFilter() {
        return new GsonBuilder()
                // 在创建过程中调用GsonBuilder.setDateFormat(String)指定一个固定的格式
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .serializeNulls()//空值不忽略
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())//反序列化Gson 空值处理
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        // f.contains("name")
                        //以上剔除包含name 的字段
                        return false;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        // return clazz == Date.class || clazz == boolean.class;
                        //以上忽略Date 和 Boolean 类型
                        return false;
                    }
                }).create();
    }

    /**
     * String 类型 gson 为空的时候处理为 ""
     */
    public static class NullStringToEmptyAdapterFactory implements TypeAdapterFactory {
        @SuppressWarnings("unchecked")
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            Class<T> rawType = (Class<T>) type.getRawType();
            if (rawType != String.class) {
                return null;
            }
            return (TypeAdapter<T>) new StringNullAdapter();
        }
    }

    public static class StringNullAdapter extends TypeAdapter<String> {
        @Override
        public String read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return "";
            }
            return reader.nextString();
        }

        @Override
        public void write(JsonWriter writer, String value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }
}
