Java_tools
=========

genericJson - tools for dealing with Json in Java (using Gson)
    -gJsonReader
      -getAtt(Object jsonObject, String path)
        	/**
        	 * 
        	 * @param jsonObject 	- 	Json String or LinkedTreeMap (which are returned in an ArrayList from getArray()) 
        	 * @param path 			  - 	path down which to navigate, example obj.employee.name
        	 * 								    -   if any of the nodes are Lists you want to iterate over, use getArray()
        	 * @return	String    -   attribute desired
        	 * 
        	 * Navigates specified path in the Json object, casting as Map/ArrayList combinations until reaching
        	 * the desired element.  If any node is actually a list of objects, use getArray() to navigate down to
        	 * that node, then iterate over the resulting ArrayList, calling getAtt() for every object.
        	 */
        	 
      -getArray(Object jsonObject, String path)
        	/**
        	 * 
        	 * @param jsonObject 	- 	Json String or LinkedTreeMap (which are returned in an ArrayList from getArray())
        	 * @param path 			  - 	path down which to navigate, example obj.business.employees
        	 * 								    
        	 * @return				- 	String of the array which can be fed back into a getAtt() 
        	 * 
        	 * Navigates down the path specified in the Json object, casting as Map/ArrayList combinations until
        	 * reaching  the desired element. 
        	 */
=========

DL_tools - Tools for downloading files from a URL in java
    -DLfromURL
      -save_Zip()
        	/**
        	 * written to save a zip file, but should work with other file types.
        	 *  
        	 * @param zipURL  - String version of the URL at which the file is located
        	 * @param outfile - String name for the filename (path included) 
        	 */
      
      -save_one_from_Zip()
        	/**
          	 * This saves a zip from a URL using save_Zip, decompresses "subfile" (writing it to a file).
          	 * Might be expensive time-wise, as it downloads the whole zip and copies the file in question
          	 * using InputStream/OutputStream. 
          	 * 
          	 * @param zipURL - URL from which you would like to download a zip file
          	 * @param subfile - Name of the file within the zip
          	 */
=========


