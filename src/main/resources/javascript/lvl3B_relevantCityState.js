//This Javascript is for narrowing down the list of cities / states displayed in the dropdown menu of lvl 3B
//Using this Javascript, the displayed cities / states should only be those of the selected Country in the Country dropdown menu 
document.addEventListener('DOMContentLoaded', function(){
    var radioButtonState = document.querySelector('#state');
    var radioButtonCity  = document.querySelector('#city');
    
    //Check every time a radio button selection is changed
    var radioButtons = document.querySelectorAll('.region_radio');
    radioButtons.forEach(function(radio) {
      radio.onchange = function() {
        //console.log(radio.value);
        var selectCountryCode = document.querySelector('#countrySubmit').value;
        console.log(selectCountryCode);
        if (radioButtonState.checked) {
          //console.log("State");
          document.querySelectorAll('#stateSubmit option').forEach(function(option) {
            if (option.className != selectCountryCode) {
              option.setAttribute("hidden", '');
            }
            else {
              option.removeAttribute("hidden");
            }
          });
        }
        else if (radioButtonCity.checked) {
          //console.log("City");
          document.querySelectorAll('#citySubmit option').forEach(function(option) {
            if (option.className != selectCountryCode) {
              option.setAttribute("hidden", '');
            }
            else {
              option.removeAttribute("hidden");
            }
          });
        }
      }
    })

    //Check every time a new country is selected
    var selectCountry = document.querySelector('#countrySubmit')
    selectCountry.onchange = function() {
      var selectCountryCode = document.querySelector('#countrySubmit').value;
        console.log(selectCountryCode);
        if (radioButtonState.checked) {
          //console.log("State");
          document.querySelectorAll('#stateSubmit option').forEach(function(option) {
            if (option.className != selectCountryCode) {
              option.setAttribute("hidden", '');
            }
            else {
              option.removeAttribute("hidden");
            }
          });
        }
        else if (radioButtonCity.checked) {
          //console.log("City");
          document.querySelectorAll('#citySubmit option').forEach(function(option) {
            if (option.className != selectCountryCode) {
              option.setAttribute("hidden", '');
            }
            else {
              option.removeAttribute("hidden");
            }
          });
        }
    }
    
  })