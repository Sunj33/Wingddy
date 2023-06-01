<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div id="app">
		<div>
			<jsp:include page="../sideBar/sideBar.jsp" />
			<div class="main-content">
				<br><br><br><br><br><br><br>
				<!-- Start Wishlist  -->
				<div class="wishlist-box-main">
					<div class="container">
						<div class="row">
							<div class="col-lg-12">
								<div class="table-main table-responsive">
									<table class="table">
										<thead>
											<tr>
												<th>상품이미지</th>
												<th>상품명</th>
												<th>가격 </th>
												<!-- <th>Stock</th> -->
												<th>장바구니 추가</th>
												<th>삭제</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td class="thumbnail-img">
													<a href="#">
												<img class="img-fluid" src="images/img-pro-01.jpg" alt="" />
											</a>
												</td>
												<td class="name-pr">
													<a href="#">
												Lorem ipsum dolor sit amet
											</a>
												</td>
												<td class="price-pr">
													<p>$ 80.0</p>
												</td>
												<!-- <td class="quantity-box">In Stock</td> -->
												<td class="add-pr">
													<a class="btn hvr-hover" href="#">Add to Cart</a>
												</td>
												<td class="remove-pr">
													<a href="#">
												<i class="fas fa-times"></i>
											</a>
												</td>
											</tr>
											<!-- <tr>
												<td class="thumbnail-img">
													<a href="#">
												<img class="img-fluid" src="images/img-pro-02.jpg" alt="" />
											</a>
												</td>
												<td class="name-pr">
													<a href="#">
												Lorem ipsum dolor sit amet
											</a>
												</td>
												<td class="price-pr">
													<p>$ 60.0</p>
												</td>
												<td class="quantity-box">In Stock</td>
												<td class="add-pr">
													<a class="btn hvr-hover" href="#">Add to Cart</a>
												</td>
												<td class="remove-pr">
													<a href="#">
												<i class="fas fa-times"></i>
											</a>
												</td>
											</tr>
											<tr>
												<td class="thumbnail-img">
													<a href="#">
												<img class="img-fluid" src="images/img-pro-03.jpg" alt="" />
											</a>
												</td>
												<td class="name-pr">
													<a href="#">
												Lorem ipsum dolor sit amet
											</a>
												</td>
												<td class="price-pr">
													<p>$ 30.0</p>
												</td>
												<td class="quantity-box">In Stock</td>
												<td class="add-pr">
													<a class="btn hvr-hover" href="#">Add to Cart</a>
												</td>
												<td class="remove-pr">
													<a href="#">
												<i class="fas fa-times"></i>
											</a>
												</td>
											</tr> -->
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- End Wishlist -->

			</div>
		</div>
	</div>

</body>
</html>