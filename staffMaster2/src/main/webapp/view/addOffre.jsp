<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un Offre</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="dark-background">
<div class="container">
    <h2>Ajouter une Offre</h2>
    <form action="offre?action=add" method="post" class="form">
        <div class="form-group">
            <label for="title">Titre:</label>
            <input type="text" name="title" id="title" class="input-field" required>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" name="description" id="description" class="input-field" required>
        </div>
        <div class="form-group">
            <label for="dateValidite">Date de validité:</label>
            <input type="date" name="dateValidite" id="dateValidite" class="input-field" required>
        </div>
        <div class="form-group">
            <label for="dateFin">Date de fin:</label>
            <input type="date" name="dateFin" id="dateFin" class="input-field" required>
        </div>
        <button type="submit" class="submit-btn">Ajouter l'Offre</button>
    </form>
</div>
</body>
</html>
