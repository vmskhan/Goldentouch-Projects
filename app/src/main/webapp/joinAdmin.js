
// Select the target node.
var target = document.querySelector('#meetingSDKElement');

// Create an observer instance.
var observer = new MutationObserver(function(mutations) {
  try{  
  if(target.innerHTML!="")
    	if(target.getElementsByTagName("div")[0].childNodes.length === 0) 
       {
         console.log("meeting ended"); 
          let request = new XMLHttpRequest();
        request.open('GET', 'http://localhost:8080/admin/deleteMeet');
        //request.responseType = 'json';
        request.onload = function() {
          console.log("meeting deleted");
          window.location.replace("/admin/dashboard");
        };

        request.send();	
        // request.on
       }
  }
  catch(err){
    console.log("no change");
  }
});

// Pass in the target node, as well as the observer options.
observer.observe(target, {
    attributes:    true,
    childList:     true,
    characterData: true
});


let meetingSDKElement = document.getElementById('meetingSDKElement');
const client = ZoomMtgEmbedded.createClient();

client.init({
  debug: true,
  zoomAppRoot: meetingSDKElement,
  language: 'en-US',
  customize: {
    meetingInfo: [
      'topic',
      'host',
      'mn',
      'pwd',
      'telPwd',
      'invite',
      'participant',
      'dc',
      'enctype',
    ],
    toolbar: {
      buttons: [
        {
          text: 'Custom Button',
          className: 'CustomButton',
          onClick: () => {
            console.log('custom button');
          },
        },
      ],
    },
  },
});

let secondRequest= new XMLHttpRequest();
secondRequest.open('POST','http://localhost:8080/admin/getMeetingDetails/');
secondRequest.responseType= 'json';
//secondRequest.setRequestHeader("Set-Cookie", "SameSite=strict")
secondRequest.onload = function() {
	
	
	
  console.log(secondRequest.response);
  let k=secondRequest.response;//.toString();
  var meetDetails=k;//JSON.parse(k);
console.log(meetDetails);

client.join({
    sdkKey: meetDetails.sdkKey,
    signature: meetDetails.signature, // role in signature needs to be 1
    meetingNumber: meetDetails.meetingNumber,
    password: meetDetails.password,
    userName: meetDetails.username
})




};
secondRequest.send();

/*ZoomMtg.join({
  signature: signature,
  meetingNumber: meetingNumber,
  userName: userName,
  apiKey: apiKey,
  userEmail: userEmail,
  passWord: password,
  success: (success) => {
    console.log(success);
  },
  error: (error) => {
    console.log(error);
  },
});*/