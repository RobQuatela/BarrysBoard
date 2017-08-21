function validateOrders() {
	event.preventDefault()
	
	var call = document.forms["inputForm"]["call orders:Monday"]
	var online = ducment.forms["inputForm"]["web orders:Monday"]
	var total = call + online
	
	var booked = document.forms["inputForm"]["booked orders:Monday"]
	var loss = document.forms["inputForm"]["loss orders:Monday"]
	var estimate = document.forms["inputForm"]["estimate orders:Monday"]
	
	if(booked + loss + estimate != total) {
		return false
	}
	else {
		document.inputForm.submit()
	}
}