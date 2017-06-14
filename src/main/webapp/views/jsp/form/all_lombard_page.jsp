<%@ page pageEncoding="UTF-8"%>
<%@ include file="/views/jspf/directive/page.jspf" %>
<%@ include file="/views/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="allLombards" scope="page" />
<%@ include file="/views/jspf/head.jspf" %>

<body>

        <%@ include file="/views/jspf/header.jspf" %>

        <div align="center">
            <br>
                <h1>Количество ломбардов в Украине - ${size}</h1>
            <br>
        </div>

        <div id="all_lo_t" class="one_line">
                <table id="lombard_table">
                    <thead>
                    <tr>
                        <td>№</td>
                        <td>Номер Ломбарда</td>
                        <td>Регион</td>
                        <td>Тип тарифа</td>
                        <td>Детали</td>
                        <td>Сделать индивидуальный тариф</td>
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
                                    <c:when test="${item.typeTarif.typeName=='New Lombard'}">
                                        <p style="color:#d731ff">${item.typeTarif.typeName}</p>
                                    </c:when>
                                    <c:when test="${item.typeTarif.typeName!='Standart' || item.typeTarif.typeName!='New Lombard'}">
                                        <p style="color:#ff8285">${item.typeTarif.typeName}</p>
                                    </c:when>
                                </c:choose>
                            </td>
                            <td><a href="controller?command=lombardTarif&numberLombard=${item.number}">Подробнее</a></td>
                            <td>
                                <c:choose>
                                    <c:when test="${item.typeTarif.typeName=='Standart'}">
                                       <a href="controller?command=lombardTarif&numberLombard=${item.number}">Сделать уникальным</a>
                                    </c:when>
                                    <c:otherwise>
                                       <a href="controller?command=lombardTarif&numberLombard=${item.number}">Сделать стандартным</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td><a href="controller?command=SortByPrice&lombard=item">Удалить</a></td>
                        </tr>
                    </c:forEach>
                </table>
        </div>

        <div class="one_line">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="forSome"/>
                <b>Изменить цену для части ЛО:  </b>
                <br>
                <c:set var="k" value="0"/>
                <c:forEach var="item" items="${listRegion}">
                    <c:set var="k" value="${k+1}"/>
                    <fieldset class="shest1">
                        <legend><input type="checkbox">${item}</legend>
                        <c:set var="k1" value="0"/>
                        <c:forEach var="item1" items="${lombards}">
                            <c:set var="k1" value="${k1+1}"/>
                            <c:if test="${item == item1.region}">
                                <label><input type="checkbox" value="${item1.number}" name="choose">${item1.number}</label><br>
                            </c:if>
                        </c:forEach>
                    </fieldset>
                </c:forEach>

                <br>
                <input type="submit" value="Для определенных" >
            </form>
        </div>

        <div class="one_line">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="forAll"/>
                <b>Изменить цену для всех ЛО:  </b>
                <input type="submit" value="Для всех" >
            </form>
        </div><br/>

<script>
    for (var u = document.getElementsByTagName("form"), i = 0; i < u.length; i++)
        u[i].onmouseover = function (b) {
            l = u[i].getElementsByTagName("fieldset");
            for (var k = 0; k < l.length; k++)
                if (l[k].className == "shest1") l[k].onmouseover = function (a) {
                    var main1 = document.querySelector('form:nth-of-type('+(b+1)+') fieldset:nth-of-type('+(a+1)+') legend [type="checkbox"]'),
                        all1 = document.querySelectorAll('form:nth-of-type('+(b+1)+') fieldset:nth-of-type('+(a+1)+') label [type="checkbox"]');
                    for(var j=0; j<all1.length; j++)
                        all1[j].onclick = function() {
                            var allChecked1 = document.querySelectorAll('form:nth-of-type('+(b+1)+') fieldset:nth-of-type('+(a+1)+') label [type="checkbox"]:checked').length;
                            main1.checked = allChecked1 == all1.length;
                            main1.indeterminate = allChecked1 > 0 && allChecked1 < all1.length;
                        }
                    main1.onchange = function() {
                        for(var j=0; j<all1.length; j++)
                            all1[j].checked = this.checked;
                    }
                }(k)
        }(i)
</script>

</body>
</html>