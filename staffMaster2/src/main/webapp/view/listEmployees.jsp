<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>staffMaster - Liste des Employés</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/listE.css">
</head>
<body>
<div class="container">
    <h2>Liste des Employes</h2>

    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Email</th>
            <th>CNSS</th>
            <th>Embauche</th>
            <th>Salaire</th>
            <th>Enfants</th>
            <th>Conges</th>
            <th>Departement</th>
            <th>Poste</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>${employee.id}</td>
                <td>${employee.name}</td>
                <td>${employee.email}</td>
                <td>${employee.cnss}</td>
                <td>${employee.dateEmbauche}</td>
                <td>${employee.salaire}</td>
                <td>${employee.numChilds}</td>
                <td>${employee.soldeConge}</td>
                <td>${employee.departement}</td>
                <td>${employee.poste}</td>
                <td>
                    <div class="df">
                        <a href="employee?action=edit&id=${employee.id}" class="btn-edit">Modifier</a>
                        <a href="employee?action=delete&id=${employee.id}" class="btn-delete" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet employe ?');">Supprimer</a>
                        <a href="employee?action=calcul&id=${employee.id}" class="btn-edit">Calcul des Allocations</a>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
