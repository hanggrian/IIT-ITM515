const EMAIL_REGEX = /^[a-zA-Z0–9._-]+@[a-zA-Z0–9.-]+\.[a-zA-Z]{2,4}$/
const USER_NAME_REGEX = /^[a-z0-9_]+$/
const DISPLAY_NAME_REGEX = /^[A-Z][a-z]*(\s+[A-Z][a-z]*)*$/

const firstNameText = document.getElementById('first-name-text');
const lastNameText = document.getElementById('last-name-text');
const idText = document.getElementById('id-text');
const emailText = document.getElementById('email-text');
const password = document.getElementById('password');
const confirmPassword = document.getElementById('confirm-password');
const createAccountSubmit = document.getElementById('create-account-submit');

firstNameText.addEventListener('change', updateSubmit);
lastNameText.addEventListener('change', updateSubmit);
idText.addEventListener('change', updateSubmit);
emailText.addEventListener('change', updateSubmit);
password.addEventListener('change', updateSubmit);
confirmPassword.addEventListener('change', updateSubmit);

function updateSubmit() {
  createAccountSubmit.disabled =
      !firstNameText.value ||
      firstNameText.value.length > 50 ||
      !DISPLAY_NAME_REGEX.test(firstNameText.value) ||
      !lastNameText.value ||
      lastNameText.value.length > 100 ||
      !DISPLAY_NAME_REGEX.test(lastNameText.value) ||
      !idText.value ||
      idText.value.length > 15 ||
      !USER_NAME_REGEX.test(idText.value) ||
      !emailText.value ||
      emailText.value.length > 254 ||
      !EMAIL_REGEX.test(emailText.value) ||
      !password.value ||
      password.length > 64 ||
      !confirmPassword.value ||
      confirmPassword.length > 64 ||
      password.value !== confirmPassword.value;
}
