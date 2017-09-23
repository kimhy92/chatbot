package vo;

import java.sql.Date;
import java.sql.Timestamp;

public class Quest {
	private String lecture_code;
	private String lecture_name;
	private Timestamp start_date;
	private Timestamp end_date;
	private String teacher;
	private String curriculum;

	public Quest() {
		super();
	}

	public Quest(String lecture_code, String lecture_name, Timestamp start_date, Timestamp end_date, String teacher,
			String curriculum) {
		super();
		this.lecture_code = lecture_code;
		this.lecture_name = lecture_name;
		this.start_date = start_date;
		this.end_date = end_date;
		this.teacher = teacher;
		this.curriculum = curriculum;
	}

	public String getLectureCode() {
		return lecture_code;
	}

	public void setLectureCode(String lecture_code) {
		this.lecture_code = lecture_code;
	}

	public String getLectureName() {
		return lecture_name;
	}

	public void setLectureName(String lecture_name) {
		this.lecture_name = lecture_name;
	}

	public Timestamp getStart_date() {
		return start_date;
	}

	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}

	public Timestamp getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Timestamp end_date) {
		this.end_date = end_date;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(String curriculum) {
		this.curriculum = curriculum;
	}

	@Override
	public String toString() {
		return "Quest [lectureId=" + lecture_code + ", lectureName=" + lecture_name + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", teacher=" + teacher + ", curriculum=" + curriculum + "]";
	}

}
