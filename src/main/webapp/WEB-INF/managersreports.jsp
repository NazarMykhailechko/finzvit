<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>--%>

<html>
<head>

    <title>Клієнти</title>

    <meta charset="utf-8" />

    <%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">--%>
    <%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2-bootstrap-css/1.4.6/select2-bootstrap.min.css">--%>
    <%--    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.4/css/select2.min.css" rel="stylesheet" />--%>
    <%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.2.1/js/bootstrap.min.js"></script>--%>
    <%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.4/js/select2.min.js"></script>--%>

    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <%--script src = "http://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js" defer ></script>--%>
    <link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" type="text/css" rel="stylesheet" />
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" defer></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/fixedheader/3.1.2/css/fixedHeader.dataTables.min.css">
    <script src="https://cdn.datatables.net/fixedheader/3.1.2/js/dataTables.fixedHeader.min.js" type="text/javascript"></script>

    <script src="https://cdn.anychart.com/releases/8.9.0/js/anychart-base.min.js" type="text/javascript"></script>
    <script src="https://cdn.anychart.com/releases/8.10.0/js/anychart-data-adapter.min.js"></script>
    <script src="https://cdn.anychart.com/js/latest/anychart-bundle.min.js"></script>
    <%--<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" type="text/css" rel="stylesheet" />
        <!--Import jQuery before export.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>--%>


    <script type="text/javascript">
        $(document).ready(function() {

            let dropdown = $('#locality-dropdown');

            dropdown.empty();

            dropdown.append('<option selected="true" disabled>Виберіть відділення</option>');
            dropdown.prop('selectedIndex', 0);

            $.getJSON('${pageContext.request.contextPath}/api/clients/findallbankbranches', function(data) {
                $("#locality-dropdown").append('<option name="Головний Банк">Головний Банк</option>');
                for (let div of data){
                    $("#locality-dropdown").append('<option name="' + div.shortname + '">' + div.shortname + '</option>');
                }
            });

        });

