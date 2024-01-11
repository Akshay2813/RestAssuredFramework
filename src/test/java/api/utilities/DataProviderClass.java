package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	
	@DataProvider(name="userData")
	public static String[][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"\\testData\\CreateUserData.xlsx";
		int rowCount= XLUtil.getRowCount(path,"Sheet1" );
		int cellCount=XLUtil.getCellCount(path, "Sheet1", 1);
		
		String[][] cellData=new String[rowCount][cellCount];
		for(int i=1;i<=rowCount; i++)
		{
			for(int j=0;j<cellCount;j++)
			{
				cellData[i-1][j]=XLUtil.getcellData(path, "Sheet1", i, j);
			}
		}
		return cellData;
	}
	
	@DataProvider(name="userNames")
	public static String[] getUserName() throws IOException
	{
		String path=System.getProperty("user.dir")+"\\testData\\CreateUserData.xlsx";
		int rowCount= XLUtil.getRowCount(path,"Sheet1" );
		
		String[] userNameData=new String[rowCount];
		for(int i=1;i<=rowCount; i++)
		{
			
			userNameData[i-1]=XLUtil.getcellData(path, "Sheet1", i, 0);
			
		}
		return userNameData;
	}
}


