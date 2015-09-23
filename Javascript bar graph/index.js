//drawing canvas variables
var mycanvas, mycontext;
//set a boolean value to tell us if we are in drawing mode, then we can draw in the canvas. Its set to false currently.
var drawing = false;

//third canvas variables that will be used to create a dataURL
var cxt2;
var canvas3 = new Array;
var snap;
var list =new Array();

//Bar Chart variables used for draving the graph
var canvas
var cxt;
var bar_height;
var  startPosition;
var bar_width;
 data = 
	[
		{name: "Mon", amount: 0, colour: "red"},
		{name: "Tue", amount: 0, colour: "blue"},
		{name: "Wed", amount: 0, colour: "yellow"},
		{name: "Thur", amount: 0, colour: "green"},
		{name: "Fri", amount: 0, colour: "orange"},
		{name: "Sat", amount: 0, colour: "black"},
		{name: "Sun", amount: 0, colour: "brown"}
	]
//gets the height and width of the canvas from the html so it can adjust to change, i have tested this. 
//By default its height is 200px, and its width is 450px
var set_width;
var set_height;

//from quirksmode.org
//returns added offset of all containing elements
function findPos(obj) {
	var curleft = curtop = 0;
	if (obj.offsetParent) {
	do 
	{
		curleft += obj.offsetLeft;
		curtop += obj.offsetTop;
	} while (obj = obj.offsetParent);
	return [curleft,curtop];
	}
}

// Return the coordinates of the mouse relative of the canvas
// we must this function an event object since it isn't an event handler
function getMouseCoords(event){
	if (!event) var event = window.event;
	var posx = 0;
	var posy = 0;
	if (event.pageX || event.pageY) 	{
		posx = event.pageX;
		posy = event.pageY;
	}
	else if (event.clientX || event.clientY) 	{
		posx = event.clientX + document.body.scrollLeft
			+ document.documentElement.scrollLeft;
		posy = event.clientY + document.body.scrollTop
			+ document.documentElement.scrollTop;
	}
	var totaloffset = findPos(mycanvas);
	var totalXoffset = totaloffset[0];
 	var totalYoffset = totaloffset[1];
	var canvasX = posx- totalXoffset;
 	var canvasY = posy- totalYoffset;
	// return coordinates in an array
	return [canvasX, canvasY];
	
}
function BarChart()
{
//create canvas object, loop through the array. draw the rectangle. 
//set the values to the amount (y axis) and the name(x axis), these are the cordinates
//fill in the colour associated wirh the colour element in array
	
	//retrieve any localStorage data that exists and display it on the page
	var i = 0;
	while (i< data.length)
	{
		//convert the already created string back to an array
		var info = JSON.parse(localStorage.getItem("data"));
		data[i]=info[i];
		i++;
	}
	
	canvas = document.getElementById("barCanvas");
	if (canvas.getContext)
	{
		cxt = canvas.getContext('2d');
	
		//set the width and height of the canvas 
		set_width =canvas.width;
		set_height =canvas.height;
		
		//Starting position of x value before the rectangle is drawn
		startPosition = 0.5;
		//To find the with of each bar, find the total number of objects in array and divide the width of the canvas by the length of the array
		 bar_width = set_width/data.length;
		//to find the height of each element you loop through the the lenght of the array
		for (var i = 0;i<data.length;i++)
		{
			//assign the amount at i'th element to variable
			 bar_height = data[i].amount;
			//use the starting x position, and bar height as x and y cordinates.use bar_height and bar_width as width and height. draw the bars
			cxt.fillStyle=data[i].colour;
			cxt.fillRect(startPosition,canvas.height-bar_height,bar_width,bar_height);
			//this line is used to draw each single bar. You add the bar width to the starting position each time you draw the bar.
			startPosition += bar_width;
			
		}
	}

}

