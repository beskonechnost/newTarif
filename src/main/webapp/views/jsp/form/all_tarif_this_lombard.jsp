<%@ page pageEncoding="UTF-8"%>
<%@ include file="/views/jspf/directive/page.jspf" %>
<%@ include file="/views/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="allLombards" scope="page" />
<%@ include file="/views/jspf/head.jspf" %>

<body>
<table id="main-container">

    <%@ include file="/views/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <form id="make_table" action="controller">
                <div>
                    <h1>Ломбард №${lombardNumber}</h1>
                </div>

                <table id="lombard_table">
                    <thead>
                    <tr>
                        <td>№</td>
                        <td>Тариф</td>
                        <td>Информация о ценах</td>
                        <td>Статус</a>
                        </td>
                    </tr>
                    </thead>

                    <c:set var="k" value="0"/>
                    <c:forEach var="item" items="${lombardTarifs}">
                        <c:set var="k" value="${k+1}"/>
                        <tr>
                            <td><c:out value="${k}"/></td>
                            <td>${item.tarifName}</td>
                            <td><a href="controller?command=lombardTarif&numberLombard=item.number">О ценах</a></td>
                            <td>${item.status}</td>
                        </tr>
                    </c:forEach>
                </table>

            </form>

            <%-- CONTENT --%>
        </td>
    </tr>

</table>
</body>
</html>