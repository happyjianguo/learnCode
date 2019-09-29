function createDOM(xml)
{
	var doc;
	var signatures = ["Msxml2.DOMDocument.6.0",
		"Msxml2.DOMDocument.5.0",
		"Msxml2.DOMDocument.4.0",
		"Msxml2.DOMDocument.3.0",
		"Msxml2.DOMDocument",
		"Microsoft.XmlDom"];
	
	for(var i = 0; i < signatures.length; i++)
	{
		try
		{
			doc = new ActiveXObject(signatures[i]);
			doc.loadXML(xml);
			break;
		}
		catch(e)
		{
		}
	}
	
	if(doc == null)
	{
		var parser = new DOMParser();
		doc = parser.parseFromString(xml, "text/xml");
		delete parser;
	}
	
	return doc;
}

