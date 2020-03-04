<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>MCINO Moodle - Available Modules</title>
    <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../../resources/theme/css/pages.css" type="text/css">
</head>

<body>
<div id="main">
    <div id="header">
        <c:import url="nested/header.jsp"/>
    </div>
    <div id="content_header"></div>
    <div id="site_content">
        <c:import url="nested/sidebar.jsp"/>
        <div id="content">
            <!-- insert the page content here -->
            <h1>Available Modules</h1>
            <input type="text" id="searchInput" onkeyup="searchTopics()" placeholder="Search for topics..">
            <table id="moduleTable">
                <tr>
                    <th>Module ID</th>
                    <th>Module Name</th>
                    <th>Topics</th>
                </tr>
                <c:if test="${fn:length(all_modules) != 0}">
                    <c:forEach var="module" items="${all_modules}">
                            <tr>
                                <td>${module.id}</td>
                                <td>
                                    <a href="module/${module.id}">
                                        ${module.name}
                                    </a>
                                </td>
                                <td>${module.topics}</td>
                            </tr>
                        </a>
                    </c:forEach>
                </c:if>
            </table>

        </div>
    </div>
    <div id="content_footer"></div>
    <div id="footer">
        MCINO Moodle @ <a href="https://www.mcinofamily.com">MCINO</a>
    </div>
</div>
</body>
<script>
    function searchTopics() {
        var input = document.getElementById("searchInput");
        var filter = input.value.toUpperCase();
        var table = document.getElementById("moduleTable");
        var tr = table.getElementsByTagName("tr");

        var td, txt, i;
        for (i=0; i<tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[2];
            if (td) {
                txt = td.textContent || td.innerText;
                if (txt.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                }
                else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
</script>
</html>
