<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport">
        <title>404 &mdash; Stisla</title>
      
        <!-- General CSS Files -->
        <link rel="stylesheet" href="${contextPath}/resources/assets/modules/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="${contextPath}/resources/assets/modules/fontawesome/css/all.min.css">
      
        <!-- CSS Libraries -->
      
        <!-- Template CSS -->
        <link rel="stylesheet" href="${contextPath}/resources/assets/css/style.css">
        <link rel="stylesheet" href="${contextPath}/resources/assets/css/components.css">
      <!-- Start GA -->
      <script async src="https://www.googletagmanager.com/gtag/js?id=UA-94034622-3"></script> 
      <script>
        window.dataLayer = window.dataLayer || [];
        function gtag(){dataLayer.push(arguments);}
        gtag('js', new Date());
      
        gtag('config', 'UA-94034622-3');
      </script>
      <!-- /END GA --></head>
      
      <body>
        <div id="app">
          <section class="section">
            <div class="container mt-5">
              <div class="page-error">
                <div class="page-inner">
                  <h1>ERROR</h1>
                  <div class="page-description">
                    ${errorMsg}
                  </div>
                  <div class="page-search">
                    <form>
                      <div class="form-group floating-addon floating-addon-not-append">
                      </div>
                    </form>
                    <div class="mt-3">
                      <a href="${contextPath}">Back to Home</a>
                    </div>
                  </div>
                </div>
              </div>
              <div class="simple-footer mt-5">
                Copyright &copy; Wingddy 2023
              </div>
            </div>
          </section>
        </div>
      
        <c:if test="${ not empty alertMsg }">
          <script>
            alert('${alertMsg}');
          </script>
          <c:remove var="alertMsg" scope="session" />
        </c:if>
        <!-- General JS Scripts -->
       <!--  <script src="assets/modules/jquery.min.js"></script>
        <script src="assets/modules/popper.js"></script>
        <script src="assets/modules/tooltip.js"></script>
        <script src="assets/modules/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/modules/nicescroll/jquery.nicescroll.min.js"></script>
        <script src="assets/modules/moment.min.js"></script>
        <script src="assets/js/stisla.js"></script> -->
        
        <!-- JS Libraies -->
      
        <!-- Page Specific JS File -->
        
        <!-- Template JS File -->
        <script src="assets/js/scripts.js"></script>
        <script src="assets/js/custom.js"></script>
      </body>
</html>