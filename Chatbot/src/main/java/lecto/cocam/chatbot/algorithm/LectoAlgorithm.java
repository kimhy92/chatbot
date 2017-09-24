package lecto.cocam.chatbot.algorithm;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LectoAlgorithm {
	private Set<String> lecture_name;
	private Set<String> teacher;
	private Set<String>	method;
	public LectoAlgorithm() {
		lecture_name=new HashSet<String>();
		teacher=new HashSet<String>();
		method=new HashSet<String>();
		
		teacher.add("정혜경");
		teacher.add("홍승연");

		lecture_name.add("파이선 프로그래밍");
		lecture_name.add("비전공자를 위한 c언어 강의");
	}
	
//	private String[] lecture_code;
//	private String[] teacher;
//	private String[] method;
//	private Map<String, String> list;// key : 엔티티명,value : 종류
//
//	public lectoAlgorithm() {
//		// 강사님
//		list.put("정혜꼉", "teacher");
//		list.put("홍승연", "teacher");
//		// 강의이름
//		list.put("c언어", "lecture_name");
//		list.put("장기교육", "lecture_name");
//		list.put("전문가", "lecture_name");
//
//	}
//
//	public String algorism(String[] entity) {
//		String teacher=null;
//		String lecture_name=null;
//		String type=null;
//		for (int i = 0; i < entity.length; ++i) {
//			String kind=list.get(entity[i]);
//			if(entity[i].equals("과정"))//과정이란 단어는 생략
//				continue;
//			if(kind.equals("teacher")){//선생이거나 과목명
//				teacher=entity[i];
//			}
//			else if(kind.equals("lecture_name")){//과목명
//				lecture_name=entity[i];
//			}
//			else{//질의 종류(가격,기간)
//				type=entity[i];
//			}
//		}
//		if(type.equals("개강")){//기간 문의
//			
//		}
//		else{//비용 문의
//			
//		}
//		String result = null;
//		return result;
//	}
}
