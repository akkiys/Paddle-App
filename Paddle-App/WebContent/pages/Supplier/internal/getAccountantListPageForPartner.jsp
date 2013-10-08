<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


  <div class="row" >  
    <div class="large-6 columns" >
     <label for="accountantList">Accountant </label>
    <select   multiple="multiple"  id="accountantList"   name="accountantList" class="multiSelect" onChange="javascript:assignAccountantToPartners();"  Style="width:70%;">
	<c:forEach var="listItems" items="${requestScope.accountantDetails}">
	<option value="<c:out value='${listItems.userId}'/>" <c:out value='${listItems.status}'/> >  <c:out value='${listItems.userName}'/> </option> <!--Partner Name  -->
	</c:forEach>
	</select>
 
 
    </div>
  
  </div>
  
  
  	<table class="table-transactions">
				<thead>
					<tr style="color:white">
						 
						<th>Accountant</th>
						<th>Default</th>
						<th>Default Reviewer</th>
						 
					</tr>
				</thead>
				<tbody>
				<c:forEach var="listItems1" items="${requestScope.activeAccountantDetails}">
					<tr>
						 
						<td><c:out value='${listItems1.userName}'/></td>
						<td><input type="radio" name="defaultAccountant"  onClick="defaultAccountantToPartner()" value="<c:out value='${listItems1.id}'/>" <c:out value='${listItems1.type}'/>></td>
						<td> <select     name="reviewerList" id="reviewerList"   onChange="javascript:defaultReviewerToPartner(<c:out value='${listItems1.id}'/>);"  Style="width:50%;">
	<c:forEach var="listItems2" items="${listItems1.reviewerList}">
	<option value="<c:out value='${listItems2.userId}'/>" <c:out value='${listItems2.status}'/> >  <c:out value='${listItems2.userName}'/> </option> <!--Reviewer Name  -->
	</c:forEach>
	</select>
   </td>
						 
					</tr>
					</c:forEach> 
				</tbody>
			</table>
 
 
 
 