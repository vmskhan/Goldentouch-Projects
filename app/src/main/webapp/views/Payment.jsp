<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="Header.jsp" />
    <div class="container-fluid">

        <div class="row mt-4 px-5">
            <div class="col-12">
                <div class="h5 text-b">Payment</div>
            </div>
        </div>

        <div class="row mt-5 px-5">
            <div class="col-7">
                <div class="text-b h5">Test Details</div>
                <div class="row mt-3">
                    <div class="col-3">Name</div>
                    <div class="col-3">Date</div>
                    <div class="col-3">Duration</div>
                    <div class="col-3">Total Marks</div>
                </div>
                <div class="row mt-3 text-b">
                    <div class="col-3">${test.tname}</div>
                    <div class="col-3">${test.start_time}</div>
                    <div class="col-3">${test.duration}</div>
                    <div class="col-3">${test.totalMarks}</div>
                </div>
            </div>
            <div class="col-5">
                <div class="px-2 py-4 shadow text-center rounded">
                    Note : You can attempt this test only one time
                    <div class="mt-3 d-grid">
                        <button id="rzp-button" class="btn btn-block bg-y text-white">Pay Rs ${test.amount}</button>
                    </div>
                </div>
            </div>
        </div>

        
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js" ></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <!-- razorpay -->
    <script src="https://checkout.razorpay.com/v1/checkout.js"></script>
    
    <script type="text/javascript">
		
        function doPost(path, requestObject, isAsync) {
			var resp;
				$.ajax({
		            url: path,
		            type: 'POST',
		            data: requestObject,
		            contentType: "application/json; charset=UTF-8",
		            async: isAsync,
		            success: function (data) {	
                        console.log(data);
		            	resp = JSON.parse(data)
		            }
		       })
		       return resp;
			}

		
		
	</script>
	
	<script>
	var resp = null;
	
	/**
		Manual Checkout for Razor Pay
	**/
	
	var options = {
		    "key": "",
		    "amount": "", 
		    "name": "",
		    "description": "",
		    "image": "",
		    "order_id":"",
		    // "handler": function (response){
		    // 	alert(response.razorpay_payment_id); /* use this razorpay_payment_id for feature reference to this order */
		    // 	$('#msform')[0].reset();
		    // 	$('#pay-success').show();
		    	
		    // },
		    "prefill": {
		        "name": "",
		        "email": ""
		    },
		    "notes": {
		        "address": ""
		    },
		    "theme": {
		        "color": ""
		    },
			"callback_url":"",
  			"redirect": true
		};
		

		document.getElementById('rzp-button').onclick = function(e){
            var tid = document.getElementById('rzp-button').getAttribute("url");
            
			resp = doPost("/payment/test/${tid}/create",JSON.stringify({}), false);
            console.log("Made request");
			if(resp.statusCode == 200) {
				options.key = resp.razorPay.secretKey;
				options.order_id = resp.razorPay.razorpayOrderId;
				options.amount = resp.razorPay.applicationFee; //paise
				options.name = resp.razorPay.merchantName;
				options.description = resp.razorPay.purchaseDescription;
				options.image = resp.razorPay.imageURL;
				options.prefill.name = resp.razorPay.customerName;
				options.prefill.email = resp.razorPay.customerEmail;
				options.notes.address = resp.razorPay.notes;
				options.theme.color = resp.razorPay.theme;
				options.callback_url = "http://localhost:8080/payment/test/${tid}/callback"
				var rzp1 = new Razorpay(options);
				rzp1.open();
				e.preventDefault();
			} else {
				
			}
		}
	
	</script>
<jsp:include page="Footer.jsp" />