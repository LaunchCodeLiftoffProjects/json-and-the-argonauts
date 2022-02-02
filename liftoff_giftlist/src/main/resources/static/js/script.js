//different filter functions are used depending on if the "Select" column is present

function filterSelectTable() {
    let input = document.getElementById("filter").value.toUpperCase();
    let table = document.getElementById("tbody");
    let rows = table.getElementsByTagName("tr");

    for (let i = 0; i < rows.length; i++) {
        //[1] corresponds to the "Name" column
        let td = rows[i].getElementsByTagName("td")[1];
         if (td) {
              let textValue = td.innerText;
              if (textValue.toUpperCase().indexOf(input) > -1) {
                rows[i].style.display = "";
              } else {
                rows[i].style.display = "none";
              }
            }
    }
}

function filterNoSelectTable() {
    let input = document.getElementById("filter-no-select").value.toUpperCase();
    let table = document.getElementById("tbody-no-select");
    let rows = table.getElementsByTagName("tr");

    for (let i = 0; i < rows.length; i++) {
        //[0] corresponds to the "Name" column
        let td = rows[i].getElementsByTagName("td")[0];
         if (td) {
              let textValue = td.innerText;
              if (textValue.toUpperCase().indexOf(input) > -1) {
                rows[i].style.display = "";
              } else {
                rows[i].style.display = "none";
              }
            }
    }
}