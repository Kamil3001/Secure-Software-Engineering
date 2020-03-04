<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="logo">
    <div id="logo_text">
        <h1><a href="home">MCINO<span class="logo_colour">moodle</span></a></h1>
        <h2>Bringing students and staff together.</h2>
    </div>
</div>
<div id="menubar">
    <ul id="menu">
        <li><a href="home">Home</a></li>
        <c:if test="${sessionScope.username != null}">
            <li><a href="statistics">Statistics</a></li>
            <li><a href="view_modules">Modules</a></li>
            <li><a href="my_profile">My Profile</a></li>
<%--            <c:if test="${sessionScope.role eq staff}">--%>
<%--                <li><a href="staff">Staff Portal</a></li>--%>
<%--            </c:if>--%>
            <li><a href="logout">Logout</a></li>
        </c:if>
    </ul>
</div>

<script>
    var pathParts = window.location.pathname.split("/")
    var page = pathParts.pop();
    var pageIfModule = pathParts.pop();
    var list = document.getElementById("menu").children;
    var aTag;
    for (var i = 0; i < list.length; i++) {
        aTag = list[i].getElementsByTagName("a")[0];
        if (aTag.getAttribute("href") === page) {
            list[i].classList.add("selected");
            break;
        }
    }
</script>