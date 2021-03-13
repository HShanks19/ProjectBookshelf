"use strict"

const output = document.getElementById("output");
const bookshelfName = document.getElementById("status");
const bName = bookshelfName.innerText;
let bookshelfname=  "\"" + bName + "\"";
console.log(bookshelfname);
console.log("http://localhost:8080/getBookStatus/" + bookshelfname);
var myModal = new bootstrap.Modal(document.getElementById('needToMatch')); 
let bookUpdateTitle = "";
let bookUpdateAuthor = "";
let bookUpdateGenre = "";
let bookUpdateYearReleased = "";
let bookUpdateStatus = "";
let bookUpdateId = 0;
var selectDrop = document.getElementById("updateBookGenreSelect");
let selectCirle = document.getElementById("updateCircleOptions");



//read Functionality
function getBooks() {
    axios.get("http://localhost:8080/getBookStatus/" + bookshelfname)
        .then(res => {
            output.innerHTML = "";

            const books = res.data;
            console.log(books);

                books.forEach(book => {
                    const newBook = renderBook(book);
                    console.log("New Book: ", newBook);
                    output.appendChild(newBook);
                   });
                }).catch(err => console.error(err))
}

function renderBook(book) {

    const newColumn = document.createElement("div");
    newColumn.className = "col";

    const newBook = document.createElement("div");
    newBook.className = "card m-5";
    newColumn.appendChild(newBook);

    const bookBody = document.createElement("div");
    bookBody.className = "card-body";
    newBook.appendChild(bookBody);

    const bookTitle = document.createElement("h5");
    bookTitle.className = "card-title";
    bookTitle.innerText = book.title;
    bookBody.appendChild(bookTitle);

    const bookText = document.createElement("p");
    bookText.className = "card-text";
    bookText.innerHTML = "Author: " + book.author;
    bookText.innerHTML += "<br>";
    bookText.innerHTML += "Genre: " + book.genre;
    bookText.innerHTML += "<br>";
    bookText.innerHTML += "Year Released: " + book.yearReleased;
    bookText.innerHTML += "<br>";
    bookText.innerHTML += "Book Status: " + book.status;
    bookBody.appendChild(bookText);

    const bookFooter = document.createElement("div");
    bookFooter.className = "card-footer";
    newBook.appendChild(bookFooter);

    const deleteBookButton = document.createElement("button");
    deleteBookButton.className = "w3-bar-item w3-button";
    deleteBookButton.innerText = "Delete";
    deleteBookButton.addEventListener('click', function () {
        deleteBook(book.id);
    });

    bookFooter.appendChild(deleteBookButton);

    const updateBookButton = document.createElement("button");
    updateBookButton.className = "w3-bar-item w3-button";
    updateBookButton.innerText = "Update";
	updateBookButton.addEventListener('click', function (){
		openModal(book.id);
	});
    
    bookFooter.appendChild(updateBookButton);
	
	const changeBookButton = document.createElement("button");
	changeBookButton.className = "w3-bar-item w3-button";
	if (book.status == "TOREAD"){
		changeBookButton.innerText = "Start Book";
		changeBookButton.addEventListener('click', function () {
		    startBook(book.id);
			alert(book.title + " has been added to your Reading Bookshelf")
		 });
	} else if (book.status == "READING"){
		changeBookButton.innerText = "Finish Book";
		changeBookButton.addEventListener('click', function () {
		    finishBook(book.id);
			alert(book.title + " has been added to your Read Bookshelf")
		 });
	} else {
		changeBookButton.innerText = "Review Book";
  		changeBookButton.addEventListener('click', function () {
			//window.location.href = 'http://localhost:8080/reviewBook.html';
			openMyReviewModal(book.id);
		});
	}

	bookFooter.appendChild(changeBookButton);

    return newColumn;
}

getBooks();

//delete functionality
function deleteBook(id) {
    axios.delete("http://localhost:8080/removeBook/" + id)
      .then(() => getBooks())
      .catch(err => console.error(err));
}

//startBook
function startBook(id){		
	axios.put("http://localhost:8080/startBook/" + id)
      .then(() => getBooks())
      .catch(err => console.error(err));
}

//finishBook
function finishBook(id){		
	axios.put("http://localhost:8080/finishBook/" + id)
      .then(() => getBooks())
      .catch(err => console.error(err));
}


//update Functionality
function openModal(id){
	myModal.show();
	axios.get("http://localhost:8080/getBookID/"+ id)
        .then(res => {
			const bookUpdate = res.data;
			bookUpdateTitle = bookUpdate.title;
			bookUpdateAuthor = bookUpdate.author;
			bookUpdateGenre = bookUpdate.genre;
			bookUpdateYearReleased = bookUpdate.yearReleased;
			bookUpdateStatus = bookUpdate.status;
			bookUpdateId = bookUpdate.id;
			
			console.log(bookUpdate);
			console.log(bookUpdateTitle);
			console.log(bookUpdateAuthor);
			console.log(bookUpdateGenre);
			console.log(bookUpdateYearReleased);
			console.log(bookUpdateStatus);
			console.log(bookUpdateId);
			
			document.getElementById('updateTitle').value = bookUpdateTitle;
    		document.getElementById('updateAuthor').value = bookUpdateAuthor;
			document.getElementById("updateBookGenreSelect").value= bookUpdateGenre;
    		document.getElementById('updateYearReleased').value = bookUpdateYearReleased;

			var radioElements = document.getElementsByName("bookshelfOption");

		    for (var i=0; i<radioElements.length; i++) {
			let value = radioElements[i].getAttribute('value');
		    if (value == bName) {
				radioElements[i].checked = true;
		     }
		    }
        }).catch(err => console.error(err))
}

document.getElementById("updateBooks").addEventListener('submit', function (event) {
    event.preventDefault();

    const updateData = {
      title: this.title.value,
      author: this.author.value,
      genre: selectDrop.options[selectDrop.selectedIndex].value,
      yearReleased: this.yearReleased.value,
      status: document.querySelector('input[name="bookshelfOption"]:checked').value
    };
  
    axios.put("http://localhost:8080/updateBook/" + bookUpdateId, updateData)
        .then(() => {
            myModal.hide();
			getBooks();
    }).catch(err => console.error(err));
});

