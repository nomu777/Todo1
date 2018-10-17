package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Todo;
import model.TodoDAO;

/**
 * 登録処理を行う
 */
@WebServlet("/servlet/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストパラメーターを受け取り、DTOに格納する準備をする
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String task = request.getParameter("task");
		String inputLimitdate = request.getParameter("limitdate");
		String userid = request.getParameter("userid");
		int status = Integer.parseInt(request.getParameter("status"));
		
		//DTOへ格納する。登録される期限(limit)はTodoクラスではinputLimitになる
		Todo dto = new Todo();
		dto.setId(id);
		dto.setTitle(title);
		dto.setTask(task);
		dto.setInputLimitdate(inputLimitdate);
		dto.setUserid(userid);
		dto.setStatus(status);
		
		String message = "";
		try(TodoDAO dao = new TodoDAO()) {
			//更新または登録処理を行う
			//idが0の時は新規登録、id>=1の時は更新
			if(id == 0) {
				dao.registerInsert(dto);
				message = "タスクの新規登録処理が完了しました";
			} else {
				dao.registerUpdate(dto);
				message = "タスク[ " + id + " ]の更新処理が完了しました";
			}
			setMessage(request, message);
		} catch(Exception e) {
			throw new ServletException(e);
		}
		
		//登録完了→一覧画面を表示する
		RequestDispatcher rd = request.getRequestDispatcher("/servlet/search");
		rd.forward(request, response);
	}
	
	/**
	 * jspで表示するメッセージを設定する
	 * 
	 * @param request
	 * 			サーブレットリクエスト
	 * @param message
	 * 			メッセージ文字列
	 */
	protected void setMessage(HttpServletRequest request, String message) {
		request.setAttribute("message", message);
	}

}