/*        $(document).ready(function() {
      anychart.onDocumentLoad(function() {

            chart = anychart.pie([]);
            chart.labels().position("outside");
            let legend = chart.legend();
            legend.maxWidth("100%");
            legend.maxHeight("30%");
            legend.itemsLayout("verticalExpandable");
            legend.position("bottom");
            legend.paginator().orientation("bottom");
            chart.container("container");
            chart.draw();

          });
        });*/

        $(document).ready(function() {

            let dropdown = $('#managerlist');

            dropdown.empty();

            dropdown.append('<option selected="true" disabled>Виберіть менеджера</option>');
            dropdown.prop('selectedIndex', 0);

            let dropdown1 = $('#managerlistresults');

            dropdown1.empty();

            dropdown1.append('<option selected="true" disabled>Виберіть менеджера</option>');
            dropdown1.prop('selectedIndex', 0);

            $.getJSON('${pageContext.request.contextPath}/api/clients/findallmanagers', function(data) {
                /*$("#locality-dropdown").append('<option name="Головний Банк">Головний Банк</option>');*/
                for (let div of data){
                    $("#managerlist").append('<option name="' + div.manager + '">' + div.manager + '</option>');
                    $("#managerlistresults").append('<option name="' + div.manager + '">' + div.manager + '</option>');
                }
            });

        });

    </script>

    <style type="text/css">

        .tabs { width: 100%; padding: 0px; margin: 0 auto; }
        .tabs>input { display: none; }
        .tabs>div {
            display: none;
            padding: 12px;
            border: 1px solid #C0C0C0;
            background: #FFFFFF;
        }
        .tabs>label {
            font-family: "Helvetica Neue", Helvetica, sans-serif;
            font-size: small;
            display: inline-block;
            padding: 7px;
            margin: 0 -5px -1px 0;
            text-align: center;
            color: #666666;
            border: 1px solid #C0C0C0;
            background: #E0E0E0;
            cursor: pointer;
        }
        .tabs>input:checked + label {
            color: #000000;
            border: 1px solid #C0C0C0;
            border-bottom: 1px solid #FFFFFF;
            background: #FFFFFF;
        }
        #tab_1:checked ~ #txt_1,
        #tab_2:checked ~ #txt_2,
        #tab_3:checked ~ #txt_3,
        #tab_4:checked ~ #txt_4 { display: block; }

        .blue-button{
            background: #25A6E1;
            padding:3px 5px;
            color:#fff;
            font-family:'Helvetica Neue',sans-serif;
            font-size:12px;
            border-radius:2px;
            -moz-border-radius:2px;
            -webkit-border-radius:4px;
            border:1px solid #1A87B9
        }

        table {
            font-family: "Helvetica Neue", Helvetica, sans-serif;
            font-size: xx-small;
            /*width: 100%; padding: 0px; margin: 0 auto;*/
        }
        th {
            background:  crimson;
            color: white;
            position: -webkit-sticky;
            position: sticky;
            top: 0;
            z-index: 2;
            /*height: 110px;*/
        }
        td,th{
            border: 1px solid gray;
            text-align: left;
            padding: 5px 10px;
        }

        .text {
            text-align: center;
        }

        h1,h2,h3{
            color: red;
        }

        .center-justified {
            text-align: justify;
            margin: 0 auto;
            width: 60em;
            font-size: small;
        }

        body {
            /* 	font-family: 'Ubuntu', sans-serif; */
            /*font-weight: bold;*/
        }
        .select2-container {
            min-width: 800px;
        }

        .select2-results__option {
            padding-right: 20px;
            vertical-align: middle;
        }
        .select2-results__option:before {
            content: "";
            display: inline-block;
            position: relative;
            height: 20px;
            width: 20px;
            border: 2px solid #e9e9e9;
            border-radius: 4px;
            background-color: #fff;
            margin-right: 20px;
            vertical-align: middle;
        }
        .select2-results__option[aria-selected=true]:before {
            font-family:fontAwesome;
            content: "\f00c";
            color: #fff;
            background-color: red;
            border: 0;
            display: inline-block;
            padding-left: 3px;
        }


        .select2-container--default .select2-results__option[aria-selected=true] {
            background-color: #fff;
        }
        .select2-container--default .select2-results__option--highlighted[aria-selected] {
            background-color: #eaeaeb;
            color: #272727;
        }
        .select2-container--default .select2-selection--multiple {
            margin-bottom: 10px;
        }
        .select2-container--default.select2-container--open.select2-container--below .select2-selection--multiple {
            border-radius: 4px;
        }

        .select2-container--default.select2-container--focus .select2-selection--multiple {
            border-color: red;
            border-width: 2px;
        }

        .select2-container--open .select2-dropdown--below {

            border-radius: 6px;
            box-shadow: 0 0 10px rgba(0,0,0,0.5);

        }
        .select2-selection .select2-selection--multiple:after {
            content: 'hhghgh';
        }

        /* select with icons badges single*/
        .select-icon .select2-selection__placeholder .badge {
            display: none;
        }

        .select-icon .placeholder {
            /* 	display: none; */
        }
        .select-icon .select2-results__option:before,
        .select-icon .select2-results__option[aria-selected=true]:before {
            display: none !important;
            /* content: "" !important; */
        }
        .select-icon  .select2-search--dropdown {
            display: none;
        }

        .ok{
            background:  green;
        }

        .right{
            background:  black;
        }

        .minus{
            background:  red;
        }

        .plus{
            background:  green;
        }


        .table-wrapper {
            position:relative;
        }
        .table-scroll {
            height:570px;
            overflow:auto;
            margin-top:5px;
        }
        .table-wrapper table {
            width:100%;


        }
        .table-wrapper table * {
        //background:yellow;
        //color:black;
        }
        .table-wrapper table thead th .text {
            position:absolute;
            top:-20px;
            z-index:2;
            height:20px;
            width:35%;
            border:1px solid red;
        }

        .front-sign-in {
            padding-top: 9px;
            padding-right: 9px;
            position: absolute;
            top: 0;
            right:0;
            width: inherit;
            -webkit-transition: -webkit-transform 0.6s;
            -moz-transition: -moz-transform 0.6s;
            -o-transition: -o-transform 0.6s;
            transition: transform 0.6s;
            display : inline-block;
            text-align: right;
            font-size: medium;
        }
        .front-sign-in-right {
            padding-top: 9px;
            padding-right: 9px;
            position: absolute;
            top: 0;
            right:0;
            width: inherit;
            -webkit-transition: -webkit-transform 0.6s;
            -moz-transition: -moz-transform 0.6s;
            -o-transition: -o-transform 0.6s;
            transition: transform 0.6s;
            display : inline-block;
            text-align: right;
            font-family: "Helvetica Neue", Helvetica, sans-serif;
            font-size: small;
        }
        .front-sign-in-left {
            padding-top: 9px;
            position: absolute;
            top: 0;
            /*right:0;*/
            width: inherit;
            -webkit-transition: -webkit-transform 0.6s;
            -moz-transition: -moz-transform 0.6s;
            -o-transition: -o-transform 0.6s;
            transition: transform 0.6s;
            display : inline-block;
            font-family: "Helvetica Neue", Helvetica, sans-serif;
            font-size: small;
        }
        #container {
            border: 1px solid black;

        }


    </style>
