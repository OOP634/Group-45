<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>

<style type="text/css">
body {
	background: grey;
	margin-top: 120px;
	margin-bottom: 120px;
}
</style>
<div class="container">
	<br>


	<br>

	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="card">
					<div class="card-body">
						<div class="row" style="margin: 5px">
							<div class="col-md-6 text-right">
								<p class="font-weight-bold mb-1">Invoice #
									${orderDetail.orderid}</p>
								<p class="text-muted"><%=new Date()%></p>
							</div>
						</div>
						<hr class="my-5">
						<div class="row">
							<div class="col-md-6">
								<p class="font-weight-bold mb-4">Customer Information</p>
								<p class="mb-1">${orderDetail.name}</p>
								<p>${orderDetail.email}</p>
								<p class="mb-1">${orderDetail.state},${orderDetail.city},${orderDetail.address1}</p>
								<p class="mb-1">${orderDetail.address2}</p>
							</div>
						</div>
						<hr class="my-5">
						<div class="row ">
							<div class="col-md-12">
								<table class="table">
									<thead>
										<tr>
											<th class="border-0 text-uppercase small font-weight-bold">#</th>
											<th class="border-0 text-uppercase small font-weight-bold">Item</th>
											<th class="border-0 text-uppercase small font-weight-bold">Quantity</th>
											<th class="border-0 text-uppercase small font-weight-bold">UnitCost</th>
											<th class="border-0 text-uppercase small font-weight-bold">Total</th>
										</tr>
									</thead>
									<tbody>
										<c:set var="totalp" value="${0}" />
										<c:forEach items="${cartList}" var="ct" varStatus="cart">
											<tr>
												<td scope="row">${cart.index+1}</td>
												<td scope="row">${ct.product.name}</td>
												<td scope="row">${ct.quantity}</td>
												<td scope="row">${ct.price}</td>
												<td scope="row">${ct.totalPrice}</td>
											</tr>
											<c:set var="totalp" value="${totalp + ct.totalPrice}" />
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>

						<div class="d-flex flex-row-reverse bg-dark text-white p-4">
							<div class="py-3 px-5 text-right">
								<div class="mb-2">Grand Total</div>
								<div class="h2 font-weight-light">${totalp }</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>
<br>
