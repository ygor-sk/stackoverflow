<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Task Page</title>
</head>
<body>
<form:form method="POST" action="/task-add" modelAttribute="task">
    <div>
        <form:label path="title">Title</form:label>
        <form:input path="title" />
    </div>

    <div>
        <form:label path="desc">Description</form:label>
        <form:input path="desc" />
    </div>

    <div>
        <input type="submit" value="Add">
    </div>
</form:form>
</body>
</html>