</head>
<body>

<div class="text">
    <img class="text" src="data:image/svg+xml;base64,PHN2ZyBpZD0ibC11LWciIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4IiB3aWR0aD0iMTg3LjdweCIgaGVpZ2h0PSI0NC44cHgiIHZpZXdCb3g9IjAgMCAxODcuNyA0NC44IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCAxODcuNyA0NC44OyIgeG1sOnNwYWNlPSJwcmVzZXJ2ZSI+DQo8c3R5bGUgdHlwZT0idGV4dC9jc3MiPg0KCS5zdDB7ZmlsbC1ydWxlOmV2ZW5vZGQ7Y2xpcC1ydWxlOmV2ZW5vZGQ7ZmlsbDojQ0MwMDAwO30NCgkuc3Qxe2ZpbGwtcnVsZTpldmVub2RkO2NsaXAtcnVsZTpldmVub2RkO2ZpbGw6Izk5OTk5OTt9DQo8L3N0eWxlPg0KPHBhdGggY2xhc3M9InN0MCIgZD0iTTQzLjYsMjcuNUwyMi4xLDlMMC42LDI3LjVDMC4yLDI1LjgsMCwyNC4yLDAsMjIuNEMwLDEwLDkuOSwwLDIyLjEsMGMxMi4yLDAsMjIuMSwxMCwyMi4xLDIyLjQgQzQ0LjIsMjQuMiw0NCwyNS44LDQzLjYsMjcuNSBNMTAxLDM1LjFjMy41LDAsNS4yLTAuMiw2LjQtMC41YzIuNC0wLjcsNC0yLjMsNC44LTQuOGMwLjMtMC44LDAuNC0xLjUsMC40LTIuMiBjMC0yLjMtMC45LTQuNC0yLjYtNS45Yy0xLjYtMS40LTMuNi0yLTYtMmgtNC4xYy0wLjUsMC4xLTEsMC4zLTEuMiwwLjdjLTAuMiwwLjQtMC40LDAuOC0wLjUsMS4zdjE5LjZoMi44VjM1LjEgTTEwOC4yLDIyLjggYzAuOCwxLjMsMS4zLDMuMSwxLjMsNC45YzAsMS43LTAuNCwzLjEtMS4zLDQuNGMtMSwxLjQtMi4zLDIuMS0zLjksMi4xbC0zLjMsMFYyMS4xYzAtMC4zLDAuMi0wLjUsMC41LTAuNWwyLjcsMCBDMTA1LjksMjAuNSwxMDcuMywyMS4zLDEwOC4yLDIyLjh6IE04OC4yLDM1LjVjMi4zLDAsNC4yLTAuNyw1LjctMi4yYzEuNS0xLjUsMi4zLTMuNCwyLjMtNS44YzAtMi4zLTAuOC00LjItMi4zLTUuNiBjLTEuNS0xLjUtMy40LTIuMi01LjYtMi4yYy0yLjMsMC00LjIsMC43LTUuNiwyLjJjLTEuNSwxLjUtMi4yLDMuNC0yLjIsNS43YzAsMi4zLDAuNyw0LjIsMi4zLDUuN0M4NC4xLDM0LjgsODYsMzUuNSw4OC4yLDM1LjUgTTkyLjMsMzIuMWMtMSwxLjctMi4zLDIuNi00LDIuNmMtMS43LDAtMy0wLjgtNC0yLjNDODMuNSwzMSw4MywyOS40LDgzLDI3LjZjMC0xLjksMC40LTMuNSwxLjItNC45YzEtMS42LDIuMy0yLjQsNC0yLjQgYzEuNiwwLDIuOSwwLjcsMy45LDIuMmMwLjksMS4zLDEuMywyLjksMS4zLDQuNkM5My41LDI5LjEsOTMuMSwzMC43LDkyLjMsMzIuMXogTTcyLjgsMjQuNmw1LjUtNWgtMS4ybC02LjcsNi4xdi02LjFoLTIuOHYxNS41IGwyLjgtMC4xdi04LjJsMC41LTAuNWw2LjksOC44aDMuMUw3Mi44LDI0LjZ6IE01Ni4yLDEzbC05LjgsMjIuMWgxbDIuOC02LjNoMTBsMi44LDYuM2gzTDU2LjIsMTN6IE01MC43LDI3LjlsNC42LTEwLjNsNC41LDEwLjMgSDUwLjd6IE0xMTQuNSwzNC4ybDUtMTIuM2MwLjEtMC4yLDAuMy0wLjQsMC41LDBjMS42LDQsMy4zLDguMyw0LjksMTIuM0gxMTQuNXogTTExMi4xLDMzLjljMCwwLjUsMC4xLDAuOCwwLjEsMWMwLDAuNiwwLDEtMC4xLDEuMiBoMC4yYzAuMy0wLjMsMC42LTAuNSwxLjEtMC43YzAuNC0wLjEsMC45LTAuMiwxLjUtMC4zYzAuNiwwLDEuNCwwLDIuNSwwaDUuOGMxLjEsMCwyLjYsMCwzLjIsMGMwLjUsMCwxLDAuMSwxLjQsMC4zIGMwLjQsMC4xLDAuNywwLjQsMC45LDAuN2gwLjJjMC0wLjMtMC4xLTAuNy0wLjEtMS4yYzAtMC40LDAtMC43LDAuMS0xaC0xbC01LjUtMTMuMWMtMC4xLTAuMy0wLjMtMC42LTAuNS0wLjggCWMtMC4yLTAuMi0wLjQtMC40LTAuNi0wLjVjLTAuNS0wLjItMC45LTAuMi0xLjQsMGMtMC4yLDAuMS0wLjMsMC4yLTAuNSwwLjRjLTAuMiwwLjItMC4zLDAuNC0wLjUsMC42bC01LjgsMTMuM0gxMTIuMXoiLz4NCjxwYXRoIGNsYXNzPSJzdDEiIGQ9Ik01LjQsMzcuMWwxNi43LTIzbDE2LjcsMjNjLTQuMSw0LjctMTAsNy43LTE2LjcsNy43QzE1LjQsNDQuOCw5LjQsNDEuOCw1LjQsMzcuMSBNMTcyLDE5LjZoLTIuOHY2LjRoLTguMXYtNi40IGgtMi44djE1LjVoMi44di04LjJoOC4xdjguMmgyLjhWMTkuNnogTTE1Mi4xLDIxTDE1Mi4xLDIxYy0wLjItMC40LTAuNC0wLjctMC42LTAuOWMtMC4yLTAuMi0wLjUtMC40LTAuNy0wLjVjLTAuNC0wLjEtMC43LTAuMS0xLjEsMGMtMC4yLDAuMS0wLjQsMC4yLTAuNiwwLjRjLTAuNCwwLjQtMC42LDAuOS0wLjgsMS41bC00LjUsMTMuNmgxLjFsMS4yLTMuN2g2LjhsMS4yLDMuN2gyLjdMMTUyLjEsMjF6ICBNMTQ2LjQsMzAuNGMxLTMsMi02LDMtOWMwLTAuMSwwLjItMC4zLDAuMywwbDIuOCw5SDE0Ni40eiBNMTMxLDIxLjdjMC4xLTAuNSwwLjItMC45LDAuNS0xLjNjMC4zLTAuNSwwLjgtMC43LDEuNC0wLjhsNy44LDB2MC45IGMtMi4yLDAtNC40LDAtNi42LDBjLTAuMiwwLTAuMywwLjEtMC4zLDAuNGwwLDUuNmMwLjIsMCwwLjYsMCwxLjIsMGMwLjUsMCwwLjksMCwxLjIsMGMyLDAsMy41LDAuMiw0LjUsMC43IGMxLjUsMC43LDIuMiwxLjksMi4yLDMuN2MwLDEuMy0wLjUsMi40LTEuNiwzLjJjLTEsMC43LTIuMSwxLjEtMy41LDEuMUgxMzFWMjEuOEMxMzEsMjEuOCwxMzEsMjEuNywxMzEsMjEuN0wxMzEsMjEuNyBNMTM2LjksMjcuNSBjMSwwLDEuOSwwLjMsMi42LDAuOWMwLjcsMC42LDEuMSwxLjQsMS4xLDIuNGMwLDEtMC40LDEuOC0xLjEsMi40Yy0wLjcsMC42LTEuNiwwLjktMi42LDAuOWgtM3YtNi42SDEzNi45eiBNMTc5LjcsMjQuNmw1LjUtNSBsLTEuMiwwbC02LjcsNnYtNi4xaC0yLjh2MTUuNWwyLjgtMC4xdi04LjJsMC41LTAuNWw2LjksOC44aDMuMUwxNzkuNywyNC42eiIvPg0KPC9zdmc+">
