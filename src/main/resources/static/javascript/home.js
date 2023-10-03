const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];



const submitForm = document.getElementById("song-form")
const noContainer = document.getElementById("song-container")


let songBody = document.getElementById('note-body')
let updateSongBtn = document.getElementById('update-note-button')


const headers = {
        'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/notes/"


function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
    document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}


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
            .catch(err => console.error(err))
}




async function getSongById(songId){
    await fetch(input:baseUrl + songId, init:{
        method: "GET",
        headers: headers
    })
        .then(res => res.json())Promise
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

<a class="btn btn-danger navbar-btn" href="./login.html" onclick="handleLogout()">Logout</a>