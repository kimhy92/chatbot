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
		
		teacher.add("������");
		teacher.add("ȫ�¿�");

		lecture_name.add("���̼� ���α׷���");
		lecture_name.add("�������ڸ� ���� c��� ����");
	}
	
//	private String[] lecture_code;
//	private String[] teacher;
//	private String[] method;
//	private Map<String, String> list;// key : ��ƼƼ��,value : ����
//
//	public lectoAlgorithm() {
//		// �����
//		list.put("�����b", "teacher");
//		list.put("ȫ�¿�", "teacher");
//		// �����̸�
//		list.put("c���", "lecture_name");
//		list.put("��ⱳ��", "lecture_name");
//		list.put("������", "lecture_name");
//
//	}
//
//	public String algorism(String[] entity) {
//		String teacher=null;
//		String lecture_name=null;
//		String type=null;
//		for (int i = 0; i < entity.length; ++i) {
//			String kind=list.get(entity[i]);
//			if(entity[i].equals("����"))//�����̶� �ܾ�� ����
//				continue;
//			if(kind.equals("teacher")){//�����̰ų� �����
//				teacher=entity[i];
//			}
//			else if(kind.equals("lecture_name")){//�����
//				lecture_name=entity[i];
//			}
//			else{//���� ����(����,�Ⱓ)
//				type=entity[i];
//			}
//		}
//		if(type.equals("����")){//�Ⱓ ����
//			
//		}
//		else{//��� ����
//			
//		}
//		String result = null;
//		return result;
//	}
}