</div>

<div class="front-sign-in-right">
    <img style="width: 15px; height: 15px;" class="text" src="data:image/webp;base64,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">
    <strong>${pageContext.request.userPrincipal.name}</strong>
    <br>
    <sec:authorize access="isAuthenticated()">
        <a href="/logout">вийти</a>
    </sec:authorize>
</div>

<%--<select id="locality-dropdown" name="locality">
</select>--%>

<%--<h5 class="text">Created by Nazar Mykhailechko</h5>--%>

<div class="tabs">
    <input type="radio" name="inset" value="" id="tab_1" checked>
    <label for="tab_1"><strong>Звіт по менеджерах</strong></label>

    <input type="radio" name="inset" value="" id="tab_2">
    <label for="tab_2"><strong>Звіт по відділеннях</strong></label>

    <input type="radio" name="inset" value="" id="tab_3">
    <label for="tab_3"><strong>Робота з потенційними клієнтами</strong></label>



    <div id="txt_1">

    <select id="managerlist" name="locality">
<%--        <option selected="true" disabled="">Виберіть менеджера</option>
        <option name="Архіпов Антон Миколайович">Архіпов Антон Миколайович</option>
        <option name="Боровик-Гречана Анастасія Василівна">Боровик-Гречана Анастасія Василівна</option>
        <option name="Джерелюк Іван Анатолійович">Джерелюк Іван Анатолійович</option>
        <option name="Гузар Ольга Олександрівна">Гузар Ольга Олександрівна</option>
        <option name="Корнієцький Денис Валерійович">Корнієцький Денис Валерійович</option>
        <option name="Миронюк Віталій Володимирович">Миронюк Віталій Володимирович</option>
        <option name="Олангаєва Юлія Геннадіївна">Олангаєва Юлія Геннадіївна</option>
        <option name="Сурженко Світлана Василівна">Сурженко Світлана Василівна</option>
        <option name="Заїка Вікторія Іванівна">Заїка Вікторія Іванівна</option>--%>
    </select>

        <div class="table-wrapper">
            <div class="table-scroll">
                <table align="left" id="managercommisinfo" class="compact hover" style="width: 65%"></table>
            </div>
        </div>

    </div>
    <div id="txt_2">

        <select id="locality-dropdown" name="locality">
            <%--<option name="Головний Банк">Головний Банк</option>--%>
        </select>

        <div class="table-wrapper">
            <div class="table-scroll">
                <table align="left" id="branchinfo" class="compact hover" style="width: 65%"></table>
                <br>
                <table align="left" id="branchcommisinfo" class="compact hover" style="width: 65%"></table>
            </div>
        </div>

    </div>
    <div id="txt_3">

        <select id="managerlistresults" name="locality"></select>

        <div class="table-wrapper">
            <div class="table-scroll">
                <table align="left" id="clientslistbymanager" class="compact hover" style="width: 65%"></table>
                <div id="container" style="position:absolute; top:25px; right:20px; width:500px; height:500px;"></div>
            </div>
        </div>

    </div>

