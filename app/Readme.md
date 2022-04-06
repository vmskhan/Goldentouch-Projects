# Onview - Online Interview Platform
## About
Onview is Online Interview Platform where an interviewer and interviewee can join an online meeting and interact with each other with ease. the interviewer can 
also create a set of questions the participant has to answer during the interview.

#### Video of Project Working [Onview](https://drive.google.com/file/d/1IwrRuCv_YLSSzlZg3hI6wGVLtFrHTiU9/view?usp=sharing) 
#### Video for Setting up Zoom API [click here](https://drive.google.com/file/d/1D4sMEcczgpl3G3XRciqt3Rk55GJuX7_9/view?usp=sharing)

## Steps for Installing the Project
1. Download the zip file of this repository or clone it.
2. Import the project/use the repository cloned in spring boot
3. Run the sql commands inside the file to setup database [sql_commands](https://github.com/vmskhan/Goldentouch-Projects/blob/456aab2f00f82b40e0e01c3e2d07e19051b08a2e/app/db_sql/onview%20mysql%20commands.txt) 
4. Create two Zoom API's one for OAUTH and the other for SDK, then place client-id,client-secret from OAUTH app and sdk-key from SDK app into application.properties file.
5. Also place the sdk-key,sdk-secret in the `.env` file inside the folder `app\node server for zoom signature\meetingsdk-sample-signature-node.js-master` (if it doesn't exist please create a file named .env)
6. Set the Email-id and the app password for the gmail account. Refer this link for app password [here](https://www.javacodemonk.com/spring-boot-send-email-with-gmail-smtp-5caea8f3#:~:text=App%20password%20setup%20in%20GMAIL&text=Goto%20https%3A%2F%2Faccounts.google,and%20then%20select%20App%20passwords.&text=Then%20click%20on%20generate.,password%20in%20email%20SMTP%20authentication.)

## Steps for Running the Project
1. Run the AppApplication.java file inside com.main package.
2. Goto `localhost:8080` to see the landing page.
3. Sign up to create a new account. By default every user siging up has the **User** authority. To give **Admin** authority type the below sql command in mysql,
     `update user set claim='admin' where uid=[the_required_uid];`
4. After creating two users, create a interview and add questions. Before starting the zoom meeting run the node server by using the command `>npm run start` in the cmd 
   in the directory `app\node server for zoom signature\meetingsdk-sample-signature-node.js-master` for retrieving signature for zoom.
5. Then create a meet as admin in one browser. Login as the participant from the other browser and start the interview to join the meet.

##  Possible Improvements
1. Add edit option for questions
2. Add feature to reuse a already completed interview again for a different participant
3. In the Test Page for participant questions can be loaded asynchronously to avoid rejoining of zoom meet after submitting each question(AJAX) 
