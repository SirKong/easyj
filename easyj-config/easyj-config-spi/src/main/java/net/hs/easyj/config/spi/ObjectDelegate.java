package net.hs.easyj.config.spi;

/**
 * 对象委托
 *
 * @author Gavin Hu
 * @create 2015/8/12
 */
public interface ObjectDelegate<T> {

    void setTarget(T target);

}
