document.addEventListener("DOMContentLoaded", () => {
    const currentDate = new Date();
    const y = currentDate.getFullYear();
    const m = String("0" + (currentDate.getMonth() + 1)).slice(-2);
    const d = String("0" + currentDate.getDate()).slice(-2);
    document.getElementById("dateValue").value = y + '-' + m + '-' + d;


})

function selectInOut(selectInout) {
    document.getElementById("in_out").value = selectInout;
}


function selectCategory(category_num) {
    if(category_num == "1"){
        document.getElementById("category_name").value = document.getElementById('category1').value;
    }else if(category_num == "2"){
        document.getElementById("category_name").value = document.getElementById('category2').value;
    }else{
        document.getElementById("category_name").value = document.getElementById('category3').value;
    }
}
