package main.java;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonCreator {
	
	public static JsonArray toJson(ResultSet rs) throws Exception {
		ResultSetMetaData md = rs.getMetaData();
		
		JsonArray json = new JsonArray();

		while(rs.next()) {
			JsonObject jobj = new JsonObject();
			
			for(int ii = 1; ii <= md.getColumnCount(); ii++) {
				jobj.addProperty(md.getColumnName(ii), rs.getString(ii));
			}
			
			json.add(jobj);
		}
		
		return json;
	}

}
