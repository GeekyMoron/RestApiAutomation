package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification res;
	//log pushed data of request into file
public RequestSpecification requestSpecification() throws IOException
{
	if(res==null) {
	PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
	res=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").setContentType(ContentType.JSON)
			.addFilter(RequestLoggingFilter.logRequestTo(log))
			.addFilter(ResponseLoggingFilter.logResponseTo(log))
			.build();//log request to print stream is for mentioning where do we need to log in file or either print
	return res;
	}
	return res;
}
public static String getGlobalValue(String key) throws IOException // static because accessing a method with out makin object
{
	Properties p=new Properties();//for reading properties file
	FileInputStream fs=new FileInputStream("F:\\Selenium_workspace\\Api_automation\\src\\test\\java\\resources\\global.properties");//getting file location
	p.load(fs);// loading file through properties file
	return p.getProperty(key);
}
public static String getJsonpath(Response response,String Key)
{
	String r=response.asString();
	JsonPath js=new JsonPath(r);
	return js.get(Key).toString();
}
public ArrayList<String> getDatathroughExcel(String testcase) throws IOException 
{
	//file input stream object
			ArrayList<String> a=new ArrayList<String>();
			FileInputStream fi=new FileInputStream("F:\\Selenium_workspace\\Api_automation\\src\\test\\Excel-data-Rest.xlsx");
	        XSSFWorkbook workbook=new XSSFWorkbook(fi);
	        int number=workbook.getNumberOfSheets();
	        for(int i=0;i<number;i++)
	        {
	    	  if(workbook.getSheetName(i).equalsIgnoreCase("DATA"))
	    	  {
	    	  XSSFSheet x=workbook.getSheetAt(i);
	    	  Iterator <Row> row=x.iterator();
	    	  Row firstrow =row.next();//taking first header row
	    	  //now we will scan all cells in that row for our desired cell.
	    	  Iterator <Cell> cell=firstrow.cellIterator();
	    	  int k=0;
	    	  int column=0;
	    	  while(cell.hasNext())
	    	  {
	    		  Cell value=cell.next();
	    		  if(value.getStringCellValue().equalsIgnoreCase("TestCases"))
	    		  {
	    			  //desired cell
	    			  column=k;
	    			  
	    		  }
	    		  k++;
	    	  }
	    	  //System.out.println(column);
	    	  //extracting columns of testcase row
	    	  //we will use row iterator for iterating over all rows for and scan all 0th index column is that equal to "EDIT"?
	    	  while(row.hasNext())
	    	  {
	    		  Row givenrow=row.next();
	    		  if(givenrow.getCell(column).getStringCellValue().equalsIgnoreCase("AddPlace API"))//test case name
	    		  {
	    			  Iterator<Cell>cellinside=givenrow.cellIterator();
	    			  while(cellinside.hasNext())
	    			  {
	    				  Cell cellreq=cellinside.next();
	    				  if(cellreq.getCellTypeEnum()==CellType.STRING)
	    				  a.add(cellreq.getStringCellValue());
	    				  else
	    				  a.add(String.valueOf(cellreq.getNumericCellValue()));
	    			  }
	    		  }
	    	  }
	    	  }
	          }
	        return a;
}
}
