/* Theory of Computation HW3
 * �{���W�١G TocHw3.java
 * �{���@�̡G ��T�T�A F74006161 �B�ۨ}
 * �{����J�G java -jar TocHw3.jar URL �m���� ���W ����~��
 * �{����X�G �Ӹ��q����`�������`����(integer)
 * �{���ت��G �ǥѪ���DataGarage������n����ơA�p��Ҭd�ߤ����q�`����
 * �{�������G �z�Lreader�N��ƱqURL�W�����U�ӡA�A�g�ѷj�MJSON�榡��KEY���o�۹�����VALUE�A
 * �P�_VALUE�O�_�ŦX�Ҭd�ߡA�ŦX�̱N���`�����[�Jsum�A�̫���`�����Y���ҨD�C
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.*;

public class TocHw3 {

	public static void main(String[] args)
	{
		try {
			if(args.length!=4) { System.out.println("Error! Argument�ƶq�����T!"); System.exit(0); }
			
			URL url = new URL(args[0]);
			Reader myReader = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
			JSONTokener myTokener = new JSONTokener(myReader);
			JSONArray myArray = new JSONArray(myTokener);
			JSONObject myObject;
			
			Pattern[] myPattern = new Pattern[2];
			myPattern[0] = Pattern.compile(args[1]);
			myPattern[1] = Pattern.compile(".*"+args[2]+".*");
			Matcher[] myMatcher = new Matcher[2];
			
			int count=0;
			long sum=0;
			
			for (int i = 0; i < myArray.length(); i++) {
				myObject = myArray.getJSONObject(i);
				myMatcher[0] = myPattern[0].matcher(myObject.getString("�m����").toString());
				if(!myMatcher[0].find()) continue;
				myMatcher[1] = myPattern[1].matcher(myObject.getString("�g�a�Ϭq��m�Ϋت��Ϫ��P").toString());
				if(!myMatcher[1].find()) continue;
				if(myObject.getInt(("����~��"))<Integer.valueOf(args[3])*100) continue;
				
				/*System.out.println(
						myObject.getString("�m����")+"\t"+
						myObject.getString("�g�a�Ϭq��m�Ϋت��Ϫ��P")+"\t"+
						myObject.getInt(("����~��"))+"\t"+
						myObject.getInt("�`����"));*/
				count++;
				sum+=myObject.getInt("�`����");
            }
			
			double avg_price=(sum/count);
			
			System.out.println((int)avg_price);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
