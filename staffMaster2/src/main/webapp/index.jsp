<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>JSP - Menu Principal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css">
</head>
<body>
<div class="container">
    <h2>Menu Principal</h2>

    <h3>Gestion des Employés</h3>
    <form action="employee" method="get" class="form-search">
        <select name="action" class="input-field">
            <option value="listEmployees">Afficher les employés</option>
            <option value="ajoutEmployee">Ajouter un employé</option>
        </select>
        <button type="submit" class="btn-submit">Exécuter</button>
    </form>

    <h3>Gestion des Offres</h3>
    <form action="offre" method="get" class="form-search">
        <select name="action" class="input-field">
            <option value="ajoutOffre">Ajouter une offre</option>
            <option value="listOffres">Afficher les offres</option>
        </select>
        <button type="submit" class="btn-submit">Exécuter</button>
    </form>

    <h3>Candidatures</h3>
    <form action="condidature" method="get" class="form-search">
        <input type="hidden" name="action" value="listCandidature">
        <button type="submit" class="btn-submit">Voir les candidatures</button>
    </form>

    <%-- Option de test email commentée --%>
    <%--<form method="get" action="testEmail">--%>
    <%--    <button type="submit">Test Email</button>--%>
    <%--</form>--%>

    <h3>Gestion des Conges</h3>
    <form action="conge" method="get" class="form-search">
        <select name="action" class="input-field">
            <option value="ajoutConge">Ajouter une conge</option>
            <option value="listConges">Afficher les conges</option>
        </select>
        <button type="submit" class="btn-submit">Exécuter</button>
    </form>
</div>
</body>
</html>
