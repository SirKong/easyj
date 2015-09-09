package net.hs.easyj.dao.mybatis.builder;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 动态的 MappedStatement 创建者
 *
 * @author Gavin
 * @create 2015/4/20
 */
public class XmlStatementDynamicBuilder {

    private static final String XML_MAPPER = "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >" +
            "<mapper namespace=\"${namespace}\"><select id=\"${id}\" parameterType=\"${parameterType}\" resultType=\"${resultType}\">${sqlScript}</select></mapper>";

    private Configuration configuration;

    private String resource;

    private String id;

    private String namespace;

    private Class parameterType;

    private Class resultType;

    private String sqlScript;

    public XmlStatementDynamicBuilder(Configuration configuration, String resource) {
        this.configuration = configuration;
        this.resource = resource;
    }

    public XmlStatementDynamicBuilder id(String id) {
        this.id = id;
        return this;
    }


    public XmlStatementDynamicBuilder namespace(String namespace) {
        this.namespace = namespace;
        return this;
    }


    public XmlStatementDynamicBuilder parameterType(Class parameterType) {
        this.parameterType = parameterType;
        return this;
    }

    public XmlStatementDynamicBuilder resultType(Class resultType) {
        this.resultType = resultType;
        return this;
    }

    public XmlStatementDynamicBuilder sqlScript(String sqlScript) {
        this.sqlScript = sqlScript;
        return this;
    }

    public MappedStatement build() {
        //
        Map<String, String> variables = new HashMap<>();
        variables.put("namespace", namespace);
        variables.put("id", id);
        variables.put("parameterType", parameterType.getName());
        variables.put("resultType", resultType.getName());
        variables.put("sqlScript", sqlScript);
        //
        String xmlMapper = createXmlMapper(variables);
        InputStream inputStream = new ByteArrayInputStream(xmlMapper.getBytes());
        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(inputStream, configuration, resource, null);
        xmlMapperBuilder.parse();
        //
        return configuration.getMappedStatement(namespace + "." + id);
    }

    private String createXmlMapper(Map<String, String> variables) {
        Pattern xmlMapperPattern = Pattern.compile("\\$\\{(\\w+)\\}");
        Matcher matcher = xmlMapperPattern.matcher(XML_MAPPER);
        //
        StringBuffer sb = new StringBuffer();
        while(matcher.find()) {
            String varName = matcher.group(1);
            String varValue = variables.get(varName);
            //
            matcher.appendReplacement(sb, varValue);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
