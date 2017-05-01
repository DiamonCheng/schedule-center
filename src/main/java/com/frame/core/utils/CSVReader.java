package com.frame.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/***
 * 这是一个读取CSV文件格式的工具类，读取前请确认文件符合csv文件标准格式。
 * 特色。。可以直接读取出List<T>类型
 * 问题：已经经过测试，经过了漫长的DEBUG过程。可能还会有问题，如遇到问题欢迎反馈。
 * @author Defferson.Cheng
 * @version $Id: CSVReader.java, v 0.1 2016年2月24日 下午3:50:33 Defferson.Cheng Exp $
 */
public class CSVReader {
    private String charset = "utf-8";
    private InputStream in;
    private char   split   = ',';

    /**
     * 构造函数，请确认是符合csv文件规范的文件
     * @param filePath 路径不包括文件名
     * @param fileName 文件名包括后缀名
     * @param split 分隔符
     * @param charset 字符集
     * @throws FileNotFoundException
     */
    public CSVReader(String filePath, String fileName, char split, String charset) throws FileNotFoundException {
        this.charset = charset;
        in = new FileInputStream(new File(filePath, fileName));
        this.split = split;
    }

    /**
     * 构造函数，请确认是符合csv文件规范的文件，字符集默认是utf-8 分隔符为逗号
     * @param filePath 路径不包括文件名
     * @param fileName 文件名包括后缀名
     * @throws FileNotFoundException
     */
    public CSVReader(String filePath, String fileName) throws FileNotFoundException {
    	in = new FileInputStream(new File(filePath, fileName));
    }

    /**
     * 构造函数，请确认是符合csv文件规范的文件 
     * @param file 要打开的文件
     * @param split 分隔符
     * @param charset 字符集
     * @throws FileNotFoundException
     */
    public CSVReader(File file, String charset, char split) throws FileNotFoundException {
    	in = new FileInputStream(file);
        this.charset = charset;
        this.split = split;

    }
    /**
     * 
     * @param in
     * @param cs
     */
    public CSVReader(InputStream in,String cs){
    	this.in=in;
    	this.charset=cs;
    }
    /**
     * 
     * @param in
     * @param cs
     * @param split
     */
    public CSVReader(InputStream in,String cs,char split){
    	this.in=in;
    	this.charset=cs;
    	this.split=split;
    }
    /**
     * 构造函数，请确认是符合csv文件规范的文件 ，字符集默认是utf-8 分隔符为逗号
     * @param file 打开的文件
     * @throws FileNotFoundException
     */
    public CSVReader(File file) throws FileNotFoundException {
    	in = new FileInputStream(file);
    }

    final private BufferedReader load() throws FileNotFoundException {
        return new BufferedReader(new InputStreamReader(in,
            Charset.forName(charset)));
    }
    /**
     * 可以得到包括头在内的字符串数组
     * 
     * @return 读出并且分隔好的字符串数组
     * @throws IOException
     */
    public String[][] getArrays() throws IOException {
    	BufferedReader r = load();
        List<String[]> list = new ArrayList<String[]>();
        char[] cbuf=new char[1024];
        int len;
        StringBuilder sb=new StringBuilder();
        while((len=r.read(cbuf))!=-1) sb.append(cbuf, 0, len);
        char[] seq=new char[sb.length()];
        sb.getChars(0, sb.length(), seq, 0);
        boolean inquort = false;
        sb=new StringBuilder();
        List<String> strList=new ArrayList<String>();
		for (int i = 0; i < seq.length; i++) {
			char c = seq[i];
			if (c == '\"') {
				if (!inquort)
					inquort = true;
				else {
					if (i < seq.length - 1 && (seq[i + 1] == split||seq[i + 1] =='\n')) {
						inquort = false;
					} else if (i < seq.length - 1 && seq[i + 1] == '\"') {
						sb.append(c);
						i++;
					} else {
						throw new IOException("SyntaxError: Invalid or unexpected token \" at index "+i);
					}
				}
			} else if (c == split && !inquort) {
				if (sb.length() == 0 && (0 == i || seq[i - 1] != '\"')) {
					strList.add(null);
				} else
					strList.add(sb.toString());
				sb=new StringBuilder();
			} else if ((c=='\n'||c=='\r')&&!inquort){
			    if (c=='\r'&&i<seq.length-1&&seq[i+1]=='\n') i++;
				if (sb.length() == 0 && (0 == i || seq[i - 1] != '\"')) {
					strList.add(null);
				} else
					strList.add(sb.toString());
				sb=new StringBuilder();
				list.add(strList.toArray(new String[strList.size()]));
				strList.clear();
			} else {
				sb.append(c);
			}
		}
        return list.toArray(new String[list.size()][]);
    }
    /**
     * 得到类似List<T> 的数据
     * 注意：javabean 请按标准规范写，没有默认构造函数、seter，geter 不规范的都会抛异常 比如void setpId(String )就会抛出nosuchmethod异常。。
     * @param classOfObject 可以放javabean的类型
     * @param fieldsMap 可以为空，为空时，认为javaBean字段名和数据库字段名一致（区分大小写），并且会拿出csv中所有列数据，不为空时，map的key放置数据库字段名称，value放置javabean字段名，将只会读取map中放置的列。
     * @param praser 可以为空，为空时采用默认的转换器，可以转换基本类型以及字符串，Date类型（默认partten为 yyyy-HH-dd hh:mm:ss） 有自定义需求的请重写本类中的 FieldsParserStandard 类 接口为FieldsParser
     * @return 返回的列表数据 没有在csv中的数据为初始值
     * @throws IOException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws ParseException
     */
    public <T> List<T> getObjects(Class<T> classOfObject, Map<String, String> fieldsMap,
    		FieldsParser praser) throws IOException, InstantiationException,
    IllegalAccessException, NoSuchFieldException,
    SecurityException, NoSuchMethodException,
    IllegalArgumentException,
    InvocationTargetException, ParseException {
    	if (null == praser)
            praser = new FieldsParserStandard();
    	String[][] dataSet=getArrays();
    	if (dataSet.length==0) throw new IOException("必须包含标题在CSV文件中");
    	String[] header=dataSet[0];
    	List<T> list = new ArrayList<T>();
    	for (int i = 1; i < dataSet.length; i++) {
    		T e = classOfObject.newInstance();
			for (int j = 0; j < dataSet[i].length; j++) {
				if (j<header.length) setFields(classOfObject, e, header[j], dataSet[i][j], fieldsMap, praser);
			}
			list.add(e);
		}
    	return list;
    }
    

