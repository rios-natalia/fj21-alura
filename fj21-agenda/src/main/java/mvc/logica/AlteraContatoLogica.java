package mvc.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlteraContatoLogica implements Logica {
	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setAttribute("id", req.getParameter("id"));

		return "/WEB-INF/jsp/altera-contato.jsp";

	}
}