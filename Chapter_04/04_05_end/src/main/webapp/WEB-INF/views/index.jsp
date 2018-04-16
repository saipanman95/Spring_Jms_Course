<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<!-- Access the bootstrap Css like this,
Spring boot will handle the resource mapping automcatically -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webjars/bootstrap/4.0.0/css/bootstrap.min.css" />

<!--
<spring:url value="/css/main.css" var="springCss" />
<link href="" rel="stylesheet" />
-->
<c:url value="/css/main.css" var="jstlCss" />
<link href="" rel="stylesheet" />
<style>
.hide{
display : none;
}
</style>

</head>
<body>

<div class="container">
<div class="header clearfix">
<nav>
<ul class="nav nav-pills float-right">
<li class="nav-item">
<a href="#" class="nav-link active">
Home
<span class="sr-only">(current)</span>
</a>
</li>
<li class="nav-item">
<a href="#" class="nav-link">
About
<span class="sr-only">(current)</span>
</a>
</li>
</ul>
</nav>
<h3 class="text-muted">Book Order Form</h3>
</div>
<div class="jumbotron row">
<div class="col-lg-6">
<img src="${pageContext.request.contextPath}/images/random_books.png" style="width: 350px;" class="rounded" alt="picture of random books"/>
</div>
<div class="col-lg-6">
<h1 class="display-3">Your Books Galore  </h1>
<p class="lead">Place book orders here to warehouse for shipping</p>
</div>
</div>
<div class="panel panel-default">
<div class="row marketing">
<div class="col-lg-12">

<div id="jmsMessageAlert" class="alert alert-success hide">
<strong>Success!</strong> <span id="message"></span>
</div>
<h4>Customer Id</h4>
<div class="input-group">
<select class="form-control" id="customerId">
<c:forEach var="item" items="${customers}">
<option value="${item.customerId}">${item.customerId} - ${item.fullName}</option>
</c:forEach>
</select>
<!--input type="text" id="customerId" class="form-control" placeholder="Customer Account Number" aria-describedby="basic-addon2"-->
</div>
<br/>
<h4>Books</h4>
<select class="form-control" id="bookId">
<c:forEach var="item" items="${books}">
<option value="${item.bookId}">${item.bookId} - ${item.title}</option>
</c:forEach>
</select>
<br/>
<button class="btn btn-primary" id="addToOrderId" onclick="processOrder();" type="button">Add to Order</button>

</div>

</div>
</div>
<footer class="footer">
<p>&copy; Your Books Galore 2017</p>
</footer>

</div>
<script>

function processOrder(){
var randomOrderId = Math.floor(Math.random() * 100000);
var bookId = $('#bookId').val();
var customerId = $('#customerId').val();
//alert(randomOrderId + " - " + customerId + " - " + bookId);
jQuery.get('${pageContext.request.contextPath}/process/order/'+randomOrderId+'/'+customerId+'/'+bookId+'/',
function(data, status){
$("#jmsMessageAlert").removeClass('hide');
$("#jmsMessageAlert span").text(data);
});
}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</body>

</html>