    private final <T> T setFields(Class<T> c, T e, String field, String value,
                                  Map<String, String> fieldMap, FieldsParser praser)
                                                                                    throws NoSuchFieldException,
                                                                                    SecurityException,
                                                                                    NoSuchMethodException,
                                                                                    IllegalAccessException,
                                                                                    IllegalArgumentException,
                                                                                    InvocationTargetException,
                                                                                    InstantiationException,
                                                                                    ParseException {
        String fieldName = fieldMap == null ? field : fieldMap.get(field);
        if (null == fieldName)
            return e;
        Field f = c.getDeclaredField(fieldName);
        StringBuilder sb = new StringBuilder(f.getName());
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        sb.insert(0, "set");
        Method m = c.getMethod(sb.toString(), f.getType());
        m.invoke(e, praser.prase(f.getType(), value, fieldName));
        return e;
    }

    /**
     * 把字符串转化成javaBean字段类的工具，复写建议用类似这样的形式。 class classname extends FieldsParserStandard implements FieldsParser
     * 
     * @author Defferson.Cheng
     * @version $Id: CSVReader.java, v 0.1 2016年2月24日 下午7:01:16 Defferson.Cheng Exp $
     */
    public interface FieldsParser {
        /**
         * 
         * 
         * @param fieldClass 字段域的类型
         * @param value 字符串的值
         * @param fieldName 字段名
         * @return
         * @throws InstantiationException
         * @throws IllegalAccessException
         * @throws ParseException
         */
        public <T> T prase(Class<T> fieldClass, String value, String fieldName)
                                                                               throws InstantiationException,
                                                                               IllegalAccessException,
                                                                               ParseException;
    }

    public static class FieldsParserStandard implements FieldsParser {
        @Override
        @SuppressWarnings("unchecked")
        public <T> T prase(Class<T> c, String value, String fieldName)
                                                                      throws InstantiationException,
                                                                      IllegalAccessException,
                                                                      ParseException {
            if (value == null) {
                if (c == int.class) {
                    return (T) new Integer(0);
                } else if (c == long.class) {
                    return (T) new Long(0);
                } else if (c == short.class) {
                    return (T) new Short((short) 0);
                } else if (c == char.class) {
                    return (T) new Character('\0');
                } else if (c == byte.class) {
                    return (T) new Byte((byte) 0);
                } else if (c == float.class) {
                    return (T) new Float(0);
                } else if (c == double.class) {
                    return (T) new Double(0);
                } else if (c == boolean.class) {
                    return (T) Boolean.FALSE;
                } else
                    return null;
            } else {
                if (c == int.class || c == Integer.class) {
                    return (T) new Integer(value);
                } else if (c == long.class || c == Long.class) {
                    return (T) new Long(value);
                } else if (c == short.class || c == Short.class) {
                    return (T) new Short(value);
                } else if (c == char.class || c == Character.class) {
                    return (T) new Character(value.charAt(0));
                } else if (c == byte.class || c == Byte.class) {
                    return (T) new Byte(value);
                } else if (c == float.class || c == Float.class) {
                    return (T) new Float(value);
                } else if (c == double.class || c == Double.class) {
                    return (T) new Double(value);
                } else if (c == boolean.class || c == Boolean.class) {
                    return (T) Boolean.valueOf(value);
                } else if (c == String.class) {
                    return (T) value;
                } else if (c == Date.class) {
                    return (T) new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(value);
                } else {
                    throw new ParseException("Can not prase String to " + c.getName()
                                             + ",you can define the praser yourself.", 265);
                }
            }
        }
    }
}
