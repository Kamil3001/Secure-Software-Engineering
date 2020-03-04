<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>
        MCINO Moodle - ${module.name}
    </title>
    <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../../resources/theme/css/pages.css" type="text/css">
</head>

<body>
<div id="main">
    <div id="header">
        <div id="logo">
            <div id="logo_text">
                <h1><a href="../home">MCINO<span class="logo_colour">moodle</span></a></h1>
                <h2>Bringing students and staff together.</h2>
            </div>
        </div>
        <div id="menubar">
            <ul id="menu">
                <li><a href="../home">Home</a></li>
                <c:if test="${sessionScope.username != null}">
                    <li><a href="../statistics">Statistics</a></li>
                    <li><a href="../view_modules">Modules</a></li>
                    <li><a href="../my_profile">My Profile</a></li>
                    <%--            <c:if test="${sessionScope.role eq staff}">--%>
                    <%--                <li><a href="staff">Staff Portal</a></li>--%>
                    <%--            </c:if>--%>
                    <li><a href="../logout">Logout</a></li>
                </c:if>
            </ul>
        </div>
    </div>
    <div id="content_header"></div>
    <div id="site_content">
        <c:import url="nested/sidebar.jsp"/>
        <div id="content">
            <!-- insert the page content here -->
            <h1>${module.name}</h1>
            <table class="student-info">
                <tr>
                    <th>Module ID:</th>
                    <td>${module.id}</td>
                </tr>
                <tr>
                    <th>Coordinator Name:</th>
                    <td>${coordinator.name}</td>
                </tr>
                <tr>
                    <th>Module Status:</th>
                    <td>
                        <c:choose>
                            <c:when test="${module.terminated}">
                                Terminated
                            </c:when>
                            <c:otherwise>
                                Active
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <%-- todo change this to show Active/Terminated instead --%>
                </tr>
                <tr>
                    <th>Capacity:</th>
                    <td>${module.capacity}</td>
                </tr>
                <tr>
                    <th>Number of Enrolled Students:</th>
                    <td>${numEnrolled}</td>
                </tr>
                <tr>
                    <th>Topics:</th>
                    <td>${module.topics}</td>
                </tr>
            </table>
            <c:if test="${sessionScope.id != null}">
                <c:choose>
                    <c:when test="${sessionScope.role eq 'staff' and coordinator.id == module.coordinatorId}">
                        TODO: IMPLEMENT DETAILS UPDATE
                    </c:when>
                    <c:when test="${module.terminated}">
                        This module has terminated.
                    </c:when>
                    <c:when test="${sessionScope.role eq 'student' and numEnrolled < module.capacity and not isEnrolled}">
                        <button onclick="window.location.href = '/modules/${module.id}/enroll/${sessionScope.id}';">
                            Enroll
                        </button>
                    </c:when>
                    <c:when test="${sessionScope.role eq 'student'}">
                        You are currently enrolled to this module.
                    </c:when>
                </c:choose>
            </c:if>
            <h2>Grade Distributions</h2>
            <%-- todo add grade distribution display for previous editions of the module --%>
        </div>
    </div>
    <div id="content_footer"></div>
    <div id="footer">
        MCINO Moodle @ <a href="https://www.mcinofamily.com">MCINO</a>
    </div>
</div>
</body>
</html>
