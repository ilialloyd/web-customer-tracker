<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- To use spring form tags, we import library first -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Save Customer</title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css"> <!-- reference to css file -->


    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/add-customer-style.css"> <!-- reference to css file -->


</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2> CRM - Customer Relationship Manager</h2>
    </div>
</div>


<div id="container">
    <h3> Save Customer</h3>
    <%--         method            modelAttribute from the /showFormForAdd method      --%>
    <form:form action="saveCustomer" modelAttribute="customer" method="POST">

        <!-- Associate this data with customer id
        IMPORTANT: this line very important, because it helps us to track id of customers when we update-->

        <form:hidden path="id" />


        <table>
            <tbody>
            <tr>
                <td><label>First Name</label></td>
                <td><form:input path="firstName"/></td>
            </tr>
            <tr>
                <td><label>Last Name</label></td>
                <td><form:input path="lastName"/></td>
            </tr>
            <tr>
                <td><label>Email</label></td>
                <td><form:input path="email"/></td>
            </tr>
            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save"/></td>
            </tr>

            </tbody>


        </table>

    </form:form>
              <!-- Add a navigating Link
              Giving option to navigate back to the customer list-->

    <div style="clear: both"></div>
<p>
    <a href="${pageContext.request.contextPath}/customer/list">Back to List</a>
</p>
</div>


</body>
</html>
