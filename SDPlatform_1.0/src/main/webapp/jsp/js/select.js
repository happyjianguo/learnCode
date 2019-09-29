	function addItem(box, text, value, sort) {
	box[box.length] = new Option(text, value);

	if (sort == true) {
		sortBox(box);
	}
	}
	
	function checkSelectedId(box)
	{
		if(box == null)
		{
			return null;
		}
		else
		{
			for(var n=0; n<box.length; n++)
			{
				if(box.options[n].selected)
				{
					return box.options[n].value;
				}
			}
			return null;
		}
	}
	
	function sortBox(box) 
	{
	   	var newBox = new Array();
	
	   	for (var i = 0; i < box.length; i++) 
		{
			newBox[i] = new Array(box[i].value, box[i].text);
		}
	
	   	newBox.sort(sortByAscending);
	
	   	for (var i = box.length - 1; i > -1; i--) {
	      	box.options[i] = null;
	   	}
	
	   	for (var i = 0; i < newBox.length; i++) {
	      	box.options[box.length] = new Option(newBox[i][1], newBox[i][0]);
	   	}
	}
	
	function sortByAscending(a, b) 
	{
	   	if (a[1].toLowerCase() > b[1].toLowerCase()) 
	   	{
	      	return 1;
	   	}
	   	else if(a[1].toLowerCase() < b[1].toLowerCase()) 
	   	{
	      	return -1;
	   	}
	   	else {
	      	return 0;
	   	}
	}
	
	function removeItem(box, value) 
	{
		if (value == null) {
			for (var i = box.length - 1; i >= 0; i--) {
				if (box.options[i].selected) {
					box[i] = null;
				}
			}
		}
		else {
			for (var i = box.length - 1; i >= 0; i--) {
				if (box.options[i].value == value) {
					box[i] = null;
				}
			}
		}
	}
	
	function addSelectItem(box, text, value, sort) 
	{
		box[box.length] = new Option(text, value);

		if (sort == true) {
			sortBox(box);
		}
	}
	
	function getItemString(box)
	{
		if(box == null || box.length <=0)
		{
			return "";
		}
		else
		{
			var boxString = "";
			boxString = "";
			for(var i=0; i<box.length; i++)
			{
				boxString += box.options[i].value + ",";
			}
			if(boxString.length > 0)
			{
				boxString = boxString.substring(0, boxString.length -1);
			}
			return boxString;
		}
	}
	
	function checkedCheckbox(box)
	{
		if(box == null)
		{
			return false;
		}
		else
		{
			for (var i = 0; i < box.length; i++)
			{
				if(box[i].checked)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	function moveAllItem(fromBox, toBox, sort)
	{
		var newText = null;
   		var newValue = null;
   		var newOption = null;
   		for (var i = 0; i < fromBox.length; i++) 
   		{
   			newText = fromBox.options[i].text;
            newValue = fromBox.options[i].value;
	        newOption = new Option(newText, newValue);
            toBox[toBox.length] = newOption;
   		}
   		for (var j = 0; j < fromBox.length; j++) 
   		{
   			removeItem(fromBox, fromBox[j].value);
   			j--;
   		}
   		
   		if (newText != null) 
   		{
	      	if (sort == true) {
	         sortBox(toBox);
	      	}
   		}
	}
	
	
	function moveItem(fromBox, toBox, sort) {
   	var newText = null;
   	var newValue = null;
   	var newOption = null;

   	if (fromBox.selectedIndex >= 0) {
      	for (var i = 0; i < fromBox.length; i++) {
         	if (fromBox.options[i].selected) {
            	newText = fromBox.options[i].text;
            	newValue = fromBox.options[i].value;
	           	newOption = new Option(newText, newValue);
            	toBox[toBox.length] = newOption;
         }
      }

      for (var i = 0; i < toBox.length; i++) 
      {
         for (var j = 0; j < fromBox.length; j++) 
         {
            if (fromBox[j].value == toBox[i].value) {
               fromBox[j] = null;

               break;
            }
         }
      }
   	}

   	if (newText != null) {
    	if (sort == true) {
        sortBox(toBox);
      }
   	}
	}
	