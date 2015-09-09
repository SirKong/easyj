package net.hs.easyj.config.spi;

/**
 * 对象创建器
 *
 * @author Gavin Hu
 * @create 2015/7/22
 */
public interface ObjectCreator<C, O> {

    O create(C config);

}
