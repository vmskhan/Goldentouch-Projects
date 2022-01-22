window.onload=totalCost();
function totalCost(){
	let l=document.getElementsByName("totalPrice");
	console.log(l);
	let p;
	let cost=0;
	let thenum;
	for(i=0;i<l.length;i++)
	{
		thenum = l[i].innerHTML
		thenum=thenum.replace( /^\D+/g, '');
		cost+=parseInt(thenum);
	}
	document.getElementById("tc").innerText=cost+"Rs";
	if(cost==0)
	{
		console.log("hidden");
		document.getElementById("paybtn").style.visibility="hidden";
	}
	else
	{
		console.log("visible");
	document.getElementById("paybtn").style.visibility="visible";	
	}
}
function removeItem(eid){
	let listItem=document.getElementById(eid);
	listItem.parentNode.removeChild(listItem);
	let data={};
	data.pid=eid;
	sendData(data);
	totalCost();
}

function sendData( data ) {
  console.log( 'Sending data' );

  const XHR = new XMLHttpRequest();

  let urlEncodedData = "",
      urlEncodedDataPairs = [],
      name;

  // Turn the data object into an array of URL-encoded key/value pairs.
  for( name in data ) {
    urlEncodedDataPairs.push( encodeURIComponent( name ) + '=' + encodeURIComponent( data[name] ) );
  }

  // Combine the pairs into a single string and replace all %-encoded spaces to
  // the '+' character; matches the behavior of browser form submissions.
  urlEncodedData = urlEncodedDataPairs.join( '&' ).replace( /%20/g, '+' );

  // Define what happens on successful data submission
  XHR.addEventListener( 'load', function(event) {
    alert( 'Item Removed from cart successfully');
  } );

  // Define what happens in case of error
  XHR.addEventListener( 'error', function(event) {
    alert( 'Oops! Something went wrong.' );
  } );

  // Set up our request
  XHR.open( 'POST', 'http://localhost:8080/cart/remove' );

  // Add the required HTTP header for form data POST requests
  XHR.setRequestHeader( 'Content-Type', 'application/x-www-form-urlencoded' );

  // Finally, send our data.
  XHR.send( urlEncodedData );
}