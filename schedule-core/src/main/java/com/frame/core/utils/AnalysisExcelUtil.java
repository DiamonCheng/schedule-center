package com.frame.core.utils;//package com.frame.core.utils;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//import java.lang.reflect.Method;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Null;
//
//import org.apache.commons.beanutils.ConvertUtils;
//import org.apache.poi.EncryptedDocumentException;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
//
///**
// * excel解析工具类 使用POI
// * 需要导入POI包
// * @author Defferson.Cheng
// *
// */
//public class AnalysisExcelUtil{
//	public static class AnalysisExcelException extends Exception{
//		private static final long serialVersionUID = 1439685890295604517L;
//		public AnalysisExcelException(String message){
//			super(message);
//		}
//		public AnalysisExcelException(Throwable e){
//			super("转换Excel出现的异常", e);
//		}
//	}
//	public interface DataTranslater{
//		public <T> T trans(String fieldName,String strVal,Class<T> fieldType);
//	}
//	public static class DefaultDataTranslater implements DataTranslater{
//		static SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		static SimpleDateFormat f2=new SimpleDateFormat("yyyy-MM-dd");
//		@SuppressWarnings("unchecked")
//		@Override
//		public <T> T trans(String fieldName, String strVal, Class<T> fieldType) {
//			if (fieldType.equals(Date.class)){
//				try {
//					return (T) f.parse(strVal);
//				} catch (ParseException e) {
//					try {
//						return (T) f2.parse(strVal);
//					} catch (ParseException e1) {
//						e1.addSuppressed(e);
//						throw new RuntimeException(e1);
//					}
//				}
//			}else if (fieldType.equals(java.sql.Date.class)){
//				try {
//					return (T) new java.sql.Date(f.parse(strVal).getTime());
//				} catch (ParseException e) {
//					try {
//						return (T) new java.sql.Date(f.parse(strVal).getTime());
//					} catch (ParseException e1) {
//						e1.addSuppressed(e);
//						throw new RuntimeException(e1);
//					}
//				}
//			}else {
//				return (T) ConvertUtils.convert(strVal, fieldType);
//			}
//		}
//	}
//	@Retention(RetentionPolicy.RUNTIME)
//	@Target({ElementType.FIELD,ElementType.METHOD})
//	public @interface MappedToHead{
//		String value();
//	}
//	/**
//	 * Excel映射成List，需要在类中的字段中使用@MappedToHead注解
//	 * @param cls
//	 * @param ins
//	 * @param tans
//	 * @return
//	 * @throws AnalysisExcelException
//	 */
//	public static <T> List<T> analysisToBean(Class<T> cls,InputStream ins,DataTranslater tans) throws AnalysisExcelException{
//		List<T> list=new ArrayList<T>();
//		String[][] table;
//		Map<String,String> headFieldMap=resolveClassFieldHeads(cls);
//		if (tans==null) tans=new DefaultDataTranslater();
//		try {
//			table = analysisExcel(ins);
//		} catch (Exception e) {
//			throw new AnalysisExcelException(e);
//		}
//		if (table.length==0) throw new AnalysisExcelException("Excel没有标题行");
//		String[] heads=table[0];
//		try {
//			for(int i=1;i<table.length;i++){
//				T target=cls.newInstance();
//				for (int j = 0; j < heads.length; j++) {
//					String head=heads[j];
//					if (head.length()==0) continue;
//					String field=headFieldMap.get("head");
//					String strVal=null;
//					try {
//						strVal=table[i][j];
//					} catch (Exception e) {
//						strVal="";
//					}
//					setValueByField(target, 
//							field, 
//							tans.trans(head, strVal, resolveFieldClass(cls, field)));
//				}
//				list.add(target);
//			}
//		} catch (Exception e) {
//			throw new AnalysisExcelException(e);
//		}
//		return list;
//	}
//	private static Map<String,String> resolveClassFieldHeads(Class<?> cls){
//		//TODO
//		return null;
//	}
//	/**
//	 * 解析输入流的excel成为数组
//	 * 注：
//	 * 1.内容前后的包括回车空白字符会被去除
//	 * 2.空行会被无视
//	 * 3.每行列数不固定所以记住有可能下标超出
//	 * 4.值不会出现null的现象空值会处理成为空字符串
//	 * @param inputStream
//	 * @return 返回表格 二维数组
//	 * @throws EncryptedDocumentException
//	 * @throws InvalidFormatException
//	 * @throws IOException
//	 */
//	public static String[][] analysisExcel(InputStream inputStream) throws EncryptedDocumentException, InvalidFormatException, IOException{
//		Workbook workBook = null;
//		workBook=WorkbookFactory.create(inputStream);
//		Sheet sheet = workBook.getSheetAt(0);
//		return analysisSheet(sheet);
//	}
//	private static String[][] analysisSheet(Sheet sheet){
//		List<String[]> table=new LinkedList<String[]>();
//		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
//			String[] row=analysisRow(sheet.getRow(i));
//			if (row!=null) table.add(row);
//		}
//		return table.toArray(new String[table.size()][]);
//	}
//	private static String[] analysisRow(Row row){
//		if (row==null) return null;
//		List<String> cellList=new LinkedList<String>();
//		boolean isValidRow=false;
//		for(int i=0;i<row.getLastCellNum();i++){
//			Cell cell=row.getCell(i);
//			if (cell==null) cellList.add("");
//			else {
//				cell.setCellType(Cell.CELL_TYPE_STRING);
//				String value=cell.getStringCellValue();
//				if (null!=value) {
//					value=trimEx(value);
//					cellList.add(value);
//				}else{
//					cellList.add("");
//				}
//				if (null!=value&&!"".equals(value)) isValidRow=true; 
//			}
//		}
//		if (isValidRow) return cellList.toArray(new String[cellList.size()]);
//		return null;
//	}
//	private static String trimEx(String s){
//		if (s==null) return null;
//		return s.replaceAll("^[\\s]+|[\\s]+$", "");
//	}
//}