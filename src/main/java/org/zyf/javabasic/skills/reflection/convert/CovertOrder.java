package org.zyf.javabasic.skills.reflection.convert;

//import org.bson.Document;

/**
 * 描述：
 *
 * @author yanfengzhang
 * @date 2020-01-02 17:41
 */
public class CovertOrder {
    /**
     public static Document pojo2Doc(Object obj) throws Exception {
     Document doc = new Document();
     Field[] declaredFields = obj.getClass().getDeclaredFields();

     for (Field field : declaredFields) {
     field.setAccessible(true);
     doc.put(field.getName(), field.get(obj));
     }

     return doc;
     }

     public static Order doc2pojo(Document doc) throws Exception {
     Order order = new Order();
     for (String key : doc.keySet()) {
     Field field = order.getClass().getDeclaredField(key);
     field.setAccessible(true);
     field.set(order, doc.get(key));
     }
     return order;
     }
     **/
}

