<%@ page import="org.example.staffmaster2.entity.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Résumé des Allocations Familiales</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="dark-background">
<div class="container">
    <h2>Résumé des Allocations Familiales</h2>

    <div class="info-group">
        <p><strong>Nom de l'employé :</strong> <%= ((Employee) request.getAttribute("employee")).getName() %></p>
        <p><strong>Salaire :</strong> <%= ((Employee) request.getAttribute("employee")).getSalaire() %> DH</p>
        <p><strong>Nombre d'enfants à charge :</strong> <%= ((Employee) request.getAttribute("employee")).getNumChilds() %></p>
    </div>

    <h3>Allocations familiales :</h3>
    <p class="info-highlight"><strong>Montant total des allocations :</strong> <%= request.getAttribute("allocations") %> DH</p>
</div>
</body>
</html>
