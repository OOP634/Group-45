<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="#">Food Delivery</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link" href="<c:url value="/welcome"/>">Home
				</a></li>
				<c:if test="${sessionScope.user == null}">
				
				<li class="nav-item"><a class="nav-link"
						href="<c:url value="/ctl/product/user/search"/>">Product</a></li>
				
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/login"/>">Login</a></li>

					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/signUp"/>">SignUp</a></li>

					<li class="nav-item"><a class="nav-link" href="<c:url value="/aboutUs"/>">About</a></li>
					<li class="nav-item"><a class="nav-link" href="<c:url value="/contactUs"/>">Contact</a>
					</li>
				</c:if>

				<c:if test="${sessionScope.user != null}">

					<c:if test="${sessionScope.user.roleId == 1}">
					
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="dropdown01"
							data-bs-toggle="dropdown" aria-expanded="false">Category</a>
							<ul class="dropdown-menu" aria-labelledby="dropdown01">
								<li><a class="dropdown-item"
									href="<c:url value="/ctl/category"/>">Add Category</a></li>
								<li><a class="dropdown-item"
									href="<c:url value="/ctl/category/search"/>">Category
										Report</a></li>

							</ul></li>
							
							<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="dropdown01"
							data-bs-toggle="dropdown" aria-expanded="false">Product</a>
							<ul class="dropdown-menu" aria-labelledby="dropdown01">
								<li><a class="dropdown-item"
									href="<c:url value="/ctl/product"/>">Add Product</a></li>
								<li><a class="dropdown-item"
									href="<c:url value="/ctl/product/search"/>">Product
										Report</a></li>

							</ul></li>
							
					</c:if>

					<c:if test="${sessionScope.user.roleId == 2}">
						<li class="nav-item"><a class="nav-link"
						href="<c:url value="/ctl/product/user/search"/>">Product</a></li>
						
						<li class="nav-item"><a class="nav-link"
						href="<c:url value="/ctl/cart/search"/>">Cart</a></li>
						
						<li class="nav-item"><a class="nav-link"
						href="<c:url value="/ctl/order/search"/>">Orders</a></li>
					</c:if>


				</c:if>


				<c:if test="${sessionScope.user != null}">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/login"/>">Logout</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>
