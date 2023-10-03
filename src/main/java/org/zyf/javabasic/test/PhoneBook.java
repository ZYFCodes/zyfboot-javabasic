package org.zyf.javabasic.test;

import java.util.HashMap;

/**
 * @program: zyfboot-javabasic
 * @description: 实现一个简单的电话号码簿（电话簿）。
 *               在这个电话簿中，用户可以存储联系人的姓名和电话号码，并且能够通过姓名快速查找相应的电话号码。
 * @author: zhangyanfeng
 * @create: 2023-09-24 22:50
 **/
public class PhoneBook {
    private HashMap<String, String> phoneNumbers;

    public PhoneBook() {
        phoneNumbers = new HashMap<>();
    }

    // 添加联系人和电话号码
    public void addContact(String name, String phoneNumber) {
        phoneNumbers.put(name, phoneNumber);
    }

    // 根据姓名查找电话号码
    public String findPhoneNumber(String name) {
        if (phoneNumbers.containsKey(name)) {
            return phoneNumbers.get(name);
        } else {
            return "Contact not found";
        }
    }

    public static void main(String[] args) {
        // 创建电话号码簿
        PhoneBook phoneBook = new PhoneBook();

        // 添加联系人和电话号码
        phoneBook.addContact("Alice", "123-456-7890");
        phoneBook.addContact("Bob", "987-654-3210");
        phoneBook.addContact("Charlie", "555-123-4567");

        // 查找联系人的电话号码
        System.out.println("Alice's Phone Number: " + phoneBook.findPhoneNumber("Alice"));
        System.out.println("Bob's Phone Number: " + phoneBook.findPhoneNumber("Bob"));
        System.out.println("Eve's Phone Number: " + phoneBook.findPhoneNumber("Eve"));
    }
}
