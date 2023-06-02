<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마켓 디테일</title>
</head>
<body>

<div id="app">
	
	<jsp:include page="../sideBar/sideBar.jsp" />
	    <div class="main-content">
	     
		    <section class="section">
		        <div class="section-header">
		     		<h1>Alphabet Market Detail</h1>
		        </div>
		
		        <div class="section-body">
					<div class="card">
						<div class="card-header text-center">
							<h3>제목 어쩌구</h3>
						</div>
						<div class="card-body">
							<div align="right">
								<h6>홍길동</h6>
								<p>2023-02-02</p>
							</div>
							<div class="article-image">
								<p style="text-align: center; font-size: 150px; margin-top: 70px;">A</p>
							</div>
							<br><br><br>
							<div align="center">
								<pre style="font-size: larger;">dfij;dsoifjuweoifjsdlfjewoifhyo;isdfh;dkjsfgweofh</pre>
							</div>
						</div>
						<div class="card-header">
						</div>
						<div class="card-body">
							
							<div class="card">	
								  <div class="card-body p-0">
									<div class="table-responsive">
									  <table class="table table-striped table-md">
										<tr>
											<td colspan="6"><h3>comments(3)</h3></td>
											<td><button class="btn btn-warning" id="modal-aphReply">댓글작성</button></td>
										</tr>
										<tr>
										  <td><input type="hidden" value="댓글넘버"></td>
										  <td><div class="badge badge-success">selected</div></td>
										  <td><h3>E</h3></td>
										  <td>김길동</td>
										  <td>나랑 바꾸자 나랑fsfkjwehokewjkfjriufjdsjfhiuewgifjsdlf</td>
										  <td>2017-01-09</td>
										  <td><a href="#" class="btn btn-primary btn-sm">바꾸기</a></td>
										</tr>
										<tr>
										  <td><input type="hidden" value="댓글넘버"></td>
										  <td><div class="badge badge-success"></div></td>
										  <td><h3>E</h3></td>
										  <td>김길동</td>
										  <td>나랑 바꾸자 나랑fsfkjwehokewjkfjriufjdsjfhiuewgifjsdlf</td>
										  <td>2017-01-09</td>
										  <td><a href="#" class="btn btn-primary btn-sm">바꾸기</a></td>
										</tr><tr>
										  <td><input type="hidden" value="댓글넘버"></td>
										  <td><div class="badge badge-success"></div></td>
										  <td><h3>E</h3></td>
										  <td>김길동</td>
										  <td>나랑 바꾸자 나랑fsfkjwehokewjkfjriufjdsjfhiuewgifjsdlf</td>
										  <td>2017-01-09</td>
										  <td><a href="#" class="btn btn-primary btn-sm">바꾸기</a></td>
										</tr>
									  </table>
									</div>
								  </div>
								  <div class="card-footer text-center">
									<nav class="d-inline-block">
									  <ul class="pagination mb-0">
										<li class="page-item disabled">
										  <a class="page-link" href="#" tabindex="-1"><i class="fas fa-chevron-left"></i></a>
										</li>
										<li class="page-item active"><a class="page-link" href="#">1 <span class="sr-only">(current)</span></a></li>
										<li class="page-item">
										  <a class="page-link" href="#">2</a>
										</li>
										<li class="page-item"><a class="page-link" href="#">3</a></li>
										<li class="page-item">
										  <a class="page-link" href="#"><i class="fas fa-chevron-right"></i></a>
										</li>
									  </ul>
									</nav>
								  </div>
								</div>
						</div>
					</div>
		      	</div>
		    </section>
	    </div>
</div>


	<form class="modal-part" id="modal-login-part">
		<p>커션! 알파벳 어쩌구 </p>
		<div class="form-group">
		<label>알파벳</label>
		<div class="input-group">
		
			<select class="form-control selectric">
				<option>A</option>
				<option>N</option>
				<option>P</option>
			</select>
		</div>
		</div>
		<div class="form-group">
		<label>Comment</label>
		<div class="input-group">
			<input type="textarea" class="form-control" >
		</div>
		</div>
	</form>


<!--
	<form class="modal-part" id="modal-login-part">
		<p>This login form is taken from elements with <code>#modal-login-part</code> id.</p>
		<div class="form-group">
			<label>Username</label>
			<div class="input-group">
			<div class="input-group-prepend">
				<div class="input-group-text">
				<i class="fas fa-envelope"></i>
				</div>
			</div>
			<input type="text" class="form-control" placeholder="Email" name="email">
			</div>
		</div>
		<div class="form-group">
			<label>Password</label>
			<div class="input-group">
			<div class="input-group-prepend">
				<div class="input-group-text">
				<i class="fas fa-lock"></i>
				</div>
			</div>
			<input type="password" class="form-control" placeholder="Password" name="password">
			</div>
		</div>
		<div class="form-group mb-0">
			<div class="custom-control custom-checkbox">
			<input type="checkbox" name="remember" class="custom-control-input" id="remember-me">
			<label class="custom-control-label" for="remember-me">Remember Me</label>
			</div>
		</div>
	</form>
-->


	<script>
		$("#modal-aphReply").fireModal({
			title: 'Login',
			body: $("#modal-login-part"),
			footerClass: 'bg-whitesmoke',
			autoFocus: false,
			onFormSubmit: function(modal, e, form) {
			// Form Data
			let form_data = $(e.target).serialize();
			console.log(form_data)
		
			// DO AJAX HERE
			let fake_ajax = setTimeout(function() {
				form.stopProgress();
				modal.find('.modal-body').prepend('<div class="alert alert-info">Please check your browser console</div>')
		
				clearInterval(fake_ajax);
			}, 1500);
		
			e.preventDefault();
			},
			shown: function(modal, form) {
			console.log(form)
			},
			buttons: [
			{
				text: '등록',
				submit: true,
				class: 'btn btn-primary btn-shadow',
				handler: function(modal) {
				}
			}
			]
		});
	</script>



	<!-- Page Specific JS File 
	<script src="${contextPath}/resources/assets/js/page/bootstrap-modal.js"></script>
</html>