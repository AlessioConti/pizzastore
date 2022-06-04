<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html class="h-100">
<body>
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Modifica Pizza</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
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
				        <h5>Modifica Pizza esistente</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form method="post" action="ExecuteUpdatePizzaServlet" class="row g-3" novalidate="novalidate">
						
							
								<div class="col-md-6">
									<label for="descrizione" class="form-label">Descrizione <span class="text-danger">*</span></label>
									<input type="text" name="descrizione" id="descrizione" class="form-control" placeholder="Inserire la descrizione"  
										value="${update_pizza_attr.descrizione }" required>
								</div>
								
								<div class="col-md-6">
									<label for="ingredienti" class="form-label">Ingredienti <span class="text-danger">*</span></label>
									<input type="text" name="ingredienti" id="ingredienti" class="form-control" placeholder="Inserire gli ingredienti"  
										value="${update_pizza_attr.ingredienti }" required>
								</div>
							
								<div class="col-md-6">
									<label for="prezzoBase" class="form-label">Prezzo Base: <span class="text-danger">*</span></label>
									<input type="number" class="form-control" name="prezzoBase" id="prezzoBase" placeholder="Inserire il prezzo" 
									value="${update_pizza_attr.prezzoBase }" required>
								</div>

								<input type="hidden" name="idPizza" value="${update_pizza_attr.id }"> 
								
								<div class="col-12">
									<button type="submit" name="updateSubmit" value="updateSubmit" id="updateSubmit" class="btn btn-primary">Modifica</button>
								</div>
		
						</form>
  
				    
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</body>
</html>