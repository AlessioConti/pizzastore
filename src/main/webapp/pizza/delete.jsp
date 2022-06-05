<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	
	   <title>Rimuovi Pizza</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Visualizza dettaglio Pizza</h5>
					    </div>
					    
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Id:</dt>
							  <dd class="col-sm-9">${delete_pizza_attr.id}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Descrizione:</dt>
							  <dd class="col-sm-9">${delete_pizza_attr.descrizione}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Ingredienti:</dt>
							  <dd class="col-sm-9">${delete_pizza_attr.ingredienti}</dd>
					    	</dl>
					  
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Prezzo Base:</dt>
							  <dd class="col-sm-9">${delete_pizza_attr.prezzoBase}</dd>
					    	</dl>
					    	
							</div>
							<!-- end info Regista -->
					    	
					    </div>
					    <!-- end card body -->
					    
					    <div class='card-footer'>
					    	<form action="ExecuteDeletePizzaServlet" method="post">
					    		<input type="hidden" name="idPizza" value="${delete_pizza_attr.id}">
						    	<button type="submit" name="submit" id="submit" class="btn btn-danger">Elimina</button>
						        <a href="ExecuteListPizzeServlet" class='btn btn-outline-secondary' style='width:80px'>
						            <i class='fa fa-chevron-left'></i> Torna Indietro
						        </a>
					        </form>
					    </div>
					<!-- end card -->
					</div>	
			  
			    
			  <!-- end container -->  
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>