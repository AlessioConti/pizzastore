<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>
	<head>
	
		<jsp:include page="../header.jsp"/>
		
		<title>Modifica Ordine</title>
	
	</head>
	<body>
		<jsp:include page="../navbar.jsp"></jsp:include>
		
		<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
					  Esempio di operazione fallita!
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
					  Aggiungere d-none nelle class per non far apparire
					   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Modifica Ordine</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form method="post" action="ExecuteUpdateOrdineServlet" class="row g-3" novalidate="novalidate">
							
							
								<div class="col-md-6">
									<label for="codice" class="form-label">Codice</label>
									<input type="text" name="codice" id="codice" class="form-control" placeholder="Inserire il codice" value="${update_ordine_attr.codice }">
								</div>
								
								<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${update_ordine_attr.data}' />
								<div class="col-md-6">
									<label for="data" class="form-label">Data di Consegna</label>
	                        		<input class="form-control" id="data" type="date" placeholder="dd/MM/yy" 
	                        				title="formato : gg/mm/aaaa"  name="data" value="${parsedDate}" >
								</div>
								
								<div class="col-md-6">
									<label for="cliente.id">Cliente</label>
								    <select class="form-select" id="cliente.id" name="cliente.id">
								    	<option value="" selected> -- Selezionare una voce -- </option>
								      	<c:forEach items="${clienti_list_attribute }" var="clienteItem">
								      		<option value="${clienteItem.id}" ${insert_ordine_attr.cliente.id == clienteItem.id?'selected':''} >${clienteItem.nome } ${clienteItem.cognome }</option>
								      	</c:forEach>
								    </select>
								</div>
								
								<div class="col-md-6">
									<label for="utente.id">Utente</label>
								    <select class="form-select" id="utente.id" name="utente.id">
								    	<option value="" selected> -- Selezionare una voce -- </option>
								      	<c:forEach items="${utenti_list_attribute }" var="utenteItem">
								      		<option value="${utenteItem.id}" ${insert_ordine_attr.utente.id == utenteItem.id?'selected':''} >${utenteItem.nome } ${utenteItem.cognome }</option>
								      	</c:forEach>
								    </select>
								</div>
								
								<c:forEach items="${pizze_list_attribute}" var="pizzaItem">
									<div class="form-check form-check-inline">
											  <input class="form-check-input" type="checkbox" value="${pizzaItem.id}"
											  		 id="pizza.id" name="pizza.id">
											  <label class="form-check-label" for="pizza.id">
											    ${pizzaItem.descrizione}
											  </label>
									</div>
								</c:forEach>
								
								<input type="hidden" name="idOrdine" value="${update_ordine_attr.id}">
								
								<div class="col-12">
									<button type="submit" name="insertSubmit" value="insertSubmit" id="insertSubmit" class="btn btn-primary">Modifica</button>
								</div>
		
								</form>
						</div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
		
		<jsp:include page="../footer.jsp"/>
	</body>
</html>