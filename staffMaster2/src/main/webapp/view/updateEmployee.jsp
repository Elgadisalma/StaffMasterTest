<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Update Employee</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="dark-background">
<div class="container">
  <h2>Update Employee</h2>
  <form action="employee?action=update" method="post" class="form">
    <input type="hidden" name="id" value="${employee.id}">

    <div class="form-group">
      <label for="name">Name:</label>
      <input type="text" name="name" id="name" class="input-field" value="${employee.name}" required>
    </div>

    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" name="email" id="email" class="input-field" value="${employee.email}" required>
    </div>

    <div class="form-group">
      <label for="password">Password:</label>
      <input type="password" name="password" id="password" class="input-field" value="${employee.password}" required>
    </div>

    <input type="hidden" name="role" value="employee">

    <div class="form-group">
      <label for="cnss">CNSS:</label>
      <input type="text" name="cnss" id="cnss" class="input-field" value="${employee.cnss}">
    </div>

    <div class="form-group">
      <label for="salaire">Salaire:</label>
      <input type="number" name="salaire" id="salaire" class="input-field" step="0.01" value="${employee.salaire}" required>
    </div>

    <div class="form-group">
      <label for="numChilds">Number of Children:</label>
      <input type="number" name="numChilds" id="numChilds" class="input-field" value="${employee.numChilds}">
    </div>

    <div class="form-group">
      <label for="soldeConge">Solde Congé:</label>
      <input type="number" name="soldeConge" id="soldeConge" class="input-field" value="${employee.soldeConge}">
    </div>

    <div class="form-group">
      <label for="departement">Departement:</label>
      <input type="text" name="departement" id="departement" class="input-field" value="${employee.departement}">
    </div>

    <div class="form-group">
      <label for="poste">Poste:</label>
      <input type="text" name="poste" id="poste" class="input-field" value="${employee.poste}">
    </div>

    <div class="form-group">
      <label for="birthday">Birthday:</label>
      <input type="date" name="birthday" id="birthday" class="input-field" value="${employee.birthday}" required>
    </div>

    <div class="form-group">
      <label for="dateEmbauche">Date Embauche:</label>
      <input type="date" name="dateEmbauche" id="dateEmbauche" class="input-field" value="${employee.dateEmbauche}" required>
    </div>

    <button type="submit" class="submit-btn">Update Employee</button>
  </form>
</div>
</body>
</html>
