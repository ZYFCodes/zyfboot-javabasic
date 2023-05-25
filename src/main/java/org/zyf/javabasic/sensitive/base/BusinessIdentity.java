package org.zyf.javabasic.sensitive.base;

import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  21:10
 */
@Data
public class BusinessIdentity {
    public long id;
    public String firstBusinessIdentity;
    public String secondBusinessIdentity;
    public long ctime;
    public long utime;
    public String cname;
    public String uname;
    public String name;
    public long templateId;
}
