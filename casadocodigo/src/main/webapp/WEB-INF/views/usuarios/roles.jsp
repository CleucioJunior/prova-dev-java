<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">

	<section id="roles-section" class="container ">
	
		<div class="container" style = "padding: 20px">
			<form:form action="${s:mvcUrl('UC#atualizar').build() }" method="post" commandName="usuario">
				<h2>Cadastro de Permissões para ${usuario.nome }</h2>
				<div class="checkbox">
					<label>Permissões: </label>
					<form:checkboxes items = "${roles }" path = "roles" delimiter=" " />
					<form:errors path="roles" />				
					<input type="hidden" name="id" value="${usuario.id}">
					<input type="hidden" name="email" value="${usuario.email}">
					<input type="hidden" name="nome" value="${usuario.nome}">
					<input type="hidden" name="senha" value="${usuario.senha}">
				</div>
				<button type="submit" class="btn btn-primary">Atualizar</button>
			</form:form>
		</div>
	</section>
</tags:pageTemplate>
