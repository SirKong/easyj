package net.hs.easyj.web.xdoc.scanner;

import net.hs.easyj.web.xdoc.model.XDoc;

import java.util.Map;

/**
 * XDoc 扫描器
 *
 * @author Gavin Hu
 * @create 2015/5/15
 */
public interface XDocScanner {

    Map<String, XDoc> scan();

}
