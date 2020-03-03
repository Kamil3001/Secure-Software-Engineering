<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>colour_blue - a page</title>
    <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../../resources/theme/css/pages.css" type="text/css">
</head>

<body>
<div id="main">
    <div id="header">
        <div id="logo">
            <div id="logo_text">
                <!-- class="logo_colour", allows you to change the colour of the text -->
                <h1><a href="home">MCINO<span class="logo_colour">moodle</span></a></h1>
                <h2>Bringing students and staff together.</h2>
            </div>
        </div>
        <div id="menubar">
            <ul id="menu">
                <li ><a href="home">Home</a></li>
                <% if(session != null && session.getAttribute("username") != null ){%>
                    <li ><a href="statistics">Statistics</a></li>
                    <li ><a href="view_modules">Modules</a></li>
                    <li class="selected"><a href="my_profile">My Profile</a></li>
                        <% if(session.getAttribute("role") != null && session.getAttribute("role").equals("staff")){%>
                            <li ><a href="staff">Staff Portal</a></li>
                        <%}%>
                    <li><a href="logout">Logout</a></li>
                <% } %>
            </ul>
        </div>
    </div>
    <div id="content_header"></div>
    <div id="site_content">
        <div class="sidebar">
            <!-- insert your sidebar items here -->
            <h3>Latest News</h3>
            <h4>New Website Launched</h4>
            <h5>January 1st, 2010</h5>
            <p>2010 sees the redesign of our website. Take a look around and let us know what you think.<br /><a href="#">Read more</a></p>
            <p></p>
            <h4>New Website Launched</h4>
            <h5>January 1st, 2010</h5>
            <p>2010 sees the redesign of our website. Take a look around and let us know what you think.<br /><a href="#">Read more</a></p>
            <h3>Useful Links</h3>
            <ul>
                <li><a href="#">link 1</a></li>
                <li><a href="#">link 2</a></li>
                <li><a href="#">link 3</a></li>
                <li><a href="#">link 4</a></li>
            </ul>
            <h3>Search</h3>
            <form method="post" action="#" id="search_form">
                <p>
                    <input class="search" type="text" name="search_field" value="Enter keywords....." />
                    <input name="search" type="image" style="border: 0; margin: 0 0 -9px 5px;" src="../../resources/theme/images/search.png" alt="Search" title="Search" />
                </p>
            </form>
        </div>
        <div id="content">
            <h1 style="margin-bottom: 0">My Student Record</h1>
            <c:if test="${not student.feePaid}">
                <small class="unpaid-fees">NOTE: You have unpaid fees, click on My Registration below to pay your fees.</small>
            </c:if>
            <table class="student-info">
                <tr>
                    <th>Student ID:</th>
                    <td>${student.id}</td>
                </tr>
                <tr>
                    <th>Name:</th>
                    <td>${student.name}</td>
                </tr>
                <tr>
                    <th>Surname:</th>
                    <td>${student.surname}</td>
                </tr>
                <tr>
                    <th>Nationality:</th>
                    <td>${student.nationality}</td>
                </tr>
                <tr>
                    <th>Address:</th>
                    <td>${student.address}</td>
                </tr>
                <tr>
                    <th>Phone Number:</th>
                    <td>${student.phoneNum}</td>
                </tr>
                <tr>
                    <th>E-mail:</th>
                    <td>${student.email}</td>
                </tr>
            </table>
            <ul class="siswebstyle">
                <li>
                    <button type="button" class="collapsible">Current Modules</button>
                    <div class="collapsible-content">
                        <ul>
                        <c:forEach var="module" items="${curr_modules}">
                            <%-- todo try change this to redirect to module page and add unenroll option --%>
                            <li>${module.name}</li>
                        </c:forEach>
                        </ul>
                    </div>
                </li>
                <li>
                    <button type="button" class="collapsible">My Grades</button>
                    <div class="collapsible-content">
                            <c:if test="${moduleGradeMap.length != 0}">
                                <table>
                                    <tr>
                                        <th>Module ID</th>
                                        <th>Module Name</th>
                                        <th>Grade</th>
                                    </tr>
                                    <c:forEach var="entry" items="${moduleGradeMap}">
                                    <tr>
                                        <td>${entry.key}</td>
                                        <td>${entry.value[0]}</td>
                                        <td>${entry.value[1]}</td>
                                    </tr>
                                    </c:forEach>
                                </table>
                            </c:if>
                    </div>
                </li>
                <li>
                    <button type="button" class="collapsible">My Registration</button>
                    <div class="collapsible-content">
                        <%-- todo add pay fees button and unregister button --%>
                        <p>Lorem ipsum...</p>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div id="content_footer"></div>
    <div id="footer">
        MCINO Moodle @ <a href="https://www.mcinofamily.com">MCINO</a>
    </div>


</div>
</body>
<script>
    var coll = document.getElementsByClassName("collapsible");
    var i;

    for (i = 0; i < coll.length; i++) {
        coll[i].addEventListener("click", function() {
            this.classList.toggle("active");
            var content = this.nextElementSibling;
            if (content.style.display === "block") {
                content.style.display = "none";
            } else {
                content.style.display = "block";
            }
        });
    }
</script>
</html>
