package net.hs.easyj.web.extender.util;

import org.w3c.dom.CharacterData;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Gavin Hu on 2015/2/7.
 */
public class XmlUtils {

    public static Document parse(InputStream source) throws Exception {
        //
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        return db.parse(source);
    }

    /**
     * Retrieve all child elements of the given DOM element that match any of the given element names. Only look at the
     * direct child level of the given element; do not go into further depth (in contrast to the DOM API's
     * <code>getElementsByTagName</code> method).
     *
     * @param ele		   the DOM element to analyze
     * @param childEleNames the child element names to look for
     * @return a List of child <code>org.w3c.dom.Element</code> instances
     * @see Element
     * @see Element#getElementsByTagName
     */
    public static List<Element> getChildElementsByTagName(Element ele, String[] childEleNames) {
        List<String> childEleNameList = Arrays.asList(childEleNames);
        NodeList nl = ele.getChildNodes();
        List<Element> childEles = new ArrayList<Element>();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element && nodeNameMatch(node, childEleNameList)) {
                childEles.add((Element) node);
            }
        }
        return childEles;
    }

    /**
     * Retrieve all child elements of the given DOM element that match the given element name. Only look at the direct
     * child level of the given element; do not go into further depth (in contrast to the DOM API's
     * <code>getElementsByTagName</code> method).
     *
     * @param ele		  the DOM element to analyze
     * @param childEleName the child element name to look for
     * @return a List of child <code>org.w3c.dom.Element</code> instances
     * @see Element
     * @see Element#getElementsByTagName
     */
    public static List<Element> getChildElementsByTagName(Element ele, String childEleName) {
        return getChildElementsByTagName(ele, new String[]{childEleName});
    }

    /**
     * Utility method that returns the first child element identified by its name.
     *
     * @param ele		  the DOM element to analyze
     * @param childEleName the child element name to look for
     * @return the <code>org.w3c.dom.Element</code> instance, or <code>null</code> if none found
     */
    public static Element getChildElementByTagName(Element ele, String childEleName) {
        NodeList nl = ele.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element && nodeNameMatch(node, childEleName)) {
                return (Element) node;
            }
        }
        return null;
    }

    /**
     * Utility method that returns the first child element value identified by its name.
     *
     * @param ele		  the DOM element to analyze
     * @param childEleName the child element name to look for
     * @return the extracted text value, or <code>null</code> if no child element found
     */
    public static String getChildElementValueByTagName(Element ele, String childEleName) {
        Element child = getChildElementByTagName(ele, childEleName);
        return (child != null ? getTextValue(child) : null);
    }

    /**
     * Retrieve all child elements of the given DOM element

     * @param ele		   the DOM element to analyze
     * @return a List of child <code>org.w3c.dom.Element</code> instances
     */
    public static List<Element> getChildElements(Element ele) {
        NodeList nl = ele.getChildNodes();
        List<Element> childEles = new ArrayList<Element>();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                childEles.add((Element) node);
            }
        }
        return childEles;
    }

    /**
     * Extract the text value from the given DOM element, ignoring XML comments. <p>Appends all CharacterData nodes and
     * EntityReference nodes into a single String value, excluding Comment nodes.
     *
     * @see CharacterData
     * @see EntityReference
     * @see Comment
     */
    public static String getTextValue(Element valueEle) {
        StringBuilder sb = new StringBuilder();
        NodeList nl = valueEle.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node item = nl.item(i);
            if ((item instanceof CharacterData && !(item instanceof Comment)) || item instanceof EntityReference) {
                sb.append(item.getNodeValue());
            }
        }
        return sb.toString();
    }

    /**
     * Namespace-aware equals comparison. Returns <code>true</code> if either {@link Node#getLocalName} or {@link
     * Node#getNodeName} equals <code>desiredName</code>, otherwise returns <code>false</code>.
     */
    public static boolean nodeNameEquals(Node node, String desiredName) {
        return nodeNameMatch(node, desiredName);
    }

    /** Matches the given node's name and local name against the given desired name. */
    private static boolean nodeNameMatch(Node node, String desiredName) {
        return (desiredName.equals(node.getNodeName()) || desiredName.equals(node.getLocalName()));
    }

    /** Matches the given node's name and local name against the given desired names. */
    private static boolean nodeNameMatch(Node node, Collection desiredNames) {
        return (desiredNames.contains(node.getNodeName()) || desiredNames.contains(node.getLocalName()));
    }

}
