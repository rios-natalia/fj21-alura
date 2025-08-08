
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.ConnectionFactory;
import modelo.Funcionario;

public class FuncionarioDao {
	private final Connection connection;

	public FuncionarioDao() {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void adiciona(Funcionario funcionario) {
		String sql = "insert into funcionario " + "(nome,usuario,senha)" + " values (?,?,?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getUsuario());
			stmt.setString(3, funcionario.getSenha());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

}
