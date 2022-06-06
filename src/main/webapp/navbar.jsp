<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<header>
  <!-- Fixed navbar -->
 <nav class="navbar navbar-expand-lg navbar-dark bg-primary" aria-label="Eighth navbar example">
    <div class="container">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample07" aria-controls="navbarsExample07" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

<div class="collapse navbar-collapse" id="navbarsExample07">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/home">Home</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="dropdown07" data-bs-toggle="dropdown" aria-expanded="false">Dropdown</a>
            <ul class="dropdown-menu" aria-labelledby="dropdown07">
            	<c:if test="${userInfo.isAdmin() }">
                  <li><a class="dropdown-item" href="${pageContext.request.contextPath}/PrepareSearchClienteServlet">Ricerca Cliente</a></li>
                  <li><a class="dropdown-item" href="${pageContext.request.contextPath}/PrepareInsertClienteServlet">Inserisci Cliente</a></li>
    			</c:if>
    			<c:if test="${userInfo.isPizzaiolo() }">
                  <li><a class="dropdown-item" href="${pageContext.request.contextPath}/PrepareSearchPizzaServlet">Ricerca Pizza</a></li>
                  <li><a class="dropdown-item" href="${pageContext.request.contextPath}/PrepareInsertPizzaServlet">Inserisci Pizza</a></li>
                  <li><a class="dropdown-item" href="${pageContext.request.contextPath}/PrepareSearchOrdineServlet">Ricerca Ordine</a></li>
                  <li><a class="dropdown-item" href="${pageContext.request.contextPath}/PrepareInsertOrdineServlet">Inserisci Ordine</a></li>    			
                </c:if>
                <c:if test="${userInfo.isFattorino() }">
	              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/ExecuteListFattorinoServlet?idUser=${userInfo.id }">Visualizza Ordini Aperti</a></li>
    			</c:if>
            </ul> 
          </li>   
        </ul>
      </div>
      <div class="col-md-3 text-end">
        <p class="navbar-text">Utente: ${userInfo.username }(${userInfo.nome } ${userInfo.cognome })
     <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a></p>
      </div>
    </div>
  </nav>

  
  
</header>