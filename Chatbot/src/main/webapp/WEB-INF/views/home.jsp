<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>부트스트랩 101 템플릿</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="header">
			<nav>
				<ul class="nav nav-pills pull-right">
					<li role="presentation" class="active"><a href="#">Home</a></li>
					<li role="presentation"><a href="#">About</a></li>
					<li role="presentation"><a href="#">Contact</a></li>
				</ul>
			</nav>
			<h3 class="text-muted"><a href="#">Lectopia</a></h3>
		</div>
		
      	<div class="jumbotron">
        	<h1>Jumbotron heading</h1>
        	<p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
        	<p><button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">상담하기</button></p>
      	</div>
      	
      	<div class="row marketing">
	        <div class="col-lg-6">
	          <h4>Subheading</h4>
	          <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>
	
	          <h4>Subheading</h4>
	          <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum.</p>
	
	          <h4>Subheading</h4>
	          <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
	        </div>

	        <div class="col-lg-6">
	          <h4>Subheading</h4>
	          <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>
	
	          <h4>Subheading</h4>
	          <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum.</p>
	
	          <h4>Subheading</h4>
	          <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
	        </div>
      </div>

      <footer class="footer">
        <p>&copy; Coding Camp 2017</p>
      </footer>
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">온라인 자동 상담하기</h4>
	      </div>
	      
	      <div class="modal-body" id="chat_room">
	          <div class="container-fluid">
	            <div class="row">
	              <div class="col-md-10"><p class="text-left">ChatBot : 안녕하세요 렉토피아입니다.</p></div>
	            </div>
	            <div class="row">
	              <div class="col-md-offset-2 col-md-10"><p class="text-right">안녕하세요 렉토피아입니다. : 사용자</p></div>
	             </div>
	          </div>
	      </div>	      
	      
	      <div class="modal-footer">
	      	<div class="row">
              <div class="col-md-10"><input type="text" id="message" class="form-control" placeholder="메시지를 입력하세요."></input></div>
              <div class="col-md-2"><button type="button" class="btn btn-primary">보내기</button></div>
	      	</div>
		  </div>
	    </div>
	  </div>
	</div>
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</body>
</html>