</div>

</body>
<script>
    $("#locality-dropdown").change(function () {
        var selectedvalue= this.value;

            console.log(selectedvalue)

        $('#branchinfo').DataTable({
                 pageLength : 200,
                 ajax : {
                    url : '${pageContext.request.contextPath}/api/clients/findallbankbrancheswithparam/' + selectedvalue,
                    dataSrc : ''
                },
                 searching: false,
                 info: false,
                 paging: false,
                 select: false,
                 destroy: true,
                 ordering: false,
                 columns : [{
                    title : 'Область',
                    data : 'nobl',
                    "width": "12%"
                }, {
                     title : 'Поштовий індекс',
                     data : 'pind',
                     "width": "10%"
                 },{
                     title : 'Адреса',
                     data : 'adress',
                     "width": "35%"
                 },{
                     title : 'Телефонний код',
                     data : 'kodt',
                     "width": "10%"
                 },{
                     title : 'Телефон',
                     data : 'telefon',
                     "width": "10%"
                 },{
                     title : 'Дата відкриття',
                     data : 'dopen',
                     "width": "10%"
                 }]
            });

        $('#branchcommisinfo').DataTable({
            pageLength : 200,
            ajax : {
                url : '${pageContext.request.contextPath}/api/clients/findallbranchcommisdata/' + selectedvalue,
                dataSrc : ''
            },
            searching: false,
            info: false,
            paging: false,
            select: false,
            destroy: true,
            ordering: false,
            columnDefs: [
                { targets: [1, 2, 3, 4], className: 'dt-body-right' },
                { targets: [0], className: 'dt-body-left' },
                { className: "dt-head-center", targets: [ 0, 1, 2, 3, 4 ]}],
            columns : [{
                title : 'Показник',
                data : 'featureukr',
                "render": function ( data, type, row, meta ) {
                    if (data.toString().indexOf("в т.ч.") !== -1) {
                        return '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + data;
                    }else{
                        return data;
                    }
                },
            },{
                title : 'Поточний місяць',
                data : 'currmonth',
                "render": function ( data, type, row, meta ) {
                    return parseFloat(data).toLocaleString();
                },
                "width": "12%"
            },{
                title : 'Попередній місяць',
                data : 'prevmonth',
                "render": function ( data, type, row, meta ) {
                    return parseFloat(data).toLocaleString();
                },
                "width": "12%"
            },{
                title : 'Приріст',
                data : 'delta',
                "render": function ( data, type, row, meta ) {
                    let color = 'green';
                    if (data < 0) {
                        color = 'red';
                    }
                    else if (data === 0) {
                        color = 'black';
                    }
                    else if (data > 0) {
                        color = 'green';
                    }
                    return '<span style="color:' + color + '">' + parseFloat(data).toLocaleString() + '</span>'
                },
                "width": "12%"
            },{
                title : 'Середнє за рік',
                data : 'averageperyear',
                "render": function ( data, type, row, meta ) {
                    return parseFloat(data).toLocaleString();
                },
                "width": "12%"
            }]
        });
    });
    $("#managerlist").change(function () {
        let selectedvalue= this.value;

        $('#managercommisinfo').DataTable({
            pageLength : 200,
            ajax : {
                url : '${pageContext.request.contextPath}/api/clients/findallmanagercommisdata/' + selectedvalue,
                dataSrc : ''
            },
            searching: false,
            info: false,
            paging: false,
            select: false,
            destroy: true,
            ordering: false,
            columnDefs: [
                         { targets: [1, 2, 3, 4], className: 'dt-body-right' },
                         { targets: [0], className: 'dt-body-left' },
                         { className: "dt-head-center", targets: [ 0, 1, 2, 3, 4 ]}],
            columns : [{
                title : 'Показник',
                data : 'featureukr',
                "render": function ( data, type, row, meta ) {
                    if (data.toString().indexOf("в т.ч.") !== -1) {
                        return '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + data;
                    }else{
                        return data;
                    }
                },
            },{
                title : 'Поточний місяць',
                data : 'currmonth',
                "render": function ( data, type, row, meta ) {
                    return parseFloat(data).toLocaleString();
                },
                "width": "12%"
            },{
                title : 'Попередній місяць',
                data : 'prevmonth',
                "render": function ( data, type, row, meta ) {
                    return parseFloat(data).toLocaleString();
                },
                "width": "12%"
            },{
                title : 'Приріст',
                data : 'delta',
                "render": function ( data, type, row, meta ) {
                    let color = 'green';
                    if (data < 0) {
                        color = 'red';
                    }
                    else if (data === 0) {
                        color = 'black';
                    }
                    else if (data > 0) {
                        color = 'green';
                    }
                    return '<span style="color:' + color + '">' + parseFloat(data).toLocaleString() + '</span>'
                },
                "width": "12%"
            },{
                title : 'Середнє за рік',
                data : 'averageperyear',
                "render": function ( data, type, row, meta ) {
                    return parseFloat(data).toLocaleString();
                },
                "width": "12%"
            }]
        });
    });

    window.addEventListener('DOMContentLoaded', function() {

        //init chart
        let chart = anychart.pie([{x: "пусто", value: 0}]);
        chart.labels().position("outside");
        let legend = chart.legend();
        legend.maxWidth("100%");
        legend.maxHeight("30%");
        legend.itemsLayout("verticalExpandable");
        legend.position("bottom");
        legend.paginator().orientation("bottom");
        chart.container("container");
        chart.draw();

        //keep chart updated to match dropdown
        const selectElem = document.querySelector('#managerlistresults');
        selectElem.addEventListener('change', event => {
            showChartFor(event.target.value);
        });
        //showChartFor(selectElem.value);

        function showChartFor(selectedvalue) {
            $.getJSON('${pageContext.request.contextPath}/api/clients/findallmanagerstatusforchart/' + selectedvalue, function(data) {

                chart.data(data);
                chart.container('container');
                chart.draw();
                })

            $('#clientslistbymanager').DataTable({
                pageLength : 200,
                ajax : {
                    url : '${pageContext.request.contextPath}/api/clients/findallmanagerbyname/' + selectedvalue,
                    dataSrc : ''
                },
                scrollx: true,
                fixedHeader: {
                    header: true,
                },
                paging: true,
                select: true,
                destroy: true,
                columns : [{
                    title : 'Змінити',
                    data : 'pr',
                    "render": function(data, type, row, meta){
                        if(type === 'display'){
                            data = '<a href="/mainform/updateclient/' + data + '">Змінити</a>';
                        }
                        return data;
                    }
                }, {
                    title : 'ЄДРПОУ',
                    data : 'pr',
                    "render": function(data, type, row, meta){
                        if(type === 'display'){
                            data = '<a href="/mainform/updateclient/' + data + '">' + data + '</a>';
                        }
                        return data;
                    }
                }, {
                    title : 'Назва клієнта',
                    data : 'client_name'
                }, {
                    title : 'Дата останнього контакту',
                    data : 'last_date_contact'
                }, {
                    title : 'Результат останнього контакту',
                    data : 'last_client_result'
                }, {
                    title : 'Історія взаємовідносин з клієнтом/коментарі',
                    data : 'client_history'
                }, {
                    title : 'Статус',
                    data : 'status'
                }, {
                    title : 'Коментарі',
                    data : 'comments'
                }
                ]
            });

            }
    });



 /*$("#managerlistresults").change(function () {
        let selectedvalue= this.value;

        $('#clientslistbymanager').DataTable({
            pageLength : 200,
            ajax : {
                url : '${pageContext.request.contextPath}/api/clients/findallmanagerbyname/' + selectedvalue,
                dataSrc : ''
            },
            scrollx: true,
            fixedHeader: {
                header: true,
            },
            paging: true,
            select: true,
            destroy: true,
            columns : [{
                title : 'Змінити',
                data : 'pr',
                "render": function(data, type, row, meta){
                    if(type === 'display'){
                        data = '<a href="/mainform/updateclient/' + data + '">Змінити</a>';
                    }
                    return data;
                }
            }, {
                title : 'ЄДРПОУ',
                data : 'pr',
                "render": function(data, type, row, meta){
                    if(type === 'display'){
                        data = '<a href="/mainform/updateclient/' + data + '">' + data + '</a>';
                    }
                    return data;
                }
            }, {
                title : 'Назва клієнта',
                data : 'client_name'
            }, {
                title : 'Дата останнього контакту',
                data : 'last_date_contact'
            }, {
                title : 'Результат останнього контакту',
                data : 'last_client_result'
            }, {
                title : 'Історія взаємовідносин з клієнтом/коментарі',
                data : 'client_history'
            }, {
                title : 'Статус',
                data : 'status'
            }, {
                title : 'Коментарі',
                data : 'comments'
            }
            ]
        });

    });
*/
</script>

</html>