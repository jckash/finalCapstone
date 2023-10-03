
//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];


//DOM Elements
const submitForm = document.getElementById("song-form")
const songContainer = document.getElementById("song-container")

//Modal Elements
let songBody = document.getElementById('song-body')
let updateSongBtn = document.getElementById('update-song-button')


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
    e.preventDefault()
    let bodyObj = {
        body: document.getElementById("note-input").value
    }
    await addSong(bodyObj);
    document.getElementById("note-input").value = ''
}

async function addSong(obj) {
    const response = await fetch(`${baseUrl}user/${userId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
    return getSongs(userId);
    }
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



const createSongCards = (array) => {
    songContainer.innerHTML = ''
    array.forEach(obj => {
        let songCard = document.createElement("div")
        songCard.classList.add("m-2")
        songCard.innerHTML = `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column justify-content-between" style="height: available">
                    <p class="card-text">${obj.body}</p>
                    <div class="d-flex justify-content-between">
                         <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>
                          <button onclick="getSongById(${obj.id})" type="button" class="btn btn-primary"
                           data-bs-toggle="modal" data-bs-target="#song-edit-modal">
                           Edit
                           </button>
                       </div>
                 </div>
            </div>

        `
        songContainer.append(songCard);
    })
}


const populateModal = (obj) =>{
    songBody.innerText = ''
    songBody.innerText = obj.body
    updateSongBtn.setAttribute('data-song-id', obj.id)
}


getSongs(userId);

submitForm.addEventListener("submit", handleSubmit)

updateSongBtn.addEventListener("click", (e)=>{
    let songId = e.target.getAttribute('data-song-id')
    handleSongEdit(songId);
})

