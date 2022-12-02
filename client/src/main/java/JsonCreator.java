package main.java;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Creates a JsonArray object from SQL query
 * @author J. Scotty Solomon
 */

public class JsonCreator {
	/**
	 * Takes a ResultSet from an SQL query and creates a JsonArray
	 * 
	 * @param ResultSet
	 * @return JsonArray 
	 * 
	 * json.toString() example:
	 * [{"NumSeasons":"3",
	 * "NumEpisodes":"10",
	 * "ReleaseDate":"2019-01-25",
	 * "Title":"The Two Popes"}]
	 */
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
