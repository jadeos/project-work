//array for images
var imagesArray = []; 

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
	
	var tag = document.getElementById('inputTags').value;
	
	//construct the url that we want to send the api request to.  
	newScript = document.createElement('script');
	request = "https://www.flickr.com/services/rest/?";
	request += "method=flickr.photos.search";
	request += "&per_page=15";
	request += "&api_key=b8d2b66fb21852288172faab75a79e63";  //api key ->b8d2b66fb21852288172faab75a79e63
	request += "&tags=house";
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



//set the opacity of the image. 
function highlightImage(nr)
{

var imgs = document.getElementById('innerImages').getElementsByTagName('img');
for (var i = 0; i < imgs.length; i++)
{
//set reqular images to 0
imgs[i].setAttribute('class', 'regularImg');
}
//set the selected images to the nr value.
imgs[nr].setAttribute('class', 'selectedImg');
imgs[nr].style.border="4px solid white";
//take a screenshot so it displays on screen 
var image = imgs[nr];
var clone = imgs[nr].cloneNode();
clone.width=810;
clone.height=400;

clone.style.border = "20px solid black";
clone.style.WebkitBorderRadius = "8px";
clone.style.position = "absolute";
clone.position="absolute";
//find the div container where we want to place the div. (below the carousel)

var div = document.getElementById("MainImage");

div. appendChild(clone);
	
}

function jsonFlickrApi(images)
{
//consuct an image url
 newStr ="";
 document.getElementById('search').innerHTML = "Find Images";
 document.getElementById('MainImage').innerHTML = " ";
 for (var i = 0; i < images.photos.photo.length; i++ )
 {
	url = "http://farm" + images.photos.photo[i].farm ;

	url += ".static.flickr.com/" ;

	url += images.photos.photo[i].server + "/";

	url += images.photos.photo[i].id + "_";

	url += images.photos.photo[i].secret;
	url += "_s.jpg";

	var newImg = document.createElement('img');
	newImg.setAttribute('class','regularImg');
	newImg.src = url;
	newImg.style.border="2px solid white";
	newImg.width=170;
	newImg.height=120;
	newImg.style.border="2px solid black";
	newImg.style.WebkitBorderRadius="4px";
	newImg.style.marginLeft ="4px";
	
	
	imagesArray.push(newImg);
	document.getElementById('innerImages').appendChild(newImg);
	
	
	}

centerImg(0);
}

//center selected image
function centerImg(nr)
{
highlightImage(nr);

var imageToCenter = imagesArray[nr];
//find the left value

	imagesArray.onload = function() 
	{
		var nrOfImages = 15;
		for (var i = 0; i <15 ; i++)
		{
		img = new Image();
		img.src = url ;
		img.onload = function() 
		{
		nrOfImages--;
		if (nrOfImages <= 0)
		{
		document.getElementById('innerImages' ).appendChild(imageToCenter);
		}
	
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
function left(nr){
	
	centerImg(0);
	document.getElementById("innerImages").style.transform = "translateX(-200px)";
	for (var i = 0;i<imagesArray.length;i++)
	{
		imagesArray[nr]=imagesArray[i];
		
		
		centerImg(1);
	
	
	}
}
function right(nr){
	
		centerImg(0);
	document.getElementById("innerImages").style.transform = "translateY(400px)";
	for (var i = 0;i<imagesArray.length;i--)
	{
		imagesArray[i]=imagesArray[nr];
		
		nr-=nr--;
		centerImg(nr);
	
	
	}
}
function init()
{
	//event listeners for button clicks.
	document.getElementById("plus").onclick = addTag;
	document.getElementById("search").onclick = getPhotos;
	
	
	document.getElementById('leftArrow').onclick =left;
	document.getElementById('rightArrow').onclick = right ;
	document.getElementById('innerImages').onclick = left ;
	document.getElementById('innerImages').onkeypress = left ;
	
	
}
//everything loads once the page has finished downloading
window.onload = init();