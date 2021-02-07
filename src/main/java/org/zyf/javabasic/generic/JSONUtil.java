package org.zyf.javabasic.generic;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/2/1  23:15
 */
public class JSONUtil {

    private JSONUtil() {
    }

    public static String toJSONString(Object data, Class<?> clazz, String... properties) {
        return Writer().object(data).filter(clazz, properties).build();
    }

    public static String toJSONString(Object data) {
        return Writer().object(data).build();
    }

    public static JSONUtil.Writer Writer() {
        return new JSONUtil.Writer();
    }

    public static class Writer {
        private Object object;
        private List<SimplePropertyPreFilter> filters = Lists.newArrayList();

        public Writer() {
        }

        public JSONUtil.Writer object(Object object) {
            this.object = object;
            return this;
        }

        public JSONUtil.Writer filter(Class<?> clazz, String... properties) {
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(clazz, properties);
            this.filters.add(filter);
            return this;
        }

        public String build() {
            SerializeWriter out = new SerializeWriter();

            try {
                JSONSerializer serializer = new JSONSerializer(out);
                serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
                serializer.config(SerializerFeature.DisableCircularReferenceDetect, true);
                Iterator var3 = this.filters.iterator();

                while (var3.hasNext()) {
                    SimplePropertyPreFilter filter = (SimplePropertyPreFilter) var3.next();
                    serializer.getPropertyPreFilters().add(filter);
                }

                serializer.write(this.object);
                String var8 = out.toString();
                return var8;
            } finally {
                out.close();
            }
        }
    }
}
