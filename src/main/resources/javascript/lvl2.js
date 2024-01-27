document.addEventListener('DOMContentLoaded', function(){
    //Toggle switch function: change between raw data num and percent
        var toggle = document.getElementById("percent_toggle");
        var dataNum = document.getElementsByClassName('data_num');
        var j = dataNum.length;
        var dataPercent = document.getElementsByClassName('data_percent');

        toggle.addEventListener("change", function (event) {
            //When switch is on (check)
            if (event.target.checked) {
                //Switch to percent value (Set classes to "check" status - See CSS)
                for(var i = 0; i < j; i++){
                    dataNum[i].className = "data_num check";
                    dataPercent[i].className = "data_percent check";
                }
            }
            //When switch is off (uncheck)
            else {
                //Switch to numeric value (Set classes to "uncheck" status - See CSS)
                for(var i = 0; i < j; i++){
                    dataNum[i].className = "data_num uncheck";
                    dataPercent[i].className = "data_percent uncheck";
                }
            }
        });
    
    //Check year range validity
        //Year (Start - End) dropdown menus
        var yearStartSelect = document.querySelector('#year_start'); //Year start dropdown menu
        var yearEndSelect = document.querySelector('#year_end'); //Year end dropdown menu
        var yearEndOptions = document.querySelectorAll('#year_end option'); //Year end dropdown options
        var submitBtt = document.querySelector("button[type = 'submit']");

        //Validity check
        yearStartSelect.addEventListener("change", function() { //Listens for when the year_start dropdown is selected
            //Selected option of year_start
            var yearStartSelected = yearStartSelect.options[yearStartSelect.selectedIndex].value;
            //console.log("Selected year: " + yearStartSelected); //Checking on console if action is detected

            //Check for invalid year_end selection
                var yearEndSelected = yearEndSelect.options[yearEndSelect.selectedIndex].value; //Previously selected year_end

                //Check if previous year_end selection is smaller than current selected year_start
                var invalidSelectedEndYear = (!isNaN(yearEndSelected)) //Make sure it's not the default "Choose" option
                                            && (parseInt(yearEndSelected) < parseInt(yearStartSelected));
                //console.log(invalidSelectedEndYear); //Value check on browser console

                //Invalid year_end detected
                if (invalidSelectedEndYear) {
                    //Set back to default "Choose" option
                    yearEndSelect.selectedIndex = "0"; 

                    //Disable submit button
                    submitBtt.disabled = true;
                    submitBtt.innerHTML = "Invalid end year";
                }

            //Hide options that are smaller than selected year_start
                yearEndOptions.forEach(function(option) {
                    //console.log(option.value);

                    if (parseInt(option.value) < parseInt(yearStartSelected)) {
                        option.setAttribute("hidden", ''); //Hide smaller options of year_end
                        //console.log("Hidden: " + option.value);
                    }
                    else {
                        option.removeAttribute("hidden");
                        //console.log("Not hidden: " + option.value);
                    }
                });

            //Enable year_end dropdown when the first year_start is selected
            yearEndSelect.disabled = false;
        });

        //Enable submit button when valid year range is selected
        yearEndSelect.addEventListener("change", function(){
            var yearEndSelected = yearEndSelect.options[yearEndSelect.selectedIndex].value;
            var yearStartSelected = yearStartSelect.options[yearStartSelect.selectedIndex].value;
            var invalidSelectedEndYear = (!isNaN(yearEndSelected)) && parseInt(yearEndSelected) < parseInt(yearStartSelected);
            //Enable submit button when valid end year is selected
            if (!invalidSelectedEndYear){
                submitBtt.disabled = false;
                submitBtt.innerHTML = "Submit";
            }
        });
})