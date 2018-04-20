package com.jiangzhichao.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExportExcel {
	/**
	 * ����: ����ΪExcel������
	 * ����: sheetName[�������е�һ�Ź����������]
	 * ����: titleName[���ı�������]
	 * ����: headers[���ÿһ�е�����]
	 * ����: dataSet[Ҫ����������Դ]
	 * ����: resultUrl[������excel�ļ���ַ]
	 * ����: pattern[ʱ���������ݵĸ�ʽ]
	 */
	public static void exportExcel(String sheetName,String titleName,String[] headers,Collection<?> dataSet,String resultUrl,String pattern) {

		doExportExcel(sheetName,titleName,headers,dataSet,resultUrl,pattern);

	}

	/**
	 * ����:����ʵ�ֵ���
	 */
	private static void doExportExcel(String sheetName,String titleName,String[] headers,Collection<?> dataSet,String resultUrl,String pattern) {

		// ����һ��������
		HSSFWorkbook workbook = new HSSFWorkbook();

		// ����һ��������
		HSSFSheet sheet = workbook.createSheet(sheetName);
		// ���ù�����Ĭ���п��Ϊ20���ֽ�
		sheet.setDefaultColumnWidth((short) 20);
		//�ڹ������кϲ����в�����
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,headers.length-1));

		// ����[����]��ʽ
		HSSFCellStyle titleStyle = workbook.createCellStyle();
		// ����[����]��ʽ
		titleStyle.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
		titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//����[����]����
		HSSFFont titleFont = workbook.createFont();
		//����[����]����
		titleFont.setColor(HSSFColor.WHITE.index);
		titleFont.setFontHeightInPoints((short) 24);
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// ��[��������]Ӧ�õ�[������ʽ]
		titleStyle.setFont(titleFont);

		// ����[����]��ʽ
		HSSFCellStyle headersStyle = workbook.createCellStyle();
		// ����[����]��ʽ
		headersStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
		headersStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headersStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headersStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		headersStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headersStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		headersStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//����[����]����
		HSSFFont headersFont = workbook.createFont();
		//����[����]����
		headersFont.setColor(HSSFColor.VIOLET.index);
		headersFont.setFontHeightInPoints((short) 12);
		headersFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// ��[��������]Ӧ�õ�[������ʽ]
		headersStyle.setFont(headersFont);

		// ����[��������]��ʽ
		HSSFCellStyle dataSetStyle = workbook.createCellStyle();
		// ����[��������]��ʽ
		dataSetStyle.setFillForegroundColor(HSSFColor.GOLD.index);
		dataSetStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		dataSetStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		dataSetStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		dataSetStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		dataSetStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		dataSetStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		dataSetStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// ����[��������]����
		HSSFFont dataSetFont = workbook.createFont();
		// ����[��������]����
		dataSetFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		dataSetFont.setColor(HSSFColor.BLUE.index);
		// ��[������������]Ӧ�õ�[����������ʽ]
		dataSetStyle.setFont(dataSetFont);

		//����������-������ʽ-��ֵ
		HSSFRow titleRow = sheet.createRow(0);
		HSSFCell titleCell = titleRow.createCell(0);
		titleCell.setCellStyle(titleStyle);
		titleCell.setCellValue(titleName);

		// ��������-������ʽ-��ֵ
		HSSFRow row = sheet.createRow(1);
		for (short i = 0; i < headers.length; i++) {

			@SuppressWarnings("deprecation")
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(headersStyle);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);

		}

		// ��������������-������ʽ-��ֵ
		Iterator<?> it = dataSet.iterator();
		int index = 1;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);   
			Object t = it.next();
			// ���÷��䣬����javabean���Ե��Ⱥ�˳�򣬶�̬����getXxx()�����õ�����ֵ
			Field[] fields = t.getClass().getDeclaredFields();
			for (short i = 0; i < fields.length; i++) {
				@SuppressWarnings("deprecation")
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(dataSetStyle);
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get"+ fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
				try {
					@SuppressWarnings("rawtypes")
					Class tCls = t.getClass();
					@SuppressWarnings("unchecked")
					Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});

					// �����ʱ������,���ո�ʽת��
					String textValue = null;
					if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
					} else {
						// �����������Ͷ������ַ����򵥴���
						textValue = value.toString();
					}

					// ����������ʽ�ж�textValue�Ƿ�ȫ�����������
					if (textValue != null) {
						Pattern p = Pattern.compile("^\\d+(\\.\\d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// �����ֵ���double����
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							// ������������ͨ����
							cell.setCellValue(textValue);
						}
					}

					OutputStream out=null;
					try {
						out = new FileOutputStream(resultUrl);
						workbook.write(out);
					} catch (IOException e) {
						e.printStackTrace();
					}finally{
						try {
							out.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} finally {
					//������Դ
					try {
						workbook.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
