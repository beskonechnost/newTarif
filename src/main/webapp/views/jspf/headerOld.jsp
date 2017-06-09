<%@ include file="/views/jspf/directive/page.jspf" %>
<%@ include file="/views/jspf/directive/taglib.jspf" %>
<%@ include file="/views/jspf/head.jspf" %>


<tr>
    <td id="header">

        <c:if test="${not empty user}">
            <div id="leftHeader">
                <!-- <a href="controller?command=AdminListEditions"><fmt:message key="header.admin.editions"></fmt:message></a> &nbsp;
                <a href="controller?command=AdminListEditions"><fmt:message key="header.admin.editions"></fmt:message></a> &nbsp;-->
            </div>

            <div id="rightHeader" >
                <!-- <a href="controller?command=logout"><fmt:message key="header.logout"></fmt:message></a>
                <a href="controller?command=logout"><fmt:message key="header.logout"></fmt:message></a>-->
            </div>
        </c:if>

        <c:if test="${empty user and title ne 'Login'}">
            <div id="rightHeader">
                <!-- <a href="signOn.jsp"><fmt:message key="header.singOn"></fmt:message></a>-->
            </div>
        </c:if>

    </td>
</tr>