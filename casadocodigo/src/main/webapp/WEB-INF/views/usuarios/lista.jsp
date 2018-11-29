<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>



<tags:pageTemplate titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">

	<section id="lista-section" class="container">
		<c:if test="${not empty message }">
				<h1 class="cdc-call">${message }</h1>
			</c:if>
			<c:if test="${empty message }">
				<h1 class="cdc-call">Últimos dias com os preços promocionais. Aproveite!</h1>
		</c:if>
		
	
		<div class="container">	
			<h1><a href="${s:mvcUrl('UC#form').build()}">Novo Usuário</a></h1>
			<h1>Lista de Usuários</h1>
			<form:form method="post" commandName="usuario">						 	
				<table class="table table-bordered table-striped table-hover">
					<tr>
						<th>Nome</th>
						<th>E-Mail</th>
						<th>Role</th>
						<th></th>
					</tr>
					<c:forEach items="${usuarios }" var="usuario">
						<tr>
							<td >
								${usuario.nome }
							</td>
							<td>
								${usuario.email }
							</td>		
							<td>			
							<c:forEach items="${usuario.roles }" var="role" varStatus="status">
								${role.nome}<c:if test="${!status.last}" >,  </c:if>
							</c:forEach>
							</td>
							<td>
								<button formaction="${s:mvcUrl('UC#roles').arg(0, usuario.id).build() }" class="button" type="submit">+</button>
							</td>					
						</tr>
					</c:forEach>
				</table>
			</form:form>
		</div>
	</section>
</tags:pageTemplate>
