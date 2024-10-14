<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Postuler à l'Offre</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="dark-background">
<div class="container">
    <h2>Postuler à l'Offre : ${offre.title}</h2>
    <form action="condidature?action=add" method="post" class="form">
        <input type="hidden" name="offreId" value="${offre.offre_id}">
        <div class="form-group">
            <label for="email">Email :</label>
            <input type="email" id="email" name="email" class="input-field" required>
        </div>
        <div class="form-group">
            <label for="competance">Compétence (Mot clé) :</label>
            <input type="text" id="competance" name="competance" class="input-field" required>
        </div>
        <button type="submit" class="submit-btn">Soumettre la candidature</button>
    </form>
</div>
</body>
</html>
