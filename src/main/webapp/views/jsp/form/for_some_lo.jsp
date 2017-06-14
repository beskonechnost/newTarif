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
                    <td>Код</td>
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
                    <c:forEach var="item1" items="${item.tarifPrices}">
                        <c:set var="k1" value="${k1+1}"/>
                        <tr>
                            <td>${item1.name}</td>
                            <td>${item1.testPrice}</td>
                            <td>${item1.price}</td>
                            <td><input name="${item.tarifName}|${item1.name}" type="text" value="${item1.price}" size="8"></td>
                        </tr>
                    </c:forEach>
                    </tr>
                </c:forEach>
            </table>
            <br>

            <!----------------------------------------->

            <!---<fieldset>
                <legend>Добавить тариф:</legend>
                <div>
                    <input type="checkbox" id="addNewTarif" name="interest" value="addNewTarif">
                    <label for="addNewTarif">Добавить новый тариф</label><br>
                    <label for="addNameTarif">Name: </label>
                    <input type="text" class="addValue" name="addNameTarif" id="addNameTarif">
                </div>
            </fieldset>-->
            <br>
            <a id="toggler" href="#"><b>Добавить тариф</b></a></br>
            <div id="box" style="display: none;">
                <fieldset>
                <legend>Добавить тариф:</legend>
                    <label for="nameNewTarif">Имя нового тарифа: </label>
                    <input type="text" name="nameNewTarif" id="nameNewTarif" /><br>

                    <div id="addPrice">
                        <input type="button" value="Добавить">
                    </div>


                </fieldset>
            </div>





            <br>
            <!----------------------------------------->

            <input type="hidden" name="newType" value="${newType}" />
            <input type="hidden" name="sel" value="${sel}" />

            <input type="submit" value="Изменить" align="center">
        </form>
    </div>

</body>

    <script>
    window.onload= function() {
        document.getElementById('toggler').onclick = function() {
            openbox('box', this);
            return false;
        };
    };
    function openbox(id, toggler) {
        var div = document.getElementById(id);
        if(div.style.display == 'block') {
            div.style.display = 'none';
            toggler.innerHTML = 'Добавить тариф';
        }
        else {
            div.style.display = 'block';
            toggler.innerHTML = 'Закрыть';
        }
    }
    </script>
    <script>
        (function() {
            var a = 1;
            document.querySelector('#addPrice input').onclick = function () {
                var theDiv = document.createElement('div');// создать новый тег div
                theDiv.innerHTML = '<b>'+a+'</b><label for="weight"> Вес:</label> <input type="text" name="weight|' + a + '" id="weight" />' +
                    '<label for="code"> Код:</label> <input type="text" name="code|' + a + '" id="code" />' +
                    '<label for="price"> Цена:</label> <input type="text" name="price|' + a + '" id="price" />' +
                    '<input type="hidden" name="count" value=' + a + ' />';  // его содержимое
                this.parentNode.appendChild(theDiv);  // поместить новый тег последним в div с id="raz" (вместо this.parentNode может быть любой другой элемент DOM)
                getComputedStyle(theDiv).opacity;
                theDiv.style.opacity = '1';
                a++;
            }
        })()
    </script>



</html>
