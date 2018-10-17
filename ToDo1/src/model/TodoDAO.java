package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Todo;

public class TodoDAO extends DAO {
	
	/**
	 * 一覧画面
	 *
	 * @return
	 * @throws Exception
	 */
	public List<Todo> todoList() throws Exception {
		List<Todo> returnList = new ArrayList<Todo>();
		
		String sql = "SELECT id, title, task, limitdate, lastupdate, userid, label, td.status, filename " 
				+ "FROM todo_list td LEFT JOIN status_list stts ON stts.status = td.status";
		
		//プリペアステートメントを取得し、実行sqlを渡す
		PreparedStatement statement = getPreparedStatement(sql);
		
		//sqlを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();
		
		//検索結果の行数分フェッチを行い、取得結果をTodoインスタンスへ格納する
		while(rs.next()) {
			Todo dto = new Todo();
			
			//クエリー結果をVOへ格納（あらかじめクエリー結果とdtoの変数名は一致させている）
			dto.setId(rs.getInt("id"));
			dto.setTitle(rs.getString("title"));
			dto.setTask(rs.getString("task"));
			dto.setLimitdate(rs.getTimestamp("limitdate"));
			dto.setLastupdate(rs.getTimestamp("lastupdate"));
			dto.setUserid(rs.getString("userid"));
			dto.setLabel(rs.getString("label"));
			dto.setFilename(rs.getString("filename"));
			
			returnList.add(dto);
		}
		
		return returnList;
	}
	
	/**
	 * 詳細画面
	 * 
	 * 表示するタスクの番号を指定して、タスク詳細を返す
	 * @param id 表示するタスクID
	 * @return
	 * @throws Exception
	 */
	public Todo detail(int id) throws Exception {
		Todo dto = new Todo();
		
		String sql = "SELECT id, title, task, limitdate, lastupdate, userid, label, td.status, filename " 
				+ "FROM todo_list td LEFT JOIN status_list stts ON stts.status = td.status where id = ?";
		
		//プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = getPreparedStatement(sql);
		statement.setInt(1, id);
		
		//SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();
		
		//検索結果の行数分フェッチを行い、取得結果をDTOへ格納する
		while(rs.next()) {
			//クエリー結果をDTOへ格納（あらかじめクエリー結果とDTOの変数名は一致させている）
			dto.setId(rs.getInt("id"));
			dto.setTitle(rs.getString("title"));
			dto.setTask(rs.getString("task"));
			dto.setLimitdate(rs.getTimestamp("limitdate"));
			dto.setLastupdate(rs.getTimestamp("lastupdate"));
			dto.setUserid(rs.getString("userid"));
			dto.setLabel(rs.getString("label"));
			dto.setStatus(rs.getInt("status"));
			dto.setFilename(rs.getString("filename"));
		}
		
		return dto;
	}
	
	/**
	 * 新規登録画面
	 * 
	 * タスクIDはAutoIncrementのキー項目なので、INSERT文のSQLに含めなくても自動的に最新のIDが登録される
	 * @param dto 入力されたタスク内容
	 * @return 追加された件数
	 * throws Exception
	 */
	public int registerInsert(Todo dto) throws Exception { 
		
		String sql = "INSERT INTO todo_list(title, task, limitdate, lastupdate, userid, status)"
				+ "VALUES(?, ?, ?, now(), ?, 0)";
		
		int result = 0;
		
		//プリペアステートメントを取得し、実行SQLを渡す
		try {
			PreparedStatement statement = getPreparedStatement(sql);
			statement.setString(1, dto.getTitle());
			statement.setString(2, dto.getTask());
			statement.setString(3, dto.getInputLimitdate());
			statement.setString(4, dto.getUserid());
			
			result = statement.executeUpdate();
			
			//コミットを行う
			super.commit();
		} catch(Exception e) {
			//ロールバックを行い、スローした例外は呼び出し元のクラスへ渡す
			super.rollback();
			throw e;
		}
		
		return result;
	}
	
