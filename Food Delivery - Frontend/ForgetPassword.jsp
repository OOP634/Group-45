<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<br>
 <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		
		<li class="breadcrumb-item active" aria-current="page">Forget Password</li>
	</ol>
	</nav>
	
<div col-md-5img-thumbnail">
		<div id="feedback">
			<div class="container">
				<div class="col-md-5">
					<div class="form-area">
						<sf:form method="post"
							action="${pageContext.request.contextPath}/forgetPassword" modelAttribute="form"
							>
							<h3 style="margin-bottom: 15px; text-align: left: ;">Forget Password</h3>
								<b><%@ include file="businessMessage.jsp"%></b>
							<div class="form-group">
								<s:bind path="email">
									<label>Login Id:</label>
									<sf:input path="${status.expression}" placeholder="Enter Email Id"
										class="form-control" />
									<font color="red" style="font-size: 13px"><sf:errors
											path="${status.expression}" /></font>
								</s:bind>
							</div>
							
							<input type="submit" name="operation" class="btn btn-primary pull-right"
								value="Go">
						</sf:form>
					</div>
				</div>
			</div>
		</div>
		<!--feedback-->
		<br>
		
