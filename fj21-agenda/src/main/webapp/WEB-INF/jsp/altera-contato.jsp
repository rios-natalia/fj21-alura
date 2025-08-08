
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="caelum"%>


<html>
<head>
<link href="css/jquery-ui.css" rel="stylesheet">
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.js"></script>
</head>
<body>
	<c:import url="cabecalho.jsp" />

	<h1>Altera Contato</h1>
	<hr />
	<form action="mvc?logica=SalvaContatoLogica&id=${id}" method="post">
		Nome: <input type="text" name="nome" /><br /> E-mail: <input
			type="text" name="email" /><br /> Endereço: <input type="text"
			name="endereco" /><br /> Data Nascimento:
		<caelum:campoData id="dataNascimento" />
		<br /> <input type="submit" value="Gravar" />
	</form>
	<c:import url="rodape.jsp" />

</body>
</html>