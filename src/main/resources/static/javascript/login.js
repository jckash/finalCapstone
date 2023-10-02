const handleSubmit = async (e) =>{
       e.preventDefault()

       let bodyObj = {
            username: loginUsername.value,
            password: loginPassword.value
       }

       const response = await fetch(input:`${baseUrl}/login`, init{
             method: "POST",
             body: JSON.stringify(bodyObj),
             headers: headers
       })
            .catch(err => console.error(err.message))

       const responseArr = await response.json()

       if (response.status === 200){
           document.cookie = `userId=${responseArr[1]}`
           window.location.replace(responseArr[0])
       }
}

loginForm.addEventListener(type:"submit", handleSubmit)