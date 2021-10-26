<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<div class="container">
	<br>
	<nav style="-bs-breadcrumb-divider: '&gt;';" aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#">Home</a></li>
			<li class="breadcrumb-item active" aria-current="page">Check Out</li>
		</ol>
	</nav>
	<br>

	<sf:form method="post"
		action="${pageContext.request.contextPath}/ctl/order"
		modelAttribute="form">

		<div class="card">
			<h5 class="card-header"
				style="background-color: #18334f; color: white;">Check Out</h5>
			<div class="card-body">
				<div class="row g-3">
					<div class="col-md-5 col-lg-4 order-md-last">
						<h4 class="d-flex justify-content-between align-items-center mb-3">
							<span class="text-muted">Item Detail</span>
						</h4>

						<table class="table table-bordered border-primary">
							<thead>
								<tr>

									<th scope="col">Image</th>
									<th scope="col">Name</th>
									<th scope="col">Price</th>
									<th scope="col">Quantity</th>
									<th scope="col">Total</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="totalp" value="${0}" />
								<c:forEach items="${cList}" var="ct" varStatus="cart">
									<tr>
										<td scope="row"><img
											src="<c:url value="/ctl/product/getImage/${ct.product.id}" />"
											width="100px" height="100px"></td>
										<td scope="row">${ct.product.name}</td>
										<td scope="row">${ct.product.price}</td>
										<td scope="row">${ct.quantity}</td>
										<td scope="row">${ct.totalPrice}</td>
									</tr>
									<c:set var="totalp" value="${totalp + ct.totalPrice}" />
								</c:forEach>
								<tr>
									<td colspan="4">Total</td>
									<td colspan="3">${totalp}</td>
								</tr>

							</tbody>
						</table>

					</div>

					<div class="col-md-7 col-lg-8">
						<h4 class="mb-3">Billing address</h4>
						<div class="row g-3">
							<div class="col-sm-6">
								<s:bind path="name">
									<label for="firstName" class="form-label">Name</label>
									<sf:input  class="form-control" 
										path="${status.expression}" placeholder="Enter Name" required="required" />
								</s:bind>
							</div>

							<div class="col-sm-6">
							<s:bind path="mobileNo">
									<label for="firstName" class="form-label">Mobile No</label>
									<sf:input  class="form-control" id="firstName"
										path="${status.expression}" placeholder="Enter Mobile No"  required="required" />
								</s:bind>

							</div>


							<div class="col-12">
								<s:bind path="email">
									<label for="firstName" class="form-label">Email</label>
									<sf:input  class="form-control" id="firstName"
										path="${status.expression}" placeholder="Enter Email"  required="required" />
								</s:bind>

							</div>

							<div class="col-md-5">
								<s:bind path="state">
									<label for="firstName" class="form-label">State</label>
									<sf:input  class="form-control" id="firstName"
										path="${status.expression}" placeholder="Enter State"  required="required" />
								</s:bind>
							</div>

							<div class="col-md-4">
								<s:bind path="city">
									<label for="firstName" class="form-label">City</label>
									<sf:input  class="form-control" id="firstName"
										path="${status.expression}" placeholder="Enter City"  required="required" />
								</s:bind>
							</div>


							<div class="col-12">
							<s:bind path="address1">
								<label for="address" class="form-label">Address</label>
								<sf:textarea  class="form-control" id="firstName"
										path="${status.expression}" placeholder="Enter address line 1 " rows="4" cols="4" required="required" />
							</s:bind>
							</div>

							<div class="col-12">
									<s:bind path="address2">
								<label for="address" class="form-label">Address 2</label>
								<sf:textarea  class="form-control" id="firstName"
										path="${status.expression}" placeholder="Enter address line 2 " rows="4" cols="4" required="required" />
							</s:bind>
							</div>


						</div>

						<hr class="my-4">

						<input type="submit" name="operation" value="Payment"
							class="btn btn-primary btn btn-info" />


					</div>


				</div>

			</div>

		</div>
	</sf:form>
</div>

