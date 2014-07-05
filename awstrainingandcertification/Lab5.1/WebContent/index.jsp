<%@page import="awslabs.lab51.Lab51"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%! // Share the client objects across threads to
    // avoid creating new clients for each web request
    private Lab51 lab51 = new Lab51();
 %>

<%
    /*
     * AWS Elastic Beanstalk checks your application's health by periodically
     * sending an HTTP HEAD request to a resource in your application. By
     * default, this is the root or default resource in your application,
     * but can be configured for each environment.
     *
     * Here, we report success as long as the app server is up, but skip
     * generating the whole page since this is a HEAD request only. You
     * can employ more sophisticated health checks in your application.
     */
    if (request.getMethod().equals("HEAD")) return;
%>

<%
	
    if (lab51 != null) {
		lab51.syncImages();
    }

%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>Developing on AWS - Lab5.1</title>
    <link rel="stylesheet" href="styles/styles.css" type="text/css" media="screen">
</head>
<body>
	
    <table class="90percent">
        <tr>
            <td><h2>Configuration Parameters:</h2></td>
            <td><h2>Host Environment:</h2></td>
        </tr>
        <tr>
 
            <td class="topalign">
                <table class="configPlaceholder">
					<%= lab51.getConfigAsHtml() %>
                </table>
            </td>
            <td class="topalign">
                <table class="sysenvPlaceholder">
                    <%= lab51.getSysEnvAsHtml() %>
                </table>
            </td>       

        </tr>
    </table>

    <h2>Image List:</h2>
    <div id="imageListPlaceholder">
    	<%= lab51.getImageListAsHtml() %>
    </div>

    <h2>Status Messages:</h2>
    <div id="statusPlaceholder">
    	<%= lab51.getStatusAsHtml() %>
    </div> 
     
</body>
</html>