//set the values for the sliders and copy to the data array to uodate the chart
function getSliderValue()
{
	//get the values from the sliders and use it to draw rhe chart
	// get the input tags from the html
	var sliderArray = document.getElementsByTagName("INPUT");
	// loop through the array 
	for (var i = 0; i < sliderArray.length;i++)
	{ 
		// set the value of the slider array to the i'th position
		sliderArray.value = i;
		//wopy the slider array value to the data array value
		data[i].amount = sliderArray[i].value;
		//clear the canvas so that we can redraw the function
		cxt.clearRect(startPosition,canvas.height-bar_height,bar_width,bar_height);

		
		//convert the updated array as a sting and then store it in local Storage
		localStorage["data"] = JSON.stringify(data);
		localStorage.setItem("data", localStorage["data"]);

		//call the function to draw the bar chart based in the new values set
		BarChart();
	}
	
}
//call a function to display a panel with sliders 
function displayValues(){
	document.getElementById("setvalues").style.display ="block";
	document.getElementById("setvalues").style.Zindex="1";
	document.getElementById("setvalues").style.visibility='visible';	

}

//call the apropriate functions to allow the slider to change its text value as the 
//slider changes.(7 functions, one for each day of the week).
//note this moves each slider and displays the correct text on the panel
function setMondayValue(mon){
	document.getElementById("mon").innerHTML=mon;
}
function setTuesdayValue(tue){
	document.getElementById("tue").innerHTML=tue;
}
function setWednesdayValue(wed){
	document.getElementById("wed").innerHTML=wed;
}
function setThursdayValue(thur){
	document.getElementById("thurs").innerHTML=thur;
}
function setFridayValue(fri){
	document.getElementById("fri").innerHTML=fri;
}
function setSaturdayValue(sat){
	document.getElementById("sat").innerHTML=sat;
}
function setSundayValue(sun){
	document.getElementById("sun").innerHTML=sun;
}
//use this function when a 'close' button is clicked so that the panel disapears. 
//find the element needed and set its display to none.
function closeValues()
{
 var showpanel = document.getElementById("setvalues");
 showpanel.style.display= "none";
 return false;
}
// this function gets called when the mouse moves over the canvas
// we must pass it the event sent to the the event handler
function draw (e) {
	// check if we are in drawing mode 
	if (drawing)
	{
		var coords = getMouseCoords(e);
		// draw a line from the previous positon to the current coordinates
		mycontext.lineTo(coords[0], coords[1]); 
		// draw the line
		mycontext.stroke();
		// If the shift key is pressed we also fill the path
		if (e.shiftKey) 
		{
			mycontext.fill();
		}
	}

}
// If we start drawing (i.e. press down the mouse button) 
// we begin a path and move to the point where the mouse is
function startdraw(e)
{
	// indicate we have started drawing by seting this variable to true
	drawing = true;
	//begin drawing the path
	mycontext.beginPath();
	// Get the canvas coords of the mouse. They are returned in an array
	// we must pass it the event object so it can retrieve the coordinates
	coords = getMouseCoords(e);
	// move to the coords of the mouse
	mycontext.moveTo(coords[0], coords[1]); 

}
// If we stop drawing we close the path
// and set the drawing variable to false
function stopdraw()
{
	mycontext.closePath();
	drawing = false;
}
//creates the screenshot function on the page and stores the value in local Storage
function setImage(){
	
	//create a third canvas for the screenshot function
	//first we find the dataURL's of the sktech and bar canvases
	var barCanvas = canvas.toDataURL();
	var sketch  = mycanvas.toDataURL();
	//use this to create the canvas for the screenshot.
	canvas3 =document.createElement("canvas");
	//set width and height of new canvas
	canvas3.width=250;
	canvas3.height=100;

	cxt2 = canvas3.getContext('2d');
	//draw the two already created canvases onto the context of the third canvas, set the width and height of that image
	cxt2.drawImage(canvas,0,0,200,100);
	cxt2.drawImage(mycanvas,0,0,200,100);
	cxt2.save();

	//take more than one screenshot.
	//create a new image element.
	//Find the display div and assoiciate it with the image
	snap = document.createElement("img");
	snap.src = document.getElementById("newscreenshot").src;
	
	//to create a link, we creare a new a element and associate with the display div element
	link = document.createElement("a");
	link.href = document.getElementById("link").href;
	//convert both to a data url
	document.getElementById("link").href = canvas3.toDataURL("image/png");
	document.getElementById("newscreenshot").src = canvas3.toDataURL("image/png");
	var image = canvas3.toDataURL("image/png");
	//get the screenshot element and force both the snap and link attributes to the list array
	list = document.getElementById("screenshots");
	list.appendChild(snap);
	list.appendChild(link);
	//create a counter variable
	var i = 0;
	//add the urls to an array
	if (document.getElementById("snapshot").onclick)
	{
	//insert each image before the first image
	list.insertBefore(list.childNodes[i],snap);
	
	//counter increases
	i++;
	}
	//display the div on screen
	document.getElementById('newscreenshot').style.display = "block";
	//store item in localStorage
	
	localStorage.setItem("newscreenshot",image);
	
}
//delete the images from the roll
//delete image from localStorage
function closeSetImage()
{	
	while (list.firstChild) {
    list.removeChild(list.firstChild);

	}	
	localStorage.removeItem("newscreenshot");
}
//return the local Storage images from local storage
function getLocalStorageImage(){
	var i =0
	while (i<localStorage.length)	
	{
		var info = localStorage.getItem("newscreenshot");
		document.getElementById("newscreenshot").src = info;
		list= info;
		i++
		}
}
// reset the application (i.e. delete the current drawing)
function reset(){
	// Clear the canvas
	mycontext.clearRect(0,0,mycanvas.clientWidth, mycanvas.clientHeight);
	mycontext.strokeStyle = "black";
}
//change the tickness of the pen for drawing on the canvas
function setThickness(x){
	mycontext.lineWidth = x;
}
//change the colour of the pen for drawing on the canvas
function setColour(name)
{
	mycontext.strokeStyle = name;
}
// Set up the application
function init() {
	
	//display the barchart on screen, even if there is no local storage element
	BarChart();
	
	if (localStorage.length){
	//get any screen shot elements that might be stored in localStorage
	//note if there is no screenshots, then it gives a error, but if there is then there is no error. it works either way.
	getLocalStorageImage();
	}
	//create the canvas for drawing on the canvas
	mycanvas = document.getElementById("myCanvas");
	//get its context
	if (mycanvas.getContext)
	{
		mycontext =  mycanvas.getContext("2d");  
		// Add event handlers for drawing on the canvas, this is linked to the funtions that are already created
		mycanvas.onmousemove = draw;
		// We start drawing if the mouse button is pressed
		mycanvas.onmousedown = startdraw;
		// We stop the drawing if the mouse leaves the canvas or the mouse button is released. 
		mycanvas.onmouseup = stopdraw;
		mycanvas.onmouseout = stopdraw;

		// Add the event handers to control the rest of the elements on the page
		//change the size of the pen
		document.getElementById("thickness").onchange = function(){ setThickness(this.value);};
		//change the colour of the pen
		document.getElementById("colour").onchange = function () {setColour(this.value);};
		//take a screenshot of the canvas and display it on the image roll
		document.getElementById("snapshot").onclick = setImage;
		//delete the images from the image roll
		document.getElementById("deleteRoll").onclick = closeSetImage;
		//clear the canvas of any drawing elements
		document.getElementById("clear").onclick = reset;
		//display a panel with sliders linked to the graph
		document.getElementById ("changeValues").onclick = displayValues;
		//change the graph values and store it in localStorage
		document.getElementById("save").onclick = getSliderValue;
		//close the panel 
		document.getElementById("close").onclick = function () {closeValues(); return false; };
		// reset the canvas except for anything stored in localStorage
		document.getElementById("Monday").onchange = function(){setMondayValue(this.value);};
		document.getElementById("Tuesday").onchange = function(){setTuesdayValue(this.value);};
		document.getElementById("Wednesday").onchange = function(){setWednesdayValue(this.value);};
		document.getElementById("Thursday").onchange = function(){setThursdayValue(this.value);};
		document.getElementById("Friday").onchange = function(){setFridayValue(this.value);};
		document.getElementById("Saturday").onchange = function(){setSaturdayValue(this.value);};
		document.getElementById("Sunday").onchange = function(){setSundayValue(this.value);};
		reset();
	}	
}

//load everything in the init function on to the page
window.onload = init;
