package com.lambda.lambda.app.util.java;

import com.lambda.lambda.common.helper.MapHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.helper.string.StringReplacementHelper;
import com.lambda.lambda.common.util.string.StringList;
import com.lambda.lambda.common.util.string.StringMap;

/**
 * Utility class for creating a map implemented Java class
 */
public final class JavaMapImplementer {
    // New Instance Method
    public static JavaMapImplementer newInstance() {
        return new JavaMapImplementer();
    }

    // Constructor Method
    private JavaMapImplementer() {
        super();
    }

    // Main Instance Method
    public StringList implement(String keyTypeName, String valueTypeName, String fieldName) {
        String templateText = this.getTemplateText();
        StringMap replacementMap = StringHelper.newStringMap();
        MapHelper.put(replacementMap, "KeyType", keyTypeName);
        MapHelper.put(replacementMap, "ValueType", valueTypeName);
        MapHelper.put(replacementMap, "this.map", fieldName == null ? "this.map" : "this." + fieldName);
        MapHelper.put(replacementMap, "\r", "");
        String text = StringReplacementHelper.replace(templateText, replacementMap);
        StringList lines = StringHelper.split(text, "[\n]");
        return lines;
    }

    // Major Methods
    public String getTemplateText() {
        return "\t@Override\r\n" + //
                "\tpublic int size() {\r\n" + //
                "\t\treturn this.map.size();\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic boolean isEmpty() {\r\n" + //
                "\t\treturn this.map.isEmpty();\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic boolean containsKey(Object key) {\r\n" + //
                "\t\treturn this.map.containsKey(key);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic boolean containsValue(Object value) {\r\n" + //
                "\t\treturn this.map.containsValue(value);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic ValueType get(Object key) {\r\n" + //
                "\t\treturn this.map.get(key);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic ValueType put(KeyType key, ValueType value) {\r\n" + //
                "\t\treturn this.map.put(key, value);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic ValueType remove(Object key) {\r\n" + //
                "\t\treturn this.map.remove(key);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic void putAll(Map<? extends KeyType, ? extends ValueType> m) {\r\n" + //
                "\t\tthis.map.putAll(m);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic void clear() {\r\n" + //
                "\t\tthis.map.clear();\t\t\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic Set<KeyType> keySet() {\r\n" + //
                "\t\treturn this.map.keySet();\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic Collection<ValueType> values() {\r\n" + //
                "\t\treturn this.map.values();\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic Set<Entry<KeyType, ValueType>> entrySet() {\r\n" + //
                "\t\treturn this.map.entrySet();\r\n" + //
                "\t}";
    }
}