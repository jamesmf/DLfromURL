package genericJson;

import java.util.ArrayList;
import java.util.Map;

import com.google.gson.Gson;

public class gJsonReader {
	
	/**
	 * 
	 * @param jsonObject 	- 	Json String
	 * @param path 			- 	path down which to navigate, example obj.employee.name
	 * 								-if any of the nodes are Lists you want to iterate over, use getArray()
	 * @return	String
	 * 
	 * Navigates down the path specified in the Json object, casting as Map/ArrayList combinations until reaching
	 * the desired element.  If any node is actually a list of objects, use getArray() to navigate down to
	 * that node, then iterate over the resulting ArrayList, calling getAtt() for every object.
	 */

	public static String getAtt(String jsonObject, String path){
		String[]	p	=	path.split("\\.");
		ArrayList 	a;
		int count = 1;
		Map m	=	(Map)	(new Gson()).fromJson(jsonObject,Map.class);
		
		while (count<p.length-1){
			try{
				a	=	(ArrayList) m.get(p[count]);
				m	=	(Map) a.get(0);
				if (a.size() > 1)
					System.out.println("attribute "+p[count]+" returned a list of objects.  returning first element.  Instead use getArray() and iterate over result.");
			} 
			catch(NullPointerException e){
				System.out.println("cannot navigate path - failed at "+p[count]);
			}
			count++;
		}
		if ((String) m.get(p[p.length-1]) != null)
			return (String) m.get(p[p.length-1]);
		else
			return "NULL ELEMENT";
	}

	
	/**
	 * 
	 * @param jsonObject 	- 	Json String
	 * @param path 			- 	path down which to navigate, example obj.employee.name
	 * 								-if any of the nodes are Lists you want to iterate over, use getArray()
	 * @return				- 	String of the array which can be fed back into a getAtt() 
	 * 
	 * Navigates down the path specified in the Json object, casting as Map/ArrayList combinations until reaching
	 * the desired element.  If any node is actually a list of objects, use getArray() to navigate down to
	 * that node, then iterate over the resulting ArrayList
	 */
	
	public static String getArray(String jsonObject, String path){
		String[]	p		=	path.split("\\.");
		int 		count	= 	1;
		Gson 		g		=	new Gson();
		Map			m		=	(Map)	g.fromJson(jsonObject,Map.class);
		ArrayList 	a;
		
		while (count<p.length-1){
			try{
				a	=	(ArrayList) m.get(p[count]);
				m	=	(Map) a.get(0);
				if (a.size() > 1)
					System.out.println("attribute "+p[count]+" returned a list of objects.  returning first element.  Instead use getArray() and iterate over result.");
			} 
			catch(NullPointerException e){
				System.out.println("cannot navigate path - failed at "+p[count]);
			}
			count++;
		}
		if ((ArrayList) m.get(p[p.length-1]) != null){
			ArrayList	arr	=	(ArrayList) m.get(p[p.length-1]);
			String 		j	=	(String) g.toJson(arr.get(0));
						//j	=	j.substring(1,j.length()-1);
			return	j;
		}
		else
			return "{}";
	}
	
}
