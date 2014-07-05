<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	${braintreeUrl}
	
	<c:url var="saveUrl" value="${braintreeUrl}" />
	<form:form modelAttribute="model" method="POST" action="${saveUrl}">
		<input type="submit" value="Pay" />
	</form:form>
</body>
</html>