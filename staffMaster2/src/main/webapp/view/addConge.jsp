<%--
  Created by IntelliJ IDEA.
  User: ycode
  Date: 14/10/2024
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Demander un conge</h2>
<form action="conge?action=add" method="post" class="form">
    <div class="form-group">
        <label for="motif">Motif:</label>
        <input type="text" name="motif" id="motif" class="input-field" required>
    </div>
    <div class="form-group">
        <label for="description">Description:</label>
        <input type="text" name="description" id="description" class="input-field" required>
    </div>
    <div class="form-group">
        <label for="dateDebutConge">Date de conge:</label>
        <input type="date" name="dateDebutConge" id="dateDebutConge" class="input-field" required>
    </div>
    <div class="form-group">
        <label for="dateFinConge">Date de conge:</label>
        <input type="date" name="dateFinConge" id="dateFinConge" class="input-field" required>
    </div>

    <button type="submit" class="submit-btn">Ajouter l'Offre</button>
</form>

</body>
</html>
