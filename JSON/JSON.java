package trunk.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class JSON {
	
	// Usage: k = JSON.fetchData()
	// Post:  k is an array of every 240 FootballPlayer
	//        that will then be parsed by the caller.
    public static JSONObject[] fetchData() throws IOException, JSONException {
    	int[] updateIDs = {1,2,4,5,6,7,9,10,11,12,13,14,15,16,20,21,23,24,25,27,28,77,78,79,80,81,82,
	            84,86,87,89,90,91,94,95,96,99,100,176,200,202,203,205,209,211,214,215,216,217,218,219,220,
	            224,225,226,227,229,230,231,232,233,235,236,237,240,241,242,244,245,246,247,249,250,252,253,
	            254,256,257,259,260,261,263,266,267,268,269,270,271,272,275,276,278,279,281,282,283,284,285,
	            286,295,335,341,342,343,344,345,347,348,349,350,351,353,354,355,356,359,362,364,366,368,369,
	            370,371,372,376,380,381,382,383,384,385,386,387,388,391,392,393,394,395,398,400,401,402,403,
	            404,407,408,409,411,412,416,417,418,419,421,448,449,450,452,454,455,456,457,458,460,461,462,
	            465,469,471,472,474,475,497,498,499,500,501,502,504,506,507,508,509,510,513,514,515,516,517,
	            520,522,524,528,530,533,535,537,540,542,543,548,550,551,555,560,562,566,569,570,571,572,579,
	            582,584,587,588,591,593,595,597,599,601,603,604,606,613,614,615,616,617,627,634,640,641,648,
	            649,655,656,658};
	  
    	JSONObject[] gogn = new JSONObject[updateIDs.length];
	
    	for (int i = 0; i < 240; i++) {
    		gogn[i] = readJsonFromUrl("http://fantasy.premierleague.com/web/api/elements/"+updateIDs[i]+"/");
    	}
	
    	return gogn;
    }

  private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }
  
  private static String readAll(Reader rd) throws IOException {
	StringBuilder sb = new StringBuilder();
	int cp;
	while ((cp = rd.read()) != -1) {
	  sb.append((char) cp);
	}
	return sb.toString();
  }
  
  public static void main(String[] args) {
	  JSONObject[] k = null;
	  try {
		  k = fetchData();
	  } catch (Exception e) {}
	  
	  System.out.println(k[0].getString("type_name"));
  }
}