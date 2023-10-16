/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/gulpfile.js to edit this template
 */

// Función para mostrar u ocultar campos según el tipo de usuario seleccionado
const toggleFields = () => {
    const userTypeSelect = document.getElementById('select-user-type');
    const nacionalityDiv = document.getElementById('nacionality-div');
    const websiteDiv = document.getElementById('website-div');
    const descriptionDiv = document.getElementById('description-div');
    const inputNacionality = document.getElementById('input-nacionality');
    const inputDescription = document.getElementById('input-description');

    if (userTypeSelect.value === 'turista') {
        // Mostrar campo de nacionalidad y ocultar los demás
        nacionalityDiv.style.display = 'block';
        websiteDiv.style.display = 'none';
        descriptionDiv.style.display = 'none';
        inputNacionality.required = true;
        inputDescription.style.required = false;
    } else if (userTypeSelect.value === 'proveedor') {
        // Mostrar campos de sitio web y descripción, ocultar nacionalidad
        nacionalityDiv.style.display = 'none';
        websiteDiv.style.display = 'block';
        descriptionDiv.style.display = 'block';
        inputNacionality.required = false;
        inputDescription.style.required = true;
    } else {
        // Si no se selecciona un tipo de usuario, ocultar todos los campos
        nacionalityDiv.style.display = 'none';
        websiteDiv.style.display = 'none';
        descriptionDiv.style.display = 'none';
        inputNacionality.required = false;
        inputDescription.style.required = false;
    }
};

// Se agrega un controlador de eventos para el cambio en la selección del tipo de usuario
const userTypeSelect = document.getElementById('select-user-type');
userTypeSelect.addEventListener('change', toggleFields);

// Llamar a la función inicialmente para establecer el estado inicial
toggleFields();
