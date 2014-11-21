<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType = "text/html;charset=utf-8" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<style type="text/css">
    table {
        border-collapse: collapse;
        text-align: center;
        font-family: 'Trebuchet MS';
    }
    td, th {
    	padding: 10px;
        font-size: 10pt;
        border: 1px solid #98bf21;
        height: 30px;
    }
    th {
        background-color:#A7C942;
        color:#ffffff;
        font-family: Georgia;
    }
    tr.alt td {
        color:#000000;
        background-color:#EAF2D3;
    }
    div {
    	margin: 20px;
    }
</style>
</head>
<body>

<div>
<table>
<tr>
<th>ID</th><th>Email</th><th>Password</th><th>Nmae</th>
</tr>
<c:forEach var="calendarusers" items="${calendarusers}">
<tr>
<td>${calendarusers.id}</td><td>${calendarusers.email}</td><td>${calendarusers.password}</td><td>${calendarusers.name}</td>
</tr> 
</c:forEach>
</table> 
</div>

<div>
<table>
<tr>
<th>ID</th><th>When</th><th>Summary</th><th>Description</th><th>Owner</th><th>NumLikes</th><th>EventLevel</th>
</tr>
<c:forEach var="events" items="${events}">
<tr>
<td>${events.id}</td><td>${events.when.time}</td><td>${events.summary}</td><td>${events.description}</td><td>${events.owner}</td><td>${events.numLikes}</td><td>${events.eventLevel}</td>
</tr> 
</c:forEach>
</table> 
</div>

<div>
<table>
<tr>
<th>ID</th><th>Event</th><th>Attendee</th>
</tr>
<c:forEach var="eventattentees" items="${eventattentees}">
<tr>
<td>${eventattentees.id}</td><td>${eventattentees.event}</td><td>${eventattentees.attendee}</td>
</tr> 
</c:forEach>
</table> 
</div>

</body>
</html>
