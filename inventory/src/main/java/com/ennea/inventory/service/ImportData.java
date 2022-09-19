package com.ennea.inventory.service;

import java.io.FileInputStream;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.ennea.inventory.model.Product;

@Service
public class ImportData {

	public List<Product> excelimport() {
		  long id = 0;
		  String productName = null;
		  String code=null;
		  int stock=0,deal=0,free=0,mrp=0,rate=0;
		  String company=null;
		  String batch=null;
		  Date expiry=null;
		  String supplier=null;
		  
		  String excelpath="src/main/resources\\SampleInventory1.xlsx";
		  FileInputStream inputStream;
		  List<Product> productlist=new ArrayList<>();
		  try {
			  inputStream = new FileInputStream(excelpath);
			  Workbook workbook =new XSSFWorkbook(inputStream);
			  Sheet firstSheet = workbook.getSheetAt(0);
			  Iterator<Row> itr=firstSheet.iterator();
			  itr.next();
			  while(itr.hasNext()) {
				  Row nextRow=itr.next();
				  
				  Iterator<Cell> cellitr=nextRow.iterator();
				  while(cellitr.hasNext()) 
				  {
					  Cell nextCell=cellitr.next();
					  int columnIndex=nextCell.getColumnIndex();
						switch (columnIndex) {
						case 0:
							code=nextCell.getStringCellValue();
							break;
						case 1:
							if(nextCell.getCellType()==CellType.STRING)
							productName=nextCell.getStringCellValue();
							else 
							productName=null;
							break;
						case 2:
							if(nextCell.getCellType()==CellType.STRING)
							batch=nextCell.getStringCellValue();
							else 
							batch=null;
							break;
						case 3:
							stock=(int)nextCell.getNumericCellValue();
						   break;
						case 4:
						 deal = (int)nextCell.getNumericCellValue();
						   break;
						case 5:
							free=(int)nextCell.getNumericCellValue();
						   break;
						case 6:
							mrp=(int)nextCell.getNumericCellValue();
						   break;
						case 7:
							stock=(int)nextCell.getNumericCellValue();
						   break;
						case 8:
							if(nextCell.getCellType()==CellType.NUMERIC)
							expiry=nextCell.getDateCellValue();
							break;
						case 9:
							if(nextCell.getCellType()==CellType.STRING)
							company=nextCell.getStringCellValue();
							else 
							company=null;
							break;
						case 10:
							if(nextCell.getCellType()==CellType.STRING)
							supplier=nextCell.getStringCellValue();
							else 
							supplier=null;
							
							break;
						default:
							break;
						
						}
						
				  }
				  productlist.add(new Product(code, productName, 
							  batch, stock, deal,mrp,free,rate , company,expiry,
							 supplier));
		  }
			  workbook.close();
			  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return productlist;
	}
}
