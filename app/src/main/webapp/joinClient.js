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
secondRequest.open('POST','http://localhost:8080/user/getMeetingDetails/');
secondRequest.responseType= 'json';
//secondRequest.setRequestHeader("Set-Cookie", "SameSite=strict")
secondRequest.onload = function() {
	
	
	
  console.log(secondRequest.response);
  let k=secondRequest.response;//.toString();
  var meetDetails=k;//JSON.parse(k);
console.log(meetDetails);

client.join({
    sdkKey: meetDetails.sdkKey,
    signature: meetDetails.signature, // role in signature needs to be 0
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