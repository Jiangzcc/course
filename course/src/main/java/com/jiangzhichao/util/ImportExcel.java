package com.jiangzhichao.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * ����Excel������==����Ա���Ŀ����Ӧ�޸�
 * 
 * @author BornToWin
 *
 */
public class ImportExcel{

	//������ʽ ����ƥ�����Եĵ�һ����ĸ
	private static final String REGEX = "[a-zA-Z]";

	/**
	 * ����: Excel���ݵ��뵽���ݿ�
	 * ����: originUrl[Excel�������·��]
	 * ����: startRow[�ӵڼ��п�ʼ]
	 * ����: endRow[���ڼ��н���
	 *                  (0��ʾ������;
	 *                  ������ʾ���ڼ��н���;
	 *                  ������ʾ�������ڼ��н���)]
	 * ����: clazz[Ҫ���صĶ��󼯺ϵ�����]
	 */
	public static List<?> importExcel(String originUrl,int startRow,int endRow,Class<?> clazz) throws IOException {
		//�Ƿ��ӡ��ʾ��Ϣ
		boolean showInfo=true;
		return doImportExcel(originUrl,startRow,endRow,showInfo,clazz);
	}

	/**
	 * ����:����ʵ�ֵ���
	 */
	private static List<Object> doImportExcel(String originUrl,int startRow,int endRow,boolean showInfo,Class<?> clazz) throws IOException {
		// �ж��ļ��Ƿ����
		File file = new File(originUrl);
		if (!file.exists()) {
			throw new IOException("�ļ���Ϊ" + file.getName() + "Excel�ļ������ڣ�");
		}
		HSSFWorkbook wb = null;
		FileInputStream fis=null;
		List<Row> rowList = new ArrayList<Row>();
		try {
			fis = new FileInputStream(file);
			// ȥ��Excel
			wb = new HSSFWorkbook(fis);
			Sheet sheet = wb.getSheetAt(0);
			// ��ȡ����к�
			int lastRowNum = sheet.getLastRowNum();
			if (lastRowNum > 0) { // ���>0����ʾ������
				out("\n��ʼ��ȡ��Ϊ��" + sheet.getSheetName() + "�������ݣ�",showInfo);
			}
			Row row = null;
			// ѭ����ȡ
			for (int i = startRow; i <= lastRowNum + endRow; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					rowList.add(row);
					out("��" + (i + 1) + "�У�",showInfo,false);
					// ��ȡÿһ��Ԫ���ֵ
					for (int j = 0; j < row.getLastCellNum(); j++) {
						String value = getCellValue(row.getCell(j));
						if (!value.equals("")) {
							out(value + " | ",showInfo,false);
						}
					}
					out("",showInfo);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			wb.close();
		}
		return returnObjectList(rowList,clazz);
	}

	/**
	 * ����:��ȡ��Ԫ���ֵ
	 */
	private static String getCellValue(Cell cell) {
		Object result = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				result = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				result = cell.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				result = cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA:
				result = cell.getCellFormula();
				break;
			case Cell.CELL_TYPE_ERROR:
				result = cell.getErrorCellValue();
				break;
			case Cell.CELL_TYPE_BLANK:
				break;
			default:
				break;
			}
		}
		return result.toString();
	}

	/**
	 * ����:����ָ���Ķ��󼯺�
	 */
	private static List<Object> returnObjectList(List<Row> rowList,Class<?> clazz) {
		List<Object> objectList=null;
		Object obj=null;
		String attribute=null;
		String value=null;
		int j=0;
		try {   
			objectList=new ArrayList<Object>();
			Field[] declaredFields = clazz.getDeclaredFields();
			for (Row row : rowList) {
				j=0;
				obj = clazz.newInstance();
				for (Field field : declaredFields) {    
					attribute=field.getName().toString();
					value = getCellValue(row.getCell(j));
					if(value.endsWith(".0")) {
						value = value.substring(0, value.length()-2);
					}
					setAttrributeValue(obj,attribute,value);    
					j++;
				}
				objectList.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectList;
	}

	/**
	 * ����:��ָ�������ָ�����Ը�ֵ
	 */
	private static void setAttrributeValue(Object obj,String attribute,String value) {
		//�õ������Ե�set������
		String method_name = convertToMethodName(attribute,obj.getClass(),true);
		Method[] methods = obj.getClass().getMethods();
		for (Method method : methods) {
			/**
			 * ��Ϊ����ֻ�ǵ���bean�����Ե�set�������������Ʋ����ظ�
			 * ����set����Ҳ�����ظ������Ծ�ֱ���÷�������ȥ����һ������
			 * ��ע����java�У�����һ�������������Ƿ�������������
			 */
			if(method.getName().equals(method_name))
			{
				Class<?>[] parameterC = method.getParameterTypes();
				try {
					/**�����(����,������,������,�ֽ���,ʱ������),
					 * ���ո��ԵĹ����valueֵת���ɸ��Ե�����
					 * ����һ�ɰ�����ǿ��ת��(����:String����)
					 */
					if(parameterC[0] == int.class || parameterC[0]==java.lang.Integer.class)
					{
						value = value.substring(0, value.lastIndexOf("."));
						method.invoke(obj,Integer.valueOf(value));
						break;
					}else if(parameterC[0] == float.class || parameterC[0]==java.lang.Float.class){
						method.invoke(obj, Float.valueOf(value));
						break;
					}else if(parameterC[0] == double.class || parameterC[0]==java.lang.Double.class)
					{
						method.invoke(obj, Double.valueOf(value));
						break;
					}else if(parameterC[0] == byte.class || parameterC[0]==java.lang.Byte.class)
					{
						method.invoke(obj, Byte.valueOf(value));
						break;
					}else if(parameterC[0] == boolean.class|| parameterC[0]==java.lang.Boolean.class)
					{
						method.invoke(obj, Boolean.valueOf(value));
						break;
					}else if(parameterC[0] == java.util.Date.class)
					{
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date date=null;
						try {
							date=sdf.parse(value);
						} catch (Exception e) {
							e.printStackTrace();
						}
						method.invoke(obj,date);
						break;
					}else
					{
						method.invoke(obj,parameterC[0].cast(value));
						break;
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * ����:�����������ɶ�Ӧ��set/get����
	 */
	private static String convertToMethodName(String attribute,Class<?> objClass,boolean isSet) {
		/** ͨ��������ʽ��ƥ���һ���ַ� **/
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(attribute);
		StringBuilder sb = new StringBuilder();
		/** �����set�������� **/
		if(isSet)
		{
			sb.append("set");
		}else{
			/** get�������� **/
			try {
				Field attributeField = objClass.getDeclaredField(attribute);
				/** �������Ϊboolean **/
				if(attributeField.getType() == boolean.class||attributeField.getType() == Boolean.class)
				{
					sb.append("is");
				}else
				{
					sb.append("get");
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
		/** ������»��߿�ͷ������ **/
		if(attribute.charAt(0)!='_' && m.find())
		{
			sb.append(m.replaceFirst(m.group().toUpperCase()));
		}else{
			sb.append(attribute);
		}
		return sb.toString();
	}

	/**
	 * ����:�����ʾ��Ϣ(��ͨ��Ϣ��ӡ)
	 */
	private static void out(String info, boolean showInfo) {
		if (showInfo) {
			System.out.print(info + (showInfo ? "\n" : ""));
		}
	}

	/**
	 * ����:�����ʾ��Ϣ(ͬһ�еĲ�ͬ��Ԫ����Ϣ��ӡ)
	 */
	private static void out(String info, boolean showInfo, boolean nextLine) {
		if (showInfo) {
			if(nextLine)
			{
				System.out.print(info + (showInfo ? "\n" : ""));
			}else
			{
				System.out.print( info );
			}
		}
	}
}