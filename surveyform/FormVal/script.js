const form = document.getElementById('form');
const username = document.getElementById('username');
const email = document.getElementById('email');
const password = document.getElementById('password');
const confirmpassword = document.getElementById('confirmpassword');
form.addEventListener('submit' , (e) => {
    e.preventDefault();
    checkInputs();
});
function  checkInputs(){
    const usernameValue = username.value.trim();
    const emailValue = email.value.trim();
    const passwordValue = password.value.trim();
    const confirmpasswordValue = confirmpassword.value.trim();
    if(usernameValue === ''){
        setError(username,'Username Cannot Be Blank');
    }
    else
    {
        setSuccess(username);
    }
    if(emailValue === '')
    {
        setError(email,'Email Cannot Be Blank');
    }else if(!isemail(emailValue))
    {
        serError(email,'Email is not valid');
    }
    else
    {
        setSuccess(email);
    }
    if(passwordValue === '')
    {
        setError(password,'Password Cannot Be Blank');
    }
    else
    {
        setSuccess(password);
    }
    if(confirmpasswordValue === '')
    {
        setError(confirmpassword,'Confirm Password Cannot Be Blank');
    }
    else if(passwordValue !== confirmpasswordValue)
    {
        setError(confirmpassword,'Password Does Not Match');
    }
    else
    {
        setSuccess(confirmpassword);
    }
}
function setError(input,message){
    const formControl = input.parentElement;
    const small = formControl.querySelector('small');
    formControl.className = 'form-control error';
    small.innerText = message;
}
function setSuccess(input){
    const formControl = input.parentElement;
    formControl.className = 'form-control success';
    
}
function isemail(email){
    return /^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$/.test(email);
}
