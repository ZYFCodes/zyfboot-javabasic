package org.zyf.javabasic.springextend.runext;

import com.google.common.collect.Lists;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.common.User;

import java.util.List;

/**
 * @author yanfengzhang
 * @description 演示如何使用CommandLineRunner接口创建一个名为UserImportCommand的命令行工具，用于导入用户数据到应用程序中。
 * @date 2021/2/23  23:24
 */
@Component
public class UserImportCommand implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        List<User> users = readUserFromFile("fileName");
        System.out.println("UserImportCommand readUserFromFile importUsers!");
    }

    // 从数据文件中读取用户信息
    private List<User> readUserFromFile(String fileName) {
        // 省略代码，从文件中读取用户信息，返回一个User对象列表
        return Lists.newArrayList();
    }
}
