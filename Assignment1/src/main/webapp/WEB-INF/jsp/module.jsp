<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>
        UoS - ${module.name}
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
                <h1><a href="../home">University of Springfield</a></h1>
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
                    <li><a href="../logout">Logout</a></li>
                </c:if>
            </ul>
        </div>
    </div>
    <div id="content_header"></div>
    <div id="site_content">
        <c:import url="nested/sidebar.jsp"/>
        <div id="content">
            <h1>${module.name}</h1>
            <c:choose>
                <c:when test="${sessionScope.role eq 'staff' and coordinator.id eq sessionScope.id and not module.terminated}">
                    <form:form modelAttribute="module" method="post" action="/modules/${module.id}/update/"> <%-- method="post" modelAttribute="studentModules" action="/grades/${module.id}/update/"--%>
                        <table class="profile-info">
                            <tr>
                                <th>Module Code:</th>
                                <td>${module.moduleCode}</td>
                                <form:input path="moduleCode" value="${module.moduleCode}" type="hidden"/>
                            </tr>
                            <tr>
                                <th>Module Name:</th>
                                <td><form:input path="name" value="${module.name}" type="text"/></td>
                            </tr>
                            <tr>
                                <th>Coordinator Name:</th>
                                <td>${coordinator.name} ${coordinator.surname} ${coordinator.id} ${module.coordinatorId}</td>
                            </tr>
                            <tr>
                                <th>Module Status:</th>
                                <td>Active <a style="float:right;" href="../modules/${module.id}/terminate">Terminate?</a></td>
                            </tr>
                            <tr>
                                <th>Capacity:</th>
                                <td><form:input path="capacity" value="${module.capacity}" type="number"/></td>
                            </tr>
                            <tr>
                                <th>Number of Enrolled Students:</th>
                                <td>${numEnrolled}</td>
                            </tr>
                            <tr>
                                <th>Topics:</th>
                                <td><form:input path="topics" value="${module.topics}"/></td>
                            </tr>
                        </table>
                        <button type="submit">Update Details</button>
                    </form:form>
                </c:when>
                <c:otherwise>
                    <table class="profile-info">
                        <tr>
                            <th>Module Code:</th>
                            <td>${module.moduleCode}</td>
                        </tr>
                        <tr>
                            <th>Coordinator Name:</th>
                            <td>${coordinator.name} ${coordinator.surname}</td>
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
                </c:otherwise>
            </c:choose>

            <c:if test="${sessionScope.id != null}">
                <c:choose>
                    <c:when test="${sessionScope.role eq 'staff' and coordinator.id == module.coordinatorId and module.terminated}">
                        <button type="button" class="collapsible">Grading</button>
                        <div class="collapsible-content">
                            <c:choose>
                                <c:when test="${studentModules != null}">
                                    <form:form method="post" modelAttribute="studentModules" action="/grades/${module.id}/update/"> <%-- Ignore the error on studentModules, it's wrong --%>
                                        <table>
                                            <tr>
                                                <th>Student ID</th>
                                                <th>Student Name</th>
                                                <th>Grade</th>
                                            </tr>
                                            <c:forEach var="entry" items="${studentModules.studentGrades}" varStatus="status">
                                                <tr>
                                                    <td>${entry.studentId}</td>
                                                    <td>${entry.studentFullName}</td>
                                                    <td>
                                                        <form:input path="studentGrades[${status.index}].studentId" type="hidden" value="${entry.studentId}"/>
                                                        <form:select path="studentGrades[${status.index}].grade">
                                                            <c:forEach var="opt" items="${gradeChoices}">
                                                                <option value="${opt}" ${opt eq entry.grade ? "selected" : ""}>${opt}</option>
                                                            </c:forEach>
                                                        </form:select>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                        <button type="submit">Save Grades</button>
                                    </form:form>
                                </c:when>
                                <c:otherwise>
                                    <h3>Uh-oh... Looks like nobody wanted to enrolled on last offering</h3>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:when>

                    <c:when test="${sessionScope.role eq 'student' and feesPaid and numEnrolled lt module.capacity and not isEnrolled and not module.terminated}">
                        <button onclick="window.location.href = '/modules/${module.id}/enroll/${sessionScope.id}';">
                            Enroll
                        </button>
                    </c:when>

                    <c:when test="${module.terminated}">
                        This offering has terminated.
                    </c:when>

                    <c:when test="${sessionScope.role eq 'student' and not feesPaid}">
                        <div class="red-info">You have unpaid fees. Please pay your fees in My Profile to enroll to modules.</div>
                    </c:when>

                    <c:when test="${sessionScope.role eq 'student'}">
                        You are currently enrolled to this module.
                    </c:when>
                </c:choose>
            </c:if>
            <c:if test="${moduleGrades != null}">
                <h2>Grade Distribution</h2>
                <div id="grade_distribution"></div>
                <script src="https://d3js.org/d3-scale-chromatic.v1.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.17/d3.min.js"></script>
                <script>

                    var margin = {top: 20, right: 20, bottom: 70, left: 40},
                        width = 600 - margin.left - margin.right,
                        height = 300 - margin.top - margin.bottom;


                    var x = d3.scale.ordinal().rangeRoundBands([0, width], .05);

                    var y = d3.scale.linear().range([height, 0]);

                    var xAxis = d3.svg.axis()
                        .scale(x)
                        .orient("bottom")

                    var yAxis = d3.svg.axis()
                        .scale(y)
                        .orient("left")
                        .ticks(10);

                    var svg = d3.select("#grade_distribution").append("svg")
                        .attr("width", width + margin.left + margin.right)
                        .attr("height", height + margin.top + margin.bottom)
                        .append("g")
                        .attr("transform",
                            "translate(" + margin.left + "," + margin.top + ")");

                    var data = d3.entries(${moduleGrades})

                    x.domain(data.map(function(d) { return d.key; }));
                    y.domain([0, d3.max(data, function(d) { return d.value; })]);

                    svg.append("g")
                        .attr("transform", "translate(0," + height + ")")
                        .call(xAxis)
                        .selectAll("text")
                        .style("text-anchor", "end")
                        .attr("dx", "-.8em")
                        .attr("dy", "-.55em")
                        .attr("transform", "rotate(-90)" );

                    svg.append("g")
                        .call(yAxis)
                        .append("text")
                        .attr("transform", "rotate(-90)")
                        .attr("y", 6)
                        .attr("dy", ".71em")
                        .style("text-anchor", "end")

                    svg.selectAll("bar")
                        .data(data)
                        .enter().append("rect")
                        .style("fill", "#007EA7")
                        .attr("x", function(d) { return x(d.key); })
                        .attr("width", x.rangeBand())
                        .attr("y", function(d) { return y(d.value); })
                        .attr("height", function(d) { return height - y(d.value); });


                </script>
            </c:if>
        </div>
    </div>
    <div id="content_footer"></div>
    <div id="footer">
        University of Springfield
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
