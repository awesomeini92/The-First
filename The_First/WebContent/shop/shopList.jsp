<%@page import="java.util.ArrayList"%>
<%@page import="vo.ShopBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<%
	// Action 클래스에서 request 객체의 setAttibute() 메서드로 저장되어 전달된 객체 가져오기(Object 타입이므로 형변환 필요)
	ArrayList<ShopBean> shopList = (ArrayList<ShopBean>)request.getAttribute("shopList");
%> 
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
<style type="text/css">
	#registForm {
		width: 500px;
		height: 610px;
		border: 1px solid red;
		margin: auto;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		margin: auto;
		width: 800px;
		border: 1px solid darkgray;
	}
	
	a {
		text-decoration: none;
	}

	#tr_top {
		background: orange;
		width: 800px; 
		text-align: center;
	}
	
	#selectSum{
		margin: auto;
		width: 800px;
		text-align: right;
	}
	
	#paymentButton {
		margin: auto;
		width: 800px;
		text-align: right;
	}
	

	
	#emptyArea {
		margin: auto;
		width: 800px;
		text-align: center;
	}
</style>

<script type="text/javascript">

	function checkAll(theForm) {
		if(theForm.remove.length == undefined) {
			theForm.remove.checked = theForm.remove.allCheck.checked;	
		} else {
			for(var i = 0; i < theForm.remove.length; i++) {
				theForm.remove[i].checked = theForm.allCheck.checked;
			}
		}
	}
	
	function qtyUp(qtyId) {
		// 수량 증가 버튼 클릭 시 해당 수량 값을 1 증가하여 수량 갱신(최대값 99)
		var element = document.getElementById("qty" + qtyId);
		if(element.value < 99) {
			element.value = Number(element.value) + 1; // 정수 연산을 위해 Number() 함수에 값 전달하여 연산 수행
			// => 덧셈연산자(+)는 문자열과의 덧셈 시 문자열 연결로 사용되기 때문
		}	
	}
	
	function qtyDown(qtyId) {
		// 수량 증가 버튼 클릭 시 해당 수량 값을 1 증가하여 수량 갱신(최소값 1)
		var element = document.getElementById("qty" + qtyId);
		if(element.value > 1) {
			element.value = Number(element.value) - 1; // 정수 연산을 위해 Number() 함수에 값 전달하여 연산 수행
			// => 뺄셈연산자(-)는 연결연산자가 아니므로 Number() 함수 생략 가능
		}	
	}
	
	function checkQty(qtyInput) {
		var qty = qtyInput.value;
		// 정상 수량 범위 외의 값(숫자 외의 문자 등 포함)이 입력되면 오류를 출력한 후 수량을 1로 정정
		if(!(qty >= 1 && qty <= 99)) {
			alert("수량을 1 ~ 99 사이의 값으로 입력하세요.");
			qtyInput.value = 1;
		}
	}
	
	function qtyChange(cartId) {
		var element = document.getElementById("qty" + cartId);
		// 카트ID 와 수량을 DogCartQtyChange.dog 서블릿 요청에 포함시켜 이동
// 		location.href='DogCartQtyChange.dog?id=' + cartId + "&qty=" + element.value;
	}
	
</script>


</head>
<body>
	
<%-- <c:if test="${startMoney != null }">
	<c:set var="startMoney" value="${startMoney }"></c:set>
</c:if>
<c:if test="${endMoney != null }">
	<c:set var="endMoney" value="${endMoney }"></c:set>
</c:if>
	<section id="listForm">
	<c:if test="${shopList != null && shopList.size() > 0 }">
		<h2>상품 목록</h2>
		<form method="post" id="shopListForm">
			<table>
				<tr class="tr_top">
					<td>
						<input type="checkbox" id="allCheck" name="allCheck" onclick="checkAll(this.form)" />
					</td>
					<td>상품이미지</td>
					<td>상품명</td>
					<td>구매수량</td>
					<td>상품금액</td>
				</tr>
			<c:forEach var="shop" items="${shopList}" varStatus="status">
				<tr class="tr_list">
					<td>
						<input type="checkbox" id="remove" name="remove" />
					</td>
					<td>
						<img src="images/${shop.product_image }" id="shopImage" />
					</td>
					<td>
						${shop.product_name }
					</td>
					<td>
						<a href="javascript:qtyUp(1)">▲</a>
						<input type="text" name="qty" id="qty" size="1" value="${shop.purchase_count }" onchange="javascript:checkQty(this)">
						<a href="javascript:qtyDown(1)">▼</a>
						<input type="button" value="변경" onclick="qtyChange(1)"/>
					</td>
					<td>
						${shop.price }
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="6" style="text-align: center;">총 금액 : ${totalMoney }원</td>
			</tr>

			</table>
		</form>
	</c:if>
	

	
	<nav id="commandList">
		<a href="DogList.dog">결제하기</a>
	</nav>
	</section>
</body>
</html>	 --%>
	
	
	
	<section id="listForm">
		<h2>상품 목록</h2>
		<table>
			<tr id="tr_top">
				<td width="70"><input type="checkbox" id="allCheck" name="allCheck" onclick="checkAll(this.form)"></td>
				<td width="150">상품이미지</td>
				<td width="400">상품명</td>
				<td width="150">구매수량</td>
				<td width="150">상품금액</td>
			</tr>
		<%if(shopList != null) {%>
			<%for(int i = 0; i < shopList.size(); i++) {%>
				<tr>
					<td align="center"><input type="checkbox"></td>
					<td align="center"><%=shopList.get(i).getProduct_image() %></td>
					<td align="center"><%=shopList.get(i).getProduct_name() %></td>
					
					
					<td align="center">
					<form>
					<input type="button" value="-" onClick="javascript:this.form.qty.value--;">
					<input type="text" name="qty" value="1" style="text-align:center; width:30px; height:15px"> 
					<input type="button" value="+" onClick="javascript:this.form.qty.value++;">
					</form>
					</td>
					
					<td align="center"><%=shopList.get(i).getPrice() %></td>
				</tr>
				
			<%} %>
			
		</table>	
			
	</section>
	
	<section id="selectSum">
		선택 상품 합계 : 
		<%
		
		%>
	</section>
	
	<section id="paymentButton">
		<a href="Payment.shop"><input type="button" value="결제하기"></a>
	</section>
	


<%} %>
</body>
</html>


















