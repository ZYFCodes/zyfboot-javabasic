package org.zyf.javabasic.test.truncate;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 复杂对象类定义
 * @author: zhangyanfeng
 * @create: 2024-12-01 11:27
 **/
public class ComplexObject {
    public User user;
    public Metadata metadata;

    public static class User {
        public String id;
        public Profile profile;
    }

    public static class Profile {
        public String bio;
        public Preferences preferences;
    }

    public static class Preferences {
        public String theme;
        public String language;
    }

    public static class Metadata {
        public String requestId;
        public String timestamp;
        public List<String> tags;
    }
}
