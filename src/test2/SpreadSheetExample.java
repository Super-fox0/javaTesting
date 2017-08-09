package test2;

import org.junit.Test;
import test2.SpreadSheetReader;

import java.util.List;

public class SpreadSheetExample 
{

	@Test
    public void spreedSheet()
	{
        SpreadSheetReader sheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/spread1.xlsx");
        List<String> row = sheetReader.readRow(1, "Input Data");

        for(String cell : row)
        {
            System.out.println(cell);
        }


    }
	
	
	
}
