package com.dev.frontend.panels.list;

import java.util.Arrays;
import java.util.List;

import com.dev.frontend.services.Services;

public class CustomerDataModel extends ListDataModel
{
	private static final long serialVersionUID = 7526529951747613655L;

	public CustomerDataModel()
	{
		super(new String[] { "Code", "Name", "Phone", "Current Credit" }, 0);
	}

	@Override
	public int getObjectType()
	{
		return Services.TYPE_CUSTOMER;
	}
	/*
	* This method use list returned by Services.listCurrentRecords and should convert it to array of rows
	* each row is another array of columns of the row
		 */
	@Override
	public String[][] convertRecordsListToTableModel(List<Object> list)
	{
		Object[] objectArray = list.toArray();
		String[] stringArray = Arrays.copyOf(objectArray, objectArray.length, String[].class);

		String[][] sampleData = new String [][]{{"01","Customer 1","+201011121314","23.4"},{"02","Customer 2","+201112131415","1.4"}};
		return sampleData;
	}
}
