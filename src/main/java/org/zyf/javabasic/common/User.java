package org.zyf.javabasic.common;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author yanfengzhang
 * @className User
 * @description 基本用户类
 * @date 2020/7/28 15:22
 */
@Data
public class User {
    private String id;
    private String name;
    private String age;

    public User(String id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return this.id + " " + this.name + " " + this.age;
    }

    /**
     * 因为不重写 equals 方法，执行 user1.equals(user2) 比较的就是两个对象的地址（即 user1 == user2），肯定是不相等的，见 Object 源码
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            /*地址相等*/
            return true;
        }

        if (obj == null) {
            /*非空性：对于任意非空引用x，x.equals(null)应该返回false*/
            return false;
        }

        if (obj instanceof User) {
            User other = (User) obj;
            //需要比较的字段相等，则这两个对象相等
            if (equalsStr(this.name, other.name)
                    && equalsStr(this.age, other.age)) {
                return true;
            }
        }

        return false;
    }

    private boolean equalsStr(String str1, String str2) {
        if (StringUtils.isEmpty(str1) && StringUtils.isEmpty(str2)) {
            return true;
        }
        if (!StringUtils.isEmpty(str1) && str1.equals(str2)) {
            return true;
        }
        return false;
    }

    /**
     * hashCode 是用于散列数据的快速存取，如利用 HashSet/HashMap/Hashtable 类来存储数据时，都会根据存储对象的 hashCode 值来进行判断是否相同的
     * 生成一个 int 类型的变量 result，并且初始化一个值，比如17, 对类中每一个重要字段，也就是影响对象的值的字段，
     * 也就是 equals 方法里有比较的字段，进行以下操作：a. 计算这个字段的值 filedHashValue = filed.hashCode(); b. 执行 result = 31 * result + filedHashValue;
     * 备注：String 源码中也使用的 31，然后网上说有这两点原因：更少的乘积结果冲突 + 31 可以被 JVM 优化
     *
     * @return
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (name == null ? 0 : name.hashCode());
        result = 31 * result + (age == null ? 0 : age.hashCode());
        return result;
    }
}
