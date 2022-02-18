<%--
  Created by IntelliJ IDEA.
  User: kski
  Date: 18.02.2022
  Time: 01:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Create a book</title>
</head>
<body>


<form action="/books">
    <label>
        ISBN:
        <input type="number" name="isbn" required>
    </label>
    <label>
        Title:
        <input type="text" name="title" required>
    </label>
    <label>
        Author:
        <input type="text" name="author" required>
    </label>
    <label>
        Publisher:
        <input type="text" name="publisher" required>
    </label>
    <label>
        Type:
        <input type="text" name="type" required>
    </label>
    <label>
        Book to replace:
        <select>
            <c:forEach var="item" items="${books}">
                <option name="bookId" value="${item.id}">${item.name}</option>
            </c:forEach>
        </select>
    </label>

    <input type="submit" value="add" method="post">
    <input type="submit" value="replace" method="put">

</form>
</body>
</html>
