# When to laundry

## A scheduling application for public laundry room 

### Introduction

Get time back in your day! Students are already busy with schoolwork, so laundry should never take more time than it 
needs. The public laundry rooms at UBC residences are often fully packed at peak times.  It's not often the case to get 
laundry done on the first try, instead, students often need to check the availability multiple times until they find a 
spot. This long-standing issue has brought several concerns to most student housing residents, such as **wasted time**, 
**disrupted plans**, **mood swings caused by uncertainty**, **anxiety from falling behind schedule**, etc. In light of 
this, the application "When to laundry" will be created to host laundry bookings for UBC student residents as a digital 
solution. This application will help users determine the availability of the laundry machines and save a spot upfront. 
Users will also have the option to join the waiting list if all machines are occupied or booked in the next 24 hours. 
Users will also get notifications if a spot is vacant or if their laundry is done. Overall, this application will 
significantly help users **arrange their laundry time** and get it done more efficiently. This application will 
also help laundry managers to **track the usage of machines** and therefore determine the priority for machine 
maintenance.


### Instructions for Users

- You can generate the first required event by adding multiple new bookings for multiple users. To accomplish this,
firstly click the button "Start booking" in the center of the home page, after which you will be prompted to a new
window with information about all the time slots. Below the time slots list are two text fields for you to make new
bookings. Input the username and the serial number of the slot that the user wishes to book, then click the "Confirm"
button to confirm this booking. After that, you will see a new confirmation message showing up in the text box.
Repeat this process to add new bookings for different users. Everytime making a new booking will automatically invoke 
the time slot panel to update. This will make the time slot you booked turns to red, meaning this time slot is no 
longer available. You can click the "View" button to see all the bookings you just made displayed
  in the message box.


- You can also generate the second required event by entering the username and clicking the cancel button to cancel the 
most recent booking for this user. Every time cancelling a booking will also invoke the time slot panel to update. The 
time slot that has just been released will return to black color. 


- You can locate my visual component in the homepage, which is avatar of the administrator user. Another visual 
component is the icon location at left corner of the window tab.

- You can save the state of my application by clicking the "Save" button in the booking window

- You can reload the state of my application by clicking the "Load" button in the booking window. You are allowed to 
upload files different from the one you saved. 


### Next Steps
To improve my current code, I would firstly refactor the DefaultWindow class and BookWindow class. The HomePage class
now inherited all the object/fields from the DefaultWindow, but it doesn't need to call any methods on the inherited 
objects. To improve the cohesion, I would just keep the imageIcon icon object and remove all the other objects and 
fields to the BookWindow class.

Another one I would like to improve upon is the Booking Class. I instantiated the booking list as an array list of 
users, with each user having the username and the booked time slot. The array list is not ideal because it allows 
duplicated items, and it doesn't have the advantage to store two fields in the same list. Instead, using a map list
would solve the problem. The key of the map would be the username (String), and the value of the map would be a 
linkedList of time slots. In this way, there is no need to create a user class and add the association from Booking to 
User, which can largely improve the coupling of the code.


<img width="487" alt="Screen Shot 2023-05-04 at 11 24 48" src="https://user-images.githubusercontent.com/54514768/236296377-b8277ef1-ccee-4e16-97af-4aac99bceffc.png">
