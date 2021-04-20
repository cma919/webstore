  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<li><a href="<spring:url value="/market/products"/>">Home</a></li>
<li><a href="<spring:url value="/market/products/"/>">Products</a></li>
<li><a href="<spring:url value="/market/products/add"/>">Add
		Product</a></li>
<li><a href="<spring:url value="/cart/"/>">Cart</a></li>

<section>
	<div class="pull-right" style="padding-right: 50px">
		<a href="?language=ab">한글</a>|<a href="?language=en">English</a>
		<c:if test="${userId == null}">
			<a href="<c:url value='/login'/>"> 
			<spring:message code="login.anchor.text" />
			</a>
		</c:if>
		<c:if test="${userId != null}">
			<a href="<c:url value='/logout'/>"> (${username}) </a>
		</c:if>
	</div>
</section>