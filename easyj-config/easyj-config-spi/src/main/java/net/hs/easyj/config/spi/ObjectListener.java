package net.hs.easyj.config.spi;

/**
 * 对象监听器
 *
 * @author Gavin Hu
 * @create 2015/8/13
 */
public interface ObjectListener<T> {

    void change(T object);

}
