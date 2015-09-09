package net.hs.easyj.config.definition;

import org.junit.Test;

import java.util.List;

/**
 * @author gavin
 * @create 15/8/11
 */
public class ObjectConfigDefinitionScannerTest {

    @Test
    public void testScan() {
        ObjectConfigDefinitionScanner scanner = new DefaultObjectConfigDefinitionScanner();
        List<ObjectConfigDefinition> definitions = scanner.scan();
        //
        ObjectConfigDefinitionCategorizer categorizer = new ObjectConfigDefinitionCategorizer(definitions);
        System.out.println(categorizer.getCategories());
    }

}
