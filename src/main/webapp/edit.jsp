<%--
  Created by IntelliJ IDEA.
  User: mingfei
  Date: 1/26/18
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>edit page</title>
</head>
<body>
<c:if test="${sessionScope.username eq null}">
    <c:redirect url="index.jsp"/>
</c:if>
<h1>edit page</h1>
<p>${sessionScope.username}</p>
<hr>
<a href="/user?action=signOut">Sign out</a>
<hr>
<form action="/book" method="post">
    <input type="hidden" name="action" value="modify">
    <%-- ***** id --%>
    <input type="hidden" name="id" value="${sessionScope.book.id}">
    <input name="title" placeholder="Title" value="${sessionScope.book.title}"><br>
    <input name="author" placeholder="Author" value="${sessionScope.book.author}"><br>
    <input name="pubTime" placeholder="Publish time" value="${sessionScope.book.pubTime}"><br>
    <input name="price" placeholder="Price" value="${sessionScope.book.price}"><br>
    <input name="amount" placeholder="Amount" value="${sessionScope.book.amount}"><br>
    Picture <input type="file" name="picture" value="${sessionScope.book.picture}"><br>
    <input type="submit" value="Save">
</form>
</body>
</html>
