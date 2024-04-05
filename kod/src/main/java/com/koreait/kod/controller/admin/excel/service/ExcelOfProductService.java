package com.koreait.kod.controller.admin.excel.service;

import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.koreait.kod.biz.productAndWishList.ProductDAO;
import com.koreait.kod.biz.productAndWishList.ProductDTO;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
@Service
@Qualifier("excelProduct")
public class ExcelOfProductService {
	@Autowired
	ProductDAO productDAO;
	
    public void download(HttpServletResponse response, ProductDTO productDTO) throws Exception {
		// excel sheet 생성
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("매출통계"); // 엑셀 sheet 이름
        sheet.setDefaultColumnWidth(28); // 디폴트 너비 설정
    /* 헤더 설정 */
        // 폰트
        XSSFFont headerXSSFFont = (XSSFFont) workbook.createFont();
        headerXSSFFont.setColor(new XSSFColor(new byte[]{(byte) 255, (byte) 255, (byte) 255}));
        // 셀 스타일
        XSSFCellStyle headerXssfCellStyle = (XSSFCellStyle) workbook.createCellStyle();
        // 셀 테두리
        headerXssfCellStyle.setBorderLeft(BorderStyle.THIN);
        headerXssfCellStyle.setBorderRight(BorderStyle.THIN);
        headerXssfCellStyle.setBorderTop(BorderStyle.THIN);
        headerXssfCellStyle.setBorderBottom(BorderStyle.THIN);
        // 배경 설정
        headerXssfCellStyle.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 34, (byte) 37, (byte) 41}));
        headerXssfCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerXssfCellStyle.setFont(headerXSSFFont);
    /* 헤더 설정 */

    /* 로우 설정 */
        // 폰트 설정
        XSSFCellStyle bodyXssfCellStyle = (XSSFCellStyle) workbook.createCellStyle();
        // 테두리 설정
        bodyXssfCellStyle.setBorderLeft(BorderStyle.THIN);
        bodyXssfCellStyle.setBorderRight(BorderStyle.THIN);
        bodyXssfCellStyle.setBorderTop(BorderStyle.THIN);
        bodyXssfCellStyle.setBorderBottom(BorderStyle.THIN);
    /* 로우 설정 */

	/* 헤더 컬럼명 저장 */
        int rowCount = 0; // 데이터가 저장될 행 // 엑셀의 행번호는 1번부터 시작
        String headerNames[] = new String[]{
        	"상품ID"	
			,"상품 재고"
			,"상품 가격"
			,"상품 카테고리"
			,"상품 브랜드"
			,"상품 이름"
			,"상품 정보"
			,"상품 판매수량"
			,"분기별 누적 판매량" 
			,"상품 매출"
			,"연 매출"
			,"분기 매출"
			,"월간 매출"
			,"일간 매출"
			,"월 주문수"
			,"연 주문수"
        };
	/* 헤더 컬럼명 저장 */
        
        Row headerRow = null;
        Cell headerCell = null;
        
    /* 헤더에 컬럼명 설정 */
        headerRow = sheet.createRow(rowCount++);
        for(int i=0; i<headerNames.length; i++) {
            headerCell = headerRow.createCell(i);
            headerCell.setCellValue(headerNames[i]); // 데이터 추가
            headerCell.setCellStyle(headerXssfCellStyle); // 스타일 추가
        }
    /* 헤더에 컬럼명 설정 */
        
	/* 상품 매출 현황 조회 */
        productDTO.setSearchCondition("quarterlyStatistics");
        List<ProductDTO> productList = productDAO.selectAll(productDTO);
    /* 상품 매출 현황 조회 */
        
	/* 데이터가 저장될 몸체 */
        String bodyDatass[][] = new String[productList.size()][];
        Row bodyRow = null;
        Cell bodyCell = null;
        for (int i = 0; i < productList.size(); i++) {
            // 각 열에 해당하는 데이터를 가져와서 bodyDatass에 할당 // 위의 컬럼명과 순서가 동일해야함..
            bodyDatass[i] = new String[] {  
            		""+productList.get(i).getProductID()
            		,""+productList.get(i).getProductStock() // 상품 재고
            		,""+productList.get(i).getProductPrice() // 상품 가격
            		,productList.get(i).getProductCategory() // 상품 카테고리
            		,productList.get(i).getProductBrand() // 상품 브랜드
            		,productList.get(i).getProductName() // 상품 이름
            		,productList.get(i).getProductInfo() // 상품 정보 
            		,""+productList.get(i).getProductSalesQuantity() // 상품 판매수량
            		,""+productList.get(i).getTotalProductSalesQuantityForQuarter() // 분기동안 상품 판매수량 합계
            		,""+productList.get(i).getProductSalesRevenue() // 상품 매출
            		,""+productList.get(i).getAnualRevenue() // 연 매출
            		,""+productList.get(i).getQuarterlyRevenue() // 분기 매출
            		,""+productList.get(i).getMonthlyRevenue() // 월간 매출
            		,""+productList.get(i).getDailyRevenue() // 일간 매출
            		,""+productList.get(i).getMonthlyOrderCnt() // 월 주문수
            		,""+productList.get(i).getYearlyOrderCnt() // 연 주문수
            };
        }
        for(String[] bodyDatas : bodyDatass) {
            bodyRow = sheet.createRow(rowCount++);

            for(int i=0; i<bodyDatas.length; i++) {
                bodyCell = bodyRow.createCell(i);
                bodyCell.setCellValue(bodyDatas[i]); // 데이터 추가
                bodyCell.setCellStyle(bodyXssfCellStyle); // 스타일 추가
            }
        }
    /* 데이터가 저장될 몸체 */

	/* 파일명 설정 및 내보내기 */  
        String fileName = "spring_productStatistics_download";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        ServletOutputStream servletOutputStream = response.getOutputStream();
        workbook.write(servletOutputStream);
        workbook.close();
        servletOutputStream.flush();
        servletOutputStream.close();
    /* 파일명 설정 및 내보내기 */  
    }
}
