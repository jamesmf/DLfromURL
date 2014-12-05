package genericJson;

import java.util.ArrayList;
import java.util.Map;

import com.google.gson.Gson;

public class gJsonReader {
	
	/**
	 * 
	 * @param jsonObject 	- 	Json String or LinkedTreeMap (which are returned in an ArrayList from getArray())
	 * @param path 			- 	path down which to navigate, example obj.employee.name
	 * 								-if any of the nodes are Lists you want to iterate over, use getArray()
	 * @return	String
	 * 
	 * Navigates down the path specified in the Json object, casting as Map/ArrayList combinations until reaching
	 * the desired element.  If any node is actually a list of objects, use getArray() to navigate down to
	 * that node, then iterate over the resulting ArrayList, calling getAtt() for every object.
	 */

	public static String getAtt(Object jsonObject, String path){
		String[]	p		=	path.split("\\.");
		boolean		is_str	=	false;
		int 		count 	= 	1;
		Map 		m		=	null;
		ArrayList 	a;
		String		str;

		
		try{
			str		=	(String) jsonObject;
			m		=	(Map)	(new Gson()).fromJson(str,Map.class);
			is_str	=	true;
		}
		catch(ClassCastException e){
			//	System.out.println("not a string");
		}
		
		if (!is_str){
			m		=	(Map)	jsonObject;
		}
		
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
	 * @param jsonObject 	- 	Json String or LinkedTreeMap (which are returned in an ArrayList from getArray())
	 * @param path 			- 	path down which to navigate, example obj.business.employees
	 * 								    
	 * @return				- 	array of LinkedTreeMaps which can be fed back into a getAtt() 
	 * 
	 * Navigates down the path specified in the Json object, casting as Map/ArrayList combinations until
	 * reaching  the desired element.  Last node must be an Array.
	 */
	
	public static ArrayList getArray(Object jsonObject, String path){
		String[]	p		=	path.split("\\.");
		boolean		is_str	=	false;
		int 		count 	= 	1;
		Map 		m		=	null;
		Gson		g		=	new Gson();
		ArrayList 	a;
		String		str;

		
		try{
			str		=	(String) jsonObject;
			m		=	(Map)	g.fromJson(str,Map.class);
			is_str	=	true;
		}
		catch(ClassCastException e){
			//System.out.println("not a string");
		}
		
		if (!is_str){
			m		=	(Map)	jsonObject;
		}
		
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
			if (arr.size()==0)
				return new ArrayList();
			String 		j	=	(String) g.toJson(arr.get(0));
			return	arr;
		}
		else
			return new ArrayList();
	}	
}
