<%@ page pageEncoding="UTF-8"%>
<%@ include file="/views/jspf/directive/page.jspf" %>
<%@ include file="/views/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="ForSome" scope="page" />
<%@ include file="/views/jspf/head.jspf" %>

<body>

    <%@ include file="/views/jspf/header.jspf" %>

    <div align="center">
        <br>
        <h1>Изменить цену для ЛО - ${nameAction}</h1>
        <br>
    </div>

    <div id="all_lo_t" class="one_line">

        <b>Цена меняется только ${nameAction}, и новый тариф будет назван: "${nameAction}"</b>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="editTarifsAndPrices"/>

            <table id="lombard_table">
                <thead>
                <tr>
                    <td>Тфриф</td>
                    <td>Вес</td>
                    <td>Цена в тарифе стандарт</td>
                    <td>Новая цена</td>
                </tr>
                </thead>

                <c:set var="k" value="0"/>
                <c:forEach var="item" items="${standart}">
                    <c:set var="k" value="${k+1}"/>
                    <tr>
                        <td rowspan="5">${item.tarifName}</td>
                    <c:set var="k1" value="0"/>
                    <c:forEach var="item1" items="${item.testPrice}">
                        <c:set var="k1" value="${k1+1}"/>
                        <tr>
                        <td>${item1.name}</td>
                        <td>${item1.price}</td>
                        <td><input name="${item.tarifName}|${item1.name}" type="text" value="${item1.price}" size="8"></td>
                        </tr>
                    </c:forEach>
                    </tr>
                </c:forEach>
            </table>

            <input type="hidden" name="newType" value="${newType}" />
            <input type="hidden" name="sel" value="${sel}" />

            <input type="submit" value="Изменить" >
        </form>
    </div>



</body>
</html>
