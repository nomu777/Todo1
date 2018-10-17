package dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Todo {
	
	/** TodoのID */
	private int id;
	
	/** Todoのタイトル */
	private String title;
	
	/** 詳細なタスクの名称 */
	private String task;
	
	/** タスクの期限 */
	private Timestamp limitdate;
	
	/** タスクの最終更新日時 */
	private Timestamp lastupdate;
	
	/** タスクのユーザーID */
	private String userid;
	
	/** タスクのステータスレコード */
	private int status;
	
	/** タスクのステータス表示 */
	private String label;
	
	/** 登録画面で入力された期限 */
	private String inputLimitdate;
	
	/** アップロードされて紐付けされたファイル名 */
	private String filename;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Timestamp getLimitdate() {
		return limitdate;
	}

	public void setLimitdate(Timestamp limitdate) {
		this.limitdate = limitdate;
	}

	public Timestamp getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Timestamp lastupdate) {
		this.lastupdate = lastupdate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getInputLimitdate() {
		return inputLimitdate;
	}

	public void setInputLimitdate(String inputLimitdate) {
		this.inputLimitdate = inputLimitdate;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
