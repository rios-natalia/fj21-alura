package mvc.logica;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContatoDao;
import modelo.Contato;

public class SalvaContatoLogica implements Logica {
	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		String endereco = req.getParameter("endereco");
		String dataTexto = req.getParameter("dataNascimento");

		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataTexto);
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.setTime(date);

		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setEndereco(endereco);
		contato.setDataNascimento(dataNascimento);

		Connection connection = (Connection) req.getAttribute("conexao");
		ContatoDao dao = new ContatoDao(connection);

		req.setAttribute("contato", contato);

		if (id == null) {
			dao.adiciona(contato);
			return "/WEB-INF/jsp/contato-adicionado.jsp";
		}

		contato.setId(Long.parseLong(id));
		dao.altera(contato);
		return "/WEB-INF/jsp/contato-alterado.jsp";
	}
}