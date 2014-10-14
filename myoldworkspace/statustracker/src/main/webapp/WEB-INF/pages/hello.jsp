<html>
<head>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>
	<h1>${message}</h1>


	<form:form method="POST">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<table cellpadding="1" cellspacing="1" border="1" class="display"
			id="example" width="80%">
			<thead>
				<tr>
					<th>ApplicationName</th>
					<th>Status</th>
					<th>Details</th>					
				</tr>
				<tr>
					<th>UVDC</th>
					<th>Up And Running</th>
					<th>All Tests Passed</th>					
				</tr>
				<tr>
					<th>UVGS</th>
					<th>Up And Running</th>
					<th>All Tests Passed</th>					
				</tr>
			</thead>
		</table>
	</form:form>

</body>
</html>