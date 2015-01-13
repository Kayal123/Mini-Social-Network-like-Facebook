<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>
</head>
<body>
<div class="container">
    <div class="site-wrapper">

      <div class="site-wrapper-inner">

        <div class="cover-container">
		
          <div class="masthead clearfix">
            <div class="inner">
              <h3 class="masthead-brand">Register</h3>
            </div>
          </div>
		
          <div class="inner cover">
			<tiles:insertAttribute name="body" />
          </div>


        </div>

      </div>

    </div> <!-- /site-wrapper -->
</div>
</body>
</html>