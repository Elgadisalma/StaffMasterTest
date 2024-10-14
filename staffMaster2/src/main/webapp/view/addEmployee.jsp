<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Ajouter un Employe</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="dark-background">
<div class="container">

  <form action="employee?action=add" method="post" class="form">
    <h2>Ajouter un Employe</h2>

    <div class="form-group">
      <label for="name">Nom:</label>
      <input type="text" name="name" id="name" class="input-field" required>
    </div>
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" name="email" id="email" class="input-field" required>
    </div>
    <div class="form-group">
      <label for="password">Password:</label>
      <input type="password" name="password" id="password" class="input-field" required>
    </div>
    <div class="form-group">
      <label for="cnss">CNSS:</label>
      <input type="text" name="cnss" id="cnss" class="input-field">
    </div>
    <div class="form-group">
      <label for="salaire">Salaire:</label>
      <input type="number" name="salaire" id="salaire" class="input-field" step="0.01" required>
    </div>
    <div class="form-group">
      <label for="numChilds">Number of Children:</label>
      <input type="number" name="numChilds" id="numChilds" class="input-field">
    </div>
    <div class="form-group">
      <label for="soldeConge">Solde Congé:</label>
      <input type="number" name="soldeConge" id="soldeConge" class="input-field">
    </div>
    <div class="form-group">
      <label for="departement">Departement:</label>
      <input type="text" name="departement" id="departement" class="input-field">
    </div>
    <div class="form-group">
      <label for="poste">Poste:</label>
      <input type="text" name="poste" id="poste" class="input-field">
    </div>
    <div class="form-group">
      <label for="birthday">Birthday:</label>
      <input type="date" name="birthday" id="birthday" class="input-field" required>
    </div>
    <div class="form-group">
      <label for="dateEmbauche">Date Embauche:</label>
      <input type="date" name="dateEmbauche" id="dateEmbauche" class="input-field" required>
    </div>
    <input type="submit" value="Ajouter" class="submit-btn">
  </form>
</div>
</body>
</html>
