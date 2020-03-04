<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1 style="margin-bottom: 0">My Staff Record</h1>
<c:if test="${not student.feePaid}">
    <small class="red-info">NOTE: You have unpaid fees, click on My Registration below to pay your fees.</small>
</c:if>
<table class="student-info">
    <tr>
        <th>Staff ID:</th>
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
</table>
<ul class="siswebstyle">
    <li>
        <button type="button" class="collapsible">My Modules</button>
        <div class="collapsible-content">
            <c:choose>
                <c:when test="${fn:length(curr_modules) != 0}">
                    <table>
                        <tr>
                            <th>Module ID</th>
                            <th>Module Name</th>
                            <th></th>
                        </tr>
                        <c:forEach var="module" items="${curr_modules}">
                            <tr>
                                <td>${module.id}</td>
                                <td><a href="/module/${module.id}">${module.name}</a></td>
                                <td><a href="/students/${student.id}/drop/${module.id}">Drop</a></td>
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