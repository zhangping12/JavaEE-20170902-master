<%--
  Created by IntelliJ IDEA.
  User: mingfei
  Date: 1/26/18
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>home page</title>
    <script>
        function del() {
            return confirm("DELETE?");
        }
    </script>
</head>
<body>
<c:if test="${sessionScope.username eq null}">
    <c:redirect url="index.jsp"/>
</c:if>
<h1>home page</h1>
<p>${sessionScope.username}</p>
<hr>
<a href="/user?action=signOut">Sign out</a>
<hr>
<form action="/book" method="post">
    <input type="hidden" name="action" value="add">
    <input name="title" placeholder="Title"><br>
    <input name="author" placeholder="Author"><br>
    <input name="pubTime" placeholder="Publish time"><br>
    <input name="price" placeholder="Price"><br>
    <input name="amount" placeholder="Amount"><br>
    Picture <input type="file" name="picture"><br>
    <input type="submit" value="Add Book">
</form>
<hr>
<pre>
    c:choose
        c:when
        c:when
        ...
        c:otherwise

    switch
        case
        case
        ...
        default
</pre>
<table border="1">
    <c:choose>
        <c:when test="${fn:length(sessionScope.books) eq 0}">
            NO RECORDS.
        </c:when>
        <c:otherwise>
            <tr>
                <th>COUNT</th>
                <th>TITLE</th>
                <th>AUTHOR</th>
                <th>PUBLISH TIME</th>
                <th>PRICE</th>
                <th>AMOUNT</th>
                <th>PICTURE</th>
                <th colspan="2">OPERATION</th>
            </tr>
        </c:otherwise>
    </c:choose>
    <c:forEach var="book" items="${sessionScope.books}" varStatus="vs">
        <tr>
            <td>${vs.count}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.pubTime}</td>
            <td>${book.price}</td>
            <td>${book.amount}</td>
            <td>${book.picture}</td>
            <td><a href="/book?action=search&id=${book.id}">EDIT</a></td>
            <td><a href="/book?action=remove&id=${book.id}" onclick="return del()">REMOVE</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
