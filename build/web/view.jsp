<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : view
    Created on : 23.05.2020, 19:48:06
    Author     : markus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Practise JAXB Web</title>
        <link rel="stylesheet" href="stylesheet.css">
    </head>
    <body>
        <h1>Practise JAXB Web</h1>

        <form method="GET">

            <select id="countrySelect" name="countrySelect" size="10" onchange="loadReps()">

                <c:forEach var="country" items="${countries}">
                    <option>${country.name}</option>
                </c:forEach>

            </select>

            <select id="repSelect" name="repSelect" size="10" onchange="loadCustomers()">

            </select>

            <select id="customerSelect" name="customerSelect" size="10">

            </select>

        </form>
        
        <br>
        <br>

        <input type="button" id="addRep" value="AddRepresentative" name="addRep" onclick="addRepresetative();"/>



        <script type="text/javascript" src="FetchData.js"></script>
        <script>

            var fd = new FetchData();

            function loadReps() {
                fd.getRepresentatives();
            }

            function loadCustomers() {
                fd.getCustomers();
            }


            function addRepresetative() {
                fd.addRepresentative();
            }



        </script>

    </body>
</html>
