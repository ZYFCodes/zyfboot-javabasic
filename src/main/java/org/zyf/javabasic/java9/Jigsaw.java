package org.zyf.javabasic.java9;

/**
 * 描述：Java 9 最大的变化之一是引入了模块系统（Jigsaw 项目）。
 * 模块就是代码和数据的封装体。模块的代码被组织成多个包，每个包中包含Java类和接口；模块的数据则包括资源文件和其他静态信息。
 * Java 9 模块的重要特征是在其工件（artifact）的根目录中包含了一个描述模块的 module-info.class 文 件。
 * 工件的格式可以是传统的 JAR 文件或是 Java 9 新增的 JMOD 文件。这个文件由根目录中的源代码文件 module-info.java 编译而来。该模块声明文件可以描述模块的不同特征。
 *
 * @author yanfengzhang
 * @date 2019-11-27 17:11
 */
public class Jigsaw {
}
