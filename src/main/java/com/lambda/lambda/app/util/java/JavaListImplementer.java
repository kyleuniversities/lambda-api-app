package com.lambda.lambda.app.util.java;

import com.lambda.lambda.common.helper.MapHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.helper.string.StringReplacementHelper;
import com.lambda.lambda.common.util.string.StringList;
import com.lambda.lambda.common.util.string.StringMap;

/**
 * Utility class for creating a list implemented Java class
 */
public final class JavaListImplementer {
    // New Instance Method
    public static JavaListImplementer newInstance() {
        return new JavaListImplementer();
    }

    // Constructor Method
    private JavaListImplementer() {
        super();
    }

    // Main Instance Method
    public StringList implement(String typeName, String fieldName) {
        String templateText = this.getTemplateText(typeName);
        StringMap replacementMap = StringHelper.newStringMap();
        MapHelper.put(replacementMap, "Type", typeName);
        MapHelper.put(replacementMap, "this.list", fieldName == null ? "this.list" : "this." + fieldName);
        MapHelper.put(replacementMap, "\r", "");
        String text = StringReplacementHelper.replace(templateText, replacementMap);
        StringList lines = StringHelper.split(text, "[\n]");
        return lines;
    }

    // Major Methods
    public String getTemplateText(String typeName) {
        return "\t@Override\r\n" + //
                "\tpublic int size() {\r\n" + //
                "\t\treturn this.list.size();\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic boolean isEmpty() {\r\n" + //
                "\t\treturn this.list.isEmpty();\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic boolean contains(Object o) {\r\n" + //
                "\t\treturn this.list.contains(o);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic Iterator<Type> iterator() {\r\n" + //
                "\t\treturn this.list.iterator();\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic Object[] toArray() {\r\n" + //
                "\t\treturn this.list.toArray();\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic <T> T[] toArray(T[] a) {\r\n" + //
                "\t\treturn this.list.toArray(a);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic boolean add(Type e) {\r\n" + //
                "\t\treturn this.list.add(e);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic boolean remove(Object o) {\r\n" + //
                "\t\treturn this.list.remove(o);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic boolean containsAll(Collection<?> c) {\r\n" + //
                "\t\treturn this.list.containsAll(c);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic boolean addAll(Collection<? extends Type> c) {\r\n" + //
                "\t\treturn this.list.addAll(c);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic boolean addAll(int index, Collection<? extends Type> c) {\r\n" + //
                "\t\treturn this.list.addAll(index, c);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic boolean removeAll(Collection<?> c) {\r\n" + //
                "\t\treturn this.list.removeAll(c);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic boolean retainAll(Collection<?> c) {\r\n" + //
                "\t\treturn this.list.retainAll(c);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic void clear() {\r\n" + //
                "\t\tthis.list.clear();\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic Type get(int index) {\r\n" + //
                "\t\treturn this.list.get(index);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic Type set(int index, Type element) {\r\n" + //
                "\t\treturn this.list.set(index, element);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic void add(int index, Type element) {\r\n" + //
                "\t\tthis.list.add(index, element);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic Type remove(int index) {\r\n" + //
                "\t\treturn this.list.remove(index);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic int indexOf(Object o) {\r\n" + //
                "\t\treturn this.list.indexOf(o);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic int lastIndexOf(Object o) {\r\n" + //
                "\t\treturn this.list.lastIndexOf(o);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic ListIterator<Type> listIterator() {\r\n" + //
                "\t\treturn this.list.listIterator();\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic ListIterator<Type> listIterator(int index) {\r\n" + //
                "\t\treturn this.list.listIterator(index);\r\n" + //
                "\t}\r\n" + //
                "\r\n" + //
                "\t@Override\r\n" + //
                "\tpublic List<Type> subList(int fromIndex, int toIndex) {\r\n" + //
                "\t\treturn this.list.subList(fromIndex, toIndex);\r\n" + //
                "\t}";
    }
}