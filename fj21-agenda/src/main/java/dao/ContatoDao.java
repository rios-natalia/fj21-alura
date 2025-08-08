package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jdbc.ConnectionFactory;
import modelo.Contato;

public class ContatoDao {
	private final Connection connection;

	public ContatoDao(Connection connection) {
		this.connection = connection;
	}

	public ContatoDao() {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void adiciona(Contato contato) {
		String sql = "insert into contatos " + "(nome,email,endereco,dataNascimento)" + " values (?,?,?,?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public List<Contato> getLista() {
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from contatos");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Contato contato = montaDadosContato(rs);
				contatos.add(contato);
			}
			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public Contato pesquisa(Long id) {
		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT * FROM contatos WHERE id = ? ORDER BY id DESC LIMIT 1");
			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Contato contato = montaDadosContato(rs);
				rs.close();
				stmt.close();

				return contato;
			}

		} catch (

		SQLException e) {
			throw new DaoException(e);
		}
		return null;
	}

	private Contato montaDadosContato(ResultSet rs) {
		try {
			Contato contato = new Contato();

			contato.setId(rs.getLong("id"));
			contato.setNome(rs.getString("nome"));
			contato.setEmail(rs.getString("email"));
			contato.setEndereco(rs.getString("endereco"));

			Calendar dataNascimento = Calendar.getInstance();
			dataNascimento.setTime(rs.getDate("dataNascimento"));
			contato.setDataNascimento(dataNascimento);

			return contato;
		} catch (SQLException e) {
			// Tentou acessar um campo que não existe na tabela
			throw new DaoException(e);
		}
	}

	public Contato pesquisaPorNome(String nome) {
		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT * FROM contatos WHERE nome = ? ORDER BY id DESC LIMIT 1");
			stmt.setString(1, nome);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Contato contato = montaDadosContato(rs);
				rs.close();
				stmt.close();

				return contato;
			}

		} catch (

		SQLException e) {
			throw new DaoException(e);
		}
		return null;
	}

	public void altera(Contato contato) {
		String sql = "update contatos set nome=?, email=?, endereco=?," + "dataNascimento=? where id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			stmt.setLong(5, contato.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Contato contato) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete " + "from contatos where id=?");
			stmt.setLong(1, contato.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
