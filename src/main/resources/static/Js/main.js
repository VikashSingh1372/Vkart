console.log("hello");
const toogleSidebar = () => {
	if ($('.sidebar').is(":visible")) {
		$(".sidebar").css("display", "none");
		$(".content").css("justify-items", "center");
		$(".menu").css("display", "block");



	}
	else {
		$(".sidebar").css("display", "block");
		$(".content").css("margin-left", "20%");
		$(".content").css("width", "80%");

		$(".menu").css("display", "none");


	}

}
const ham = () => {
	if ($('.content').is(":visible")) {
		$(".content").css("display", "none");
		$(".menu").css("display", "block");



	}
	else {
		$(".content").css("display", "block");
		$(".menu").css("display", "block");


	}

}


const search = () => {
		let query = $("#search-input").val();
		if (query == "") {
			$(".search-result").hide();

		}
		else {
			console.log(query);
			let url =`http://localhost:8080/search/${query}`;
		  fetch(url).then((response)=>{
			return response.json();
			
		}).then((data)=>{
			console.log(data)
			let text =`<div class='list-group'>`
			data.forEach((product)=>{
				text += `<a href='/home/product/${product.productId}'  style="text-decoration: none;,margin-bottom:2px;">${product.productName}<br></a>`
				
			})
			text +=`</div>`;
			$(".search-result").html(text);
						$(".search-result").show();

		})
		}
	}
	
	const msg=()=>{
		alert("Added to cart successfully")
		console.log("added")
	}
