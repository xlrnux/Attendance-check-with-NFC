package util;

import java.util.ArrayList;

/**
 * Servlet implementation class DbUtil
 */
//JSP 2.2 & Servlet 3.0 (출판 혜지원, 저자 오정원) P.509 부터 참조
public class IdUtill {
	
	public String packId(int day, int classCnt, String lecroom_id){
		String id = Integer.toString(day) +"_"+ Integer.toString(classCnt) +"_"+ lecroom_id;
		return id;
	}
	public ArrayList<String> unPackId(String id){
		id = id.replace("", "");
		System.out.println("id: "+id);
		String[] data = id.split("\\|");
		for(String a: data){
			System.out.println(a);
		}
		String day = data[0];
		System.out.println("data[0]: "+ data[0]);
		String period = data[1];
		System.out.println("data[1]: "+ data[1]);
		String lecroom_id = data[2];
		System.out.println("data[2]: "+ data[2]);
		String lecture_id = data[3];
		System.out.println("data[3]: "+ data[3]);
		ArrayList<String> unPackList = new ArrayList<String>();
		unPackList.add(day);
		unPackList.add(period);
		unPackList.add(lecroom_id);
		unPackList.add(lecture_id);
		return unPackList;
	}	
}
