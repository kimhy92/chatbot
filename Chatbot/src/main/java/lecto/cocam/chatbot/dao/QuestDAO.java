package lecto.cocam.chatbot.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import vo.Quest;

@Repository
public class QuestDAO {
	private SqlSession sqlSession;
	
	public QuestDAO() {
		super();
	}

	public QuestDAO(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	public void insert(Quest quest){
		sqlSession.insert("dao.insert",quest);
	}
	public void deleteQuset(Quest quest){
		sqlSession.delete("dao.delete",quest);
	}
	public List<Quest> read() {	
		return sqlSession.selectList("readAll");
	}
}
