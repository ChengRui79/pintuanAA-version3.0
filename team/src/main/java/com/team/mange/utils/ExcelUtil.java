package com.team.mange.utils;

import cn.hutool.core.bean.BeanUtil;
import com.team.mange.common.Constant;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 导出Excel文档工具类
 *
 */
public class ExcelUtil {

	/**
     * 创建excel文档，
     * @param list 数据
     * @param keys list中map的key数组集合
     * @param columnNames excel的列名
     * */
    public static Workbook createWorkBook(List<Map<String, Object>> list,String []keys,String[] columnNames) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet("sheet1");
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<keys.length;i++){
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        //设置列名
        for(int i=0;i<columnNames.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        //设置每行每列的值
        for (int i = 1; i <= list.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow((int) i);
            // 在row行上创建一个方格
            for(short j=0;j<keys.length;j++){
                Cell cell = row1.createCell(j);
                cell.setCellValue(list.get(i-1).get(keys[j]) == null ? " " : list.get(i-1).get(keys[j]).toString());
                cell.setCellStyle(cs2);
            }
        }
        return wb;
    }

    /**
     * 创建excel文档（带有合计的）
     * @param list 数据
     * @param keys list中map的key数组集合
     * @param totalList 总计数据
     * @param totalkeys 总计list中map的key数组集合
     * @param colspanNum 显示合计字样要合并多少单元格
     * @param columnNames excel的列名
     * @return
     */
    public static Workbook createWorkBookTotal(List<Map<String, Object>> list, String[] keys, List<Map<String, Object>> totalList,
                                               String[] totalkeys, int colspanNum, String[] columnNames, String fileName) {
        // 创建excel工作簿
    	HSSFWorkbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        HSSFSheet sheet = wb.createSheet(fileName);
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<keys.length;i++){
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        //设置列名
        for(int i=0;i<columnNames.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        //设置每行每列的值
        for (short i = 1; i <= list.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow(i);
            // 在row行上创建一个方格
            for(short j=0;j<keys.length;j++){
                Cell cell = row1.createCell(j);
                cell.setCellValue(list.get(i-1).get(keys[j]) == null ? " " : list.get(i-1).get(keys[j]).toString());
                cell.setCellStyle(cs2);
            }
        }
        //设置合计每列的值
        if(totalList!=null && totalList.size()>0){
        	int num = list.size()+1;
        	Row row2 = sheet.createRow(num);
        	 for (short i = 1; i <= totalList.size(); i++) {
                 for(short j=0;j<=totalkeys.length;j++){
                	 if(j == 0){
                		 if(colspanNum > 0){
                    		 CellRangeAddress region = new CellRangeAddress(num, (short)num, 0, (short)colspanNum);
                			 sheet.addMergedRegion(region);
                		 }
                		 Cell cell = row2.createCell(j);
                		 cell.setCellValue("合计：");
                		 cell.setCellStyle(cs2);
                	 }else{
                		 Cell cell = row2.createCell(colspanNum+j);
                		 cell.setCellValue(totalList.get(i-1).get(totalkeys[j-1]) == null ? " " : totalList.get(i-1).get(totalkeys[j-1]).toString());
                		 cell.setCellStyle(cs2);
                	 }
                 }
             }
        }
        return wb;
    }

    public static List<Map<String, Object>> createExcelRecord(List list) {
        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < list.size(); j++) {
            list1.add(BeanUtil.beanToMap(list.get(j)));
        }
        return list1;
    }

    public static void downloadExcel(HttpServletResponse response, String fileName,
                                     List<Map<String,Object>> list, String[] keys, String[] columnNames, HttpServletRequest request)
			throws IOException {


        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
        	ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        //清空输出流
        response.reset();

        String userAgent = request.getHeader("user-agent");
        String finalFileName;
            //IE11浏览器
        if(userAgent.equals(Constant.IE_ELEVEN)){
            finalFileName = URLEncoder.encode(fileName,"UTF8");
            //10及以下IE浏览器
        }else if(StringUtils.contains(userAgent.toLowerCase(), Constant.MSIE)){
            finalFileName = URLEncoder.encode(fileName,"UTF8");
            //Edge浏览器
        }else if(StringUtils.contains(userAgent.toLowerCase(), Constant.EDGE)){
            finalFileName = URLEncoder.encode(fileName,"UTF8");
            //google,火狐浏览器
        }else if(StringUtils.contains(userAgent.toLowerCase(), Constant.MOZILLA)){
            finalFileName = new String(fileName.getBytes(), "ISO8859-1");
        }else{
            //其他浏览器
            finalFileName = URLEncoder.encode(fileName,"UTF8");
        }
        finalFileName = finalFileName + ".xls";
        //这里设置一下让浏览器弹出下载提示框，而不是直接在浏览器中打开
        response.setHeader("Content-Disposition", "attachment; filename=\"" + finalFileName + "\"");
        response.setContentType("application/vnd.ms-excel");

        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        read(is, out, bis, bos);
    }

    private static void read(InputStream is, ServletOutputStream out, BufferedInputStream bis, BufferedOutputStream bos) throws IOException {
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
    }


    public static void downloadExcelTotal(HttpServletResponse response, String fileName,
                                          List<Map<String,Object>> list, String[] keys, List<Map<String, Object>> totalList, String []totalkeys, int colspanNum , String[] columnNames)
			throws UnsupportedEncodingException, IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
        	ExcelUtil.createWorkBookTotal(list, keys, totalList, totalkeys, colspanNum, columnNames,fileName).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        read(is, out, bis, bos);
    }

    /**
     * 得到Excel表中的值
     *
     * @param hssfCell
     *            Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    @SuppressWarnings("static-access")
    public static String getValue(HSSFCell hssfCell) {
    	if(hssfCell==null){
    		return "";
    	}else{
	        if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
	            // 返回布尔类型的值
	            return String.valueOf(hssfCell.getBooleanCellValue());
	        } else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
	        	DecimalFormat df = new DecimalFormat("#");
	            // 返回数值类型的值
	            return String.valueOf(df.format(hssfCell.getNumericCellValue()));
	        } else {
	            // 返回字符串类型的值
	            return String.valueOf(hssfCell.getStringCellValue());
	        }
    	}
    }
}
