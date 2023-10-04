
//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];


//DOM Elements
const submitForm = document.getElementById("songForm")
const songContainer = document.getElementById("song-container")

//Modal Elements
//let songName = document.getByElementId('songName')
//let updateSongBtn = document.getElementById('update-song-button')


const headers = {
        'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/songs/"


//Logout that clears cookie

function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
    document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

// Form submits new songs

const handleSubmit = async (e) => {
    let bodyObj = {
        songName: document.getElementById("songName1").value,
        artist: document.getElementById("artist").value,
        album: document.getElementById("album").value
    }

    await addSong(bodyObj);
}

async function addSong(obj) {
    const response = await fetch(`${baseUrl}user/${userId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    }).then((response)=>{
    console.log(response)
    return response.json()
    }).then((data)=>{
    console.log(data)
    createSongCards(data)
    })
console.log("song added")
 getSongs(userId);
}




async function getSongs(userId) {
    await fetch(`${baseUrl}user/${userId}`, {
        method: "GET",
        headers: headers
    })
            .then(response => response.json())
            .then(data => createSongCards(data))
            .catch(err => console.error(err))
}




async function getSongById(songId){
    await fetch(baseUrl + songId, {
        method: "GET",
        headers: headers
    })
        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))
}

async function handleSongEdit(songId){
    let bodyObj = {
        id: noteId,
        body: songBody.value
    }

    await fetch(baseUrl, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getSongs(userId);
}



async function handleDelete(songId){
    await fetch(baseUrl + songId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getSongs(userId);
}

//function using form input
 function createSongCards(data) {
 console.log(data)
//      const songName = document.getElementById("songName").value;
//      const artist = document.getElementById("artist").value;
//      const album = document.getElementById("album").value;

//create new card element
if (data.length === 0 ){
    return
}


      const card = document.createElement("div");
            card.classList.add("song-card");
            card.innerHTML = `
              <h2>${data.songName}</h2>
              <p><strong>Artist:</strong> <span>${data.artist}</span></p>
              <p><strong>Album:</strong> <span>${data.album}</span></p>
              <button class="edit-button">Edit</button>
              <button class="delete-button">Delete</button>
            `;

    const cardContainer = document.getElementById("cardContainer");
      cardContainer.appendChild(card);

    document.getElementById("songForm").reset();

    const deleteButton = card.querySelector(".delete-button");
    deleteButton.addEventListener("click", function () {
    card.remove();
    });


    const editButton = card.querySelector(".edit-button");
          editButton.addEventListener("click", function () {

          const h2 = card.querySelector("h2");
                  const artistSpan = card.querySelector("p strong:first-child + span");
                  const albumSpan = card.querySelector("p strong:last-child + span");

                  h2.contentEditable = !h2.isContentEditable;
                          artistSpan.contentEditable = !artistSpan.isContentEditable;
                          albumSpan.contentEditable = !albumSpan.isContentEditable;


                   if (h2.isContentEditable) {
                             editButton.textContent = "Save";
                           } else {
                             editButton.textContent = "Edit";
                           }
          });
    }


document.getElementById("songForm").addEventListener("submit", function (e) {
      e.preventDefault();
      handleSubmit();
      createSongCards();
});



//const populateModal = (obj) =>{
//    songBody.innerText = ''
//    songBody.innerText = obj.body
//    updateSongBtn.setAttribute('data-song-id', obj.id)
//}


getSongs(userId);

//submitForm.addEventListener("submit", handleSubmit)

//updateSongBtn.addEventListener("click", (e)=>{
//    let songId = e.target.getAttribute('data-song-id')
//    handleSongEdit(songId);
//})


// Create for Memories card

//DOM Elements
const submitSecondForm = document.getElementById("memoryForm")
const memoryContainer = document.getElementById("memory-container")



const baseUrlTwo = "http://localhost:8080/api/v1/memories/"

// Form submits new memories

const handleMemorySubmit = async (e) => {
    let bodyObj = {
        season: document.getElementById("season").value,
        memory: document.getElementById("memoryName1").value,
    }

    await addMemory(bodyObj);
}

async function addMemory(obj) {
    const response = await fetch(`${baseUrlTwo}user/${userId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    }).then((response)=>{
    console.log(response)
    return response.json()
    }).then((data)=>{
    console.log(data)
    createMemoryCards(data)
    })
console.log("memory added")
 getMemories(userId);
}

async function getMemories(userId) {
    await fetch(`${baseUrlTwo}user/${userId}`, {
        method: "GET",
        headers: headers
    })
            .then(response => response.json())
            .then(data => createMemoryCards(data))
            .catch(err => console.error(err))
}

async function getMemoryById(memoryId){
    await fetch(baseUrlTwo + memoryId, {
        method: "GET",
        headers: headers
    })
        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))
}

//function using form input
 function createMemoryCards(data) {
 console.log(data)


//create new card element
if (data.length === 0 ){
    return
}


      const card = document.createElement("div");
            card.classList.add("memory-card");
            card.innerHTML = `
              <h2>${data.season}</h2>
              <p><strong>Memory:</strong> ${data.memory}</p>
              <button class="edit-button">Edit</button>
              <button class="delete-button">Delete</button>
            `;

    const cardContainer = document.getElementById("memoryCardContainer");
      cardContainer.appendChild(card);

    document.getElementById("memoryForm").reset();

    const deleteButton = card.querySelector(".delete-button");
    deleteButton.addEventListener("click", function () {
    card.remove();
    });


    const editButton = card.querySelector(".edit-button");
          editButton.addEventListener("click", function () {

          const h2 = card.querySelector("h2");
                  const memorySpan = card.querySelector("p strong:first-child + span");


                  h2.contentEditable = !h2.isContentEditable;
                          memorySpan.contentEditable = !memorySpan.isContentEditable;



                   if (h2.isContentEditable) {
                             editButton.textContent = "Save";
                           } else {
                             editButton.textContent = "Edit";
                           }
          });
    }


document.getElementById("memoryForm").addEventListener("submit", function (e) {
      e.preventDefault();
      handleMemorySubmit();
      createMemoryCards();
});
