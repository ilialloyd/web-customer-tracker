<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer list</title>
    <%-- referance our style sheet   --%>
    <link type="text/css"
          rel="stylesheet"
    <%--          this gives proper name of app  ------   link to our css file  --%>
          href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>


<body>

<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Manager </h2>
    </div>
</div>

<div id="container">
    <div id="content">

        <!-- Add Customer Button -->
        <%--  also adding css style  button --%>
        <input type="button" value="Add Customer"
               onclick="window.location.href='showFormForAdd'; return false;"
               class="add-button"
        />



               <!-- Search Box -->
        <form:form action="search" method="GET">
            Search customer: <input type="text" name="theSearchName" />


            <input type="submit" value="Search" class="add-button"
            />

        </form:form>






    <%--           html table headers     --%>
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Action</th>

            </tr>

            <!--Loop over and print customers -->
            <c:forEach var="tempCustomer" items="${customers}">


                <!-- adding an "update" link  for every customer on the list -->
                <c:url var="updateLink" value="/customer/showFormForUpdate">

                    <!-- Embedding customer id here, so, when we update customer We can update by its id-->
                    <c:param name="customerId" value="${tempCustomer.id}"/>

                </c:url>

                <!--adding DELETE function to the page -->
                <c:url var="deleteLink" value="/customer/delete">

                    <!-- Embedding customer id here, so, when we update customer We can update by its id-->
                    <c:param name="customerId" value="${tempCustomer.id}"/>

                </c:url>
                <tr>
                    <td>${tempCustomer.firstName} </td>
                    <td>${tempCustomer.lastName} </td>
                    <td>${tempCustomer.email} </td>

                    <td>
                        <!-- Display the update link   //  this will be "Update" word with embed update link-->
                        <a href="${updateLink}">Update</a>
                        |  <!--this line is the line between update and delete -->
                        <a href="${deleteLink}"
                     <%--  we also add JavaScript code here to pop up |
                     confirm(...) displays a confirmation popup dialog--%>
                         onclick="if(!(confirm('Are you sure you want to delete this customer'))) return false"
                        >Delete</a>

                    </td>

                </tr>

            </c:forEach>

        </table>
    </div>
</div>

</body>
</html>