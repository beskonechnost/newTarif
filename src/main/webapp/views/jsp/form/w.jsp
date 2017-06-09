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

            <form action="controller">
                <div>
                    <b>Количество ломбардов в Украине - ${size}</b>
                </div>

                <table id="lombard_table">
                    <thead>
                    <tr>
                        <td>№</td>
                        <td>Номер Ломбарда</td>
                        <td>Регион</td>
                        <td>Тип тарифа</td>
                        <td>Детали</td>
                        <td>Удалить</td>
                    </tr>
                    </thead>

                    <c:set var="k" value="0"/>
                    <c:forEach var="item" items="${lombards}">
                        <c:set var="k" value="${k+1}"/>
                        <tr>
                            <td><c:out value="${k}"/></td>
                            <td>${item.number}</td>
                            <td>${item.region}</td>
                            <td><c:choose>
                                <c:when test="${item.typeTarif.typeName=='Standart'}">
                                    <p style="color:#6690ff">${item.typeTarif.typeName}</p>
                                </c:when>
                                <c:when test="${item.typeTarif.typeName=='New'}">
                                    <p style="color:#7fff9e">${item.typeTarif.typeName}</p>
                                </c:when>
                                <c:when test="${item.typeTarif.typeName=='Special'}">
                                    <p style="color:#ff8285">${item.typeTarif.typeName}</p>
                                </c:when>
                                <c:otherwise>undefined</c:otherwise>
                            </c:choose>
                            </td>
                            <td><a href="controller?command=lombardTarif&numberLombard=${item.number}">Подробнее</a></td>
                            <td><a href="controller?command=SortByPrice&lombard=item">Удалить</a></td>
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