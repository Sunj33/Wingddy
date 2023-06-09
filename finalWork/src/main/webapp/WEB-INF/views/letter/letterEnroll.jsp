<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쪽지 작성</title>
</head>
<body>

<div id="app">
	
	<jsp:include page="../sideBar/sideBar.jsp" />
	    <div class="main-content">
	     
		    <section class="section">
		        <div class="section-header">
		     		<h1>쪽지 작성</h1>
		        </div>
		
		        <div class="section-body">
					<div class="card">
						<div class="card-body">
							<form action="insert.le">
								<input type="hidden" name="classNo" value="${requestScope.classroom.classNo}">
								<input type="hidden" name="sender" value="${sessionScope.loginUser.memberNo}">
								<div align="center">
									<input type="hidden" name="toManitto">
									<input type="hidden" name="anonymous">
									<c:choose>
										<c:when test="${not empty myManitti}">
											
											<p style="font-size: larger; font-weight: bolder;">'마니띠에게' 클릭 시 익명으로 보낼 수 있어요!</p>
											<input type="radio" name="recipient" value="${myManitto}" id="myManitto">마니또에게
											
											<input type="radio" name="recipient" value="${myManitti}" id="myManitti">마니띠에게
											
											<!--<button type="button" onclick="noneCheck();" class="btn btn-primary">선택취소</button>-->
											
										</c:when>
										<c:otherwise>
											<p style="font-size: larger; font-weight: bolder;">마니또게임을 하고 있지 않아요!</p>
										</c:otherwise>
									</c:choose>
								</div>
								<script>

									$("input:radio[name='recipient']").change(function() {

										var id = $(this).prop("id");
										
										/*
										if ($(this).val() === "${myManitto}") {
											$("input[name='toManitto']").val("Y");
											$("input[name='anonymous']").val("N");
										} 
										if ($(this).val() === "${myManitto}"){
											$("input[name='toManitto']").val("N");
											$("input[name='anonymous']").val("Y");
										}
										*/

										if(id === 'myManitto'){
											$("input[name='toManitto']").val("Y");
											$("input[name='anonymous']").val("N");
										}else{
											$("input[name='toManitto']").val("N");
											$("input[name='anonymous']").val("Y");
										}

										//console.log("toManitto : " + $("input[name='toManitto']").val());
										//console.log("anonymous : "+$("input[name='anonymous']").val());
									})
									
									function noneCheck(){
										$("input:radio[name='recipient']").prop('checked', false);
										$("#recipientSelect").prop("disabled", false);
										
										$("input[name='anonymous']").val("N");
										$("input[name='toManitto']").val("N");
									}

									$("input:radio[name='recipient']").click(function(){
										
										$("#recipientSelect").prop("disabled", true);
										$("#recipientSelect").val('');
							
									})
							/*
									$(document).ready(function() {
										$("#recipientSelect").click(function(){

											console.log($("#recipientSelect").prop("disabled"));
											
											if($("#recipientSelect").prop("disabled")){
												$("#recipientSelect").prop("disabled", false);
												noneCheck();
											}
											
										});
									});
									*/

									
									function test(){

										if($("#recipientSelect").prop("disabled")){  
											$("#recipientSelect").prop("disabled", false);

											
										}
										
										noneCheck();
										
											
									}
									

									/*
									$(document).ready(function() {
										$("#recipientSelect").on("change", function() {
										if ($(this).val() !== "") {
											noneCheck();
										} 

									});
									})
									*/
									

								</script>

									<div class="form-group row mb-4">
									<label class="col-form-label text-md-right col-12 col-md-3 col-lg-3">To</label>
									<div class="col-sm-12 col-md-7" onclick="test();">
										<select class="form-control selectric" id="recipientSelect" name="recipient" required >
											<option value="">-</option>
											<c:forEach items="${requestScope.recipientList}" var="r">
												<c:if test="${r.memberNo ne sessionScope.loginUser.memberNo}">
													<option value="${r.memberNo}">${r.recipient}</option>
												</c:if>
											</c:forEach>
										</select>
									</div>
									</div>
									<div class="form-group row mb-4">
									<label class="col-form-label text-md-right col-12 col-md-3 col-lg-3">Content</label>
									<div class="col-sm-12 col-md-7">
										<textarea class="summernote-simple" name="letterContent" required></textarea>
									</div>
									</div>

									<div class="form-group row mb-4">
										<label class="col-form-label text-md-right col-12 col-md-3 col-lg-3">Present</label>
										<div class="col-sm-12 col-md-7">
											<select class="form-control selectric" name="gift">
												<option value="">-</option>
												<c:forEach items="${requestScope.myAlphabet}" var="a">
													<option value="${a.alphabet}">${a.alphabet} (${a.count})</option>
												</c:forEach>
											</select>
										</div>
										</div>
									</div>

									<div class="form-group row mb-4">
									<label class="col-form-label text-md-right col-12 col-md-3 col-lg-3"></label>
									<div class="col-sm-12 col-md-7 text-center">
										<button class="btn btn-primary">Send</button>
									</div>
								</div>
							</form>
						</div>
					</div>
		      	</div>
		    </section>
	    </div>
</div>

</body>
</html>