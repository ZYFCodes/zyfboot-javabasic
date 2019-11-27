package org.zyf.javabasic.java8;

/**
 * 描述：Stream 使用一种类似用 SQL 语句从数据库查询数据的直观方式来提供一种对 Java 集合运算和表达的高阶抽象。
 *      这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选，排序，聚合等。
 *      元素流在管道中经过中间操作（intermediate operation）的处理，最后由最终操作(terminal operation)得到前面处理的结果。
 *      +--------------------+       +------+   +------+   +---+   +-------+
 *      | stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
 *      +--------------------+       +------+   +------+   +---+   +-------+
 *
 * @author yanfengzhang
 * @date 2019-11-27 15:05
 */
public class Stream {
}
