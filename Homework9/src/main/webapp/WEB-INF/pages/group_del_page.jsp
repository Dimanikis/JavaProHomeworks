<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>New Group</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <form role="form" class="form-horizontal" action="/group/del" method="post">
                <div class="form-group"><h3>New Group</h3></div>
                <select class="selectpicker form-control form-group" name="group">
                    <option value="-1">Default</option>
                    <c:forEach items="${groups}" var="group">
                        <option value="${group.id}">${group.name}</option>
                    </c:forEach>
                </select>
                <div class="form-group"><input type="submit" class="btn btn-primary" value="Del"></div>
            </form>
        </div>
    </body>
</html>