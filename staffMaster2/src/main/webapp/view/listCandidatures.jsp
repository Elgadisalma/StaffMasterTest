<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Liste des Candidatures</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css">
</head>
<body>
<div class="container">
    <h2>Liste des Candidatures</h2>

    <form method="get" action="condidature" class="form-search">
        <input type="hidden" name="action" value="listCandidature">
        <label for="competance" class="form-label">Rechercher par competence :</label>
        <input type="text" id="competance" name="competance" placeholder="Competence" value="${param.competance}" class="input-field">
        <input type="submit" value="Rechercher" class="btn-submit">
    </form>


    <table class="table">
        <thead>
        <tr>
            <th>Email</th>
            <th>Competence</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="candidature" items="${candidatures}">
            <tr>
                <td>${candidature.email}</td>
                <td>${candidature.competance}</td>
                <td>
                    <a href="condidature?action=confirm&id=${candidature.condidature_id}" class="btn-edit">Confirmer</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
