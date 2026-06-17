
const form = document.getElementById("setup");
form.addEventListener("submit" , function(event){
    const c1 = document.getElementById("cat1").value.trim();
    const c2 = document.getElementById("cat2").value.trim();
    const c3 = document.getElementById("cat3").value.trim();
    if(!c1||!c2||!c3){
        alert("３つ入力してください");
        event.preventDefault();
        return;
    }
});

//送信処理
const categories = [c1,c2,c3];
document.getElementById("hiddenCategories").value=JSON.stringify(categories);