	/**
	 * 更新画面
	 * 
	 * @param dto 入力されたタスク内容
	 * @return 
	 * throws Exception
	 */
	public int registerUpdate(Todo dto) throws Exception {
		String sql = "UPDATE todo_list SET title = ?, task = ?, limitdate = ?, lastupdate = now(), userid = ?, status = ? WHERE id = ?";
		
		//プリペアステートメントを取得し、実行SQLを渡す
		int result = 0;
		try {
			PreparedStatement statement = getPreparedStatement(sql);
			statement.setString(1, dto.getTitle());
			statement.setString(2, dto.getTask());
			statement.setString(3, dto.getInputLimitdate());
			statement.setString(4, dto.getUserid());
			statement.setInt(5, dto.getStatus());
			statement.setInt(6, dto.getId());
			
			result = statement.executeUpdate();
			
			//コミットを行う
			super.commit();
		} catch(Exception e) {
			super.rollback();
			throw e;
		}
		
		return result;
	}
	
	/**
	 * 削除画面。指定されたidのタスクを削除する
	 * 
	 * @param id
	 * @return 削除件数
	 * throws Exception
	 */
	public int delete(int id) throws Exception {
		String sql = "DELETE FROM todo_list where id = ?";
		
		//SQLを実行してその結果を取得する。
		int result = 0;
		try {
			//プリペアステートメントを取得し、実行SQLを渡す
			PreparedStatement statement = getPreparedStatement(sql);
			statement.setInt(1, id);
			
			result = statement.executeUpdate();
			
			//コミットを行う
			super.commit();
		} catch(Exception e) {
			super.rollback();
			throw e;
		}
		
		return result;
	}
	
	/**
	 * タスクに添付されるアップロードされたファイル情報を更新する
	 * 
	 * @param vo
	 * throws Exception
	 */
	public int updateUploadInfo(Todo dto) throws Exception {
		String sql = "UPDATE todo_list SET filename = ? WHERE id = ?";
		int result = 0;
		//プリペアステートメントを取得し、実行SQLを渡す
		try {
			PreparedStatement statement = getPreparedStatement(sql);
			statement.setString(1, dto.getFilename());
			statement.setInt(2, dto.getId());
			
			//登録を行う
			result = statement.executeUpdate();
			
			//コミットを行う
			super.commit();
		} catch(Exception e) {
			super.rollback();
			throw e;
		}
		
		return result;
	}
	
	/**
	 * 検索機能。指定されたidのタスクを削除する
	 * 
	 * @param id
	 * @return 検索結果
	 * throws Exception
	 */
	public List<Todo> searchList(String searchText) throws Exception {
		List<Todo> returnSearchList = new ArrayList<Todo>();
		
		String sql = "SELECT id, title, task, limitdate, lastupdate, userid, label, td.status, filename " 
				+ "FROM todo_list td LEFT JOIN status_list stts ON stts.status = td.status";
		
		//プリペアステートメントを取得し、実行sqlを渡す
		PreparedStatement statement = getPreparedStatement(sql);
		
		//sqlを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();
		
		//検索結果の行数分フェッチを行い、取得結果をTodoインスタンスへ格納する
		while(rs.next()) {
			Todo dto = new Todo();
			
			//クエリー結果をVOへ格納（あらかじめクエリー結果とdtoの変数名は一致させている）
			if(rs.getString("title").equals(searchText)) {
				dto.setId(rs.getInt("id"));
				dto.setTitle(rs.getString("title"));
				dto.setTask(rs.getString("task"));
				dto.setLimitdate(rs.getTimestamp("limitdate"));
				dto.setLastupdate(rs.getTimestamp("lastupdate"));
				dto.setUserid(rs.getString("userid"));
				dto.setLabel(rs.getString("label"));
				dto.setFilename(rs.getString("filename"));
				returnSearchList.add(dto);
			} 
		}
		
		return returnSearchList;
	}
}
