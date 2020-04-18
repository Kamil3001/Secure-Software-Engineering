<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1 style="margin-bottom: 0">My Student Record</h1>
<c:if test="${not student.feePaid}">
    <small class="red-info">NOTE: You have unpaid fees, click on My Registration below to pay your fees.</small>
</c:if>
<table class="profile-info">
    <tr>
        <th>Student ID:</th>
        <td><c:out value = "${student.id}"/></td>
    </tr>
    <tr>
        <th>Name:</th>
        <td><c:out value = "${student.name}"/></td>
    </tr>
    <tr>
        <th>Surname:</th>
        <td><c:out value = "${student.surname}"/></td>
    </tr>
    <tr>
        <th>Nationality:</th>
        <td><c:out value = "${student.nationality}"/></td>
    </tr>
    <tr>
        <th>Gender:</th>
        <td><c:out value = "${student.gender}"/></td>
    </tr>
    <tr>
        <th>Address:</th>
        <td><c:out value = "${student.address}"/></td>
    </tr>
    <tr>
        <th>Phone Number:</th>
        <td><c:out value = "${student.phoneNum}"/></td>
    </tr>
    <tr>
        <th>E-mail:</th>
        <td><c:out value = "${student.email}"/></td>
    </tr>
</table>
<ul class="siswebstyle">
    <li>
        <button type="button" class="collapsible">Current Modules</button>
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
    <li>
        <button type="button" class="collapsible">My Grades</button>
        <div class="collapsible-content">
            <c:choose>
                <c:when test="${fn:length(moduleGradeMap) != 0}">
                    <table>
                        <tr>
                            <th>Module ID</th>
                            <th>Module Name</th>
                            <th>Grade</th>
                        </tr>
                        <c:forEach var="entry" items="${moduleGradeMap}">
                            <tr>
                                <td>${entry.key}</td>
                                <td><a href="/module/${entry.key}">${entry.value[0]}</a></td>
                                <td>${entry.value[1]}</td>
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
    <li>
        <button type="button" class="collapsible">My Registration</button>
        <div class="collapsible-content">
            <c:if test="${not student.feePaid}">
                <a href="/students/${student.id}/payFees">Pay Fees</a>
            </c:if>
            <p>
                <a href="/students/${student.id}/unregister">Unregister</a>
                <small class="red-info">(THIS WILL PERMANENTLY DELETE YOUR RECORD)</small>
            </p>
        </div>
    </li>
</ul>