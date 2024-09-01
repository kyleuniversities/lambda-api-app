package com.lambda.lambda.app.util.java;

import com.lambda.lambda.common.helper.MapHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.helper.string.StringReplacementHelper;
import com.lambda.lambda.common.util.string.StringList;
import com.lambda.lambda.common.util.string.StringMap;

/**
 * Utility class for creating a set implemented Java class
 */
public final class JavaSetImplementer {
    // New Instance Method
    public static JavaSetImplementer newInstance() {
        return new JavaSetImplementer();
    }

    // Constructor Method
    private JavaSetImplementer() {
        super();
    }

    // Main Instance Method
    public StringList implement(String typeName, String fieldName) {
        String templateText = this.getTemplateText(typeName);
        StringMap replacementMap = StringHelper.newStringMap();
        MapHelper.put(replacementMap, "Type", typeName);
        MapHelper.put(replacementMap, "this.set", fieldName == null ? "this.set" : "this." + fieldName);
        MapHelper.put(replacementMap, "\r", "");
        String text = StringReplacementHelper.replace(templateText, replacementMap);
        StringList lines = StringHelper.split(text, "[\n]");
        return lines;
    }

    // Major Methods
    public String getTemplateText(String typeName) {
        return "\t@Override\r\n" + //
                "\tpublic int size() {\r\n" + //
                "\t\treturn this.set.size();\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic boolean isEmpty() {\r\n" + //
                "\t\treturn this.set.isEmpty();\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic boolean contains(Object o) {\r\n" + //
                "\t\treturn this.set.contains(o);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic Iterator<Type> iterator() {\r\n" + //
                "\t\treturn this.set.iterator();\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic Object[] toArray() {\r\n" + //
                "\t\treturn this.set.toArray();\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic <T> T[] toArray(T[] a) {\r\n" + //
                "\t\treturn this.set.toArray(a);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic boolean add(Type e) {\r\n" + //
                "\t\treturn this.set.add(e);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic boolean remove(Object o) {\r\n" + //
                "\t\treturn this.set.remove(o);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic boolean containsAll(Collection<?> c) {\r\n" + //
                "\t\treturn this.set.containsAll(c);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic boolean addAll(Collection<? extends Type> c) {\r\n" + //
                "\t\treturn this.set.addAll(c);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic boolean removeAll(Collection<?> c) {\r\n" + //
                "\t\treturn this.set.removeAll(c);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic boolean retainAll(Collection<?> c) {\r\n" + //
                "\t\treturn this.set.retainAll(c);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic void clear() {\r\n" + //
                "\t\tthis.set.clear();\r\n" + //
                "\t}";
    }
}