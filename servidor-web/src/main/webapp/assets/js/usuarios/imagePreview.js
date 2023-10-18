/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/gulpfile.js to edit this template
 */

const signupForm = document.querySelector('#signup-form');
const file = document.querySelector('#input-photo');
const image = document.querySelector('#image-preview');

const renderImagePreview = (formData) => {
  const file = formData.get('photo');
  const imageUrl = URL.createObjectURL(file);
  image.setAttribute('src', imageUrl);
};

file.addEventListener('change', () => {
  let formData = new FormData(signupForm);
  renderImagePreview(formData);
});