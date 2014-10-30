package rookwithfriends.util;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.rookwithfriends.util.JSONUtility;

public class JSONUtilityTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void toGsonTest() {
		Map<String,Object> response = new HashMap<String, Object>();
		String playerIdString = "0";
		response.put("playerConnected", playerIdString);
		
    	Gson gson = new Gson();
    	String responseJSON = gson.toJson(response); 
    	
    	assertEquals("{\"playerConnected\":\"0\"}", responseJSON);
	}
	
	@Test
	public void jsonUtilTest() {
		Map<String,Object> response = new HashMap<String, Object>();
		String playerIdString = "testID";
		response.put("playerConnected", playerIdString);
		
    	String responseJSON = JSONUtility.convertToJson((HashMap)response);
    	
    	assertEquals("{\"playerConnected\":\"testID\"}", responseJSON);
	}

}
