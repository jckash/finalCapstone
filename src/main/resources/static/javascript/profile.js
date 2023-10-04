
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
//    document.getElementById("songName1").value = ''
//    document.getElementById("artist").value = ''
//    document.getElementById("album").value = ''
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
//        .catch(err => console.error(err.message))
//    if (response.status == 200) {
//    return getSongs(userId);
//    }
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
              <p><strong>Artist:</strong> ${data.artist}</p>
              <p><strong>Album:</strong> ${data.album}</p>
            `;

const cardContainer = document.getElementById("cardContainer");
      cardContainer.appendChild(card);

 document.getElementById("songForm").reset();
    }


document.getElementById("songForm").addEventListener("submit", function (e) {
      e.preventDefault();
      handleSubmit();
      createSongCards();
})

const populateModal = (obj) =>{
    songBody.innerText = ''
    songBody.innerText = obj.body
    updateSongBtn.setAttribute('data-song-id', obj.id)
}


getSongs(userId);

//submitForm.addEventListener("submit", handleSubmit)

//updateSongBtn.addEventListener("click", (e)=>{
//    let songId = e.target.getAttribute('data-song-id')
//    handleSongEdit(songId);
//})