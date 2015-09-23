//array for images
var imagesArray = []; 

//adding, removing tag input fields
function addTag(){
	//create a div 
		var container = document.createElement("div");
		//find the first textbox
		var tagTextBox= document.getElementsByTagName('input');
		//create a text box, set it's attributes
		var newTextBox = document.createElement('input');
		newTextBox.setAttribute('type','text');
	
		//create plus and minus buttons
		var newbutton = document.createElement('button');
		//set its text value
		var text = document.createTextNode("-"); 
		//place it onto the button		
	    newbutton.appendChild(text);  
		//to remove a tag fied we 1: create an event handler so that something will happen when the button is clicked
		newbutton.onclick = function () {
		//find the buttons parent node (contauner)
		var innerEl = this.parentNode;
		//find the parent node of the container
		var outerEl = this.parentNode.parentNode;
		//remove the container from its parent 
		outerEl.removeChild(innerEl);
		}
		//make it to appear on the screen after the button has been clicked
		//find the div in the html and append it onto the container div
		document.getElementById('inputTags').appendChild(container);
		//place the new elements inside the container div
		container.appendChild(newTextBox);
		container.appendChild(newbutton);	
	
}


function getPhotos(){
	
	//used to get the value from input box 
	var tag = document.getElementById('inputTags').value;
	
	//construct the url that we want to send the api request to.  
	newScript = document.createElement('script');
	request = "https://www.flickr.com/services/rest/?";
	request += "method=flickr.photos.search";
	request += "&per_page=15";
	request += "&api_key=b8d2b66fb21852288172faab75a79e63";  //api key ->b8d2b66fb21852288172faab75a79e63
	request += "&tags=dog";
	request += "&format=json";
	request += "&tag_mode=all";
	
	//once we construct this url, we create a var called newscript and set its attributes to the flicker image we get back
	newScript.setAttribute('src',request);
	//place the image on the page.
	document.getElementsByTagName('head')[0].appendChild(newScript);
	//place a loader image to display until all the images have been downloaded
	document.getElementById('search').innerHTML = "<img src = 'ajax-loader.gif'>";
	 document.getElementById('MainImage').innerHTML =  "<img src = 'ajax-loader.gif'>";
 
		
}



//set the opacity of the image. show the selected image and display larger version
function highlightImage(nr)
{

//find the images within the carousel
var imgs = document.getElementById('innerImages').getElementsByTagName('img');
//loop through to differenciate between regular and highlighted images
for (var i = 0; i < imgs.length; i++)
{
//set reqular images to 0
imgs[i].setAttribute('class', 'regularImg');
}
//set the selected images to the nr value.
imgs[nr].setAttribute('class', 'selectedImg');
//set a border for the selected image
imgs[nr].style.border="4px solid white";
//assign it to a variable
var image = imgs[nr];
//clone the image using cloneNode function
var clone = imgs[nr].cloneNode();
//set the width and height of the image to be larger than the orgional
clone.width=810;
clone.height=400;
//give it a border
clone.style.border = "20px solid black";
//ensure the border radius property is compatible with chrome and firefox
clone.style.BorderRadius="8px";
clone.style.WebkitBorderRadius = "8px";
clone.MozBorderRadius="8px";
//set the position so that we only get on image at a time(over lapping the previous image)
clone.style.position = "absolute";
//find the div container where we want to place the div. (below the carousel)
var div = document.getElementById("MainImage");
//place it in the div
div. appendChild(clone);
	
}

//function to retrieve images and create new image elements
function jsonFlickrApi(images)
{

 newStr ="";
 //stop the loader gif
 document.getElementById('search').innerHTML = "Find Images";
 //get rid of text that says no images
 document.getElementById('MainImage').innerHTML = " ";
 
 //consuct an image url for each image 
 for (var i = 0; i < images.photos.photo.length; i++ )
 {
	url = "http://farm" + images.photos.photo[i].farm ;

	url += ".static.flickr.com/" ;

	url += images.photos.photo[i].server + "/";

	url += images.photos.photo[i].id + "_";

	url += images.photos.photo[i].secret;
	url += "_s.jpg";

	//create an image element for each image
	var newImg = document.createElement('img');
	//set it as a regular image
	newImg.setAttribute('class','regularImg');
	//set its src to be the url constructed
	newImg.src = url;
	//set its border, height, width and margin elements, ensure border radius is comaptible with chrome and firefox
	newImg.style.border="2px solid white";
	newImg.width=170;
	newImg.height=120;
	newImg.style.border="2px solid black";
	newImg.style.borderRadius = "4px";
	newImg.style.WebkitBorderRadius="4px";
	newImg.style.MozBorderRadius="4px";
	newImg.style.marginLeft ="4px";
	
	//force the image into the array
	imagesArray.push(newImg);
	//put the image on screen
	document.getElementById('innerImages').appendChild(newImg);
	
	
	}
//center the image
centerImg(0);
}

//center selected image
function centerImg(nr)
{
//call the select image function to displsy the selected image
highlightImage(nr);
//associate the nr value of the array with a variable
var imageToCenter = imagesArray[nr];
//call onload
	imagesArray.onload = function() 
	{
		//totol number of images is 15  as i specified in url request
		var nrOfImages = 15;
		//loop through each image
		for (var i = 0; i <15 ; i++)
		{
		//create an image element for each one
		img = new Image();
		//set the source
		img.src = url ;
		//when the image is downloaded...
		img.onload = function() 
		{
		//place the image in the carousel
		nrOfImages--;
		if (nrOfImages <= 0)
		{
		document.getElementById('innerImages' ).appendChild(imageToCenter);
		}
		//positioning the carousel and the images to the left . 
		innerLeftOffset = document.getElementById('innerImages').getElementsByTagName('class');
		halfOuterW = document.getElementById("outerImages").offsetWidth / 2;
		halfImageW = document.getElementById("innerImages").offsetWidth / 2;
		outerLeftOffset = halfOuterW - halfImageW; 
		LeftPos = outerLeftOffset - innerLeftOffset;
		document.getElementById("innerImages").style.left = LeftPos + "px";
		}
		}; //end of  onload function	
	}; //end of  onload function

}

//create a function that moves the images to the left
function left(nr){
	//move the image to the left on the same line(x axis)
	document.getElementById("innerImages").style.transform = "translateX(-200px)";
	for (var i = 0;i<imagesArray.length;i++)
	{
		//center images at one instead of 0,this only moves once
		centerImg(1);
	
	
	}
}
//move the image to the right. 
function right(nr){
	
	//move the image to the left on the same line(x axis)
	document.getElementById("innerImages").style.transform = "translateX(0px)";
	for (var i = 0;i<imagesArray.length;i++)
	{
		//center the image back to the previous, this only moves once
		centerImg(0);
	
	}
}
function init()
{
	//event listeners for button clicks.
	document.getElementById("plus").onclick = addTag;
	document.getElementById("search").onclick = getPhotos;
	
	//event handlers for carousel button clicks or key press
	document.getElementById('leftArrow').onclick =left;
	document.getElementById('rightArrow').onclick = right;
	document.getElementById('innerImages').onclick = left;
	document.getElementById('innerImages').onkeydown = left;
	
	
}
//everything loads once the page has finished downloading
window.onload = init();