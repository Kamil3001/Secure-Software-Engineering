<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1 style="margin-bottom: 0">My Staff Record</h1>
<table class="profile-info">
    <tr>
        <th>Staff ID:</th>
        <td>${coordinator.id}</td>
    </tr>
    <tr>
        <th>Name:</th>
        <td>${coordinator.name}</td>
    </tr>
    <tr>
        <th>Surname:</th>
        <td>${coordinator.surname}</td>
    </tr>
    <tr>
        <th>Nationality:</th>
        <td>${coordinator.nationality}</td>
    </tr>
    <tr>
        <th>Gender:</th>
        <td>${coordinator.gender}</td>
    </tr>
</table>
<ul class="siswebstyle">
    <li>
        <button type="button" class="collapsible">My Modules</button>
        <div class="collapsible-content">
            <c:choose>
                <c:when test="${fn:length(taught_modules) != 0}">
                    <table>
                        <tr>
                            <th>Module ID</th>
                            <th>Module Name</th>
                            <th>Status</th>
                        </tr>
                        <c:forEach var="module" items="${taught_modules}">
                            <tr>
                                <td>${module.id}</td>
                                <td><a href="/module/${module.id}">${module.name}</a></td>
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
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <h3>Nothing to display..</h3>
                </c:otherwise>
            </c:choose>
        </div>
    </li>
</ul>