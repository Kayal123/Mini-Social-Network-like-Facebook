<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<script type="text/javascript">
    function onSubmit(uri) {
        $("#friendshipForm").attr("action", uri );
        $("#friendshipForm").submit();
    }
</script>

<div class="container" id="friendship">
    <!-- Add Friend By Email -->
    <div class="container" id="friendManagementByEmail">
        <form role="form" method="POST">
        <!-- <table class="table">
            <tr><td> -->
  		<div class="form-group">
                <label for="emailInput" >Add a friend by email</label> <br />
                <input name="friendEmail" value="" id="emailInput"/> <br /> 
                <input type="submit" class="btn btn-default" name="request" value="Request Friendship" />
                <input type="submit" class="btn btn-default" name="remove" value="Remove Friendship" />
  		</div>
            <!-- </td></tr>
        </table> -->         
        </form>
    </div>
    
    <div class="row" id="friendManagementMessages" style="color:red; font-style: italic; font-weight:bold;">
       <p>${message}</p>
    </div>

    <!-- View list of Friends -->
    <div class="container">
       <form role="form" id="friendshipForm" method="POST" >
        <table class="table table-bordered table-striped">
            <tr><th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Friendship Status</th>
                <th><!-- Provide controls for Accept, Reject, Remove, View --></th></tr>
                
            <c:forEach items="${friendships}" var="friend">
            <tr>
                <td>${friend.friend.firstname}</td>
                <td>${friend.friend.lastname}</td>
                <td>${friend.friend.email}</td>
                <td>${friend.status}</td>
    
                <td>
                <c:if test="${friend.status == 'ACCEPTED'}"> 
                <input onclick="javascript:onSubmit('${pageContext.request.contextPath}/friendship/remove/${friend.friend.id}')" value="Remove" type="button"/>
                <input onclick="" value="View" type="button"/>
                </c:if>
                <c:if test="${friend.status == 'WAITING'}"> 
                <input onclick="javascript:onSubmit('${pageContext.request.contextPath}/friendship/remove/${friend.friend.id}')" value="Remove" type="button"/>
                </c:if>
                <c:if test="${friend.status == 'PENDING'}"> 
                <input onclick="javascript:onSubmit('${pageContext.request.contextPath}/friendship/accept/${friend.friend.id}')" value="Accept" type="button" class="btn btn-primary"/>
                <input onclick="javascript:onSubmit('${pageContext.request.contextPath}/friendship/reject/${friend.friend.id}')" value="Reject" type="button" class="btn btn-default"/>
                <input onclick="javascript:onSubmit('${pageContext.request.contextPath}/friendship/remove/${friend.friend.id}')" value="Remove" type="button" class="btn btn-warning"/>
                </c:if>
                <c:if test="${friend.status == 'REJECTED'}"> 
                <input onclick="javascript:onSubmit('${pageContext.request.contextPath}/friendship/remove/${friend.friend.id}')" value="Remove" type="button"/>
                </c:if>
                </td>
             </tr>
            </c:forEach>
        </table>
        </form>
    </div>
</div>