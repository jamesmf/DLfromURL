package genericJson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import genericJson.gJsonReader;

import com.google.gson.Gson;

public class test {

	public static void main(String[] args) throws IOException {
		String 			line;
		BufferedReader 	br	=	new BufferedReader(new FileReader("H:\\My Documents\\switch_to_linux\\Drug Landing Page\\json_files\\WK2XYI10QM.json"));
		Gson 			g	=	new Gson();
		
		while ((line = br.readLine()) != null){
			if (line.trim().length()!=0){
				System.out.println(line);
				String 			s	= 	gJsonReader.getAtt(line, "obj.OBE.app_num");
				ArrayList		a	=	gJsonReader.getArray(line, "obj.OBE");
				System.out.println(a);
				for (Object o : a){
					String		b	=	gJsonReader.getAtt(o,"a.DF_Route");
					System.out.println(b);
				}
			}
			}
	}

}
