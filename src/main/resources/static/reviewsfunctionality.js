"use strict"

const output = document.getElementById("output");
var myUpdateReviewModal = new bootstrap.Modal(document.getElementById('updateReviewCapability')); 
var selectDropReview = document.getElementById("updateRatingSelect");
let reviewUpdateTitle = "";
let reviewUpdateRating = 0;
let reviewUpdateBody = "";
let reviewBookId = 0;
let reviewBookTitle = "";
let reviewId = 0;
const newColumn = document.createElement("div");
newColumn.className = "col";

//read functionality
function getReviews() {
    axios.get("http://localhost:8080/getReview")
        .then(res => {
            output.innerHTML = "";

            const reviews = res.data;
            console.log(reviews);

                reviews.forEach(review => {
					getReviewCardTitle(review);
					output.appendChild(newColumn);
                   });
                }).catch(err => console.error(err))
}

getReviews();

//delete review
function deleteReview(id) {
    axios.delete("http://localhost:8080/removeReview/" + id)
      .then(() => 
			location.reload()
			).catch(err => console.error(err));
}

//get Book Title
function getReviewCardTitle(review){
	
	axios.get("http://localhost:8080/getBookID/"+ review.bookId)
        .then(res => {
			const bookReviewed = res.data;
			
			reviewBookTitle = bookReviewed.title;
			
			console.log(reviewBookTitle);
			
		    const newReview = document.createElement("div");
		    newReview.className = "card m-5";
		    newColumn.appendChild(newReview);
		
		    const reviewBody = document.createElement("div");
		    reviewBody.className = "card-body";
		    newReview.appendChild(reviewBody);
		
		    const reviewTitle = document.createElement("h5");
		    reviewTitle.className = "card-title";
		    reviewTitle.innerText = "Book Review: " + reviewBookTitle;
		
			reviewBody.appendChild(reviewTitle);
		
		    const reviewText = document.createElement("p");
		    reviewText.className = "card-text";
			reviewText.innerHTML = "Review Title: " + review.reviewTitle;
			reviewText.innerHTML += "<br>";
		    reviewText.innerHTML += "Rating: " + review.rating;
		    reviewText.innerHTML += "<br>";
		    reviewText.innerHTML +=  review.reviewBody;
		
		    reviewBody.appendChild(reviewText);
		
		    const reviewFooter = document.createElement("div");
		    reviewFooter.className = "card-footer"
		    newReview.appendChild(reviewFooter);
		
		    const deleteReviewButton = document.createElement("a");
		    deleteReviewButton.className = "card-link";
		    deleteReviewButton.innerText = "Delete";
		    deleteReviewButton.addEventListener('click', function () {
		        deleteReview(review.id);
		    });
		
		    reviewFooter.appendChild(deleteReviewButton);
		
		    const updateReviewButton = document.createElement("a");
		    updateReviewButton.className = "card-link";
		    updateReviewButton.innerText = "Update";
			updateReviewButton.addEventListener('click', function () {
		        openUpdateReviewModal(review.id);
		    });
		    
		    reviewFooter.appendChild(updateReviewButton);
			
		 }).catch(err => console.error(err))
}


//update review
function openUpdateReviewModal(id){
	myUpdateReviewModal.show();
	
	axios.get("http://localhost:8080/getReview/"+ id)
        .then(res => {
			const reviewUpdate = res.data;
			
			console.log(reviewUpdate);
			
			reviewUpdateTitle = reviewUpdate.reviewTitle;
			reviewUpdateRating = reviewUpdate.rating;
			reviewUpdateBody = reviewUpdate.reviewBody;
			reviewBookId= reviewUpdate.bookId;
			reviewId = id;
			
			console.log(reviewUpdateTitle);
			console.log(reviewUpdateRating);
			console.log(reviewUpdateBody);
			console.log("Book ID: " + reviewBookId);
			
			//getTitle(reviewBookId);
			
			console.log(reviewBookTitle);
			
			document.getElementById('updateReviewTitle').value = reviewUpdateTitle;
    		document.getElementById('updateRatingSelect').value = reviewUpdateRating;
			document.getElementById("updateReviewBody").value= reviewUpdateBody;
			
        }).catch(err => console.error(err))
}

document.getElementById("review").addEventListener('submit', function (event) {
    event.preventDefault();
	
    const reviewData = {
      reviewTitle: this.updateReviewTitle.value,
      rating: selectDropReview.options[selectDropReview.selectedIndex].value,
      reviewBody: this.updateReviewBody.value,
    };
  	
	console.log(reviewData);

    axios.put("http://localhost:8080/updateReview/" + reviewId, reviewData)
        .then(() => {
			myUpdateReviewModal.hide();
			//getReviews(); 
			location.reload(); 
    }).catch(err => console.error(err));
});
