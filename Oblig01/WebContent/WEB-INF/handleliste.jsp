<%--
  Created by IntelliJ IDEA.
  User: Peder
  Date: 12.09.2017
  Time: 22.56
  To change this template use File | Settings | File Templates.
--%>

<jsp:include page="./partials/header.jsp" />

<%@ page contentType="text/html; charset=ISO-8859-1" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="static no.hvl.dat104.controller.UrlMappings.HANDLELISTE_URL" %>

<div class="container">
    <div class="row">
        <c:if test="${flash == 'Error'}">
            <p class="alert alert-danger"> ${melding}</p>
            <c:remove var="flash" scope="session" />
        </c:if>
        <c:if test="${flash == 'Success'}">
            <p class="alert alert-success"> ${melding}</p>
            <c:remove var="flash" scope="session" />
        </c:if>
        <form class="form-group" action="<%= HANDLELISTE_URL %>" method="post">
            <label for="LeggTil">Legg Til:</label>
             <input type="text" class="form-control" id="LeggTil" name="vare" placeholder="Varenavn.." autofocus>
            <input style="margin: 10px;" class="btn btn-primary" type="submit" value="Submit">
        </form>

        <c:forEach var="vare" items="${varer}">
        	<li style="list-style-type: none;">
            	<form action="<%= HANDLELISTE_URL %>" method="post">
                	<input type="hidden" name="varenavn" value="${vare.vare_id}">
                	<input class="btn btn-danger" type="submit" value="Slett">  
                	<c:out value = "${vare.navn}"/>              
            	</form>
            </li>
        </c:forEach>
    </div>
</div>
