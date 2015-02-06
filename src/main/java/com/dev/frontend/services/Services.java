package com.dev.frontend.services;

import java.lang.Object;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.dev.frontend.panels.ComboBoxItem;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.JSONArray;
import org.omg.CORBA.*;

public class Services 
{
	public static final int TYPE_PRODUCT = 1;
	public static final int TYPE_CUSTOMER = 2;
	public static final int TYPE_SALESORDER = 3;
	/*
		 * This method is called eventually after you click save on any edit screen
		 * object parameter is the return object from calling method guiToObject on edit screen
		 * and the type is identifier of the object type and may be TYPE_PRODUCT ,
		 * TYPE_CUSTOMER or TYPE_SALESORDER
	*/

    public static Object save(Object object,int objectType)
	{
        String baseURI = getString(objectType);

          JsonObject jsonObject = (JsonObject) object;
		 //Converting to string request 
	       Gson gson = new Gson();
	       String jsonString = gson.toJson(jsonObject);
		 
		 ClientResponse response = requestGenerator( baseURI, jsonString,"post");
		return response.getEntity(String.class);
		
	}

    /**
     * This method is called to get the BASE URI accroding to the Object Type.
     * @param objectType
     * @return
     */
    private static String getString(int objectType) {
        String baseURI = null;

        if (objectType == TYPE_PRODUCT ){
            baseURI = "http://localhost:8080/salesorderapp/rest/Products/";

        }else if (objectType == TYPE_CUSTOMER){
            baseURI = "http://localhost:8080/salesorderapp/rest/Customers/";

        } else if (objectType == TYPE_SALESORDER){
            baseURI = "http://localhost:8080/salesorderapp/ws/create/SalesOrders/";
        }
        return baseURI;
    }

    /**
	 * This method is called to generate the request based on URI
	 * @return 
	 **/

	private static ClientResponse requestGenerator( String baseURI, String jsonObject, String protocol) {
		//String response_return =null;
		ClientResponse response = null;
		try {
		       Client client = Client.create();
		       WebResource webResource = client.resource(baseURI);

		       // POST method
			if("post".equals(protocol)) {
				 response = webResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, jsonObject);
				}
			if ("get".equals(protocol)) {
				 response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
			}
		        // check response status code
		        if (response.getStatus() != 200) {
		            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		        }
		        // display response
		       // response_return = response.getEntity(String.class);

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		return response;
	}
	public static Object readRecordByCode(String code,int objectType)
	{
		//TODO by the candidate
		/*
		 * This method is called when you select record in list view of any entity
		 * and also called after you save a record to re-bind the record again
		 * the code parameter is the first column of the row you have selected
		 * and the type is identifier of the object type and may be TYPE_PRODUCT ,
		 * TYPE_CUSTOMER or TYPE_SALESORDER */ 
		return null;
	}
	public static boolean deleteRecordByCode(String code,int objectType)
	{
		//TODO by the candidate
		/*
		 * This method is called when you click delete button on an edit view
		 * the code parameter is the code of (Customer - PRoduct ) or order number of Sales Order
		 * and the type is identifier of the object type and may be TYPE_PRODUCT ,
		 * TYPE_CUSTOMER or TYPE_SALESORDER
		 */ 
		return true;
	}
    /*
     * This method is called when you open any list screen and should return all records of the specified type
     */
	public static List<Object> listCurrentRecords(int objectType)
	{
        String baseURI = getString(objectType);
		baseURI = baseURI + "all/";

        ClientResponse response = requestGenerator( baseURI, null, "get");
		JSONArray ja = response.getEntity(JSONArray.class);
		ArrayList<Object> custArray = new Gson().fromJson(ja.toString(), new TypeToken<List<Object>>(){}.getType());
		return custArray;
	}
	public static List<ComboBoxItem> listCurrentRecordRefernces(int objectType) 
	{	
		//TODO by the candidate
		/*
		 * This method is called when a Combo Box need to be initialized and should
		 * return list of ComboBoxItem which contains code and description/name for all records of specified type
		 */
		return new ArrayList<ComboBoxItem>();
	}
	public static double getProductPrice(String productCode) {
		//TODO by the candidate
		/*
		 * This method is used to get unit price of product with the code passed as a parameter
		 */
		return 1;
	}
}
